"""
初始测试生成器
基于PlantUML和大模型的测试用例自动生成
"""

import datetime
import re
import sys
import uuid
from collections import Counter, defaultdict
from pathlib import Path
from typing import Dict, List, Any, Optional, Union, Tuple, Set
from tqdm import tqdm

# 添加项目根目录到Python路径
package_root = Path(__file__).resolve().parents[2]
workspace_root = package_root.parent
for p in (workspace_root,):
    p_str = str(p)
    if p_str not in sys.path:
        sys.path.insert(0, p_str)

from maptest.modules.initial_generation.failure_analyzer import InitialGeneratorFailureAnalyzer
from maptest.modules.initial_generation.preflight import InitialGeneratorPreflightHelper
from maptest.modules.initial_generation.project_context import InitialGeneratorProjectContextHelper
from maptest.modules.initial_generation.prompt_builder import InitialGeneratorPromptBuilder
from maptest.modules.initial_generation.repair_pipeline import InitialGeneratorRepairPipelineHelper
from maptest.modules.initial_generation.run_context import InitialRunContext
from maptest.modules.postprocessor.focal_method_resolver import PostProcessorFocalMethodResolver

# 导入新的核心组件
from maptest.core import (
    config, get_logger, LoggerMixin, log_performance,
    LLMClient, MavenExecutor, FileUtils
)

# 导入java2plantuml模块
from maptest.modules.java2plantuml.java2plantumlmain import parser as java2plantuml_parser
from maptest.modules.java2plantuml.uml_pruner import GlobalIndexer, UMLPruner


