"""
修复器：基于大模型的修复器
主要修复junit测试用例代码的编译错误和运行错误
"""

import re
from typing import Dict, List, Optional, Any
from pathlib import Path

# 添加项目根目录到Python路径
import sys
project_root = Path(__file__).parent.parent.parent.parent
sys.path.insert(0, str(project_root))

from maptest.core import LoggerMixin, log_function_call, log_performance, LLMClient, FileUtils
from maptest.modules.Optimizer.delete_ErrorTest import ErrorLineDeleter


class RepairResult:
    """修复结果"""
    
    def __init__(self, success: bool = False, repaired_code: str = "", 
                 error_messages: Optional[List[str]] = None, repair_strategy: str = ""):
        self.success = success
        self.repaired_code = repaired_code
        self.error_messages = error_messages if error_messages is not None else []
        self.repair_strategy = repair_strategy
        self.repair_mode = ""
        self.repair_steps = []
        self.rule_fix_applied = False
        self.llm_fix_applied = False
        self.no_effect_reason = ""
        self.analysis_invoked = False
        self.analysis_skipped_reason = ""
    
    def add_repair_step(self, step: str):
        """添加修复步骤"""
        self.repair_steps.append(step)


class ErrorAnalyzer:
    """Analyze compilation/runtime failures from Maven output."""
    
    COMMON_ERRORS = {
        # 测试失败
        'test_failure': [
            r'expected:.*but was:',
            r'ComparisonFailure',
            r'AssertionError',
            r'Tests run:.*Failures:'
        ],
        # 编译错误
        'compilation_error': [
            r'cannot be resolved',
            r'cannot find symbol',
            r'method.*not applicable',
            r'The import.*cannot be resolved'
        ]
    }
    
    @classmethod
    def categorize_errors(cls, error_output: str) -> Dict[str, List[str]]:
        """
        分析错误输出并分类错误类型
        
        Args:
            error_output: 错误输出文本
            
        Returns:
            错误类型分类结果
        """
        categorized_errors = {}
        
        for error_type, patterns in cls.COMMON_ERRORS.items():
            matches = []
            for pattern in patterns:
                found_matches = re.findall(pattern, error_output, re.IGNORECASE)
                if found_matches:
                    matches.extend(found_matches)
            
            if matches:
                categorized_errors[error_type] = list(set(matches))  # 去重
        
        return categorized_errors

    @staticmethod
    def analyze_errors(error_output: str) -> Dict[str, Any]:
        """
        分析错误报告，提取错误数量错误行和测试方法名

        Args:
            error_output: 错误输出文本

        Returns:
            包含错误信息的字典，格式:
            {
                "error_count": 总错误数,
                "error_lines": [错误行号列表],
                "test_method_names": [测试方法名列表]
            }
        """
        result = {
            "error_count": 0,
            "error_lines": [],
            "test_method_names": []
        }
        
        # 先尝试从日志中直接提取错误数量
        # 匹配格式: [INFO] X errors 或 [INFO] X error
        error_count_pattern = re.compile(
            r'\[INFO]\s+(\d+)\s+errors?\s*',
            re.MULTILINE
        )
        count_match = error_count_pattern.search(error_output)
        if count_match:
            result["error_count"] = int(count_match.group(1))
        
        # 编译错误正则表达式
        # 匹配格式: [ERROR] /path/to/file.java:[line,column] error message
        compilation_pattern = re.compile(
            r'\[ERROR]\s+.+?\.java:\[(\d+),\d+]\s+',
            re.MULTILINE
        )
        
        # 测试错误正则表达式
        # 匹配格式: [ERROR]   ClassName.testMethodName:line expected:<...> but was:<...>
        # 匹配格式: [ERROR]   ClassName.testMethodName:line - ErrorType...
        test_error_pattern = re.compile(
            r'\[ERROR]\s+(\w+)\.(\w+):(\d+)\s+',
            re.MULTILINE
        )
        
        # 查找编译错误（不去重，同一行可能有多个错误）
        for match in compilation_pattern.finditer(error_output):
            line_num = int(match.group(1))
            result["error_lines"].append(line_num)
        
        # 查找测试错误（测试方法名去重，但行号不去重）
        seen_methods = set()
        for match in test_error_pattern.finditer(error_output):
            test_method = match.group(2)
            line_num = int(match.group(3))
            if test_method not in seen_methods:
                seen_methods.add(test_method)
                result["test_method_names"].append(test_method)
            result["error_lines"].append(line_num)
        
        # 如果没有从日志中提取到错误数量，则根据错误行数统计
        if result["error_count"] == 0:
            result["error_count"] = len(result["error_lines"])
        
        return result



