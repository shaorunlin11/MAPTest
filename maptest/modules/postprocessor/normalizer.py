from __future__ import annotations

import re
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

try:
    import javalang
except Exception:
    javalang = None


class PostProcessorV2InputError(ValueError):
    pass


class PostProcessorV2Normalizer:
    REQUIRED_PROJECT_FIELDS = (
        "run_id",
        "run_complete",
        "detailed_results",
    )

    def __init__(self, repos_dir: Path | None = None):
        self.repos_dir = repos_dir

    def normalize_project_results(self, results_data: Dict[str, Any], project_name: str) -> Dict[str, Any]:
        self.validate_project_results(results_data, project_name)

        normalized = dict(results_data)
        source_project_name = self.normalize_text(normalized.get("project_name") or project_name)
        repo_project_name = self.resolve_repo_project_name(source_project_name, fallback_name=project_name)
        batch_id = self.extract_batch_id(project_name)

        normalized["project_name"] = repo_project_name
        normalized["source_project_name"] = source_project_name
        if batch_id:
            normalized["batch_id"] = batch_id

        normalized["detailed_results"] = [
            self.normalize_method_result(
                method_data,
                result_project_name=source_project_name,
                repo_project_name=repo_project_name,
                batch_id=batch_id,
            )
            for method_data in normalized.get("detailed_results", [])
        ]
        return normalized

    def validate_project_results(self, results_data: Dict[str, Any], project_name: str) -> None:
        if not isinstance(results_data, dict):
            raise PostProcessorV2InputError(f"{project_name}: overall_results.json must contain an object")

        missing = [field for field in self.REQUIRED_PROJECT_FIELDS if field not in results_data]
        if missing:
            raise PostProcessorV2InputError(f"{project_name}: missing required fields {missing}")

        if not bool(results_data.get("run_complete", False)):
            raise PostProcessorV2InputError(f"{project_name}: initial generation run is not complete")

        detailed_results = results_data.get("detailed_results")
        if not isinstance(detailed_results, list):
            raise PostProcessorV2InputError(f"{project_name}: detailed_results must be a list")

        run_id = self.normalize_text(results_data.get("run_id"))
        for index, method_data in enumerate(detailed_results):
            if not isinstance(method_data, dict):
                raise PostProcessorV2InputError(f"{project_name}: detailed_results[{index}] must be an object")

            method_run_id = self.normalize_text(method_data.get("run_id"))
            if method_run_id and run_id and method_run_id != run_id:
                raise PostProcessorV2InputError(
                    f"{project_name}: detailed_results[{index}].run_id does not match project run_id"
                )

    def normalize_method_result(
        self,
        method_data: Dict[str, Any],
        result_project_name: str,
        repo_project_name: str,
        batch_id: str = "",
    ) -> Dict[str, Any]:
        normalized = dict(method_data)
        under_test = dict(normalized.get("Under_test_method") or {})

        class_name = self.normalize_text(normalized.get("class_name") or under_test.get("Class_name"))
        method_name = self.normalize_text(normalized.get("method_name") or under_test.get("Method_name"))

        normalized["run_id"] = self.normalize_text(normalized.get("run_id"))
        normalized["class_name"] = class_name
        normalized["method_name"] = method_name
        normalized["method_key"] = self.normalize_text(
            normalized.get("method_key") or self.build_method_key(class_name, method_name)
        )
        normalized["source_project_name"] = self.normalize_text(normalized.get("project_name") or result_project_name)
        normalized["project_name"] = repo_project_name
        normalized["repo_project_name"] = repo_project_name

        if batch_id:
            normalized["batch_id"] = self.normalize_text(normalized.get("batch_id") or batch_id)

        if class_name:
            under_test["Class_name"] = class_name
        if method_name:
            under_test["Method_name"] = method_name
        if under_test:
            normalized["Under_test_method"] = under_test

        normalized["test_code"] = self.normalize_test_code(normalized)
        normalized["focal_method_code"] = self.normalize_focal_method_code(normalized)
        if normalized["focal_method_code"]:
            under_test = dict(normalized.get("Under_test_method") or {})
            under_test["Method_body"] = normalized["focal_method_code"]
            normalized["Under_test_method"] = under_test
        normalized["coverage_status"] = self.normalize_coverage_status(normalized)
        normalized.setdefault("initial_uncovered_lines", list(normalized.get("uncovered_lines") or []))
        normalized.setdefault("initial_line_coverage", normalized.get("line_coverage"))
        normalized.setdefault("initial_branch_coverage", normalized.get("branch_coverage"))
        return normalized

    def normalize_coverage_status(self, method_data: Dict[str, Any]) -> str:
        raw_status = self.normalize_text(method_data.get("coverage_status")).lower()
        known_statuses = {
            "coverage_valid",
            "coverage_handles_only",
            "coverage_unresolved",
            "coverage_unsupported_target_kind",
            "coverage_missing_report",
            "coverage_stale_report",
            "coverage_parse_failed",
        }
        if raw_status in known_statuses:
            return raw_status

        if method_data.get("uncovered_lines"):
            return "coverage_handles_only"
        if method_data.get("line_coverage") is not None or method_data.get("branch_coverage") is not None:
            return "coverage_unresolved"
        return "coverage_unresolved"

    def resolve_repo_project_name(self, raw_name: str, fallback_name: str = "") -> str:
        candidates: List[str] = []
        for name in (raw_name, fallback_name):
            normalized = self.normalize_text(name)
            if normalized and normalized not in candidates:
                candidates.append(normalized)

        for candidate in list(candidates):
            stripped = re.sub(r"_(small|batch|part|split|chunk|shard)\d+$", "", candidate)
            if stripped and stripped not in candidates:
                candidates.append(stripped)

        if self.repos_dir:
            for candidate in candidates:
                if (self.repos_dir / candidate).exists():
                    return candidate

        return candidates[0] if candidates else ""

    @staticmethod
    def extract_batch_id(project_name: str) -> str:
        match = re.search(r"_(small|batch|part|split|chunk|shard)\d+$", PostProcessorV2Normalizer.normalize_text(project_name))
        return match.group(0).lstrip("_") if match else ""

    @staticmethod
    def normalize_text(value: Any) -> str:
        if value is None:
            return ""
        text = str(value).strip()
        if text.lower() in {"false", "none", "null"}:
            return ""
        return text

    def normalize_test_code(self, method_data: Dict[str, Any]) -> str:
        inline_code = (
            method_data.get("optimized_code")
            or method_data.get("test_code")
            or method_data.get("generated_test_code")
            or method_data.get("test_generated")
        )
        if inline_code:
            return str(inline_code)

        for path_key in ("test_file_saved", "original_test_path"):
            test_code = self.read_test_code_file(method_data.get(path_key))
            if test_code:
                return test_code
        return ""

    @staticmethod
    def read_test_code_file(path_value: Any) -> str:
        if not path_value:
            return ""

        path = Path(str(path_value))
        if not path.is_file():
            return ""

        try:
            return path.read_text(encoding="utf-8", errors="ignore")
        except OSError:
            return ""

    def normalize_focal_method_code(self, method_data: Dict[str, Any]) -> str:
        inline_code = (
            method_data.get("focal_method_code")
            or method_data.get("method_body")
            or (method_data.get("Under_test_method") or {}).get("Method_body")
        )
        source_code = self._read_source_code_for_method(method_data)
        if source_code:
            explicit_range = self._explicit_method_range(method_data)
            signature_range = self._resolve_signature_method_range_from_source(
                source_code,
                method_name=self.normalize_text(method_data.get("method_name")),
                class_name=self.normalize_text(method_data.get("class_name")),
                method_data=method_data,
            )
            if signature_range:
                start, end = signature_range
                method_data["focal_method_start_line"] = start
                method_data["focal_method_end_line"] = end
                return self._method_code_from_range(source_code, signature_range)

        if inline_code:
            if not self._explicit_method_range(method_data):
                self._populate_focal_method_range_from_source(method_data)
            return str(inline_code)

        if not source_code:
            return ""

        class_name = self.normalize_text(method_data.get("class_name"))
        method_name = self.normalize_text(method_data.get("method_name"))
        if not class_name or not method_name:
            return ""

        explicit_range = self._explicit_method_range(method_data)
        if explicit_range:
            return self._method_code_from_range(source_code, explicit_range)

        resolved_range = self._resolve_method_range_from_source(
            source_code,
            method_name=method_name,
            class_name=class_name,
            method_data=method_data,
        )
        if not resolved_range:
            return ""

        start, end = resolved_range
        method_data["focal_method_start_line"] = start
        method_data["focal_method_end_line"] = end
        return self._method_code_from_range(source_code, resolved_range)

    def _read_source_code_for_method(self, method_data: Dict[str, Any]) -> str:
        source_path = self._source_path_for_method(method_data)
        if not source_path or not source_path.is_file():
            return ""
        try:
            return source_path.read_text(encoding="utf-8", errors="ignore")
        except OSError:
            return ""

    def _source_path_for_method(self, method_data: Dict[str, Any]) -> Optional[Path]:
        if not self.repos_dir:
            return None
        class_name = self.normalize_text(method_data.get("class_name"))
        if not class_name:
            return None
        package_name = self.normalize_text(method_data.get("package_name"))
        package_path = Path(*package_name.split(".")) if package_name else Path()
        source_root = self.repos_dir / self.normalize_text(method_data.get("repo_project_name")) / "src" / "main" / "java"
        source_path = source_root / package_path / f"{class_name}.java"
        if source_path.is_file() or package_name:
            return source_path
        matches = list(source_root.rglob(f"{class_name}.java")) if source_root.is_dir() else []
        return matches[0] if matches else source_path

    @staticmethod
    def _method_code_from_range(source_code: str, method_range: Tuple[int, int]) -> str:
        start, end = method_range
        lines = source_code.splitlines()
        return "\n".join(lines[start - 1:end]).strip()

    def _populate_focal_method_range_from_source(self, method_data: Dict[str, Any]) -> Optional[Tuple[int, int]]:
        source_code = self._read_source_code_for_method(method_data)
        if not source_code:
            return None

        class_name = self.normalize_text(method_data.get("class_name"))
        method_name = self.normalize_text(method_data.get("method_name"))
        if not class_name or not method_name:
            return None

        resolved_range = self._resolve_method_range_from_source(
            source_code,
            method_name=method_name,
            class_name=class_name,
            method_data=method_data,
        )
        if not resolved_range:
            return None

        start, end = resolved_range
        method_data["focal_method_start_line"] = start
        method_data["focal_method_end_line"] = end
        return resolved_range

    @classmethod
    def _resolve_signature_method_range_from_source(
        cls,
        source_code: str,
        method_name: str,
        class_name: str,
        method_data: Dict[str, Any],
    ) -> Optional[Tuple[int, int]]:
        if not cls.normalize_text(method_data.get("focal_signature")):
            return None
        candidates = cls._javalang_method_start_lines(source_code, method_name, class_name)
        if not candidates:
            candidates = cls._regex_method_start_lines(source_code, method_name, class_name)

        scored = []
        for start_line in candidates:
            method_range = cls._slice_java_block_with_range(source_code, start_line)
            if method_range is None:
                continue
            scored.append(
                (
                    cls._signature_match_score(source_code, method_range, method_data),
                    -(method_range[1] - method_range[0]),
                    -method_range[0],
                    method_range,
                )
            )
        if not scored:
            return None
        scored.sort(reverse=True)
        return scored[0][3] if scored[0][0] > 0 else None

    @classmethod
    def _resolve_method_range_from_source(
        cls,
        source_code: str,
        method_name: str,
        class_name: str,
        method_data: Dict[str, Any],
    ) -> Optional[Tuple[int, int]]:
        candidates = cls._javalang_method_start_lines(source_code, method_name, class_name)
        if not candidates:
            candidates = cls._regex_method_start_lines(source_code, method_name, class_name)

        ranges = [
            method_range
            for start_line in candidates
            for method_range in (cls._slice_java_block_with_range(source_code, start_line),)
            if method_range is not None
        ]
        return cls._select_method_range(source_code, ranges, method_data)

    @staticmethod
    def _explicit_method_range(method_data: Dict[str, Any]) -> Optional[Tuple[int, int]]:
        for start_key, end_key in (
            ("focal_method_start_line", "focal_method_end_line"),
            ("method_start_line", "method_end_line"),
            ("start_line", "end_line"),
        ):
            try:
                start = int(method_data.get(start_key))
                end = int(method_data.get(end_key))
            except (TypeError, ValueError):
                continue
            if start > 0 and end >= start:
                return start, end
        return None

    @staticmethod
    def _javalang_method_start_lines(source_code: str, method_name: str, class_name: str) -> List[int]:
        if javalang is None:
            return []
        try:
            tree = javalang.parse.parse(source_code)
        except Exception:
            return []

        starts: List[int] = []
        for _, node in tree.filter(javalang.tree.MethodDeclaration):
            if node.name == method_name and node.position:
                starts.append(int(node.position.line))
        for _, node in tree.filter(javalang.tree.ConstructorDeclaration):
            if method_name == class_name and node.name == class_name and node.position:
                starts.append(int(node.position.line))
        return starts

    @staticmethod
    def _regex_method_start_lines(source_code: str, method_name: str, class_name: str) -> List[int]:
        starts: List[int] = []
        method_pattern = re.compile(rf"\b{re.escape(method_name)}\s*\(")
        constructor_pattern = re.compile(rf"\b{re.escape(class_name)}\s*\(")
        for index, line in enumerate(source_code.splitlines(), 1):
            if method_pattern.search(line) or (method_name == class_name and constructor_pattern.search(line)):
                starts.append(index)
        return starts

    @classmethod
    def _select_method_range(
        cls,
        source_code: str,
        ranges: List[Tuple[int, int]],
        method_data: Dict[str, Any],
    ) -> Optional[Tuple[int, int]]:
        if not ranges:
            return None
        if len(ranges) == 1:
            return ranges[0]

        signature_scores = [
            (
                cls._signature_match_score(source_code, method_range, method_data),
                -(method_range[1] - method_range[0]),
                -method_range[0],
                method_range,
            )
            for method_range in ranges
        ]
        signature_scores.sort(reverse=True)
        if signature_scores[0][0] > 0:
            return signature_scores[0][3]

        target_lines = cls._target_lines(method_data)
        if target_lines:
            scored = []
            for start, end in ranges:
                overlap = sum(1 for line in target_lines if start <= line <= end)
                scored.append((overlap, -(end - start), -start, (start, end)))
            scored.sort(reverse=True)
            if scored[0][0] > 0:
                return scored[0][3]
        return ranges[0]

    @classmethod
    def _signature_match_score(
        cls,
        source_code: str,
        method_range: Tuple[int, int],
        method_data: Dict[str, Any],
    ) -> int:
        expected_signature = cls.normalize_text(method_data.get("focal_signature"))
        if not expected_signature:
            return 0

        candidate_header = cls._method_header_text(source_code, method_range)
        if not candidate_header:
            return 0

        expected_params = cls._signature_param_types(expected_signature)
        candidate_params = cls._signature_param_types(candidate_header)
        if (
            cls._signature_has_parameter_list(expected_signature)
            and cls._signature_has_parameter_list(candidate_header)
            and expected_params == candidate_params
        ):
            return 100

        expected_simple = [cls._simple_type_name(item) for item in expected_params]
        candidate_simple = [cls._simple_type_name(item) for item in candidate_params]
        if expected_simple and expected_simple == candidate_simple:
            return 80
        return 0

    @staticmethod
    def _method_header_text(source_code: str, method_range: Tuple[int, int]) -> str:
        start, end = method_range
        lines = source_code.splitlines()
        header_lines: List[str] = []
        for line in lines[max(start - 1, 0):min(end, start + 20)]:
            header_lines.append(line.strip())
            if "{" in line:
                break
        return " ".join(header_lines)

    @classmethod
    def _signature_param_types(cls, signature: str) -> List[str]:
        text = cls._strip_annotations(str(signature or ""))
        open_index = text.find("(")
        close_index = text.rfind(")")
        if open_index < 0 or close_index < open_index:
            return []
        params_text = text[open_index + 1:close_index].strip()
        if not params_text:
            return []
        return [
            normalized
            for param in cls._split_params(params_text)
            for normalized in (cls._normalize_param_type(param),)
            if normalized
        ]

    @classmethod
    def _signature_has_parameter_list(cls, signature: str) -> bool:
        text = cls._strip_annotations(str(signature or ""))
        return text.find("(") >= 0 and text.rfind(")") > text.find("(")

    @staticmethod
    def _strip_annotations(text: str) -> str:
        return re.sub(r"@\w+(?:\([^)]*\))?\s*", "", str(text or "")).strip()

    @staticmethod
    def _split_params(params_text: str) -> List[str]:
        params: List[str] = []
        depth = 0
        current: List[str] = []
        for char in params_text:
            if char == "<":
                depth += 1
            elif char == ">" and depth > 0:
                depth -= 1
            elif char == "," and depth == 0:
                params.append("".join(current).strip())
                current = []
                continue
            current.append(char)
        if current:
            params.append("".join(current).strip())
        return params

    @staticmethod
    def _normalize_param_type(param: str) -> str:
        text = re.sub(r"@\w+(?:\([^)]*\))?\s*", "", str(param or "")).strip()
        text = re.sub(r"\bfinal\b\s*", "", text).strip()
        text = text.replace("...", "[]")
        parts = text.split()
        if len(parts) > 1:
            text = " ".join(parts[:-1])
        return re.sub(r"\s+", "", text)

    @staticmethod
    def _simple_type_name(type_name: str) -> str:
        text = re.sub(r"<.*>", "", str(type_name or ""))
        array_suffix = "[]" if text.endswith("[]") else ""
        text = text[:-2] if array_suffix else text
        return text.split(".")[-1] + array_suffix

    @staticmethod
    def _target_lines(method_data: Dict[str, Any]) -> List[int]:
        collected: List[int] = []
        for detail in method_data.get("uncovered_line_details") or []:
            if not isinstance(detail, dict):
                continue
            try:
                collected.append(int(detail.get("line")))
            except (TypeError, ValueError):
                continue
        for line in method_data.get("uncovered_lines") or []:
            try:
                collected.append(int(line))
            except (TypeError, ValueError):
                continue
        return sorted({line for line in collected if line > 0})

    @staticmethod
    def _slice_java_block_with_range(source_code: str, start_line: int) -> Optional[Tuple[int, int]]:
        lines = source_code.splitlines()
        start_index = max(start_line - 1, 0)

        while start_index > 0 and lines[start_index - 1].lstrip().startswith("@"):
            start_index -= 1

        brace_started = False
        brace_balance = 0
        for end_index in range(start_index, len(lines)):
            line = lines[end_index]
            if not brace_started and ";" in line and "{" not in line:
                return start_index + 1, end_index + 1
            brace_balance += line.count("{")
            brace_balance -= line.count("}")
            if "{" in line:
                brace_started = True
            if brace_started and brace_balance == 0:
                return start_index + 1, end_index + 1
        return None

    @staticmethod
    def build_method_key(class_name: str, method_name: str) -> str:
        if class_name and method_name:
            return f"{class_name}#{method_name}"
        return class_name or method_name or "unknown_method"

