from __future__ import annotations

import re
from dataclasses import dataclass, replace

from .coverage import PostProcessorV2CoverageService
from .models import Baseline, FailureSignal, MethodContext, PatchCandidate
from .validation import PostProcessorV2ValidationService


@dataclass(frozen=True)
class PatchEvaluation:
    candidate: PatchCandidate
    candidate_baseline: Baseline | None
    accepted: bool
    reason: str
    failure_signal: FailureSignal | None = None


@dataclass(frozen=True)
class _JavaTestMethod:
    name: str
    code: str


@dataclass(frozen=True)
class _StructuredMergeResult:
    code: str
    added_method_names: tuple[str, ...]
    skipped: str = ""

    @property
    def changed(self) -> bool:
        return bool(self.added_method_names)


class StructuredTestMerger:
    def merge_new_tests(self, baseline_code: str, candidate_code: str) -> _StructuredMergeResult:
        baseline_methods = self._extract_test_methods(baseline_code)
        candidate_methods = self._extract_test_methods(candidate_code)
        if not baseline_methods:
            return _StructuredMergeResult(baseline_code, (), "baseline_has_no_tests")
        if not candidate_methods:
            return _StructuredMergeResult(baseline_code, (), "candidate_has_no_tests")

        existing_names = {method.name for method in baseline_methods if method.name}
        new_methods = [
            method
            for method in candidate_methods
            if method.name and method.name not in existing_names
        ]
        if not new_methods:
            return _StructuredMergeResult(baseline_code, (), "no_new_test_methods")

        insert_at = self._find_last_class_close(baseline_code)
        if insert_at < 0:
            return _StructuredMergeResult(baseline_code, (), "baseline_class_close_not_found")

        addition = "\n\n" + "\n\n".join(method.code.strip() for method in new_methods) + "\n"
        merged = baseline_code[:insert_at].rstrip() + addition + baseline_code[insert_at:]
        return _StructuredMergeResult(merged, tuple(method.name for method in new_methods))

    @classmethod
    def _extract_test_methods(cls, code: str) -> list[_JavaTestMethod]:
        methods: list[_JavaTestMethod] = []
        for match in re.finditer(r"(?m)^[ \t]*@Test\b[^\n]*(?:\n|$)", str(code or "")):
            start = cls._annotation_block_start(code, match.start())
            body_open = code.find("{", match.end())
            if body_open < 0:
                continue
            end = cls._find_matching_brace(code, body_open)
            if end < 0:
                continue
            method_code = code[start : end + 1].strip()
            name = cls._method_name(method_code)
            if name:
                methods.append(_JavaTestMethod(name=name, code=method_code))
        return methods

    @staticmethod
    def _annotation_block_start(code: str, test_annotation_start: int) -> int:
        line_start = code.rfind("\n", 0, test_annotation_start) + 1
        start = line_start
        cursor = line_start
        while cursor > 0:
            previous_line_end = cursor - 1
            previous_line_start = code.rfind("\n", 0, previous_line_end) + 1
            previous_line = code[previous_line_start:previous_line_end].strip()
            if not previous_line.startswith("@"):
                break
            start = previous_line_start
            cursor = previous_line_start
        return start

    @staticmethod
    def _method_name(method_code: str) -> str:
        match = re.search(
            r"\b(?:public|protected|private)?\s*(?:static\s+)?void\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(",
            method_code,
        )
        return match.group(1) if match else ""

    @staticmethod
    def _find_last_class_close(code: str) -> int:
        return str(code or "").rfind("}")

    @staticmethod
    def _find_matching_brace(code: str, open_index: int) -> int:
        depth = 0
        in_string = False
        in_char = False
        in_line_comment = False
        in_block_comment = False
        escaped = False

        for index in range(open_index, len(code)):
            char = code[index]
            next_char = code[index + 1] if index + 1 < len(code) else ""

            if in_line_comment:
                if char == "\n":
                    in_line_comment = False
                continue
            if in_block_comment:
                if char == "*" and next_char == "/":
                    in_block_comment = False
                continue
            if in_string:
                if escaped:
                    escaped = False
                elif char == "\\":
                    escaped = True
                elif char == '"':
                    in_string = False
                continue
            if in_char:
                if escaped:
                    escaped = False
                elif char == "\\":
                    escaped = True
                elif char == "'":
                    in_char = False
                continue

            if char == "/" and next_char == "/":
                in_line_comment = True
                continue
            if char == "/" and next_char == "*":
                in_block_comment = True
                continue
            if char == '"':
                in_string = True
                continue
            if char == "'":
                in_char = True
                continue
            if char == "{":
                depth += 1
            elif char == "}":
                depth -= 1
                if depth == 0:
                    return index
        return -1