class InitialGenerator(LoggerMixin):
    """
    初始测试生成器 (Phase 1)。

    运作流程:
    1. 扫描 RepoData 并定位焦点方法。
    2. 调用 UMLPruner 获取裁剪后的依赖上下文。
    3. 意图分析 (Intention RAG): 调用 LLM 对焦点方法进行逐行逻辑解析，生成意图字典。
    4. 初始测试生成：结合 UML 上下文和意图字典生成基础 JUnit 测试。
    5. 验证与跳过：执行 Maven 测试，并根据结果更新增量缓存，支持断点续生成。
    """

    def __init__(
        self,
        intention_tag: str,
        model_name: Optional[str] = None,
        run_mode: str = "fresh",
        context_mode: Optional[str] = None,
    ):
        """
        初始化
        
        Args:
            intention_tag: 意图标签
            model_name: 模型名称
        """
        self.intention_tag = intention_tag
        self.model_name = model_name or config.get('default_model.name')
        self.run_mode = self._normalize_run_mode(run_mode)
        self.context_mode = self._normalize_context_mode(
            context_mode or config.get("initial_generation.context_mode", "uml")
        )
        self.use_uml_context = self.context_mode == "uml"
        self.use_method_intention_context = self.context_mode != "raw_llm"
        self.artifact_version = InitialRunContext.ARTIFACT_VERSION
        
        # 初始化LLM客户端
        self.llm_client = LLMClient(self.model_name)
        
        # 设置路径配置
        self._setup_paths()
        
        # 超时配置
        self.test_timeout = config.get('maven.timeout', 120)
        self.repair_max_rounds = int(config.get('testing.repair_max_rounds', 4))
        self.enable_preflight = bool(config.get('testing.enable_preflight', True))
        self.enable_failure_classification = bool(config.get('testing.enable_failure_classification', True))
        self.enable_java_version_detection = bool(config.get('testing.enable_java_version_detection', True))
        
        # 初始化索引器和裁剪器
        self.indexer = None
        self.pruner = None
        self._project_context_cache: Dict[str, Dict[str, Any]] = {}

        self.logger.info(
            f"初始生成器: {intention_tag}, 模型: {self.model_name}, 测试超时: {self.test_timeout}秒, "
            f"run_mode={self.run_mode}, context_mode={self.context_mode}"
        )

    def _normalize_run_mode(self, run_mode: str) -> str:
        return InitialRunContext.normalize_run_mode(run_mode)

    @staticmethod
    def _normalize_context_mode(context_mode: str) -> str:
        mode = str(context_mode or "uml").strip().lower().replace("-", "_")
        if mode in {"no_uml", "without_uml", "no_plantuml", "none"}:
            return "no_uml"
        if mode in {"raw", "raw_llm", "raw_validation", "raw_llm_validation", "llm_raw"}:
            return "raw_llm"
        return "uml"

    def _compute_input_digest(self, project_data: List[Dict[str, Any]]) -> str:
        return InitialRunContext.compute_input_digest(project_data)

    def _build_method_key(self, class_name: str, method_name: str) -> str:
        return InitialRunContext.build_method_key(class_name, method_name)

    def _sanitize_file_name(self, raw_name: str) -> str:
        return InitialRunContext.sanitize_file_name(raw_name)

    def _build_method_variant_key(self, class_name: str, method_name: str, signature: Any) -> str:
        return InitialRunContext.build_method_variant_key(class_name, method_name, signature)

    def _build_method_intention_file_name(
        self,
        class_name: str,
        method_name: str,
        method_variant_key: str = "",
    ) -> str:
        return InitialRunContext.build_method_intention_file_name(
            class_name,
            method_name,
            method_variant_key=method_variant_key,
        )

    def _build_test_prompt_file_name(
        self,
        class_name: str,
        method_name: str,
        method_variant_key: str = "",
    ) -> str:
        return InitialRunContext.build_test_prompt_file_name(
            class_name,
            method_name,
            method_variant_key=method_variant_key,
        )

    def _build_expected_test_class_name(
        self,
        class_name: str,
        method_name: str,
        signature: Any,
        overloaded: bool = False,
    ) -> str:
        return InitialRunContext.build_expected_test_class_name(
            class_name,
            method_name,
            signature,
            overloaded=overloaded,
        )

    def _prepare_reused_result(self, result: Dict[str, Any], run_id: str,
                               method_key: str, method_variant_key: str = "",
                               focal_signature: str = "") -> Dict[str, Any]:
        return InitialRunContext.prepare_reused_result(
            result,
            run_id,
            method_key,
            method_variant_key=method_variant_key,
            focal_signature=focal_signature,
        )
    
    def _add_line_numbers(self, code: str) -> str:
        """为代码添加行号"""
        lines = code.split('\n')
        max_width = len(str(len(lines)))
        return '\n'.join([f"{str(i+1).rjust(max_width)} | {line}" for i, line in enumerate(lines)])
    
    def _setup_paths(self):
        """设置路径配置"""
        base_dir = package_root.resolve()
        workspace_dir = workspace_root.resolve()
        
        # 实验结果目录。消融分支必须和正常初始生成产物隔离。
        result_dir_map = {
            "uml": "uml-based_generation",
            "no_uml": "no-uml_generation",
            "raw_llm": "raw-llm_generation",
        }
        result_dir_name = result_dir_map.get(self.context_mode, "uml-based_generation")
        self.uml_experiment_results_dir = (base_dir / "experiment_results" / result_dir_name).resolve()
        
        # RepoData目录
        self.repo_data_dir = (workspace_dir / "RepoData").resolve()
        
        # Repos目录
        self.repos_dir = (workspace_dir / "Repos").resolve()
        
        # 确保实验结果目录存在
        FileUtils.ensure_directory(self.uml_experiment_results_dir)

    def _resolve_test_file_path(self, raw_test_info: str, project_name: str) -> Path:
        raw_file_path = raw_test_info.split('###')[0] if '###' in raw_test_info else raw_test_info
        normalized = raw_file_path.replace('\\', '/')
        marker = '/Repos/'

        if marker in normalized:
            relative_path = normalized.split(marker, 1)[1].lstrip('/\\')
            return self.repos_dir / relative_path

        project_marker = f'/{project_name}/'
        if project_marker in normalized:
            relative_path = normalized.split(project_marker, 1)[1].lstrip('/\\')
            return self.repos_dir / project_name / relative_path

        raw_path = Path(raw_file_path)
        if raw_path.is_absolute():
            return raw_path

        return self.repos_dir / project_name / raw_path

    def _build_saved_test_path(self, project_name: str, test_file_path: Path) -> Path:
        try:
            relative_path = test_file_path.relative_to(self.repos_dir)
        except ValueError:
            relative_path = Path(project_name) / "src" / "test" / "java" / test_file_path.name
        return self.uml_experiment_results_dir / project_name / "GeneratedTest" / relative_path

    def _get_project_context_helper(self) -> InitialGeneratorProjectContextHelper:
        helper = getattr(self, "_project_context_helper", None)
        cache = getattr(self, "_project_context_cache", None)
        if cache is None:
            cache = {}
            self._project_context_cache = cache
        enable_java_version_detection = getattr(self, "enable_java_version_detection", True)
        if helper is None:
            helper = InitialGeneratorProjectContextHelper(
                repos_dir=self.repos_dir,
                logger=self.logger,
                enable_java_version_detection=enable_java_version_detection,
                cache=cache,
            )
            self._project_context_helper = helper
        else:
            helper.repos_dir = self.repos_dir
            helper.logger = self.logger
            helper.enable_java_version_detection = enable_java_version_detection
            helper.cache = cache
        return helper

    def _resolve_repo_project_name(self, raw_name: str) -> str:
        """Map subset file names such as *_small20 back to the real repo directory."""
        return self._get_project_context_helper().resolve_repo_project_name(raw_name)

    def _get_project_context(self, repo_project_name: str) -> Dict[str, Any]:
        return self._get_project_context_helper().get_project_context(repo_project_name)

    def _parse_java_levels_from_pom(self, pom_path: Path) -> Tuple[str, str]:
        return self._get_project_context_helper().parse_java_levels_from_pom(pom_path)

    def _update_java_levels_from_output(self, repo_project_name: str, build_output: str) -> Dict[str, Any]:
        return self._get_project_context_helper().update_java_levels_from_output(repo_project_name, build_output)

    def _extract_java_levels_from_output(self, build_output: str) -> Tuple[str, str]:
        return self._get_project_context_helper().extract_java_levels_from_output(build_output)

    @staticmethod
    def _normalize_java_level(value: Any) -> str:
        return InitialGeneratorProjectContextHelper.normalize_java_level(value)

    def _build_generation_context(self, under_test_method: Dict[str, Any], focal_method_code: str,
                                  focal_method_info: str, plantuml_code: str,
                                  package_name: str, class_name: str,
                                  repo_project_name: str) -> Dict[str, Any]:
        return self._get_project_context_helper().build_generation_context(
            under_test_method=under_test_method,
            focal_method_code=focal_method_code,
            focal_method_info=focal_method_info,
            plantuml_code=plantuml_code,
            package_name=package_name,
            class_name=class_name,
            repo_project_name=repo_project_name,
        )

    def _resolve_focal_file_path(self, under_test_method: Dict[str, Any], repo_project_name: str) -> str:
        return self._get_project_context_helper().resolve_focal_file_path(under_test_method, repo_project_name)

    def _parse_import_candidates(self, import_block: str, package_name: str) -> List[Dict[str, str]]:
        return self._get_project_context_helper().parse_import_candidates(import_block, package_name)

    def _extract_type_candidates_from_code(self, code: str, package_name: str) -> List[Dict[str, str]]:
        return self._get_project_context_helper().extract_type_candidates_from_code(code, package_name)

    def _extract_type_candidates_from_uml(self, plantuml_code: str, package_name: str) -> List[Dict[str, str]]:
        return self._get_project_context_helper().extract_type_candidates_from_uml(plantuml_code, package_name)

    @staticmethod
    def _build_standard_type_candidates() -> List[Dict[str, str]]:
        return InitialGeneratorProjectContextHelper.build_standard_type_candidates()

    def _extract_member_signatures(self, focal_method_code: str) -> Tuple[List[str], List[str]]:
        return self._get_project_context_helper().extract_member_signatures(focal_method_code)

    def _format_member_signature(self, node: Any) -> str:
        return self._get_project_context_helper().format_member_signature(node)

    def _format_type_node(self, node: Any) -> str:
        return self._get_project_context_helper().format_type_node(node)

    @staticmethod
    def _extract_signature_names(signatures: List[str]) -> List[str]:
        return InitialGeneratorProjectContextHelper.extract_signature_names(signatures)

    def _extract_method_signature(self, focal_method_info: str, method_name: str) -> str:
        return InitialGeneratorProjectContextHelper.extract_method_signature(focal_method_info, method_name)

    def _get_focal_method_resolver(self) -> PostProcessorFocalMethodResolver:
        resolver = getattr(self, "_focal_method_resolver", None)
        if resolver is None:
            resolver = PostProcessorFocalMethodResolver(self)
            self._focal_method_resolver = resolver
        else:
            resolver.processor = self
        return resolver

    def _resolve_focal_method_range_for_result(
        self,
        result: Dict[str, Any],
        project_name: str,
    ) -> Optional[Tuple[int, int]]:
        candidate_pairs = [
            ("focal_method_start_line", "focal_method_end_line"),
            ("method_start_line", "method_end_line"),
            ("start_line", "end_line"),
        ]
        for start_key, end_key in candidate_pairs:
            start_line = self._coerce_positive_int(result.get(start_key))
            end_line = self._coerce_positive_int(result.get(end_key))
            if start_line is None or end_line is None or end_line < start_line:
                continue
            return start_line, end_line

        method_data = {
            "package_name": result.get("package_name", ""),
            "class_name": result.get("class_name", ""),
            "method_name": result.get("method_name", ""),
            "focal_signature": result.get("focal_signature", ""),
            "uncovered_lines": result.get("uncovered_lines") or [],
            "uncovered_line_details": result.get("uncovered_line_details") or [],
        }

        try:
            return self._get_focal_method_resolver().resolve_focal_method_range(method_data, project_name)
        except Exception as exc:
            self.logger.warning(
                f"覆盖率重跑时解析方法范围失败 {method_data.get('class_name')}."
                f"{method_data.get('method_name')}: {exc}"
            )
            return None

    def _collect_batch_coverage(
        self,
        maven_executor: MavenExecutor,
        class_fqn: str,
        method_name: str,
        focal_method_range: Optional[Tuple[int, int]] = None,
    ) -> Dict[str, Any]:
        if focal_method_range is not None:
            return maven_executor.collect_focal_method_coverage(
                class_fqn,
                method_name,
                focal_method_range=focal_method_range,
            )
        return maven_executor.parse_jacoco_for_method(class_fqn, method_name)

    @staticmethod
    def _coerce_positive_int(value: Any) -> Optional[int]:
        try:
            parsed = int(value)
        except (TypeError, ValueError):
            return None
        return parsed if parsed > 0 else None

    @staticmethod
    def _new_failure_state() -> Dict[str, str]:
        return InitialGeneratorFailureAnalyzer.new_failure_state()

    @staticmethod
    def _extract_failure_signature(error_info: str) -> str:
        return InitialGeneratorFailureAnalyzer.extract_failure_signature(error_info)

    def _classify_failure(
        self,
        error_info: str,
        compile_success: int,
        test_success: int,
        candidate_type_map: Optional[Dict[str, List[Dict[str, str]]]] = None,
        stage_hint: Optional[str] = None
    ) -> Dict[str, str]:
        return InitialGeneratorFailureAnalyzer.classify_failure(
            error_info=error_info,
            compile_success=compile_success,
            test_success=test_success,
            candidate_type_map=candidate_type_map,
            stage_hint=stage_hint,
        )

    @staticmethod
    def _merge_failure_state(state: Dict[str, str], classified: Dict[str, str], first_only: bool = False) -> Dict[str, str]:
        return InitialGeneratorFailureAnalyzer.merge_failure_state(state, classified, first_only=first_only)

    @staticmethod
    def _clear_failure_state(state: Dict[str, str]) -> Dict[str, str]:
        return InitialGeneratorFailureAnalyzer.clear_failure_state(state)

    @staticmethod
    def _build_standard_simple_types() -> Set[str]:
        return InitialGeneratorPreflightHelper.build_standard_simple_types()

    def _rewrite_package_statement(self, code: str, expected_package: str) -> str:
        return InitialGeneratorPreflightHelper.rewrite_package_statement(code, expected_package)

    def _inject_imports(self, code: str, imports: List[str]) -> str:
        return InitialGeneratorPreflightHelper.inject_imports(code, imports)

    def _normalize_test_header(self, code: str, expected_package: str) -> Tuple[str, List[str]]:
        return InitialGeneratorPreflightHelper.normalize_test_header(code, expected_package)

    def _rewrite_public_class_name(self, code: str, expected_class_name: str) -> str:
        return InitialGeneratorPreflightHelper.rewrite_public_class_name(code, expected_class_name)

    def _collect_missing_imports(self, test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        return InitialGeneratorPreflightHelper.collect_missing_imports(test_code, generation_context)

    def _detect_shadowing_helper_classes(self, test_code: str, expected_class_name: str,
                                         generation_context: Dict[str, Any]) -> List[str]:
        return InitialGeneratorPreflightHelper.detect_shadowing_helper_classes(
            test_code, expected_class_name, generation_context
        )

    def _detect_java_level_issues(self, test_code: str, java_source_level: str) -> List[str]:
        return InitialGeneratorPreflightHelper.detect_java_level_issues(test_code, java_source_level)

    def _detect_unresolved_simple_types(self, test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        return InitialGeneratorPreflightHelper.detect_unresolved_simple_types(test_code, generation_context)

    def _detect_reflection_signature_issues(self, test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        return InitialGeneratorPreflightHelper.detect_reflection_signature_issues(test_code, generation_context)

    def _run_preflight_checks(self, test_code: str, expected_package: str, expected_class_name: str,
                              generation_context: Dict[str, Any]) -> Dict[str, Any]:
        return InitialGeneratorPreflightHelper.run_preflight_checks(
            test_code=test_code,
            expected_package=expected_package,
            expected_class_name=expected_class_name,
            generation_context=generation_context,
        )

    @staticmethod
    def _select_repair_mode(compile_success: int, test_success: int, error_info: str, stage_hint: str = "") -> str:
        text = str(error_info or "")
        if stage_hint == "preflight" or not compile_success:
            return "compile_repair"
        if any(token in text for token in ["NoSuchMethodException", "IllegalArgumentException", "InvocationTargetException"]):
            return "reflection_repair"
        if not test_success:
            return "semantic_test_repair"
        return "compile_repair"
    
    @log_performance()
    def process_project(self, json_file_path: str) -> bool:
        """
        处理项目 - 主要的处理流程
        
        Args:
            json_file_path: 项目数据JSON文件路径
            
        Returns:
            是否成功处理
        """
        try:
            started_at = datetime.datetime.now().isoformat()
            run_id = uuid.uuid4().hex
            # 获取项目名称
            project_name = Path(json_file_path).stem
            repo_project_name = self._resolve_repo_project_name(project_name)
            
            self.logger.info(f"开始处理项目: {project_name} (repo={repo_project_name})")
            
            # 读取项目数据
            project_data = FileUtils.read_json(json_file_path)
            if not project_data:
                self.logger.error(f"无法读取项目数据: {json_file_path}")
                return False
            
            # 确保项目数据是列表格式
            if not isinstance(project_data, list):
                self.logger.error(f"项目数据格式错误，期望列表格式: {json_file_path}")
                return False
            input_digest = self._compute_input_digest(project_data)
            
            # 为每个项目创建独立的实验目录
            project_experiment_dir = self.uml_experiment_results_dir / project_name
            FileUtils.ensure_directory(project_experiment_dir)
            
            # 读取已有的结果用于跳过
            existing_results = {}
            overall_json = project_experiment_dir / "overall_results.json"
            old_data = {}
            legacy_existing_results = defaultdict(list)
            if overall_json.exists():
                old_data = FileUtils.read_json(str(overall_json))
                if old_data and "detailed_results" in old_data:
                    for r in old_data["detailed_results"]:
                        variant_key = str(r.get("method_variant_key", "") or "").strip()
                        if variant_key:
                            existing_results[variant_key] = r
                            continue
                        key = self._build_method_key(r.get("class_name", ""), r.get("method_name", ""))
                        legacy_existing_results[key].append(r)

            effective_run_mode = self.run_mode
            if effective_run_mode == "incremental":
                same_digest = bool(old_data) and old_data.get("input_digest") == input_digest
                run_complete = bool(old_data) and bool(old_data.get("run_complete", False))
                if not (same_digest and run_complete):
                    self.logger.warning(
                        f"incremental条件不满足（same_digest={same_digest}, run_complete={run_complete}），自动切换到fresh"
                    )
                    effective_run_mode = "fresh"

            if effective_run_mode == "fresh":
                FileUtils.clean_directory(project_experiment_dir)
                FileUtils.ensure_directory(project_experiment_dir)
                existing_results = {}
                legacy_existing_results = defaultdict(list)

            self._cleanup_test_files(self.repos_dir / repo_project_name)
            
            # 初始化全局索引。no_uml 消融分支不生成 UML/pruned context。
            self.indexer = None
            self.pruner = None
            if self.use_uml_context:
                self.indexer = GlobalIndexer()
                project_src_dir = self.repos_dir / repo_project_name
                self.logger.info(f"正在索引项目: {project_name} at {project_src_dir}")
                self.indexer.index_project(str(project_src_dir))
                self.pruner = UMLPruner(self.indexer)
            else:
                self.logger.info(f"context_mode={self.context_mode}: skip project UML indexing and pruning")

            # 处理每个方法
            method_key_counts = Counter(
                self._build_method_key(
                    item.get("Under_test_method", {}).get("Class_name", ""),
                    item.get("Under_test_method", {}).get("Method_name", ""),
                )
                for item in project_data
            )
            overloaded_method_keys = {
                key for key, count in method_key_counts.items()
                if key and count > 1
            }
            results = []
            for method_data in tqdm(project_data, desc=f"处理{project_name}"):
                try:
                    under_test_method = method_data.get('Under_test_method', {})
                    method_name = under_test_method.get('Method_name', '')
                    class_name = under_test_method.get('Class_name', '')
                    focal_method_info = under_test_method.get('Method_body', '')
                    method_key = self._build_method_key(class_name, method_name)
                    focal_signature = self._extract_method_signature(focal_method_info, method_name)
                    method_variant_key = self._build_method_variant_key(class_name, method_name, focal_signature)
                    method_is_overloaded = method_key in overloaded_method_keys
                    
                    if effective_run_mode == "incremental":
                        reused_result = None
                        variant_result = existing_results.get(method_variant_key)
                        if variant_result and variant_result.get('test_success', 0) == 1:
                            reused_result = self._prepare_reused_result(
                                variant_result,
                                run_id,
                                method_key,
                                method_variant_key=method_variant_key,
                                focal_signature=focal_signature,
                            )
                        else:
                            legacy_matches = legacy_existing_results.get(method_key, [])
                            if not method_is_overloaded and len(legacy_matches) == 1:
                                legacy_result = legacy_matches[0]
                                if legacy_result.get('test_success', 0) == 1:
                                    reused_result = self._prepare_reused_result(
                                        legacy_result,
                                        run_id,
                                        method_key,
                                        method_variant_key=method_variant_key,
                                        focal_signature=focal_signature,
                                    )
                            elif method_is_overloaded and legacy_matches:
                                self.logger.info(
                                    f"Skip ambiguous legacy incremental reuse for overloaded method: {method_key}"
                                )

                        if reused_result:
                            results.append(reused_result)
                            self.logger.info(f"Reuse successful cached result: {method_variant_key}")
                            continue

                    result = self._process_method(
                        method_data,
                        project_name,
                        run_id=run_id,
                        repo_project_name=repo_project_name,
                        focal_signature=focal_signature,
                        method_variant_key=method_variant_key,
                        method_is_overloaded=method_is_overloaded,
                    )
                    if result:
                        results.append(result)
                except Exception as e:
                    self.logger.error(f"Failed to process method: {e}")
                    continue
            
            results = self._batch_calculate_coverage(results, repo_project_name)

            self._save_overall_results(
                project_name=project_name,
                results=results,
                run_id=run_id,
                run_mode=effective_run_mode,
                input_digest=input_digest,
                started_at=started_at,
                completed_at=datetime.datetime.now().isoformat()
            )
            
            self.logger.info(f"Project processing completed: {project_name}, methods={len(results)}")
            return True
            
        except Exception as e:
            self.logger.error(f"Failed to process project: {e}")
            return False
    
    @log_performance()
    def _process_method(self, method_data: Dict[str, Any], project_name: str,
                        run_id: str = "", repo_project_name: Optional[str] = None,
                        focal_signature: str = "", method_variant_key: str = "",
                        method_is_overloaded: bool = False) -> Dict[str, Any]:
        """
        处理单个方法 - 核心处理逻辑
        
        Args:
            method_data: 方法数据
            project_name: 项目名称
            
        Returns:
            处理结果
        """
        under_test_method = method_data.get('Under_test_method', {})
        test_method = method_data.get('Test_method', {})
        
        if not under_test_method:
            self.logger.warning("Missing Under_test_method payload")
            return {}
        
        try:
            repo_project_name = repo_project_name or self._resolve_repo_project_name(project_name)
            # 提取方法信息
            focal_class = under_test_method.get('Class_declaration', '')
            fields = under_test_method.get('Filed', '')
            constructors = under_test_method.get('constructors', '')
            focal_method_info = under_test_method.get('Method_body', '')

            # 构建焦点方法代码。raw_llm 消融只给 Method_body，不拼接类声明、字段、构造器等结构上下文。
            full_focal_method_code = self._build_focal_method_code(
                focal_class, fields, constructors, focal_method_info
            )
            focal_method_code = focal_method_info if self.context_mode == "raw_llm" else full_focal_method_code
            
            # 获取其他信息
            test_import_info = under_test_method.get('all_Import_statements', '')
            focal_method_name = under_test_method.get('Method_name', '')
            package_name = under_test_method.get('packageName', '')
            class_name = under_test_method.get('Class_name', '')
            method_key = self._build_method_key(class_name, focal_method_name)
            focal_signature = focal_signature or self._extract_method_signature(focal_method_info, focal_method_name)
            method_variant_key = method_variant_key or self._build_method_variant_key(
                class_name,
                focal_method_name,
                focal_signature,
            )
            expected_test_class_name = self._build_expected_test_class_name(
                class_name,
                focal_method_name,
                focal_signature,
                overloaded=method_is_overloaded,
            )
            
            # TODO 生成测试文件路径
            test_file_name = expected_test_class_name + ".java"
            raw_test_info = test_method.get('TestInfo', '')
            test_file_path = self._resolve_test_file_path(raw_test_info, repo_project_name).parent / test_file_name
            test_file_saved = self._build_saved_test_path(project_name, test_file_path)
            print("\n---------------------------------------------------------------")
            self.logger.info(f"Process method: {focal_method_name}")
            
            # 步骤1: 生成PlantUML代码（项目级解析 + 裁剪） 和 基于LLM的意图生成
            # no_uml 消融分支不生成/传入 UML context。
            plantuml_code = ""
            if self.use_uml_context:
                # 使用裁剪器生成针对焦点方法的类图
                if not self.pruner:
                    self.indexer = GlobalIndexer()
                    project_src_dir = self.repos_dir / repo_project_name
                    self.indexer.index_project(str(project_src_dir))
                    self.pruner = UMLPruner(self.indexer)

                plantuml_code = self.pruner.prune(
                    class_name,
                    focal_method_name,
                    focal_method_code
                )
            
            # 使用方法本身的代码进行逐行意图分析。raw_llm 消融跳过意图分析，只保留验证/修复闭环。
            method_intention = ""
            if self.use_method_intention_context:
                method_intention = self._generate_method_intention(focal_method_info, focal_method_name, plantuml_code)
            else:
                self.logger.info(f"context_mode=raw_llm: skip method intention analysis for {class_name}#{focal_method_name}")

            # 保存方法意图分析到文件

            if method_intention:
                intention_file_name = self._build_method_intention_file_name(
                    class_name,
                    focal_method_name,
                    method_variant_key=method_variant_key,
                )
                intention_path = self.uml_experiment_results_dir / project_name / "MethodIntention" / intention_file_name
                FileUtils.ensure_directory(intention_path.parent)
                FileUtils.write_file(intention_path, method_intention)

            # 步骤2: 构建测试生成prompt（包含方法意图）
            generation_context = self._build_generation_context(
                under_test_method=under_test_method,
                focal_method_code=focal_method_code,
                focal_method_info=focal_method_info,
                plantuml_code=plantuml_code,
                package_name=package_name,
                class_name=class_name,
                repo_project_name=repo_project_name,
            )
            generation_context["context_mode"] = self.context_mode
            generation_context["use_uml_context"] = self.use_uml_context
            generation_context["use_method_intention_context"] = self.use_method_intention_context
            failure_state = self._new_failure_state()

            test_prompt = self._build_test_prompt(
                focal_method_code, focal_method_name, test_import_info,
                plantuml_code, package_name, class_name, method_intention,
                expected_test_class_name=expected_test_class_name,
                project_constraints=generation_context
            )
            
            # 步骤3: 生成测试代码
            generated_test = self._generate_test_code(
                test_prompt,
                raw_mode=self.context_mode == "raw_llm",
            )
            if not generated_test.strip():
                classified = self._classify_failure(
                    "",
                    0,
                    0,
                    generation_context.get("candidate_type_map"),
                    stage_hint="generation"
                )
                self._merge_failure_state(failure_state, classified, first_only=True)
                self._merge_failure_state(failure_state, classified)
                return {
                    "project_name": repo_project_name,
                    "method_name": focal_method_name,
                    "class_name": class_name,
                    "method_key": method_key,
                    "method_variant_key": method_variant_key,
                    "focal_signature": focal_signature,
                    "run_id": run_id,
                    "artifact_version": self.artifact_version,
                    "context_mode": self.context_mode,
                    "use_uml_context": self.use_uml_context,
                    "use_method_intention_context": self.use_method_intention_context,
                    "package_name": package_name,
                    "plantuml_generated": plantuml_code if plantuml_code else "False",
                    "method_intention_generated": method_intention if method_intention else "False",
                    "test_generated": "False",
                    "test_file_saved": "False",
                    "original_test_path": str(test_file_path),
                    "compile_success": 0,
                    "test_success": 0,
                    "line_coverage": 0.0,
                    "branch_coverage": 0.0,
                    "uncovered_lines": [],
                    "coverage_status": "coverage_unresolved",
                    "coverage_error_hint": "",
                    "coverage_last_checked_at": "",
                    "repair_rounds": 0,
                    "used_delete_fallback": False,
                    "repair_repeat_count": 0,
                    "repair_repeat_guard_hits": 0,
                    "java_source_level": generation_context.get("java_source_level", "1.8"),
                    "java_target_level": generation_context.get("java_target_level", "1.8"),
                    "repair_route_history": [],
                    **failure_state,
                }
            
            # 保存TestPrompt到实验结果目录
            test_prompt_file_name = self._build_test_prompt_file_name(
                class_name,
                focal_method_name,
                method_variant_key=method_variant_key,
            )
            test_prompt_path = self.uml_experiment_results_dir / project_name / "TestPrompt" / test_prompt_file_name
            FileUtils.ensure_directory(test_prompt_path.parent)
            FileUtils.write_file(test_prompt_path, test_prompt)
            
            # 步骤5: 执行并修复测试
            pipeline_result = InitialGeneratorRepairPipelineHelper.run(
                owner=self,
                generated_test=generated_test,
                test_prompt=test_prompt,
                repo_project_name=repo_project_name,
                test_file_path=test_file_path,
                test_file_saved=test_file_saved,
                package_name=package_name,
                class_name=class_name,
                expected_test_class_name=expected_test_class_name,
                focal_method_code=focal_method_code,
                focal_method_name=focal_method_name,
                method_intention=method_intention,
                generation_context=generation_context,
                failure_state=failure_state,
            )
            generated_test = pipeline_result["generated_test"]
            compile_success = pipeline_result["compile_success"]
            test_success = pipeline_result["test_success"]
            repair_route_history = pipeline_result["repair_route_history"]
            repair_count = pipeline_result["repair_count"]
            used_delete_fallback = pipeline_result["used_delete_fallback"]
            repair_repeat_count = pipeline_result["repair_repeat_count"]
            repair_repeat_guard_hits = pipeline_result["repair_repeat_guard_hits"]

            if isinstance(test_file_saved, Path):
                FileUtils.write_file(test_file_saved, generated_test)
            result = {
                "project_name": repo_project_name,
                "method_name": focal_method_name,
                "class_name": class_name,
                "method_key": method_key,
                "method_variant_key": method_variant_key,
                "focal_signature": focal_signature,
                "run_id": run_id,
                "artifact_version": self.artifact_version,
                "context_mode": self.context_mode,
                "use_uml_context": self.use_uml_context,
                "use_method_intention_context": self.use_method_intention_context,
                "package_name": package_name,
                "plantuml_generated": plantuml_code if plantuml_code else "False",
                "method_intention_generated": method_intention if method_intention else "False",
                "test_generated": generated_test if generated_test else "False",
                "test_file_saved": str(test_file_saved) if test_file_saved else "False",
                "original_test_path": str(test_file_path),
                "compile_success": compile_success,
                "test_success": test_success,
                "line_coverage": 0.0,
                "branch_coverage": 0.0,
                "uncovered_lines": [],
                "coverage_status": "coverage_unresolved",
                "coverage_error_hint": "",
                "coverage_last_checked_at": "",
                "repair_rounds": repair_count,
                "used_delete_fallback": used_delete_fallback,
                "repair_repeat_count": repair_repeat_count,
                "repair_repeat_guard_hits": repair_repeat_guard_hits,
                "java_source_level": generation_context.get("java_source_level", "1.8"),
                "java_target_level": generation_context.get("java_target_level", "1.8"),
                "repair_route_history": repair_route_history,
                **failure_state,
            }
            
            return result
            
        except Exception as e:
            self.logger.error(f"Failed to process method: {e}")
            return {}

    def _generate_method_intention(self, focal_method_code: str, focal_method_name: str,
                                   plantuml_code: str) -> str:
        """
        生成方法意图分析 - 使用LLM逐行分析方法实现逻辑

        Args:
            focal_method_code: 焦点方法代码
            focal_method_name: 焦点方法名称
            plantuml_code: PlantUML代码

        Returns:
            方法意图分析结果
        """
        try:
            # 为代码添加行号，方便LLM进行逐行分析
            code_with_line_numbers = self._add_line_numbers(focal_method_code)
            intention_prompt = InitialGeneratorPromptBuilder.build_method_intention_prompt(
                code_with_line_numbers=code_with_line_numbers,
                focal_method_name=focal_method_name,
                plantuml_code=plantuml_code
            )

            messages = [
                {
                    "role": "system",
                    "content": InitialGeneratorPromptBuilder.METHOD_INTENTION_SYSTEM
                },
                {
                    "role": "user",
                    "content": intention_prompt
                }
            ]

            response = self.llm_client.chat_completion(messages, temperature=0.1)

            if response:
                self.logger.info(f"成功生成方法意图分析: {focal_method_name}")
                return response.strip()
            else:
                self.logger.warning(f"方法意图分析生成失败: {focal_method_name}")
                return ""

        except Exception as e:
            self.logger.error(f"生成方法意图分析失败: {e}")
            return ""

    @staticmethod
    def _build_test_prompt(focal_method_code: str, focal_method_name: str,
                           test_import_info: str, plantuml_code: str,
                           package_name: str, class_name: str, method_intention: str = "",
                           expected_test_class_name: str = "",
                           project_constraints: Optional[Dict[str, Any]] = None) -> str:
        return InitialGeneratorPromptBuilder.build_test_prompt(
            focal_method_code=focal_method_code,
            focal_method_name=focal_method_name,
            test_import_info=test_import_info,
            plantuml_code=plantuml_code,
            package_name=package_name,
            class_name=class_name,
            method_intention=method_intention,
            expected_test_class_name=expected_test_class_name,
            project_constraints=project_constraints
        )
    
    def _generate_test_code(self, prompt: str, raw_mode: bool = False) -> str:
        """
        生成测试代码
        
        Args:
            prompt: 测试生成prompt
            
        Returns:
            生成的测试代码
        """
        try:
            messages = [
                {
                    "role": "system",
                    "content": (
                        InitialGeneratorPromptBuilder.RAW_TEST_GENERATION_SYSTEM
                        if raw_mode
                        else InitialGeneratorPromptBuilder.TEST_GENERATION_SYSTEM
                    )
                },
                {"role": "user", "content": prompt}
            ]
            
            response = self.llm_client.chat_completion(messages, temperature=0.1)
            
            if response:
                # 提取代码块
                test_code = self._extract_code_from_response(response)
                self.logger.info("成功生成测试代码")
                return test_code
            else:
                self.logger.error("LLM响应为空")
                return ""
                
        except Exception as e:
            self.logger.error(f"生成测试代码失败: {e}")
            return ""
    
    def _extract_code_from_response(self, response: str) -> str:
        """
        从LLM响应中提取代码
        
        Args:
            response: LLM响应
            
        Returns:
            提取的代码
        """
        # 查找代码块
        code_match = re.search(r'```java(.*?)```', response, re.DOTALL)
        if code_match:
            return code_match.group(1).strip()
        
        # 如果没有找到java代码块，尝试查找普通代码块
        code_match = re.search(r'```(.*?)```', response, re.DOTALL)
        if code_match:
            return code_match.group(1).strip()
        
        # 如果没有找到代码块，返回整个响应
        return response.strip()
    
    def _save_test_file(self, generated_test: str, original_test_path: Path, 
                       package_name: str, class_name: str) -> str:
        """
        保存测试文件
        
        Args:
            generated_test: 生成的测试代码
            original_test_path: 原始测试文件路径
            package_name: 包名
            class_name: 类名
            
        Returns:
            完整的测试代码
        """
        # 添加包声明
        if not generated_test.startswith("package"):
            generated_test = f"package {package_name};\n\n{generated_test}"

        # 添加生成时间注释
        # current_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        # header = f"// Generated by maptest at {current_time}\n"
        # header += f"// original_test_path: {original_test_path}\n"
        # full_test_code = header + generated_test
        full_test_code = generated_test

        # 确保目标目录存在，并写入测试文件
        FileUtils.ensure_directory(original_test_path.parent)
        FileUtils.write_file(original_test_path, full_test_code)
        self.logger.info(f"成功保存测试文件: {original_test_path}")

        return full_test_code


    def _execute_test(self, test_file_path: Path, project_name: str, test_file_saved: Path) -> tuple:
        """
        执行测试
        
        Args:
            test_file_path: 测试文件路径
            project_name: 项目名称
            test_file_saved: 保存的测试文件路径
            
        Returns:
            (编译成功, 测试成功, 测试结果) 元组
        """
        # 获取项目路径
        project_path = self.repos_dir / project_name

        # 执行编译和测试，使用超时配置
        maven_executor = MavenExecutor(str(project_path), timeout=self.test_timeout)
        maven_executor.clear_test_reports()
        result = maven_executor.compile_and_test()
        compile_success = 1 if result.compile_success else 0
        test_success = 1 if result.test_success else 0
        
        self.logger.info(f"测试执行结果 - 编译: {compile_success}, 测试: {test_success}")
        
        # 保存详细的测试执行信息到LogINFO目录
        test_info_path = str(test_file_saved).replace("GeneratedTest", "LogINFO")
        
        FileUtils.write_file(test_info_path, str(result.output))
        
        # 清理测试文件，准备下一次运行
        self._cleanup_test_files(project_path)
        
        return compile_success, test_success, result.output
    
    def _cleanup_test_files(self, project_path: Path):
        """
        清理测试文件
        
        Args:
            project_path: 项目路径
        """
        try:
            test_dir = project_path / "src" / "test"
            if test_dir.exists():
                # 清理测试目录，但保留目录结构
                for item in test_dir.rglob("*.java"):
                    item.unlink()
                self.logger.info("测试文件已清理")
        except Exception as e:
            self.logger.error(f"清理测试文件失败: {e}")
    
    @staticmethod
    def _build_focal_method_code(focal_class: str, fields: str,
                                 constructors: str, focal_method_info: str) -> str:
        """构建焦点方法代码"""
        code_parts = [
            focal_class,
            fields + "\n" if fields else "",
            constructors + "\n" if constructors else "",
            "\n// Focal method\n",
            focal_method_info,
            "\n}"
        ]
        
        # 清理空行
        code = '\n'.join(filter(lambda x: x.strip(), '\n'.join(code_parts).split('\n')))
        return code

    def _batch_calculate_coverage(self, results: List[Dict[str, Any]], project_name: str) -> List[Dict[str, Any]]:
        """
        批量计算覆盖率 - 在所有方法处理完毕后统一运行。
        逐个恢复 test_success==1 的测试文件，运行 Maven + Jacoco，
        收集每个方法的 uncovered_lines 并写回 results。

        Args:
            results: 所有方法的处理结果列表
            project_name: 项目名称

        Returns:
            更新了覆盖率字段的 results 列表
        """
        # 筛选需要计算覆盖率的方法
        successful_results = [r for r in results if r.get('test_success') == 1]
        if not successful_results:
            self.logger.info("没有成功的测试，跳过覆盖率计算")
            return results

        self.logger.info(f"开始批量计算覆盖率: {len(successful_results)} 个成功方法")
        project_path = self.repos_dir / project_name

        for r in tqdm(successful_results, desc=f"计算覆盖率"):
            try:
                r.setdefault('coverage_status', 'coverage_unresolved')
                r.setdefault('coverage_error_hint', '')
                r.setdefault('coverage_last_checked_at', '')
                r['line_coverage'] = 0.0
                r['branch_coverage'] = 0.0
                r['uncovered_lines'] = []
                r['uncovered_line_details'] = []
                test_file_saved = r.get('test_file_saved', '')
                original_test_path = r.get('original_test_path', '')
                class_name = r.get('class_name', '')
                method_name = r.get('method_name', '')
                package_name = r.get('package_name', '')

                if not test_file_saved or not original_test_path:
                    r['coverage_status'] = 'coverage_unresolved'
                    r['coverage_error_hint'] = '缺少测试文件路径，未执行覆盖率计算'
                    continue

                # 读取保存的测试代码
                test_code = FileUtils.read_file(test_file_saved)
                if not test_code:
                    r['coverage_status'] = 'coverage_unresolved'
                    r['coverage_error_hint'] = '测试代码为空，未执行覆盖率计算'
                    continue

                # 恢复测试文件到项目
                test_path = Path(original_test_path)
                FileUtils.ensure_directory(test_path.parent)
                FileUtils.write_file(test_path, test_code)

                # 执行测试 (Jacoco 报告会在 mvn test 阶段自动生成)
                maven_executor = MavenExecutor(str(project_path), timeout=self.test_timeout)
                maven_executor.clear_test_reports()
                maven_result = maven_executor.compile_and_test()
                maven_output = str(getattr(maven_result, "output", "") or "")
                r['coverage_last_checked_at'] = datetime.datetime.now().isoformat()

                if maven_result.test_success:
                    if hasattr(maven_executor, "ensure_jacoco_report"):
                        jacoco_result = maven_executor.ensure_jacoco_report(Path(original_test_path).stem)
                        maven_output += "\n########## JaCoCo INFO ##########\n" + str(
                            getattr(jacoco_result, "output", "") or getattr(jacoco_result, "error", "") or ""
                        )
                    # 构建类全限定名
                    class_fqn = f"{package_name}.{class_name}" if package_name else class_name
                    focal_method_range = self._resolve_focal_method_range_for_result(r, project_name)
                    if focal_method_range is not None:
                        r['focal_method_start_line'], r['focal_method_end_line'] = focal_method_range

                    coverage_data = self._collect_batch_coverage(
                        maven_executor,
                        class_fqn,
                        method_name,
                        focal_method_range=focal_method_range,
                    )

                    line_coverage = self._safe_float(coverage_data.get('line_coverage'))
                    branch_coverage = self._safe_float(coverage_data.get('branch_coverage'))
                    r['line_coverage'] = line_coverage if line_coverage is not None else 0.0
                    r['branch_coverage'] = branch_coverage if branch_coverage is not None else 0.0
                    r['uncovered_lines'] = coverage_data.get('uncovered_lines', [])
                    r['uncovered_line_details'] = coverage_data.get('uncovered_line_details', [])
                    for coverage_key in (
                        'coverage_report_fresh',
                        'coverage_percentage_source',
                        'coverage_handles_source',
                        'coverage_file_line_coverage',
                        'coverage_file_branch_coverage',
                        'coverage_method_counter_resolved',
                        'coverage_method_range_resolved',
                        'coverage_status',
                        'coverage_error_hint',
                    ):
                        if coverage_key in coverage_data:
                            r[coverage_key] = coverage_data.get(coverage_key)
                    status, hint = self._evaluate_coverage_quality(
                        line_coverage=r.get('line_coverage'),
                        branch_coverage=r.get('branch_coverage'),
                        uncovered_lines=r.get('uncovered_lines'),
                        maven_output=maven_output
                    )
                    raw_status = coverage_data.get('coverage_status')
                    raw_hint = coverage_data.get('coverage_error_hint')
                    if status == 'coverage_parse_failed' and raw_status:
                        status = raw_status
                        hint = raw_hint or hint
                    r['coverage_status'] = status
                    r['coverage_error_hint'] = hint

                    self.logger.info(
                        f"  {class_name}.{method_name}: "
                        f"行覆盖={r['line_coverage']:.1f}%, "
                        f"分支覆盖={r['branch_coverage']:.1f}%, "
                        f"未覆盖行={len(r['uncovered_lines'])}, "
                        f"coverage_status={r['coverage_status']}"
                    )
                else:
                    lowered_output = maven_output.lower()
                    if "timeout" in lowered_output or "超时" in maven_output:
                        self.logger.warning(f"  {class_name}.{method_name}: 覆盖率计算时超时")
                        r['coverage_status'] = "coverage_stale_report"
                        r['coverage_error_hint'] = "覆盖率重跑超时"
                    elif not getattr(maven_result, "compile_success", False):
                        self.logger.warning(f"  {class_name}.{method_name}: 覆盖率计算时编译失败")
                        r['coverage_status'] = "coverage_parse_failed"
                        r['coverage_error_hint'] = "覆盖率重跑编译失败"
                    else:
                        self.logger.warning(f"  {class_name}.{method_name}: 覆盖率计算时测试失败")
                        r['coverage_status'] = "coverage_parse_failed"
                        r['coverage_error_hint'] = "覆盖率重跑测试失败"

                # 清理测试文件
                self._cleanup_test_files(project_path)

            except Exception as e:
                self.logger.error(f"计算覆盖率失败 ({r.get('method_name')}): {e}")
                r['line_coverage'] = 0.0
                r['branch_coverage'] = 0.0
                r['uncovered_lines'] = []
                r['uncovered_line_details'] = []
                r['coverage_status'] = "coverage_parse_failed"
                r['coverage_error_hint'] = f"覆盖率计算异常: {e}"
                r['coverage_last_checked_at'] = datetime.datetime.now().isoformat()

        # 统计覆盖率结果
        full_coverage = sum(
            1 for r in successful_results
            if r.get('coverage_status') == 'coverage_valid' and r.get('line_coverage', 0) >= 100.0
        )
        self.logger.info(f"覆盖率计算完成: {full_coverage}/{len(successful_results)} 个方法达到100%行覆盖")

        return results

    def _evaluate_coverage_quality(self, line_coverage: Any, branch_coverage: Any,
                                   uncovered_lines: Any, maven_output: str = "") -> tuple:
        uncovered = uncovered_lines or []
        line_value = self._safe_float(line_coverage)
        branch_value = self._safe_float(branch_coverage)

        has_signal = (
            bool(uncovered) or
            (line_value is not None and line_value > 0.0) or
            (branch_value is not None and branch_value > 0.0)
        )
        if has_signal:
            return "coverage_valid", ""

        lowered_output = (maven_output or "").lower()
        if "timeout" in lowered_output or "超时" in (maven_output or ""):
            return "coverage_stale_report", "覆盖率结果缺失，疑似超时"

        return "coverage_parse_failed", "覆盖率结果为空或解析失败"

    def _safe_float(self, value: Any) -> Optional[float]:
        try:
            if value is None or value == "":
                return None
            return float(value)
        except (TypeError, ValueError):
            return None

    @staticmethod
    def _has_generated_artifact(value: Any) -> bool:
        if value is None:
            return False
        if isinstance(value, bool):
            return value
        text = str(value).strip()
        return bool(text) and text.lower() not in {"false", "none", "null", "0"}
    
    def _save_overall_results(self, project_name: str, results: List[Dict[str, Any]],
                              run_id: str, run_mode: str, input_digest: str,
                              started_at: str, completed_at: str):
        """
        保存总体结果
        
        Args:
            project_name: 项目名称
            results: 结果列表
        """
        try:
            # 计算统计信息
            total_methods = len(results)
            successful_plantuml = sum(1 for r in results if self._has_generated_artifact(r.get('plantuml_generated')))
            successful_intention = sum(1 for r in results if self._has_generated_artifact(r.get('method_intention_generated')))
            successful_test_generation = sum(1 for r in results if self._has_generated_artifact(r.get('test_generated')))
            successful_compilation = sum(1 for r in results if r.get('compile_success', 0))
            successful_tests = sum(1 for r in results if r.get('test_success', 0))
            
            overall_result = {
                "project_name": project_name,
                "timestamp": datetime.datetime.now().isoformat(),
                "run_id": run_id,
                "run_mode": run_mode,
                "context_mode": self.context_mode,
                "use_uml_context": self.use_uml_context,
                "use_method_intention_context": self.use_method_intention_context,
                "result_root": str(self.uml_experiment_results_dir),
                "input_digest": input_digest,
                "run_complete": True,
                "started_at": started_at,
                "completed_at": completed_at,
                "total_methods": total_methods,
                "compilation_num": successful_compilation,     
                "test_num": successful_tests,
                "plantuml_generation_rate": f"{(successful_plantuml / total_methods * 100):.2f}%" if total_methods > 0 else "0.00%",
                "method_intention_generation_rate": f"{(successful_intention / total_methods * 100):.2f}%" if total_methods > 0 else "0.00%",
                "test_generation_rate": f"{(successful_test_generation / total_methods * 100):.2f}%" if total_methods > 0 else "0.00%",
                "compilation_rate": f"{(successful_compilation / total_methods * 100):.2f}%" if total_methods > 0 else "0.00%",
                "test_success_rate": f"{(successful_tests / total_methods * 100):.2f}%" if total_methods > 0 else "0.00%",
                "detailed_results": results
            }
            
            # 保存结果文件
            result_file = self.uml_experiment_results_dir / project_name / "overall_results.json"
            FileUtils.write_json(result_file, overall_result)
            
            self.logger.info(f"总体结果已保存: {result_file}")
            self.logger.info(f"统计信息 - 总方法: {total_methods}, "
                           f"PlantUML生成率: {overall_result['plantuml_generation_rate']}, "
                           f"方法意图生成率: {overall_result['method_intention_generation_rate']}, "
                           f"测试生成率: {overall_result['test_generation_rate']}, "
                           f"编译率: {overall_result['compilation_rate']}, "
                           f"测试成功率: {overall_result['test_success_rate']}")
            
        except Exception as e:
            self.logger.error(f"保存总体结果失败: {e}")


def main():
    """主函数"""
    # 设置日志级别
    from maptest.core import setup_logging
    setup_logging('INFO')
    
    logger = get_logger("InitialGenerator")
    logger.info("开始 InitialGenerator 测试")
    
    # 使用意图标签
    intention_tag = "uml-based-generation"
    
    # 选择要处理的项目
    projects = [
                # 'humaneval-test-original.json',
                #'sachin-handiekar_jInstagram.json',
                #'tabulapdf_tabula-java.json',
                'Zappos_zappos-json.json'
                ]
    
    # 处理每个项目
    for project_name in projects:
        logger.info(f"处理项目: {project_name}")
        
        json_file_path = workspace_root / "RepoData" / project_name
        if not json_file_path.exists():
            logger.warning(f"项目文件不存在: {json_file_path}")
            continue
        
        # 创建生成器并处理项目
        generator = InitialGenerator(intention_tag)
        success = generator.process_project(str(json_file_path))
        
        if success:
            logger.info(f"项目处理成功: {project_name}")
        else:
            logger.error(f"项目处理失败: {project_name}")
    
    logger.info("所有项目处理完成")
