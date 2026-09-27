from pathlib import Path
from typing import Any, Dict, List, Optional
import re

import javalang

from maptest.core import FileUtils


class PostProcessorFocalMethodResolver:
    def __init__(self, processor: Any):
        self.processor = processor

    def resolve_focal_method_code(self, method_data: Dict, project_name: str) -> str:
        candidates = [
            method_data.get("focal_method_code"),
            method_data.get("method_body"),
            (method_data.get("Under_test_method") or {}).get("Method_body"),
        ]
        for candidate in candidates:
            normalized = self._normalize_text_value(candidate)
            if normalized:
                return normalized
        method_code, _ = self.load_focal_method_source(project_name, method_data)
        return method_code

    def resolve_focal_method_range(self, method_data: Dict, project_name: str):
        _, method_range = self.load_focal_method_source(project_name, method_data)
        if method_range and self._normalize_text_value(method_data.get("focal_signature")):
            return method_range

        explicit_range = self._resolve_explicit_method_range(method_data)
        if explicit_range:
            return explicit_range

        return method_range

    def load_focal_method_source(self, project_name: str, method_data: Dict):
        package_name = method_data.get("package_name", "")
        class_name = method_data.get("class_name", "")
        method_name = method_data.get("method_name", "")

        if not class_name or not method_name:
            return "", None

        package_path = Path(*package_name.split(".")) if package_name else Path()
        search_roots = [
            self.processor.repos_dir / project_name / "src" / "main" / "java",
            self.processor.repos_dir / project_name / "src" / "java",
            self.processor.repos_dir / project_name / "src",
        ]

        for root in search_roots:
            source_files = [root / package_path / f"{class_name}.java"]
            if not package_name and root.is_dir():
                source_files.extend(root.rglob(f"{class_name}.java"))
            for source_file in source_files:
                if not source_file.exists():
                    continue
                method_code, method_range = self.extract_method_source_from_file(
                    source_file,
                    method_name,
                    class_name,
                    method_data=method_data,
                )
                if method_code:
                    return method_code, method_range
        return "", None

    def extract_method_source_from_file(
        self,
        source_file: Path,
        method_name: str,
        class_name: str,
        method_data: Optional[Dict] = None,
    ):
        try:
            source_code = FileUtils.read_file(source_file)
            tree = javalang.parse.parse(source_code)
            candidates: List[Dict[str, Any]] = []

            for _, node in tree.filter(javalang.tree.MethodDeclaration):
                if node.name != method_name or not node.position:
                    continue
                method_code, method_range = self._slice_java_block_with_range(source_code, node.position.line)
                candidates.append({"code": method_code, "range": method_range, "kind": "method"})
            for _, node in tree.filter(javalang.tree.ConstructorDeclaration):
                if method_name != class_name or node.name != class_name or not node.position:
                    continue
                method_code, method_range = self._slice_java_block_with_range(source_code, node.position.line)
                candidates.append({"code": method_code, "range": method_range, "kind": "constructor"})
            selected = self._select_best_candidate(candidates, method_data or {})
            if selected:
                return selected["code"], selected["range"]
        except Exception as exc:
            logger = getattr(self.processor, "logger", None)
            if logger is not None:
                logger.warning(f"failed to extract focal method source {source_file}: {exc}")
        return "", None

    def _select_best_candidate(self, candidates: List[Dict[str, Any]], method_data: Dict):
        if not candidates:
            return None
        if len(candidates) == 1:
            return candidates[0]

        signature = self._normalize_text_value(method_data.get("focal_signature"))
        if signature:
            scored_candidates = []
            for candidate in candidates:
                score = self._signature_match_score(candidate.get("code", ""), signature)
                line_range = candidate.get("range") or (0, 0)
                scored_candidates.append((score, -(line_range[1] - line_range[0]), -line_range[0], candidate))
            scored_candidates.sort(reverse=True)
            if scored_candidates and scored_candidates[0][0] > 0:
                return scored_candidates[0][3]

        explicit_range = self._resolve_explicit_method_range(method_data)
        if explicit_range:
            for candidate in candidates:
                if candidate.get("range") == explicit_range:
                    return candidate

        target_lines = self._collect_target_lines(method_data)
        if target_lines:
            scored_candidates = []
            for candidate in candidates:
                line_range = candidate.get("range") or ()
                if len(line_range) != 2:
                    continue
                start_line, end_line = line_range
                overlap_count = sum(1 for line in target_lines if start_line <= line <= end_line)
                scored_candidates.append((overlap_count, -(end_line - start_line), -start_line, candidate))

            if scored_candidates:
                scored_candidates.sort(reverse=True)
                if scored_candidates[0][0] > 0:
                    return scored_candidates[0][3]

        return candidates[0]

    def _signature_match_score(self, candidate_code: str, expected_signature: str) -> int:
        candidate_header = self._method_header_text(candidate_code)
        if not candidate_header:
            return 0

        expected_params = self._signature_param_types(expected_signature)
        candidate_params = self._signature_param_types(candidate_header)
        if (
            self._signature_has_parameter_list(expected_signature)
            and self._signature_has_parameter_list(candidate_header)
            and expected_params == candidate_params
        ):
            return 100

        expected_simple = [self._simple_type_name(param) for param in expected_params]
        candidate_simple = [self._simple_type_name(param) for param in candidate_params]
        if expected_simple and expected_simple == candidate_simple:
            return 80
        return 0

    @staticmethod
    def _method_header_text(candidate_code: str) -> str:
        header_lines: List[str] = []
        for line in str(candidate_code or "").splitlines()[:20]:
            header_lines.append(line.strip())
            if "{" in line or ";" in line:
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

    def _collect_target_lines(self, method_data: Dict) -> List[int]:
        collected = []
        for detail in method_data.get("uncovered_line_details") or []:
            parsed = self._coerce_positive_int((detail or {}).get("line"))
            if parsed is not None:
                collected.append(parsed)
        for line in method_data.get("uncovered_lines") or []:
            parsed = self._coerce_positive_int(line)
            if parsed is not None:
                collected.append(parsed)
        return sorted(set(collected))

    def _resolve_explicit_method_range(self, method_data: Dict):
        candidate_pairs = [
            ("focal_method_start_line", "focal_method_end_line"),
            ("method_start_line", "method_end_line"),
            ("start_line", "end_line"),
        ]

        for start_key, end_key in candidate_pairs:
            start_value = self._coerce_positive_int(method_data.get(start_key))
            end_value = self._coerce_positive_int(method_data.get(end_key))
            if start_value is None or end_value is None:
                continue
            if end_value < start_value:
                continue
            return start_value, end_value
        return None

    def _normalize_text_value(self, value: Any) -> str:
        normalizer = getattr(self.processor, "_normalize_text_value", None)
        if callable(normalizer):
            return str(normalizer(value) or "").strip()
        if value is None:
            return ""
        text = str(value).strip()
        if text.lower() in {"false", "none", "null"}:
            return ""
        return text

    @staticmethod
    def _coerce_positive_int(value):
        try:
            parsed = int(value)
        except (TypeError, ValueError):
            return None
        return parsed if parsed > 0 else None

    def _slice_java_block_with_range(self, source_code: str, start_line: int):
        lines = source_code.splitlines()
        start_index = max(start_line - 1, 0)

        while start_index > 0 and lines[start_index - 1].lstrip().startswith("@"):
            start_index -= 1

        brace_started = False
        brace_balance = 0
        for end_index in range(start_index, len(lines)):
            line = lines[end_index]
            brace_balance += line.count("{")
            brace_balance -= line.count("}")
            if "{" in line:
                brace_started = True
            if brace_started and brace_balance == 0:
                return "\n".join(lines[start_index:end_index + 1]).strip(), (start_index + 1, end_index + 1)

        return "\n".join(lines[start_index:]).strip(), (start_index + 1, len(lines))