class PatchEvaluator:
    def __init__(
        self,
        validation_service: PostProcessorV2ValidationService,
        coverage_service: PostProcessorV2CoverageService,
    ) -> None:
        self.validation = validation_service
        self.coverage = coverage_service
        self.merger = StructuredTestMerger()

    def evaluate(self, context: MethodContext, baseline: Baseline, candidate: PatchCandidate) -> PatchEvaluation:
        candidate = self.validation.validate_patch(context, candidate)
        failure_signal = self._classify_strategy_failure(context, candidate)
        if not candidate.runnable and failure_signal is not None and not failure_signal.repairable:
            return PatchEvaluation(
                candidate=candidate,
                candidate_baseline=None,
                accepted=False,
                reason="candidate_not_runnable",
                failure_signal=failure_signal,
            )

        if not candidate.runnable:
            candidate = self.validation.repair_once(context, candidate)
            failure_signal = self._classify_strategy_failure(context, candidate) or failure_signal

        if not candidate.runnable:
            return PatchEvaluation(
                candidate=candidate,
                candidate_baseline=None,
                accepted=False,
                reason="candidate_not_runnable",
                failure_signal=failure_signal or self._generic_failure_signal(candidate),
            )

        candidate_baseline = candidate.to_baseline()
        refreshed = self.coverage.refresh_coverage(candidate_baseline, context=context)
        candidate_baseline = candidate_baseline.with_coverage(refreshed)

        merged_evaluation = self._evaluate_structured_merge(context, baseline, candidate, candidate_baseline)
        if merged_evaluation is not None:
            return merged_evaluation

        if candidate_baseline.coverage_better_than(baseline):
            return PatchEvaluation(
                candidate=candidate,
                candidate_baseline=candidate_baseline,
                accepted=True,
                reason="coverage_improved",
            )

        if not self._candidate_invokes_focal_method(context, candidate):
            return PatchEvaluation(
                candidate=candidate,
                candidate_baseline=candidate_baseline,
                accepted=False,
                reason="missing_focal_invocation",
                failure_signal=FailureSignal(
                    category="missing_focal_invocation",
                    summary="Generated patch did not visibly invoke the focal method; ensure the next candidate exercises the selected target through the focal API.",
                    repairable=False,
                    strategy_signature="missing_focal_invocation",
                    evidence=context.method_name,
                ),
            )

        return PatchEvaluation(
            candidate=candidate,
            candidate_baseline=candidate_baseline,
            accepted=False,
            reason="no_coverage_gain",
        )

    def _evaluate_structured_merge(
        self,
        context: MethodContext,
        baseline: Baseline,
        candidate: PatchCandidate,
        candidate_baseline: Baseline,
    ) -> PatchEvaluation | None:
        merge = self.merger.merge_new_tests(baseline.test_code, candidate.test_code)
        if not merge.changed:
            return None

        merged_candidate = PatchCandidate(
            test_code=merge.code,
            compile_success=candidate.compile_success,
            test_success=candidate.test_success,
            repaired=candidate.repaired,
            source="structured_test_merge",
            metadata={
                **dict(candidate.metadata),
                "structured_merge": {
                    "added_test_methods": list(merge.added_method_names),
                    "base_source": candidate.source,
                },
            },
        )
        merged_candidate = self.validation.validate_patch(context, merged_candidate)
        if not merged_candidate.runnable:
            return None

        merged_baseline = merged_candidate.to_baseline()
        merged_coverage = self.coverage.refresh_coverage(merged_baseline, context=context)
        merged_baseline = merged_baseline.with_coverage(merged_coverage)
        merged_candidate = replace(merged_candidate, coverage=merged_coverage)

        if not merged_baseline.coverage_better_than(baseline):
            return None

        if candidate_baseline.coverage_better_than(baseline) and candidate_baseline.coverage.score > merged_baseline.coverage.score:
            return None

        return PatchEvaluation(
            candidate=merged_candidate,
            candidate_baseline=merged_baseline,
            accepted=True,
            reason="coverage_improved_structured_merge",
        )

    @classmethod
    def _classify_strategy_failure(cls, context: MethodContext, candidate: PatchCandidate) -> FailureSignal | None:
        output = cls._failure_text(candidate)
        code = str(candidate.test_code or "")
        combined = f"{output}\n{code}"
        lowered = combined.lower()

        if cls._looks_like_non_controllable_mock(lowered):
            target = cls._extract_mock_target(combined) or "runtime-type"
            return FailureSignal(
                category="non_controllable_mock",
                summary=(
                    f"Avoid mocking or spying non-controllable runtime/final type `{target}`; "
                    "use real instances, public seams, or controllable collaborators instead."
                ),
                repairable=False,
                strategy_signature=f"non_controllable_mock:{target}",
                evidence=cls._short_evidence(output),
            )

        if cls._looks_like_private_final_state(lowered, code):
            field = cls._extract_reflected_field(code) or "private/final state"
            return FailureSignal(
                category="private_final_state",
                summary=(
                    f"Avoid mutating `{field}` through private/final reflection; "
                    "build the required state through constructors, setters, builders, or existing test fixtures."
                ),
                repairable=False,
                strategy_signature=f"private_final_state:{field}",
                evidence=cls._short_evidence(output),
            )

        if cls._looks_like_invalid_construction(lowered):
            target = cls._extract_constructor_target(combined) or context.class_name or "constructed type"
            return FailureSignal(
                category="invalid_construction",
                summary=(
                    f"Avoid inventing constructors or factory signatures for `{target}`; "
                    "instantiate objects only through APIs shown by the source summary or current test."
                ),
                repairable=False,
                strategy_signature=f"invalid_construction:{target}",
                evidence=cls._short_evidence(output),
            )

        return None

    @staticmethod
    def _failure_text(candidate: PatchCandidate) -> str:
        metadata = candidate.metadata or {}
        output = str(metadata.get("validation_output") or "")
        if output:
            return output
        attempts = metadata.get("repair_attempts") or []
        if attempts:
            return str(attempts[-1])
        return ""

    @staticmethod
    def _looks_like_non_controllable_mock(text: str) -> bool:
        return any(
            marker in text
            for marker in (
                "cannot mock/spy class",
                "cannot mock final",
                "cannot mock/spy because",
                "mockito cannot mock",
                "cannot mock wrapper types",
                "cannot mock primitive types",
            )
        )

    @staticmethod
    def _looks_like_private_final_state(text: str, code: str) -> bool:
        if any(
            marker in text
            for marker in (
                "can not set final",
                "cannot set final",
                "illegalaccessexception",
                "modifiers field",
                "private final",
                "static final",
            )
        ):
            return "getdeclaredfield" in text or "field" in text or "final" in text
        code_lower = code.lower()
        return "getdeclaredfield" in code_lower and "setaccessible(true)" in code_lower and ".set(" in code_lower

    @staticmethod
    def _looks_like_invalid_construction(text: str) -> bool:
        return (
            "nosuchmethodexception" in text
            or "cannot resolve constructor" in text
            or "no suitable constructor found" in text
            or ("constructor " in text and "cannot be applied to given types" in text)
            or "actual and formal argument lists differ in length" in text
        )

    @staticmethod
    def _extract_mock_target(text: str) -> str:
        for pattern in (
            r"Cannot mock/spy class\s+([A-Za-z0-9_.$]+)",
            r"mock\s*\(\s*([A-Za-z0-9_.$]+)\.class",
            r"spy\s*\(\s*([A-Za-z0-9_.$]+)\.class",
        ):
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                return match.group(1)
        return ""

    @staticmethod
    def _extract_reflected_field(code: str) -> str:
        match = re.search(r"getDeclaredField\s*\(\s*[\"']([^\"']+)[\"']", code)
        return match.group(1) if match else ""

    @staticmethod
    def _extract_constructor_target(text: str) -> str:
        for pattern in (
            r"NoSuchMethodException:\s*([A-Za-z0-9_.$]+)\.<init>",
            r"constructor\s+([A-Za-z0-9_.$]+)\s+in",
            r"no suitable constructor found for\s+([A-Za-z0-9_.$]+)",
        ):
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                return match.group(1)
        return ""

    @classmethod
    def _generic_failure_signal(cls, candidate: PatchCandidate) -> FailureSignal:
        evidence = cls._short_evidence(cls._failure_text(candidate))
        summary = "Patch remained non-runnable after validation/repair."
        if evidence:
            summary = f"{summary} Last validation signal: {evidence}"
        return FailureSignal(
            category="validation_failure",
            summary=summary,
            repairable=True,
            evidence=evidence,
        )

    @staticmethod
    def _short_evidence(text: str, max_chars: int = 240) -> str:
        normalized = " ".join(str(text or "").split())
        if len(normalized) <= max_chars:
            return normalized
        return normalized[:max_chars].rstrip() + "..."

    @staticmethod
    def _candidate_invokes_focal_method(context: MethodContext, candidate: PatchCandidate) -> bool:
        method_name = str(context.method_name or "").strip()
        class_name = str(context.class_name or "").strip()
        code = str(candidate.test_code or "")
        if not method_name:
            return True

        if method_name == class_name:
            return bool(re.search(rf"\bnew\s+{re.escape(class_name)}\s*\(", code))

        method_call = re.compile(rf"(?:\.|\b){re.escape(method_name)}\s*\(")
        if method_call.search(code):
            return True

        reflection_call = re.compile(
            rf"\bget(?:Declared)?Method\s*\(\s*[\"']{re.escape(method_name)}[\"']",
            re.DOTALL,
        )
        return bool(reflection_call.search(code))
