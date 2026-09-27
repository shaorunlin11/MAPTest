from __future__ import annotations

import os
import re
import stat
from pathlib import Path
from typing import Any, Callable, Dict, Optional

from maptest.core import FileUtils, MavenExecutor

from maptest.modules.Optimizer.Repairer import repair_test_code

from .models import Baseline, CoverageState, MethodContext, PatchCandidate, baseline_from_method_result
from .source_api import SourceApiExtractor, SourceApiSummary, format_source_api_summary


class PostProcessorV2ValidationService:
    def __init__(
        self,
        repos_dir: Optional[Path] = None,
        maven_timeout_seconds: int = 120,
        maven_executor_factory: Optional[Callable[..., Any]] = None,
        repair_attempt_limit: int = 2,
        patch_repair_attempt_limit: int = 1,
        repair_function: Optional[Callable[..., Any]] = None,
        source_api_extractor: Optional[SourceApiExtractor] = None,
    ):
        package_root = Path(__file__).resolve().parents[2]
        workspace_root = package_root.parent
        self.repos_dir = repos_dir or workspace_root / "Repos"
        self.maven_timeout_seconds = int(maven_timeout_seconds)
        self.maven_executor_factory = maven_executor_factory or MavenExecutor
        self.repair_attempt_limit = max(0, int(repair_attempt_limit))
        self.patch_repair_attempt_limit = max(0, int(patch_repair_attempt_limit))
        self.repair_function = repair_function or repair_test_code
        self.source_api_extractor = source_api_extractor or SourceApiExtractor(self.repos_dir)

    def validate_current_test(self, context: MethodContext) -> Baseline:
        baseline = baseline_from_method_result(context.raw_result, source="initial")
        if not context.test_code:
            return baseline
        if not self._contains_test_annotation(context.test_code):
            metadata = dict(baseline.metadata)
            metadata["validation_output"] = "initial test contains no executable test annotation"
            return Baseline(
                test_code=context.test_code,
                compile_success=False,
                test_success=False,
                coverage=baseline.coverage,
                source="initial_no_test_methods",
                metadata=metadata,
            )

        compile_success, test_success, output = self.validate_test_code(context, context.test_code)
        metadata = dict(baseline.metadata)
        metadata["validation_output"] = output
        return Baseline(
            test_code=context.test_code,
            compile_success=compile_success,
            test_success=test_success,
            coverage=baseline.coverage,
            source="validated_initial",
            metadata=metadata,
        )

    @staticmethod
    def _contains_test_annotation(test_code: str) -> bool:
        return bool(re.search(r"@(Test|ParameterizedTest|RepeatedTest|TestFactory|TestTemplate|Theory)\b", test_code or ""))

    def stabilize_once(self, context: MethodContext, baseline: Baseline) -> Baseline:
        current_code = baseline.test_code or context.test_code
        if not current_code:
            return Baseline(
                test_code="",
                compile_success=False,
                test_success=False,
                coverage=baseline.coverage,
                source="stabilization_no_test_code",
                metadata=dict(baseline.metadata),
            )

        current_error = str(baseline.metadata.get("validation_output") or "")
        repair_logs = []

        for attempt in range(1, self.repair_attempt_limit + 1):
            repair_result = self.repair_function(
                current_code,
                test_prompt=self._repair_prompt_context(context),
                error_info=current_error,
                delete_test=False,
                temperature=0.1,
                repair_mode=self._repair_mode(baseline.compile_success, baseline.test_success),
                repair_context=self._repair_context(context, current_code),
            )
            repaired_code = str(getattr(repair_result, "repaired_code", "") or "")
            repair_success = bool(getattr(repair_result, "success", False))
            repair_logs.append({
                "attempt": attempt,
                "repair_success": repair_success,
                "strategy": str(getattr(repair_result, "repair_strategy", "") or ""),
            })

            if not repair_success or not repaired_code.strip():
                continue

            compile_success, test_success, output = self.validate_test_code(context, repaired_code)
            metadata = dict(baseline.metadata)
            metadata["validation_output"] = output
            metadata["repair_attempts"] = repair_logs
            if compile_success and test_success:
                return Baseline(
                    test_code=repaired_code,
                    compile_success=True,
                    test_success=True,
                    coverage=baseline.coverage,
                    source="stabilized_repair",
                    metadata=metadata,
                )

            current_code = repaired_code
            current_error = output

        metadata = dict(baseline.metadata)
        metadata["repair_attempts"] = repair_logs
        return Baseline(
            test_code=current_code,
            compile_success=False,
            test_success=False,
            coverage=baseline.coverage,
            source="stabilization_failed",
            metadata=metadata,
        )

    def build_zero_coverage_template(self, context: MethodContext, baseline: Baseline) -> Baseline:
        class_name = context.class_name or "UnknownClass"
        method_name = context.method_name or "unknownMethod"
        test_class_name = f"{class_name}{method_name[:1].upper()}{method_name[1:]}ZeroCoverageTest"
        template = (
            "import org.junit.Test;\n\n"
            f"public class {test_class_name} {{\n"
            "    @Test\n"
            "    public void generatedBaselineCompiles() {\n"
            "        // Zero-coverage baseline: keep the test class runnable before enhancement.\n"
            "    }\n"
            "}\n"
        )
        compile_success, test_success, output = self.validate_test_code(context, template)
        coverage_metadata = dict(baseline.coverage.metadata)
        coverage_metadata["coverage_reset_reason"] = "zero_coverage_template_does_not_call_focal_method"
        coverage_metadata["previous_line_coverage"] = baseline.coverage.line_coverage
        coverage_metadata["previous_branch_coverage"] = baseline.coverage.branch_coverage
        target_status = baseline.coverage.status
        if not target_status or target_status == "unknown":
            target_status = "coverage_handles_only" if baseline.coverage.uncovered_lines else "coverage_unresolved"
        zero_coverage = CoverageState(
            line_coverage=0.0,
            branch_coverage=0.0,
            uncovered_lines=baseline.coverage.uncovered_lines,
            target_line_coverage=baseline.coverage.target_line_coverage,
            status=target_status,
            metadata=coverage_metadata,
        )
        return Baseline(
            test_code=template,
            compile_success=compile_success,
            test_success=test_success,
            coverage=zero_coverage,
            source="zero_coverage_template",
            metadata={"fallback_from": baseline.source, "validation_output": output},
        )

    def validate_patch(self, context: MethodContext, candidate: PatchCandidate) -> PatchCandidate:
        if not candidate.test_code:
            metadata = dict(candidate.metadata)
            metadata["validation_output"] = "patch candidate has no test code"
            return PatchCandidate(
                test_code="",
                compile_success=False,
                test_success=False,
                coverage=candidate.coverage,
                repaired=candidate.repaired,
                source=candidate.source,
                metadata=metadata,
            )

        test_code = self._normalize_patch_code_with_source_api(context, candidate.test_code)
        compile_success, test_success, output = self.validate_test_code(context, test_code)
        metadata = dict(candidate.metadata)
        metadata["validation_output"] = output
        return PatchCandidate(
            test_code=test_code,
            compile_success=compile_success,
            test_success=test_success,
            coverage=candidate.coverage,
            repaired=candidate.repaired,
            source=candidate.source,
            metadata=metadata,
        )

    def repair_once(self, context: MethodContext, candidate: PatchCandidate) -> PatchCandidate:
        if candidate.runnable:
            return candidate

        metadata = dict(candidate.metadata)
        if not candidate.test_code:
            metadata.setdefault("repair_attempts", [])
            return PatchCandidate(
                test_code="",
                compile_success=False,
                test_success=False,
                coverage=candidate.coverage,
                repaired=candidate.repaired,
                source="patch_repair_unavailable",
                metadata=metadata,
            )

        if self.patch_repair_attempt_limit <= 0:
            metadata["repair_attempts"] = []
            return PatchCandidate(
                test_code=candidate.test_code,
                compile_success=candidate.compile_success,
                test_success=candidate.test_success,
                coverage=candidate.coverage,
                repaired=candidate.repaired,
                source="patch_repair_skipped",
                metadata=metadata,
            )

        current_code = self._normalize_patch_code_with_source_api(context, candidate.test_code)
        current_compile_success = candidate.compile_success
        current_test_success = candidate.test_success
        current_error = str(candidate.metadata.get("validation_output") or "")
        repair_logs = []

        for attempt in range(1, self.patch_repair_attempt_limit + 1):
            repair_result = self.repair_function(
                current_code,
                test_prompt=self._repair_prompt_context(context, candidate),
                error_info=current_error,
                delete_test=False,
                temperature=0.1,
                repair_mode=self._repair_mode(current_compile_success, current_test_success),
                repair_context=self._repair_context(context, current_code),
            )
            repaired_code = str(getattr(repair_result, "repaired_code", "") or "")
            repair_success = bool(getattr(repair_result, "success", False))
            repair_log = {
                "attempt": attempt,
                "repair_success": repair_success,
                "strategy": str(getattr(repair_result, "repair_strategy", "") or ""),
            }
            repair_logs.append(repair_log)

            if not repair_success or not repaired_code.strip():
                repair_log["stop_reason"] = "repairer_returned_no_code"
                break

            repaired_code = self._normalize_patch_code_with_source_api(context, repaired_code)
            compile_success, test_success, output = self.validate_test_code(context, repaired_code)
            repair_log["validation_compile_success"] = compile_success
            repair_log["validation_test_success"] = test_success
            current_code = repaired_code
            current_compile_success = compile_success
            current_test_success = test_success
            current_error = output

            if compile_success and test_success:
                metadata["repair_attempts"] = repair_logs
                metadata["validation_output"] = output
                return PatchCandidate(
                    test_code=repaired_code,
                    compile_success=True,
                    test_success=True,
                    coverage=candidate.coverage,
                    repaired=True,
                    source="patch_repaired",
                    metadata=metadata,
                )

        metadata["repair_attempts"] = repair_logs
        metadata["validation_output"] = current_error
        return PatchCandidate(
            test_code=current_code,
            compile_success=current_compile_success,
            test_success=current_test_success,
            coverage=candidate.coverage,
            repaired=True,
            source="patch_repair_failed",
            metadata=metadata,
        )

    def validate_test_code(self, context: MethodContext, test_code: str) -> tuple[bool, bool, str]:
        project_path = self.repos_dir / context.project_name
        if not project_path.exists():
            return False, False, f"project path does not exist: {project_path}"

        package_name = str(context.raw_result.get("package_name") or "")
        test_class_name = self.extract_test_class_name(test_code, context)
        package_path = Path(*package_name.split(".")) if package_name else Path()
        temp_test_path = project_path / "src" / "test" / "java" / package_path / f"{test_class_name}.java"

        try:
            FileUtils.ensure_directory(temp_test_path.parent)
            FileUtils.write_file(temp_test_path, test_code)
            self._restore_test_resource_permissions(project_path)

            executor = self.maven_executor_factory(str(project_path), timeout=self.maven_timeout_seconds)
            if hasattr(executor, "clear_test_reports"):
                executor.clear_test_reports()
            if hasattr(executor, "compile_and_test_targeted"):
                result = executor.compile_and_test_targeted(test_class_name)
            else:
                result = executor.compile_and_test()

            output = str(getattr(result, "output", "") or getattr(result, "error", "") or "")
            if bool(getattr(result, "test_success", False)) and hasattr(executor, "ensure_jacoco_report"):
                jacoco_result = executor.ensure_jacoco_report(test_class_name)
                output += "\n########## JaCoCo INFO ##########\n" + str(
                    getattr(jacoco_result, "output", "") or getattr(jacoco_result, "error", "") or ""
                )
            return bool(getattr(result, "compile_success", False)), bool(getattr(result, "test_success", False)), output
        except Exception as exc:
            return False, False, str(exc)
        finally:
            if temp_test_path.exists():
                try:
                    temp_test_path.unlink()
                except OSError:
                    pass
            self._restore_test_resource_permissions(project_path)

    @staticmethod
    def extract_test_class_name(test_code: str, context: MethodContext) -> str:
        if test_code:
            class_match = re.search(r"\bpublic\s+class\s+([A-Za-z_][A-Za-z0-9_]*)\b", test_code)
            if not class_match:
                class_match = re.search(r"\bclass\s+([A-Za-z_][A-Za-z0-9_]*)\b", test_code)
            if class_match:
                return class_match.group(1)

        class_name = context.class_name or "Generated"
        method_name = context.method_name or "Baseline"
        return f"{class_name}{method_name[:1].upper()}{method_name[1:]}Test"

    @staticmethod
    def _restore_test_resource_permissions(project_path: Path) -> None:
        resource_dir = project_path / "src" / "test" / "resources"
        if not resource_dir.exists():
            return

        try:
            resource_files = list(resource_dir.rglob("*"))
        except OSError:
            return

        for resource_file in resource_files:
            if not resource_file.is_file():
                continue
            try:
                current_mode = stat.S_IMODE(os.stat(resource_file).st_mode)
                os.chmod(resource_file, current_mode | stat.S_IRUSR | stat.S_IWUSR)
            except OSError:
                continue

    @staticmethod
    def _repair_mode(compile_success: bool, test_success: bool) -> str:
        if not compile_success:
            return "compile_repair"
        if not test_success:
            return "runtime_repair"
        return "compile_repair"

    @staticmethod
    def _repair_prompt_context(context: MethodContext, candidate: Optional[PatchCandidate] = None) -> str:
        if candidate is not None:
            prompt = str((candidate.metadata or {}).get("prompt") or "")
            if prompt:
                return prompt
        return str(
            context.raw_result.get("test_prompt")
            or context.raw_result.get("test_prompt_generated")
            or context.raw_result.get("prompt")
            or ""
        )

    def _repair_context(self, context: MethodContext, test_code: str = "") -> dict[str, Any]:
        expected_class_name = self.extract_test_class_name(test_code or context.test_code, context)
        source_api = self.source_api_extractor.extract(context)
        return {
            "project_name": context.project_name,
            "class_name": context.class_name,
            "method_name": context.method_name,
            "method_key": context.method_key,
            "package_name": str(context.raw_result.get("package_name") or ""),
            "expected_test_class_name": expected_class_name,
            "generation_context": {
                "candidate_type_map": self._source_api_candidate_type_map(source_api),
            },
            "source_api_context": format_source_api_summary(source_api),
            "focal_method_code": str(
                context.raw_result.get("focal_method_code")
                or context.raw_result.get("method_body")
                or (context.raw_result.get("Under_test_method") or {}).get("Method_body")
                or ""
            ),
        }

    def _normalize_patch_code_with_source_api(self, context: MethodContext, test_code: str) -> str:
        if not test_code:
            return ""

        source_api = self.source_api_extractor.extract(context)
        type_map = self._source_api_candidate_type_map(source_api)
        normalized = test_code
        if self._source_api_is_high_confidence(source_api):
            normalized = self._rewrite_known_wrong_imports(normalized, type_map)
            normalized = self._rewrite_assignable_class_literals(normalized, source_api, type_map)
        normalized = self._ensure_imports_for_used_types(normalized, type_map)
        return normalized

    @staticmethod
    def _source_api_is_high_confidence(source_api: SourceApiSummary) -> bool:
        return str(getattr(source_api, "confidence", "") or "").lower() == "high"

    @staticmethod
    def _source_api_candidate_type_map(source_api: SourceApiSummary) -> Dict[str, list[dict[str, str]]]:
        type_map: Dict[str, list[dict[str, str]]] = {}
        for import_line in source_api.imports:
            imported = str(import_line or "").removeprefix("import ").removesuffix(";").strip()
            if imported.startswith("static ") or imported.endswith(".*") or "." not in imported:
                continue
            simple_name = imported.rsplit(".", 1)[-1]
            PostProcessorV2ValidationService._add_type_candidate(type_map, simple_name, imported)

        for hint in source_api.class_literal_hints:
            fqcn = str(hint or "").split(".class", 1)[0].strip()
            if not fqcn or "." not in fqcn:
                continue
            simple_name = fqcn.rsplit(".", 1)[-1]
            PostProcessorV2ValidationService._add_type_candidate(type_map, simple_name, fqcn)
        return type_map

    @staticmethod
    def _add_type_candidate(type_map: Dict[str, list[dict[str, str]]], simple_name: str, fqcn: str) -> None:
        candidates = type_map.setdefault(simple_name, [])
        if not any(candidate.get("fqcn") == fqcn for candidate in candidates):
            candidates.append({"fqcn": fqcn})

    @staticmethod
    def _rewrite_known_wrong_imports(test_code: str, type_map: Dict[str, list[dict[str, str]]]) -> str:
        lines = []
        for line in test_code.splitlines():
            stripped = line.strip()
            if stripped.startswith("import ") and stripped.endswith(";"):
                imported = stripped.removeprefix("import ").removesuffix(";").strip()
                simple_name = imported.rsplit(".", 1)[-1] if "." in imported else imported
                candidates = type_map.get(simple_name, [])
                if len(candidates) == 1 and imported != candidates[0].get("fqcn", ""):
                    continue
            lines.append(line)
        return "\n".join(lines)

    @staticmethod
    def _rewrite_assignable_class_literals(
        test_code: str,
        source_api: SourceApiSummary,
        type_map: Dict[str, list[dict[str, str]]],
    ) -> str:
        normalized = test_code
        for hint in source_api.class_literal_hints:
            match = re.match(r"([\w.]+)\.class assignable to ([A-Za-z_][A-Za-z0-9_]*)", str(hint or ""))
            if not match:
                continue
            replacement_fqcn, base_type = match.groups()
            replacement_simple = replacement_fqcn.rsplit(".", 1)[-1]
            if not re.search(rf"\bClass<\?\s+extends\s+{re.escape(base_type)}", normalized):
                continue
            base_literal_pattern = re.compile(
                rf"(?<![A-Za-z0-9_$.]){re.escape(base_type)}\.class\b"
            )
            if not base_literal_pattern.search(normalized):
                continue
            normalized = base_literal_pattern.sub(f"{replacement_simple}.class", normalized)
            type_map.setdefault(replacement_simple, [{"fqcn": replacement_fqcn}])
            break
        return normalized

    @staticmethod
    def _ensure_imports_for_used_types(test_code: str, type_map: Dict[str, list[dict[str, str]]]) -> str:
        required_imports = []
        for simple_name, candidates in type_map.items():
            if len(candidates) != 1:
                continue
            fqcn = candidates[0].get("fqcn", "")
            if not fqcn or PostProcessorV2ValidationService._is_implicitly_imported_java_lang(fqcn):
                continue
            if re.search(rf"\b{re.escape(simple_name)}\b", test_code):
                required_imports.append(f"import {fqcn};")
        if not required_imports:
            return test_code
        return PostProcessorV2ValidationService._inject_imports(test_code, required_imports)

    @staticmethod
    def _is_implicitly_imported_java_lang(fqcn: str) -> bool:
        return fqcn.startswith("java.lang.") and "." not in fqcn.removeprefix("java.lang.")

    @staticmethod
    def _inject_imports(test_code: str, imports: list[str]) -> str:
        lines = test_code.splitlines()
        existing = {line.strip() for line in lines if line.strip().startswith("import ")}
        pending = [import_line for import_line in imports if import_line not in existing]
        if not pending:
            return test_code

        insert_index = 0
        package_index = -1
        last_import_index = -1
        for index, line in enumerate(lines):
            stripped = line.strip()
            if stripped.startswith("package "):
                package_index = index
            elif stripped.startswith("import "):
                last_import_index = index

        if last_import_index >= 0:
            insert_index = last_import_index + 1
        elif package_index >= 0:
            insert_index = package_index + 1

        prefix = lines[:insert_index]
        suffix = lines[insert_index:]
        if prefix and prefix[-1].strip():
            prefix.append("")
        prefix.extend(pending)
        prefix.append("")
        return "\n".join(prefix + suffix)
