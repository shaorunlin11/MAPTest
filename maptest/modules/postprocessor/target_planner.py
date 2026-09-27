from __future__ import annotations

import re
import logging
from dataclasses import dataclass
from typing import Any, Callable, Dict, Iterable, List, Optional, Tuple

from maptest.modules.cfg_generator import JavaCFGGenerator

from .compile_guard import CompileGuard
from .models import Baseline, CoverageState, MethodContext, Requirement, TargetPlan, normalize_lines
from .planning_agents import PlanningAgentInput, PlanningRequirementAgent, default_planning_agents


@dataclass(frozen=True)
class CFGPathEvidence:
    source_line: int
    cfg_line: int
    signature: Tuple[str, ...]
    steps: Tuple[str, ...]


@dataclass(frozen=True)
class _TargetPlanCandidate:
    target_lines: Tuple[int, ...]
    reachability: str
    base_requirements: Tuple[Requirement, ...]
    confidence: str
    cfg_path: Tuple[str, ...] = ()
    path_signature: Tuple[str, ...] = ()
    uncovered_line_details: Tuple[Dict[str, Any], ...] = ()


class CFGPathProvider:
    def build_path_evidence(
        self,
        context: MethodContext,
        source_lines: Iterable[int],
    ) -> Dict[int, Optional[CFGPathEvidence]]:
        focal_method_code = self._focal_method_code(context)
        lines = normalize_lines(tuple(source_lines))
        if not focal_method_code or not lines:
            return {line: None for line in lines}

        generator = JavaCFGGenerator()
        generator.generate_cfg(focal_method_code)

        evidence: Dict[int, Optional[CFGPathEvidence]] = {}
        for source_line in lines:
            cfg_line = self._to_cfg_line(context, source_line)
            steps = tuple(generator.get_path_to_uncovered_node(cfg_line))
            if self._path_failed(steps):
                evidence[source_line] = None
                continue

            catch_context = self._catch_context_for_line(focal_method_code, cfg_line)
            evidence[source_line] = CFGPathEvidence(
                source_line=source_line,
                cfg_line=cfg_line,
                signature=self._path_signature(steps, catch_context=catch_context),
                steps=steps,
            )
        return evidence

    @staticmethod
    def _focal_method_code(context: MethodContext) -> str:
        under_test = context.raw_result.get("Under_test_method") or {}
        return str(
            context.raw_result.get("focal_method_code")
            or context.raw_result.get("method_body")
            or under_test.get("Method_body")
            or ""
        ).strip()

    @staticmethod
    def _to_cfg_line(context: MethodContext, source_line: int) -> int:
        try:
            start_line = int(context.raw_result.get("focal_method_start_line"))
        except (TypeError, ValueError):
            return source_line
        if start_line > 0 and source_line >= start_line:
            return source_line - start_line + 1
        return source_line

    @staticmethod
    def _path_failed(steps: Tuple[str, ...]) -> bool:
        if not steps:
            return True
        joined = " ".join(steps).lower()
        return "unreachable" in joined or "path analysis failed" in joined

    @staticmethod
    def _path_signature(steps: Tuple[str, ...], catch_context: str = "") -> Tuple[str, ...]:
        normalized = []
        for step in steps:
            text = re.sub(r"\(L\d+\)", "", str(step)).strip()
            text = re.sub(r"\s+", " ", text)
            if catch_context and text == "Catch handler":
                text = f"Catch handler: {catch_context}"
            if text:
                normalized.append(text)
        return tuple(normalized)

    @classmethod
    def _catch_context_for_line(cls, focal_method_code: str, cfg_line: int) -> str:
        lines = str(focal_method_code or "").splitlines()
        if cfg_line <= 0 or cfg_line > len(lines):
            return ""

        catch_start_index = -1
        for index in range(cfg_line - 1, -1, -1):
            if re.search(r"\bcatch\s*\(", lines[index]):
                catch_start_index = index
                break
        if catch_start_index < 0:
            return ""

        catch_lines = []
        for line in lines[catch_start_index: min(len(lines), catch_start_index + 6)]:
            catch_lines.append(line.strip())
            if "{" in line:
                break

        catch_text = re.sub(r"\s+", " ", " ".join(catch_lines)).strip()
        catch_text = re.sub(r"^.*?\bcatch\s*\(", "catch (", catch_text)
        catch_text = catch_text.split("{", 1)[0].strip()
        match = re.match(r"catch\s*\((.*)\)\s*$", catch_text)
        if not match:
            return catch_text
        return f"catch ({cls._normalize_catch_parameters(match.group(1))})"

    @staticmethod
    def _normalize_catch_parameters(parameters: str) -> str:
        text = re.sub(r"\s+", " ", str(parameters or "")).strip()
        text = re.sub(r"\s*\|\s*", " | ", text)
        return text