class LLMRepairer(LoggerMixin):
    """LLM-based test repairer."""

    def __init__(self, intention_tag: str = "llm-repair", model_name: Optional[str] = None):
        """
        初始化基于大模型的修复器
        
        Args:
            intention_tag: 意图标签
            model_name: 模型名称
        """
        self.intention_tag = intention_tag
        self.llm_client = LLMClient(model_name)
        self.error_line = None
    
    @log_function_call()
    @log_performance()
    def repair(self, test_code: str, test_prompt: Optional[str] = None, error_info: Optional[str] = None,
               delete_test: bool = False, temperature: float = 0.1,
               repair_mode: Optional[str] = None,
               repair_context: Optional[Dict[str, Any]] = None) -> RepairResult:
        """
        基于大模型修复测试代码
        
        Args:
            test_code: 测试代码
            test_prompt: 初始生成提示
            error_info: 错误信息
            delete_test: 是否删除错误测试
            temperature: 大模型温度设置
            
        Returns:
            修复结果
        """
        result = RepairResult()
        result.add_repair_step("start_llm_repair")
        repair_context = repair_context or {}
        
        try:
            if not error_info:
                result.add_repair_step("缺少错误信息，无法使用大模型修复")
                result.no_effect_reason = "missing_error_info"
                result.analysis_skipped_reason = "missing_error_info"
                return result
            
            # 分析错误
            categorized_errors = ErrorAnalyzer.categorize_errors(error_info)
            analyze_errors = ErrorAnalyzer.analyze_errors(error_info)
            error_count = analyze_errors["error_count"]
            result.add_repair_step(
                f"analyzed {error_count} errors across {len(categorized_errors)} categories"
            )
            error_analysis = ""
            resolved_mode = self._resolve_repair_mode(repair_mode, error_info)
            result.repair_strategy = resolved_mode
            result.repair_mode = resolved_mode

            # Delete failing tests when requested.
            if delete_test and resolved_mode != "compile_repair":
                error_lines = analyze_errors.get("error_lines", [])
                result.analysis_invoked = False
                result.analysis_skipped_reason = "delete_test"
                test_method_names = analyze_errors.get("test_method_names", [])
                repaired_code, error_methods_dict = ErrorLineDeleter.extract_testmethod_from_testclass(
                    test_code,
                    error_lines,
                    test_method_names=test_method_names
                )
                if self._is_valid_delete_result(test_code, repaired_code, error_methods_dict):
                    result.repaired_code = repaired_code
                    result.success = True
                    result.add_repair_step("delete_test_applied")
                    result.repair_strategy = "delete error test"
                    result.no_effect_reason = ""
                    self.logger.info("delete_test_applied")
                else:
                    result.success = False
                    result.repaired_code = test_code
                    result.repair_strategy = ""
                    result.no_effect_reason = "delete_test_no_match"
                    result.analysis_skipped_reason = "delete_test_no_match"
                    result.add_repair_step("删除分支未命中任何测试方法")
                    self.logger.warning("delete_test_no_match")
                return result

            # 先执行规则化预修复，避免常见编译问题反复空转
            rule_fixed_code, rule_fix_notes = self._apply_rule_based_fixes(test_code, error_info, repair_context)
            if rule_fixed_code.strip() != test_code.strip():
                result.success = True
                result.repaired_code = rule_fixed_code
                result.repair_strategy = "rule-based-precheck"
                result.rule_fix_applied = True
                result.no_effect_reason = ""
                result.analysis_invoked = False
                result.analysis_skipped_reason = "rule_based_precheck"
                for note in rule_fix_notes:
                    result.add_repair_step(note)
                self.logger.info("规则化预修复已生效，跳过本轮LLM修复")
                return result
            
            # 构建修复prompt
            result.analysis_invoked = True
            result.analysis_skipped_reason = ""
            error_analysis = self._analyze_test_error_with_llm(error_info, test_code)
            initial_prompt = self._build_initial_prompt_context(test_prompt, test_code)
            repair_prompt = initial_prompt + "# Test Code Repair Task\n" + self._build_repair_prompt(test_code, error_analysis, categorized_errors)
            
            # 调用大模型进行修复
            repair_prompt = initial_prompt + "# Test Code Repair Task\n" + self._build_repair_prompt(
                test_code,
                error_analysis,
                categorized_errors,
                resolved_mode,
                repair_context
            )
            messages = [
                {
                    "role": "system",
                    "content": self._build_repair_system_prompt(resolved_mode)
                },
                {"role": "user", "content": repair_prompt}
            ]
            
            response = self.llm_client.chat_completion(messages, temperature=temperature)
            
            if response:
                # 提取修复后的代码
                repaired_code = self._extract_code_from_response(response)
                
                if repaired_code and repaired_code.strip():
                    result.success = True
                    result.repaired_code = repaired_code
                    result.repair_strategy = "llm-based"
                    result.llm_fix_applied = True
                    result.no_effect_reason = ""
                    result.add_repair_step("大模型已执行修复")
                    self.logger.info("大模型已执行修复")
                else:
                    result.add_repair_step("大模型未能生成有效的修复代码")
                    result.no_effect_reason = "llm_empty_code"
                    self.logger.warning("大模型响应中未找到有效的代码")
            else:
                result.add_repair_step("大模型调用失败")
                result.no_effect_reason = "llm_call_failed"
                self.logger.error("LLM调用失败")
            
            return result
            
        except Exception as e:
            self.logger.error(f"基于大模型的修复失败: {e}")
            result.add_repair_step(f"修复过程异常: {str(e)}")
            result.no_effect_reason = "exception"
            return result

    def _apply_rule_based_fixes(self, test_code: str, error_info: str,
                                repair_context: Optional[Dict[str, Any]] = None) -> tuple:
        fixed_code = test_code
        notes: List[str] = []
        repair_context = repair_context or {}
        generation_context = repair_context.get("generation_context", {}) or {}

        file_name_match = re.search(r'file named ([A-Za-z_][A-Za-z0-9_]*)\.java', error_info or "")
        if file_name_match:
            expected_class_name = file_name_match.group(1)
            updated = self._rewrite_public_class_name(fixed_code, expected_class_name)
            if updated != fixed_code:
                fixed_code = updated
                notes.append(f"规则修复: public class 名称调整为 {expected_class_name}")

        if not file_name_match and repair_context.get("expected_test_class_name"):
            updated = self._rewrite_public_class_name(fixed_code, repair_context["expected_test_class_name"])
            if updated != fixed_code:
                fixed_code = updated
                notes.append(f"rule fix: aligned public class name to {repair_context['expected_test_class_name']}")

        package_name = repair_context.get("package_name", "")
        if package_name and not re.search(r'^\s*package\s+[A-Za-z0-9_.]+\s*;', fixed_code, re.MULTILINE):
            fixed_code = f"package {package_name};\n\n{fixed_code.lstrip()}"
            notes.append(f"rule fix: inserted package {package_name}")

        required_imports = []
        if "cannot find symbol" in (error_info or "").lower():
            if re.search(r'\bMethod\b', test_code) and "import java.lang.reflect.Method;" not in fixed_code:
                required_imports.append("import java.lang.reflect.Method;")
            if re.search(r'\bField\b', test_code) and "import java.lang.reflect.Field;" not in fixed_code:
                required_imports.append("import java.lang.reflect.Field;")
            if re.search(r'\bConstructor\b', test_code) and "import java.lang.reflect.Constructor;" not in fixed_code:
                required_imports.append("import java.lang.reflect.Constructor;")
            required_imports.extend(self._resolve_missing_symbol_imports(fixed_code, error_info, generation_context))

        if required_imports:
            fixed_code = self._inject_imports(fixed_code, required_imports)
            notes.append(f"规则修复: 自动补充 import {', '.join(required_imports)}")

        fixed_code = self._dedupe_header(fixed_code)
        return fixed_code, notes

    def _is_valid_delete_result(self, original_code: str, repaired_code: str,
                                removed_methods: Dict[int, str]) -> bool:
        if not removed_methods:
            return False
        if not repaired_code or not repaired_code.strip():
            return False
        if repaired_code.strip() == original_code.strip():
            return False
        stripped_code = repaired_code.strip()
        if "class " not in stripped_code:
            return False
        if stripped_code.count("{") != stripped_code.count("}"):
            return False
        return True

    def _rewrite_public_class_name(self, code: str, expected_class_name: str) -> str:
        class_pattern = re.compile(r'(\bpublic\s+class\s+)([A-Za-z_][A-Za-z0-9_]*)')
        return class_pattern.sub(rf'\1{expected_class_name}', code, count=1)

    def _inject_imports(self, code: str, imports: List[str]) -> str:
        lines = code.splitlines()
        if not lines:
            return code

        existing_imports = {line.strip() for line in lines if line.strip().startswith("import ")}
        pending_imports = [imp for imp in imports if imp not in existing_imports]
        if not pending_imports:
            return code

        insert_index = 0
        package_index = -1
        last_import_index = -1
        for i, line in enumerate(lines):
            stripped = line.strip()
            if stripped.startswith("package "):
                package_index = i
            if stripped.startswith("import "):
                last_import_index = i

        if last_import_index >= 0:
            insert_index = last_import_index + 1
        elif package_index >= 0:
            insert_index = package_index + 1
        else:
            insert_index = 0

        prefix = lines[:insert_index]
        suffix = lines[insert_index:]
        if prefix and prefix[-1].strip() != "":
            prefix.append("")
        prefix.extend(pending_imports)
        prefix.append("")
        return "\n".join(prefix + suffix)

    @staticmethod
    def _resolve_repair_mode(repair_mode: Optional[str], error_info: str) -> str:
        if repair_mode:
            return repair_mode
        text = str(error_info or "")
        if any(token in text for token in ["NoSuchMethodException", "IllegalArgumentException", "InvocationTargetException"]):
            return "reflection_repair"
        lowered = text.lower()
        if any(token in lowered for token in ["expected:<", "comparisonfailure", "assertionerror", "expected exception"]):
            return "semantic_test_repair"
        return "compile_repair"

    @staticmethod
    def _build_repair_system_prompt(repair_mode: str) -> str:
        common = (
            "You are a professional Java unit-test repair assistant. "
            "Only change the failing parts of the test. Keep the rest stable. "
            "Do not define placeholder production classes inside the test file."
        )
        if repair_mode == "compile_repair":
            return common + " Focus on imports, signatures, Java version compatibility, and package/class declarations."
        if repair_mode == "reflection_repair":
            return common + " Focus on real constructor and method signatures for reflection-based calls. Do not invent helper APIs or Object.class placeholders."
        return common + " Focus on assertions and expected exceptions. Infer behavior only from provided source context."

    @staticmethod
    def _resolve_missing_symbol_imports(test_code: str, error_info: str,
                                        generation_context: Dict[str, Any]) -> List[str]:
        imports: List[str] = []
        candidate_map = generation_context.get("candidate_type_map", {}) or {}
        existing_imports = {line.strip() for line in test_code.splitlines() if line.strip().startswith("import ")}
        for match in re.finditer(r'symbol:\s+class\s+([A-Za-z_][A-Za-z0-9_]*)', error_info or "", re.IGNORECASE):
            symbol_name = match.group(1)
            candidates = candidate_map.get(symbol_name, [])
            if len(candidates) != 1:
                continue
            fqcn = candidates[0].get("fqcn", "")
            import_stmt = f"import {fqcn};"
            if fqcn and import_stmt not in existing_imports and import_stmt not in imports:
                imports.append(import_stmt)
        return imports

    @staticmethod
    def _dedupe_header(code: str) -> str:
        lines = code.splitlines()
        normalized: List[str] = []
        seen_package = False
        seen_imports: set = set()
        for line in lines:
            stripped = line.strip()
            if stripped.startswith("package "):
                if seen_package:
                    continue
                seen_package = True
            if stripped.startswith("import "):
                if stripped in seen_imports:
                    continue
                seen_imports.add(stripped)
            normalized.append(line)
        return "\n".join(normalized)

    def _build_initial_prompt_context(self, test_prompt: Optional[str], test_code: str) -> str:
        prompt_text = (test_prompt or "").strip()
        if prompt_text:
            if "## 测试导入信息" in prompt_text:
                prompt_text = prompt_text.split("## 测试导入信息")[0]
            if "## Test Import Information" in prompt_text:
                prompt_text = prompt_text.split("## Test Import Information")[0]
            prompt_text = prompt_text.replace("# 测试生成任务", "# Focal Method Under Test")
            prompt_text = prompt_text.replace("# Test Generation Task", "# Focal Method Under Test")
            return prompt_text.rstrip() + "\n"

        return (
            "# Focal Method Under Test\n"
            "## Known Context\n"
            "- Missing initial generation prompt. Use the current test code below as the repair context.\n\n"
            "## Current Test Code\n"
            "```java\n"
            f"{test_code}\n"
            "```\n"
        )

    @staticmethod
    def _truncate_prompt_text(text: str, limit: int) -> str:
        text = re.sub(r"\s+", " ", str(text or "")).strip()
        if len(text) <= limit:
            return text
        return text[: max(0, limit - 3)].rstrip() + "..."

    def _build_anti_repeat_section(self, anti_repeat_summary: Optional[Dict[str, Any]]) -> str:
        anti_repeat_summary = anti_repeat_summary or {}
        recent_attempts = list(anti_repeat_summary.get("recent_attempts", []) or [])[-2:]
        do_not_repeat = list(anti_repeat_summary.get("do_not_repeat", []) or [])[:4]
        repeat_count = int(anti_repeat_summary.get("repeat_count", 0) or 0)
        if not recent_attempts and not do_not_repeat:
            return ""

        lines = ["", "## Anti-Repeat Constraints"]
        if recent_attempts:
            lines.append("Recent failed attempts:")
            for attempt in recent_attempts:
                mode = self._truncate_prompt_text(attempt.get("repair_mode", "") or "unknown", 40)
                category = self._truncate_prompt_text(attempt.get("failure_category", "") or "other", 40)
                signature = self._truncate_prompt_text(attempt.get("failure_signature", ""), 120)
                lines.append(f"- [{mode}] {category}: {signature}")
        if do_not_repeat:
            lines.append("Do not repeat:")
            for item in do_not_repeat:
                lines.append(f"- {self._truncate_prompt_text(item, 140)}")
        if repeat_count > 1:
            lines.append(f"Repeat count: {repeat_count}")
        return "\n".join(lines)
    
    def _build_repair_prompt(self, test_code: str, error_analysis: str,
                           categorized_errors: Dict[str, List[str]],
                           repair_mode: str = "compile_repair",
                           repair_context: Optional[Dict[str, Any]] = None) -> str:
        """
        构建修复prompt
        
        Args:
            test_code: 测试代码
            error_analysis: 错误信息
            categorized_errors: 分类的错误
            
        Returns:
            修复prompt
        """
        repair_context = repair_context or {}
        generation_context = repair_context.get("generation_context", {}) or {}
        anti_repeat_section = self._build_anti_repeat_section(
            repair_context.get("anti_repeat_summary")
        )
        route_requirements = {
            "compile_repair": "Focus on imports, package/class headers, Java version compatibility, and exact signatures. Do not use delete-test to hide compile errors.",
            "semantic_test_repair": "Focus on assertions and expected exceptions. Only infer behavior from the provided focal source and method intention.",
            "reflection_repair": "Focus on real reflection targets. Use only real constructor and method signatures; do not invent helper calls or Object.class placeholders."
        }
        error_summary = "\n".join([
            f"- {error_type}: {len(errors)} error(s)"
            for error_type, errors in categorized_errors.items()
        ])
        
        prompt = f"""
## Error Analysis
```
{error_analysis}
```

## Error Categories
{error_summary}

## Original Test Code
```java
{test_code}
```

## Repair Requirements
1. Carefully analyze the errors above, identify the concrete code issues, and determine the repair strategy.
2. Repair only the broken parts and keep the rest of the code unchanged.
3. Ensure the repaired code can compile and run.
4. Preserve the original test logic and intent.
5. Use standard JUnit 4 syntax and best practices.
6. If repair is impossible, delete the broken test method or replace it with a new one.
7. **Do not define classes inside the test file that have the same names as production classes.**
8. **If compilation fails because a class cannot be found, add the correct import instead of defining that class inside the test file.**
9. **Use real method names from actual classes; do not guess method names from field names.**
10. **Delete any duplicate class definitions from the test file, such as `ImageData`, `User`, `Request`, `Response`, `Verbs`, or `TagInfoData`.**

## Output Format
Return only the repaired full Java code, including the `package` and `import` statements, wrapped inside ```java ... ```.
Do not output explanations or any other text.
"""
        prompt += (
            "\n\n## Routed Constraints\n"
            f"- mode: {repair_mode}\n"
            f"- guidance: {route_requirements.get(repair_mode, route_requirements['compile_repair'])}\n"
            f"- failure type: {repair_context.get('failure_type', '')}\n"
            f"- failure route: {repair_context.get('failure_route', '')}\n"
            "```java\n"
            f"{repair_context.get('focal_method_code', '')}\n"
            "```\n"
            f"Method intention: {repair_context.get('method_intention', '') or 'N/A'}\n"
            f"Java source: {repair_context.get('java_source_level', '')}\n"
            f"Java target: {repair_context.get('java_target_level', '')}\n"
            f"Focal signature: {repair_context.get('focal_signature', '')}\n"
            f"Expected test class: {repair_context.get('expected_test_class_name', '')}\n"
        )
        if anti_repeat_section:
            prompt += anti_repeat_section
        return prompt.strip()
    
    def _extract_code_from_response(self, response: str) -> str:
        """
        从 LLM 响应中提取代码
        
        Args:
            response: LLM响应
            
        Returns:
            提取的代码
        """
        # 查找 Java 代码块
        code_match = re.search(r'```java(.*?)```', response, re.DOTALL)
        if code_match:
            return code_match.group(1).strip()
        
        # 如果没有找到java代码块，尝试查找普代码块
        code_match = re.search(r'```(.*?)```', response, re.DOTALL)
        if code_match:
            return code_match.group(1).strip()
        
        # 如果没有找到代码块，返回整个响应
        return response.strip()

    def _analyze_test_error_with_llm(self, log_content: str, test_code: str = "") -> str:
        """
        使用LLM分析测试错误信息

        Args:
            log_content: 日志内容
            test_code: 可选的测试代码

        Returns:
            解析后的错误信息字典
        """
        # 获取 LLM 提示词
        error_analysis_prompt = self._build_error_analysis_prompt(log_content, test_code)

        # 调用LLM分析
        self.logger.info("正在分析错误原因...")
        response = self.llm_client.chat_completion(
            messages=[
                {"role": "system", "content": "You are a professional Java test-failure analysis expert."},
                {"role": "user", "content": error_analysis_prompt}
            ],
            temperature=0.1
        )

        return response

    @staticmethod
    def _build_error_analysis_prompt(log_content: str, test_code: str = "") -> str:
        """
        构建错误分析的LLM提示

        Args:
            log_content: 日志内容
            test_code: 可选的测试代码

        Returns:
            构建的提示文本
        """
        prompt = """You are a professional Java test-failure analysis expert who diagnoses Maven test failures. Analyze the Maven build and test logs below, together with the related test code if provided, and produce a concise failure summary that helps repair the tests.

# Test Log
{log_content}
""".format(log_content=log_content)

        if test_code:
            prompt += f"""# Related Test Code
{test_code}"""

        prompt += """

# Output Format
Follow the exact format below and do not add extra explanation. Keep the whole response under 30 lines and do not repeat logs or code:
```
Failing test methods:
@test
{test method body}

Error summary:
[error type]: [class name].[method name] line <line>line_number</line> - [brief error description]
[error type]: [class name].[method name] line <line>line_number</line> - [brief error description]
(Important: line numbers must be wrapped in <line>...</line>, for example <line>12</line> or <line>3</line>.)

Root-cause analysis:
[Write a short paragraph that directly explains the failure]

Repair suggestion:
[Write a short paragraph describing how to repair the test. Only test code may be changed; do not recommend changing the focal production method.]
```

"""

        return prompt


# 便捷函数
def repair_test_code(test_code: str,
                    test_prompt: Optional[str] = None,
                    error_info: Optional[str] = None,
                    model_name: Optional[str] = None,
                    delete_test: bool = False,
                    temperature: float = 0.1,
                    repair_mode: Optional[str] = None,
                    repair_context: Optional[Dict[str, Any]] = None) -> RepairResult:
    """
    修复测试代码的便捷函数
    
    Args:
        test_code: 测试代码
        test_prompt: 初始测试生成提示
        error_info: 错误信息
        model_name: 模型名称
        delete_test: 是否删除错误测试
        temperature: 大模型温度设置

    Returns:
        修复结果

    """
    repairer = LLMRepairer(model_name=model_name)
    return repairer.repair(
        test_code,
        test_prompt,
        error_info,
        delete_test,
        temperature,
        repair_mode=repair_mode,
        repair_context=repair_context
    )
