from __future__ import annotations

import datetime
import json
import re
import shutil
from pathlib import Path
from typing import Any, Dict, Iterable, List

from .models import MethodOutcome


class PostProcessorV2OutputWriter:
    def __init__(self, output_root: Path | None = None):
        package_root = Path(__file__).resolve().parents[2]
        self.output_root = output_root or package_root / "experiment_results" / "post-process-v2"

    def reset_project_output(self, project_name: str) -> Path:
        if not str(project_name or "").strip():
            raise ValueError("project_name is required before resetting postprocessor output")
        project_output_dir = self.output_root / project_name
        output_root = self.output_root.resolve()
        target_dir = project_output_dir.resolve()
        if target_dir == output_root or output_root not in target_dir.parents:
            raise ValueError(f"refusing to reset output path outside output root: {project_output_dir}")
        if project_output_dir.exists():
            shutil.rmtree(project_output_dir)
        project_output_dir.mkdir(parents=True, exist_ok=True)
        return project_output_dir

    def write_project_results(self, project_name: str, outcomes: Iterable[MethodOutcome]) -> Path:
        project_output_dir = self.output_root / project_name
        project_output_dir.mkdir(parents=True, exist_ok=True)
        generated_test_dir = project_output_dir / "GeneratedTest"
        trace_dir = project_output_dir / "AgentTraces"

        outcome_list = list(outcomes)
        test_file_by_method = self.write_test_files(generated_test_dir, outcome_list)
        trace_file_by_method = self.write_agent_trace_files(trace_dir, outcome_list)
        payload = self.build_project_payload(project_name, outcome_list, test_file_by_method, trace_file_by_method)
        result_file = project_output_dir / "optimized_results.json"
        result_file.write_text(
            json.dumps(payload, ensure_ascii=False, indent=2),
            encoding="utf-8",
        )
        return result_file

    def write_test_files(self, generated_test_dir: Path, outcomes: List[MethodOutcome]) -> Dict[str, str]:
        generated_test_dir.mkdir(parents=True, exist_ok=True)
        for stale_test_file in generated_test_dir.glob("*.java"):
            stale_test_file.unlink()

        test_file_by_method: Dict[str, str] = {}
        used_class_names: set[str] = set()
        for index, outcome in enumerate(outcomes, 1):
            test_code = str(outcome.baseline.test_code or "").strip()
            if not test_code:
                continue
            class_name = self.extract_test_class_name(test_code) or self.default_test_class_name(outcome)
            safe_class_name = self.safe_java_class_name(class_name) or f"Generated{index}Test"
            final_class_name = self.unique_test_class_name(safe_class_name, used_class_names, index)
            test_code = self.rewrite_test_class_name(test_code, class_name, final_class_name)
            test_file = generated_test_dir / f"{final_class_name}.java"
            test_file.write_text(test_code + "\n", encoding="utf-8")
            test_file_by_method[self.outcome_artifact_key(outcome)] = str(test_file.relative_to(self.output_root))
        return test_file_by_method

    def write_agent_trace_files(self, trace_dir: Path, outcomes: List[MethodOutcome]) -> Dict[str, str]:
        trace_file_by_method: Dict[str, str] = {}
        used_names: set[str] = set()
        for index, outcome in enumerate(outcomes, 1):
            if not outcome.trace_events:
                continue
            trace_dir.mkdir(parents=True, exist_ok=True)
            base_name = self.safe_artifact_name(outcome.context.method_key or f"method_{index}")
            file_name = self.unique_file_name(f"{base_name}_agent_trace.json", used_names, index)
            trace_file = trace_dir / file_name
            raw_result = outcome.context.raw_result or {}
            trace_file.write_text(
                json.dumps(
                    {
                        "project_name": outcome.context.project_name,
                        "method_key": outcome.context.method_key,
                        "method_variant_key": raw_result.get("method_variant_key", ""),
                        "focal_signature": raw_result.get("focal_signature", ""),
                        "focal_method_start_line": raw_result.get("focal_method_start_line"),
                        "focal_method_end_line": raw_result.get("focal_method_end_line"),
                        "events": [self.readable_trace_event(event) for event in outcome.trace_events],
                    },
                    ensure_ascii=False,
                    indent=2,
                ),
                encoding="utf-8",
            )
            trace_file_by_method[self.outcome_artifact_key(outcome)] = str(trace_file.relative_to(self.output_root))
        return trace_file_by_method

    @classmethod
    def readable_trace_event(cls, event: Dict[str, Any]) -> Dict[str, Any]:
        readable = dict(event)
        for key in ("prompt", "raw_output", "validation_output"):
            if key in readable:
                readable[key] = cls.trace_text_lines(readable.get(key))
        return readable

    @staticmethod
    def trace_text_lines(value: Any) -> List[str]:
        text = str(value or "")
        if not text:
            return []
        return text.splitlines()

    def build_project_payload(
        self,
        project_name: str,
        outcomes: List[MethodOutcome],
        test_file_by_method: Dict[str, str] | None = None,
        trace_file_by_method: Dict[str, str] | None = None,
    ) -> Dict[str, Any]:
        test_file_by_method = test_file_by_method or {}
        trace_file_by_method = trace_file_by_method or {}
        return {
            "project_name": project_name,
            "generated_at": datetime.datetime.now().isoformat(),
            "summary": {
                "total_methods": len(outcomes),
                "successful_methods": sum(1 for outcome in outcomes if outcome.success),
            },
            "detailed_results": [
                self.outcome_to_dict(
                    outcome,
                    test_file_by_method.get(self.outcome_artifact_key(outcome), ""),
                    trace_file_by_method.get(self.outcome_artifact_key(outcome), ""),
                )
                for outcome in outcomes
            ],
        }

    def outcome_to_dict(self, outcome: MethodOutcome, test_file: str = "", agent_trace_file: str = "") -> Dict[str, Any]:
        payload = outcome.to_dict()
        payload["test_file"] = test_file
        payload["agent_trace_file"] = agent_trace_file
        payload["target_line_mapping"] = self.line_mapping(outcome)
        return payload

    @staticmethod
    def outcome_artifact_key(outcome: MethodOutcome) -> str:
        raw_result = outcome.context.raw_result or {}
        package_match = re.search(r"^\s*package\s+([\w.]+)\s*;", outcome.baseline.test_code or "", re.MULTILINE)
        package_name = str(raw_result.get("package_name") or (package_match.group(1) if package_match else ""))
        variant_key = str(raw_result.get("method_variant_key") or "").strip()
        if variant_key:
            return f"{package_name}::{variant_key}"
        signature = str(raw_result.get("focal_signature") or "").strip()
        if signature:
            return f"{package_name}::{outcome.context.method_key}::{signature}"
        return f"{package_name}::{outcome.context.method_key}"

    @staticmethod
    def extract_test_class_name(test_code: str) -> str:
        class_match = re.search(r"\bpublic\s+class\s+([A-Za-z_][A-Za-z0-9_]*)\b", test_code)
        if not class_match:
            class_match = re.search(r"\bclass\s+([A-Za-z_][A-Za-z0-9_]*)\b", test_code)
        return class_match.group(1) if class_match else ""

    @staticmethod
    def default_test_class_name(outcome: MethodOutcome) -> str:
        class_name = outcome.context.class_name or "Generated"
        method_name = outcome.context.method_name or "Baseline"
        return f"{class_name}{method_name[:1].upper()}{method_name[1:]}Test"

    @classmethod
    def unique_test_class_name(cls, class_name: str, used_class_names: set[str], index: int) -> str:
        safe_name = cls.ensure_surefire_test_name(cls.safe_java_class_name(class_name) or f"Generated{index}Test")
        candidate = safe_name
        while candidate in used_class_names:
            candidate = cls.add_test_name_suffix(safe_name, str(index))
            index += 1
        used_class_names.add(candidate)
        return candidate

    @staticmethod
    def ensure_surefire_test_name(class_name: str) -> str:
        if class_name.startswith("Test") or class_name.endswith("Test") or class_name.endswith("TestCase"):
            return class_name
        match = re.match(r"^(.+)Test(_[A-Za-z0-9]+)$", class_name)
        if match:
            return f"{match.group(1)}{match.group(2)}Test"
        return f"{class_name}Test"

    @staticmethod
    def add_test_name_suffix(class_name: str, suffix: str) -> str:
        safe_suffix = re.sub(r"\W+", "_", str(suffix or "")).strip("_") or "1"
        if class_name.endswith("TestCase"):
            return f"{class_name[:-8]}_{safe_suffix}TestCase"
        if class_name.endswith("Test"):
            return f"{class_name[:-4]}_{safe_suffix}Test"
        return f"{class_name}_{safe_suffix}Test"

    @staticmethod
    def safe_java_class_name(value: str) -> str:
        safe = re.sub(r"\W+", "_", str(value or "")).strip("_")
        if not safe:
            return ""
        if not re.match(r"[A-Za-z_]", safe):
            safe = f"Generated{safe}"
        return safe

    @staticmethod
    def rewrite_test_class_name(test_code: str, old_class_name: str, new_class_name: str) -> str:
        if not old_class_name or not new_class_name or old_class_name == new_class_name:
            return test_code
        pattern = rf"\b(public\s+class\s+){re.escape(old_class_name)}\b"
        rewritten, count = re.subn(pattern, rf"\g<1>{new_class_name}", test_code, count=1)
        if count:
            return rewritten
        pattern = rf"\b(class\s+){re.escape(old_class_name)}\b"
        return re.sub(pattern, rf"\g<1>{new_class_name}", test_code, count=1)

    @staticmethod
    def unique_file_name(file_name: str, used_names: set[str], index: int) -> str:
        safe_name = PostProcessorV2OutputWriter.safe_artifact_name(file_name) or f"GeneratedTest{index}.java"
        if safe_name not in used_names:
            used_names.add(safe_name)
            return safe_name
        suffix = Path(safe_name).suffix
        stem = safe_name[: -len(suffix)] if suffix else safe_name
        candidate = f"{stem}_{index}{suffix}"
        while candidate in used_names:
            index += 1
            candidate = f"{stem}_{index}{suffix}"
        used_names.add(candidate)
        return candidate

    @staticmethod
    def safe_artifact_name(value: str) -> str:
        return re.sub(r"[^A-Za-z0-9_.-]+", "_", str(value or "")).strip("._")

    @classmethod
    def line_mapping(cls, outcome: MethodOutcome) -> List[Dict[str, Any]]:
        coverage = outcome.baseline.coverage
        source_lines = tuple(coverage.uncovered_lines or ())
        raw_result = outcome.context.raw_result or {}
        start_line = cls.focal_start_line(raw_result)
        code_by_relative_line = {
            index: line.rstrip()
            for index, line in enumerate(cls.focal_method_code(raw_result).splitlines(), 1)
        }
        mapping = []
        for source_line in source_lines:
            relative_line = source_line - start_line + 1 if start_line and source_line >= start_line else source_line
            mapping.append(
                {
                    "source_line": source_line,
                    "focal_relative_line": relative_line,
                    "focal_method_start_line": start_line or None,
                    "code": code_by_relative_line.get(relative_line, ""),
                }
            )
        return mapping

    @staticmethod
    def focal_start_line(raw_result: Dict[str, Any]) -> int:
        try:
            return int(raw_result.get("focal_method_start_line") or 0)
        except (TypeError, ValueError):
            return 0

    @staticmethod
    def focal_method_code(raw_result: Dict[str, Any]) -> str:
        under_test = raw_result.get("Under_test_method") or {}
        return str(
            raw_result.get("focal_method_code")
            or raw_result.get("method_body")
            or under_test.get("Method_body")
            or ""
        ).strip()