class TargetPlanner:
    def __init__(
        self,
        path_provider: Optional[CFGPathProvider] = None,
        compile_guard: Optional[CompileGuard] = None,
        planning_agents: Optional[Iterable[PlanningRequirementAgent]] = None,
        logger: Optional[logging.Logger] = None,
    ):
        self.path_provider = path_provider or CFGPathProvider()
        self.compile_guard = compile_guard or CompileGuard()
        self.planning_agents = tuple(planning_agents) if planning_agents is not None else default_planning_agents()
        self.logger = logger or logging.getLogger(__name__)

    def build_plans(
        self,
        context: MethodContext,
        baseline: Baseline,
        trace_sink: Optional[Callable[[Dict[str, Any]], None]] = None,
    ) -> List[TargetPlan]:
        plans = [
            self.enrich_candidate(candidate, context, baseline, trace_sink=trace_sink)
            for candidate in self.build_plan_candidates(context, baseline)
        ]
        return sorted(plans, key=self._plan_sort_key)

    def build_plan_candidates(
        self,
        context: MethodContext,
        baseline: Baseline,
    ) -> List[_TargetPlanCandidate]:
        coverage = baseline.coverage
        lines = normalize_lines(coverage.uncovered_lines)
        if not lines:
            return []

        details_by_line = self._details_by_line(coverage)
        evidence_by_line = self.path_provider.build_path_evidence(context, lines)
        compile_reqs = self.compile_guard.build_requirements(context, baseline)

        grouped_lines: Dict[Tuple[str, ...], List[int]] = {}
        steps_by_signature: Dict[Tuple[str, ...], Tuple[str, ...]] = {}
        direct_lines: List[int] = []

        for line in lines:
            evidence = evidence_by_line.get(line)
            if evidence is None:
                direct_lines.append(line)
                continue
            grouped_lines.setdefault(evidence.signature, []).append(line)
            steps_by_signature.setdefault(evidence.signature, evidence.steps)

        candidates = [
            self._cfg_candidate(
                lines=tuple(group_lines),
                signature=signature,
                cfg_path=steps_by_signature.get(signature, ()),
                details_by_line=details_by_line,
                compile_requirements=compile_reqs,
            )
            for signature, group_lines in grouped_lines.items()
        ]
        candidates.extend(
            self._direct_candidate(
                line,
                details_by_line.get(line),
                compile_requirements=compile_reqs,
            )
            for line in direct_lines
        )
        return sorted(candidates, key=self._candidate_sort_key)

    def enrich_candidate(
        self,
        candidate: _TargetPlanCandidate,
        context: MethodContext,
        baseline: Baseline,
        trace_sink: Optional[Callable[[Dict[str, Any]], None]] = None,
    ) -> TargetPlan:
        requirements = list(candidate.base_requirements)
        self._extend_with_planning_requirements(
            context=context,
            baseline=baseline,
            target_lines=candidate.target_lines,
            reachability=candidate.reachability,
            requirements=requirements,
            compile_requirements=[],
            cfg_path=candidate.cfg_path,
            path_signature=candidate.path_signature,
            uncovered_line_details=candidate.uncovered_line_details,
            trace_sink=trace_sink,
        )
        return TargetPlan(
            target_lines=candidate.target_lines,
            reachability=candidate.reachability,
            requirements=tuple(requirements),
            confidence=candidate.confidence,
        )

    def select_enriched_plan(
        self,
        context: MethodContext,
        baseline: Baseline,
        failed_plan_keys: set[tuple[int, ...]],
        max_candidates: int,
        trace_sink: Optional[Callable[[Dict[str, Any]], None]] = None,
    ) -> Optional[TargetPlan]:
        limit = max(1, int(max_candidates))
        candidates = self.build_plan_candidates(context, baseline)
        for candidate in candidates[:limit]:
            if tuple(candidate.target_lines) in failed_plan_keys:
                continue
            return self.enrich_candidate(candidate, context, baseline, trace_sink=trace_sink)
        return None

    def select_plan(self, context: MethodContext, baseline: Baseline) -> Optional[TargetPlan]:
        plans = self.build_plans(context, baseline)
        return plans[0] if plans else None

    def _cfg_candidate(
        self,
        lines: Tuple[int, ...],
        signature: Tuple[str, ...],
        cfg_path: Tuple[str, ...],
        details_by_line: Dict[int, Dict],
        compile_requirements: List[Requirement],
    ) -> _TargetPlanCandidate:
        requirements: List[Requirement] = [
            Requirement(kind="coverage", text=f"cover target lines {', '.join(str(line) for line in lines)}"),
        ]
        if signature:
            requirements.append(
                Requirement(
                    kind="condition",
                    text=(
                        "CFG route available for target lines "
                        f"{', '.join(str(line) for line in lines)}; detailed path was used during planning"
                    ),
                )
            )
        requirements.extend(self._coverage_detail_requirements(lines, details_by_line))
        requirements.extend(compile_requirements)
        return _TargetPlanCandidate(
            target_lines=lines,
            reachability="cfg",
            base_requirements=tuple(requirements),
            confidence="medium",
            cfg_path=cfg_path,
            path_signature=signature,
            uncovered_line_details=self._line_details(lines, details_by_line),
        )

    def _direct_candidate(
        self,
        line: int,
        detail: Optional[Dict],
        compile_requirements: List[Requirement],
    ) -> _TargetPlanCandidate:
        requirements = [
            Requirement(kind="coverage", text=f"execute line {line}"),
        ]
        requirements.extend(self._coverage_detail_requirements((line,), {line: detail} if detail else {}))
        requirements.extend(compile_requirements)
        return _TargetPlanCandidate(
            target_lines=(line,),
            reachability="direct",
            base_requirements=tuple(requirements),
            confidence="low",
            uncovered_line_details=(dict(detail),) if detail else (),
        )

    def _extend_with_planning_requirements(
        self,
        context: MethodContext,
        baseline: Baseline,
        target_lines: Tuple[int, ...],
        reachability: str,
        requirements: List[Requirement],
        compile_requirements: List[Requirement],
        cfg_path: Tuple[str, ...] = (),
        path_signature: Tuple[str, ...] = (),
        uncovered_line_details: Tuple[Dict, ...] = (),
        trace_sink: Optional[Callable[[Dict[str, Any]], None]] = None,
    ) -> None:
        requirements.extend(compile_requirements)
        for agent in self.planning_agents:
            before_count = len(requirements)
            agent_name = agent.__class__.__name__
            self.logger.debug(
                "planning_agent_start agent=%s target_lines=%s reachability=%s requirements_so_far=%s",
                agent_name,
                list(target_lines),
                reachability,
                before_count,
            )
            planning_input = PlanningAgentInput(
                context=context,
                baseline=baseline,
                target_lines=target_lines,
                reachability=reachability,
                requirements_so_far=tuple(requirements),
                cfg_path=cfg_path,
                path_signature=path_signature,
                uncovered_line_details=tuple(dict(detail) for detail in uncovered_line_details),
                focal_method_code=CFGPathProvider._focal_method_code(context),
                trace_sink=trace_sink,
            )
            requirements.extend(agent.build_requirements(planning_input))
            added = requirements[before_count:]
            self.logger.debug(
                "planning_agent_done agent=%s added=%s kinds=%s",
                agent_name,
                len(added),
                [requirement.kind for requirement in added],
            )

    @staticmethod
    def _details_by_line(coverage: CoverageState) -> Dict[int, Dict]:
        details = {}
        for detail in coverage.metadata.get("uncovered_line_details", []) or []:
            if not isinstance(detail, dict):
                continue
            try:
                line = int(detail.get("line"))
            except (TypeError, ValueError):
                continue
            details[line] = dict(detail)
        return details

    @staticmethod
    def _coverage_detail_requirements(lines: Tuple[int, ...], details_by_line: Dict[int, Dict]) -> List[Requirement]:
        requirements: List[Requirement] = []
        for line in lines:
            detail = details_by_line.get(line) or {}
            if detail.get("branch_gap") or detail.get("reason") in {"branch_gap", "mixed"}:
                requirements.append(Requirement(kind="coverage", text=f"line {line} has an uncovered branch"))
            elif detail.get("instruction_gap") or detail.get("reason") == "instruction_gap":
                requirements.append(Requirement(kind="coverage", text=f"line {line} has uncovered instructions"))
        return requirements

    @staticmethod
    def _line_details(lines: Tuple[int, ...], details_by_line: Dict[int, Dict]) -> Tuple[Dict, ...]:
        return tuple(dict(details_by_line[line]) for line in lines if line in details_by_line)

    @staticmethod
    def _plan_sort_key(plan: TargetPlan) -> Tuple[int, int, int]:
        has_branch_gap = any(
            requirement.kind == "coverage" and "branch" in requirement.text.lower()
            for requirement in plan.requirements
        )
        reachability_priority = 0 if plan.reachability == "cfg" else 1
        branch_priority = 0 if has_branch_gap else 1
        return (branch_priority, reachability_priority, plan.target_lines[0])

    @staticmethod
    def _candidate_sort_key(candidate: _TargetPlanCandidate) -> Tuple[int, int, int]:
        has_branch_gap = any(
            requirement.kind == "coverage" and "branch" in requirement.text.lower()
            for requirement in candidate.base_requirements
        )
        reachability_priority = 0 if candidate.reachability == "cfg" else 1
        branch_priority = 0 if has_branch_gap else 1
        return (branch_priority, reachability_priority, candidate.target_lines[0])
