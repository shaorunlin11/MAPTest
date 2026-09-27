from __future__ import annotations

from dataclasses import dataclass, field, replace
from typing import Any, Dict, List, Optional, Tuple


def _as_float(value: Any, default: float = 0.0) -> float:
    try:
        return float(value)
    except (TypeError, ValueError):
        return default


def _as_bool(value: Any) -> bool:
    if isinstance(value, bool):
        return value
    if isinstance(value, (int, float)):
        return bool(value)
    if isinstance(value, str):
        return value.strip().lower() in {"1", "true", "yes", "y", "on", "success"}
    return False


def normalize_lines(value: Any) -> Tuple[int, ...]:
    if value is None:
        return ()
    if isinstance(value, int):
        return (value,)
    if not isinstance(value, (list, tuple, set)):
        return ()

    normalized: List[int] = []
    for item in value:
        try:
            normalized.append(int(item))
        except (TypeError, ValueError):
            continue
    return tuple(sorted(set(normalized)))


@dataclass(frozen=True)
class MethodContext:
    project_name: str
    method_key: str
    class_name: str = ""
    method_name: str = ""
    test_code: str = ""
    raw_result: Dict[str, Any] = field(default_factory=dict)


@dataclass(frozen=True)
class CoverageState:
    line_coverage: float = 0.0
    branch_coverage: float = 0.0
    uncovered_lines: Tuple[int, ...] = ()
    target_line_coverage: float = 100.0
    status: str = "unknown"
    metadata: Dict[str, Any] = field(default_factory=dict)

    @property
    def done(self) -> bool:
        return self.line_coverage >= self.target_line_coverage and not self.uncovered_lines

    @property
    def has_locatable_targets(self) -> bool:
        return bool(self.uncovered_lines)

    @property
    def score(self) -> Tuple[float, float, int]:
        return (self.line_coverage, self.branch_coverage, -len(self.uncovered_lines))


@dataclass(frozen=True)
class TargetGroup:
    lines: Tuple[int, ...]
    reason: str = "uncovered_lines"
    metadata: Dict[str, Any] = field(default_factory=dict)


@dataclass(frozen=True)
class Requirement:
    kind: str
    text: str

    def __post_init__(self) -> None:
        normalized_kind = str(self.kind or "").strip().lower()
        normalized_text = str(self.text or "").strip()
        object.__setattr__(self, "kind", normalized_kind)
        object.__setattr__(self, "text", normalized_text)
        if not normalized_kind:
            raise ValueError("Requirement.kind must not be empty")
        if not normalized_text:
            raise ValueError("Requirement.text must not be empty")

    def to_dict(self) -> Dict[str, str]:
        return {"kind": self.kind, "text": self.text}


@dataclass(frozen=True)
class TargetPlan:
    target_lines: Tuple[int, ...]
    reachability: str
    requirements: Tuple[Requirement, ...] = ()
    confidence: str = "low"

    def __post_init__(self) -> None:
        normalized_lines = normalize_lines(self.target_lines)
        normalized_reachability = str(self.reachability or "").strip().lower()
        normalized_confidence = str(self.confidence or "").strip().lower() or "low"
        normalized_requirements = tuple(self.requirements or ())

        if not normalized_lines:
            raise ValueError("TargetPlan.target_lines must not be empty")
        if normalized_reachability not in {"cfg", "direct"}:
            raise ValueError("TargetPlan.reachability must be 'cfg' or 'direct'")
        if normalized_confidence not in {"high", "medium", "low"}:
            raise ValueError("TargetPlan.confidence must be high, medium, or low")
        for requirement in normalized_requirements:
            if not isinstance(requirement, Requirement):
                raise TypeError("TargetPlan.requirements must contain Requirement objects")

        object.__setattr__(self, "target_lines", normalized_lines)
        object.__setattr__(self, "reachability", normalized_reachability)
        object.__setattr__(self, "requirements", normalized_requirements)
        object.__setattr__(self, "confidence", normalized_confidence)

    def to_dict(self) -> Dict[str, Any]:
        return {
            "target_lines": list(self.target_lines),
            "reachability": self.reachability,
            "requirements": [requirement.to_dict() for requirement in self.requirements],
            "confidence": self.confidence,
        }


