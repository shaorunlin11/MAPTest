from __future__ import annotations

import datetime
import re
from pathlib import Path
from typing import Any, Callable, Optional
from typing import List

from maptest.core import MavenExecutor

from .models import Baseline, CoverageState, MethodContext, PatchCandidate, TargetGroup, normalize_lines


class PostProcessorV2CoverageService:
    def __init__(
        self,
        target_line_coverage: float = 100.0,
        target_group_size: int = 3,
        repos_dir: Optional[Path] = None,
        maven_timeout_seconds: int = 120,
        maven_executor_factory: Optional[Callable[..., Any]] = None,
    ):
        self.target_line_coverage = target_line_coverage
        self.target_group_size = max(1, int(target_group_size))
        package_root = Path(__file__).resolve().parents[2]
        workspace_root = package_root.parent
        self.repos_dir = repos_dir or workspace_root / "Repos"
        self.maven_timeout_seconds = int(maven_timeout_seconds)
        self.maven_executor_factory = maven_executor_factory or MavenExecutor

    def refresh_coverage(
        self,
        baseline: Baseline | PatchCandidate,
        context: Optional[MethodContext] = None,
    ) -> CoverageState:
        if isinstance(baseline, Baseline) and baseline.source == "zero_coverage_template":
            coverage = baseline.coverage or CoverageState()
            if not coverage.uncovered_lines and context is not None:
                fallback_lines = self._fallback_uncovered_lines_from_focal_method(context)
                if fallback_lines:
                    metadata = dict(coverage.metadata)
                    metadata["coverage_refresh_after_zero_template"] = "fallback_focal_method_lines"
                    metadata["coverage_error_hint"] = (
                        "zero-coverage template had no uncovered_lines; "
                        "using executable-looking focal method lines as enhancement targets; "
                        "JaCoCo collection is skipped because the template does not call the focal method"
                    )
                    return CoverageState(
                        line_coverage=0.0,
                        branch_coverage=coverage.branch_coverage,
                        uncovered_lines=fallback_lines,
                        target_line_coverage=self.target_line_coverage,
                        status="coverage_handles_only",
                        metadata=metadata,
                    )

            metadata = dict(coverage.metadata)
            metadata["coverage_refresh_skipped"] = "zero_coverage_template"
            return CoverageState(
                line_coverage=coverage.line_coverage,
                branch_coverage=coverage.branch_coverage,
                uncovered_lines=coverage.uncovered_lines,
                target_line_coverage=self.target_line_coverage,
                status=coverage.status,
                metadata=metadata,
            )

        if isinstance(baseline, Baseline) and not baseline.runnable:
            coverage = baseline.coverage or CoverageState()
            metadata = dict(coverage.metadata)
            metadata["coverage_refresh_skipped"] = "baseline_not_runnable"
            metadata["coverage_error_hint"] = (
                "coverage was not refreshed because the baseline test is not runnable"
            )
            return CoverageState(
                line_coverage=coverage.line_coverage,
                branch_coverage=coverage.branch_coverage,
                uncovered_lines=coverage.uncovered_lines,
                target_line_coverage=self.target_line_coverage,
                status=coverage.status,
                metadata=metadata,
            )

        if context is not None:
            refreshed = self.collect_focal_method_coverage(context)
            if refreshed is not None:
                preserved = self._preserve_previous_targets_if_refresh_unusable(baseline, refreshed)
                if preserved is not None:
                    return preserved
                return refreshed

        coverage = baseline.coverage or CoverageState()
        return CoverageState(
            line_coverage=coverage.line_coverage,
            branch_coverage=coverage.branch_coverage,
            uncovered_lines=coverage.uncovered_lines,
            target_line_coverage=self.target_line_coverage,
            status=coverage.status,
            metadata=dict(coverage.metadata),
        )

    def _preserve_previous_targets_if_refresh_unusable(
        self,
        baseline: Baseline | PatchCandidate,
        refreshed: CoverageState,
    ) -> Optional[CoverageState]:
        if not isinstance(baseline, Baseline):
            return None
        previous = baseline.coverage or CoverageState()
        if not previous.uncovered_lines or refreshed.uncovered_lines:
            return None
        if refreshed.status not in {
            "coverage_missing_report",
            "coverage_stale_report",
            "coverage_unresolved",
            "coverage_parse_failed",
        }:
            return None

        metadata = dict(previous.metadata)
        metadata["coverage_refresh_preserved_previous_targets"] = refreshed.status
        metadata["coverage_refresh_discarded_unusable_status"] = refreshed.status
        metadata["coverage_refresh_discarded_line_coverage"] = refreshed.line_coverage
        metadata["coverage_refresh_discarded_branch_coverage"] = refreshed.branch_coverage
        refreshed_hint = str((refreshed.metadata or {}).get("coverage_error_hint") or "")
        if refreshed_hint:
            metadata["coverage_error_hint"] = refreshed_hint
        return CoverageState(
            line_coverage=previous.line_coverage,
            branch_coverage=previous.branch_coverage,
            uncovered_lines=previous.uncovered_lines,
            target_line_coverage=self.target_line_coverage,
            status=previous.status,
            metadata=metadata,
        )

    def build_target_groups(self, coverage: CoverageState) -> List[TargetGroup]:
        lines = list(coverage.uncovered_lines)
        groups: List[TargetGroup] = []
        for index in range(0, len(lines), self.target_group_size):
            groups.append(TargetGroup(lines=tuple(lines[index:index + self.target_group_size])))
        return groups

    def collect_focal_method_coverage(self, context: MethodContext) -> Optional[CoverageState]:
        project_path = self.repos_dir / context.project_name
        if not project_path.exists():
            return CoverageState(
                target_line_coverage=self.target_line_coverage,
                status="coverage_unresolved",
                metadata={"coverage_error_hint": f"project path does not exist: {project_path}"},
            )

        class_name = context.class_name
        method_name = context.method_name
        if not class_name or not method_name:
            return CoverageState(
                target_line_coverage=self.target_line_coverage,
                status="coverage_unresolved",
                metadata={"coverage_error_hint": "missing class_name or method_name"},
            )

        package_name = str(context.raw_result.get("package_name") or "")
        class_fqn = f"{package_name}.{class_name}" if package_name else class_name
        focal_range = self._focal_method_range(context)

        try:
            executor = self.maven_executor_factory(str(project_path), timeout=self.maven_timeout_seconds)
            coverage_data = executor.collect_focal_method_coverage(
                class_fqn=class_fqn,
                method_name=method_name,
                focal_method_range=focal_range,
            )
        except Exception as exc:
            return CoverageState(
                target_line_coverage=self.target_line_coverage,
                status="coverage_parse_failed",
                metadata={
                    "coverage_error_hint": str(exc),
                    "coverage_last_checked_at": datetime.datetime.now().isoformat(),
                },
            )

        return self._coverage_state_from_data(coverage_data)

    def _coverage_state_from_data(self, coverage_data: dict[str, Any]) -> CoverageState:
        metadata = {
            "uncovered_line_details": coverage_data.get("uncovered_line_details", []),
            "coverage_error_hint": coverage_data.get("coverage_error_hint", ""),
            "coverage_report_fresh": bool(coverage_data.get("coverage_report_fresh", False)),
            "coverage_percentage_source": coverage_data.get("coverage_percentage_source", ""),
            "coverage_handles_source": coverage_data.get("coverage_handles_source", ""),
            "coverage_file_line_coverage": coverage_data.get("coverage_file_line_coverage"),
            "coverage_file_branch_coverage": coverage_data.get("coverage_file_branch_coverage"),
            "coverage_method_counter_resolved": bool(coverage_data.get("coverage_method_counter_resolved", False)),
            "coverage_method_range_resolved": bool(coverage_data.get("coverage_method_range_resolved", False)),
            "coverage_last_checked_at": datetime.datetime.now().isoformat(),
        }
        return CoverageState(
            line_coverage=self._as_float(coverage_data.get("line_coverage")),
            branch_coverage=self._as_float(coverage_data.get("branch_coverage")),
            uncovered_lines=normalize_lines(coverage_data.get("uncovered_lines")),
            target_line_coverage=self.target_line_coverage,
            status=str(coverage_data.get("coverage_status") or "coverage_unresolved"),
            metadata=metadata,
        )

    @staticmethod
    def _focal_method_range(context: MethodContext) -> Optional[tuple[int, int]]:
        start = context.raw_result.get("focal_method_start_line")
        end = context.raw_result.get("focal_method_end_line")
        try:
            if start is None or end is None:
                return None
            return int(start), int(end)
        except (TypeError, ValueError):
            return None

    @staticmethod
    def _as_float(value: Any) -> float:
        try:
            if value is None or value == "":
                return 0.0
            return float(value)
        except (TypeError, ValueError):
            return 0.0

    @classmethod
    def _fallback_uncovered_lines_from_focal_method(cls, context: MethodContext) -> tuple[int, ...]:
        raw_result = context.raw_result or {}
        under_test = raw_result.get("Under_test_method") or {}
        focal_code = str(
            raw_result.get("focal_method_code")
            or raw_result.get("method_body")
            or under_test.get("Method_body")
            or ""
        ).strip()
        if not focal_code:
            return ()

        try:
            start_line = int(raw_result.get("focal_method_start_line") or 0)
        except (TypeError, ValueError):
            start_line = 0

        target_lines: list[int] = []
        for index, line in enumerate(focal_code.splitlines(), 1):
            if not cls._looks_executable_java_line(line):
                continue
            source_line = start_line + index - 1 if start_line > 0 else index
            target_lines.append(source_line)
        return normalize_lines(target_lines)

    @staticmethod
    def _looks_executable_java_line(line: str) -> bool:
        text = re.sub(r"//.*$", "", str(line or "")).strip()
        if not text:
            return False
        if text in {"{", "}", "};"}:
            return False
        if text.startswith("@"):
            return False
        if text in {"else", "try", "finally"} or text.startswith("else "):
            return False
        if re.match(r"^(?:public|protected|private)?\s*(?:abstract\s+)?[\w<>\[\].?,\s]+\s+\w+\s*\([^)]*\)\s*;$", text):
            return False
        if re.match(r"^(?:public|protected|private)\b.*\)\s*\{?$", text) and ";" not in text:
            return False
        return any(token in text for token in (";", "if ", "for ", "while ", "switch ", "catch ", "throw ", "return "))