@dataclass(frozen=True)
class Baseline:
    test_code: str
    compile_success: bool = False
    test_success: bool = False
    coverage: CoverageState = field(default_factory=CoverageState)
    source: str = "initial"
    metadata: Dict[str, Any] = field(default_factory=dict)

    @property
    def runnable(self) -> bool:
        return self.compile_success and self.test_success

    def with_coverage(self, coverage: CoverageState) -> "Baseline":
        return replace(self, coverage=coverage)

    def coverage_better_than(self, other: "Baseline") -> bool:
        return self.coverage.score > other.coverage.score


@dataclass(frozen=True)
class PatchCandidate:
    test_code: str
    compile_success: bool = False
    test_success: bool = False
    coverage: Optional[CoverageState] = None
    repaired: bool = False
    source: str = "patch"
    metadata: Dict[str, Any] = field(default_factory=dict)

    @property
    def runnable(self) -> bool:
        return self.compile_success and self.test_success

    def to_baseline(self) -> Baseline:
        return Baseline(
            test_code=self.test_code,
            compile_success=self.compile_success,
            test_success=self.test_success,
            coverage=self.coverage or CoverageState(),
            source=self.source,
            metadata=dict(self.metadata),
        )


@dataclass(frozen=True)
class FailureSignal:
    category: str
    summary: str
    repairable: bool = True
    strategy_signature: str = ""
    evidence: str = ""

    def __post_init__(self) -> None:
        normalized_category = str(self.category or "").strip().lower()
        normalized_summary = str(self.summary or "").strip()
        normalized_signature = str(self.strategy_signature or "").strip().lower()
        normalized_evidence = str(self.evidence or "").strip()
        if not normalized_category:
            raise ValueError("FailureSignal.category must not be empty")
        if not normalized_summary:
            raise ValueError("FailureSignal.summary must not be empty")
        object.__setattr__(self, "category", normalized_category)
        object.__setattr__(self, "summary", normalized_summary)
        object.__setattr__(self, "strategy_signature", normalized_signature)
        object.__setattr__(self, "evidence", normalized_evidence)

    def to_dict(self) -> Dict[str, Any]:
        return {
            "category": self.category,
            "summary": self.summary,
            "repairable": self.repairable,
            "strategy_signature": self.strategy_signature,
            "evidence": self.evidence,
        }


@dataclass(frozen=True)
class MethodOutcome:
    context: MethodContext
    baseline: Baseline
    success: bool
    iterations: int = 0
    stop_reason: str = ""
    logs: Tuple[str, ...] = ()
    trace_events: Tuple[Dict[str, Any], ...] = ()

    def to_dict(self) -> Dict[str, Any]:
        raw_result = self.context.raw_result or {}
        return {
            "project_name": self.context.project_name,
            "method_key": self.context.method_key,
            "method_variant_key": raw_result.get("method_variant_key", ""),
            "package_name": raw_result.get("package_name", ""),
            "class_name": self.context.class_name,
            "method_name": self.context.method_name,
            "focal_signature": raw_result.get("focal_signature", ""),
            "focal_method_start_line": raw_result.get("focal_method_start_line"),
            "focal_method_end_line": raw_result.get("focal_method_end_line"),
            "success": self.success,
            "iterations": self.iterations,
            "stop_reason": self.stop_reason,
            "compile_success": self.baseline.compile_success,
            "test_success": self.baseline.test_success,
            "line_coverage": self.baseline.coverage.line_coverage,
            "branch_coverage": self.baseline.coverage.branch_coverage,
            "uncovered_lines": list(self.baseline.coverage.uncovered_lines),
            "baseline_source": self.baseline.source,
            "logs": list(self.logs),
        }


def baseline_from_method_result(method_result: Dict[str, Any], source: str = "initial") -> Baseline:
    coverage = CoverageState(
        line_coverage=_as_float(method_result.get("line_coverage")),
        branch_coverage=_as_float(method_result.get("branch_coverage")),
        uncovered_lines=normalize_lines(method_result.get("uncovered_lines")),
        status=str(method_result.get("coverage_status") or "unknown"),
    )
    return Baseline(
        test_code=str(
            method_result.get("optimized_code")
            or method_result.get("test_code")
            or method_result.get("generated_test_code")
            or ""
        ),
        compile_success=_as_bool(method_result.get("compile_success")),
        test_success=_as_bool(method_result.get("test_success")),
        coverage=coverage,
        source=source,
        metadata={"raw_compile_success": method_result.get("compile_success")},
    )
