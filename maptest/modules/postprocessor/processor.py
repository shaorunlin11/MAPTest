from __future__ import annotations

import json
import logging
from pathlib import Path
from typing import Any, Dict, Iterable, List, Optional

from .config import PostProcessorV2Config
from .coverage import PostProcessorV2CoverageService
from .models import Baseline, FailureSignal, MethodContext, MethodOutcome, PatchCandidate, Requirement, TargetPlan
from .normalizer import PostProcessorV2Normalizer
from .output import PostProcessorV2OutputWriter
from .patch_agent import PatchAgent
from .patch_evaluator import PatchEvaluator
from .target_planner import TargetPlanner
from .validation import PostProcessorV2ValidationService


class PostProcessorV2:
    def __init__(
        self,
        max_iterations: Optional[int] = None,
        max_no_gain_rounds: Optional[int] = None,
        config: Optional[PostProcessorV2Config] = None,
        config_overrides: Optional[Dict[str, Any]] = None,
        validation_service: Optional[PostProcessorV2ValidationService] = None,
        coverage_service: Optional[PostProcessorV2CoverageService] = None,
        target_planner: Optional[TargetPlanner] = None,
        patch_agent: Optional[PatchAgent] = None,
        patch_evaluator: Optional[PatchEvaluator] = None,
        normalizer: Optional[PostProcessorV2Normalizer] = None,
        output_writer: Optional[PostProcessorV2OutputWriter] = None,
        results_root: Optional[Path] = None,
        output_root: Optional[Path] = None,
        repos_dir: Optional[Path] = None,
        logger: Optional[logging.Logger] = None,
    ):
        override_values = dict(config_overrides or {})
        if max_iterations is not None:
            override_values["max_iterations"] = max_iterations
        if max_no_gain_rounds is not None:
            override_values["max_no_gain_rounds"] = max_no_gain_rounds
        self.config = (config or PostProcessorV2Config.from_global_config()).with_overrides(override_values)
        self.max_iterations = self.config.max_iterations
        self.max_no_gain_rounds = self.config.max_no_gain_rounds
        self.max_plans_per_baseline = self.config.max_plans_per_baseline
        self.max_failure_feedback_requirements = self.config.max_failure_feedback_requirements
        package_root = Path(__file__).resolve().parents[2]
        workspace_root = package_root.parent
        self.repos_dir = repos_dir or workspace_root / "Repos"
        self.validation = validation_service or PostProcessorV2ValidationService(
            repos_dir=self.repos_dir,
            maven_timeout_seconds=self.config.maven_timeout_seconds,
            repair_attempt_limit=self.config.stabilization_repair_attempt_limit,
            patch_repair_attempt_limit=self.config.patch_repair_attempt_limit,
        )
        self.coverage = coverage_service or PostProcessorV2CoverageService(
            repos_dir=self.repos_dir,
            maven_timeout_seconds=self.config.maven_timeout_seconds,
            target_line_coverage=self.config.target_line_coverage,
            target_group_size=self.config.target_group_size,
        )
        self.target_planner = target_planner or TargetPlanner()
        self.patch_agent = patch_agent or PatchAgent()
        self.patch_evaluator = patch_evaluator or PatchEvaluator(self.validation, self.coverage)
        self.logger = logger or logging.getLogger(__name__)

        self.results_root = results_root or package_root / "experiment_results" / "uml-based_generation"
        self.normalizer = normalizer or PostProcessorV2Normalizer(repos_dir=self.repos_dir)
        self.output_writer = output_writer or PostProcessorV2OutputWriter(output_root=output_root)

    def process_project(self, project_name: str) -> List[MethodOutcome]:
        result_file = self.results_root / project_name / "overall_results.json"
        self.logger.info("postprocessor_v2_project_start project=%s result_file=%s", project_name, result_file)
        with result_file.open("r", encoding="utf-8") as handle:
            project_result = json.load(handle)

        project_result = self.normalizer.normalize_project_results(project_result, project_name)
        method_results = project_result.get("detailed_results") or []
        self.logger.info("postprocessor_v2_project_methods project=%s count=%s", project_name, len(method_results))
        outcomes = []
        output_dir = self.output_writer.reset_project_output(project_name)
        self.logger.info("postprocessor_v2_output_reset project=%s dir=%s", project_name, output_dir)
        output_file = self.output_writer.write_project_results(project_name, outcomes)
        self.logger.info("postprocessor_v2_output_initialized project=%s file=%s", project_name, output_file)
        for index, method_result in enumerate(method_results, 1):
            method_name = method_result.get("method_key") or self._build_method_key(
                str(method_result.get("class_name") or ""),
                str(method_result.get("method_name") or ""),
            )
            self.logger.info(
                "postprocessor_v2_method_progress project=%s index=%s/%s method=%s",
                project_name,
                index,
                len(method_results),
                method_name,
            )
            outcome = self.process_method(method_result, project_name=project_name)
            outcomes.append(outcome)
            self.logger.info(
                "postprocessor_v2_method_done project=%s index=%s/%s method=%s success=%s iterations=%s stop=%s coverage=%s",
                project_name,
                index,
                len(method_results),
                outcome.context.method_key,
                outcome.success,
                outcome.iterations,
                outcome.stop_reason,
                self._coverage_summary(outcome.baseline.coverage),
            )
            output_file = self.output_writer.write_project_results(project_name, outcomes)
            self.logger.info(
                "postprocessor_v2_output_updated project=%s completed=%s/%s file=%s",
                project_name,
                index,
                len(method_results),
                output_file,
            )
        self.logger.info("postprocessor_v2_project_done project=%s methods=%s", project_name, len(outcomes))
        return outcomes

    def process_methods(self, method_results: Iterable[Dict[str, Any]], project_name: str) -> List[MethodOutcome]:
        return [
            self.process_method(method_result, project_name=project_name)
            for method_result in method_results
        ]

    def process_method(self, method_result: Dict[str, Any], project_name: str = "") -> MethodOutcome:
        context = self.normalize_method_context(method_result, project_name=project_name)
        logs: List[str] = []
        trace_events: List[Dict[str, Any]] = []
        self.logger.info("method_start method=%s project=%s", context.method_key, context.project_name)

        baseline = self.validation.validate_current_test(context)
        logs.append(f"validated_initial:runnable={baseline.runnable}")
        self.logger.info(
            "initial_validation method=%s compile=%s test=%s runnable=%s source=%s",
            context.method_key,
            baseline.compile_success,
            baseline.test_success,
            baseline.runnable,
            baseline.source,
        )

        if not baseline.runnable:
            self.logger.info("initial_not_runnable_stabilizing method=%s", context.method_key)
            baseline = self.validation.stabilize_once(context, baseline)
            logs.append(f"stabilized_once:runnable={baseline.runnable}")
            self.logger.info(
                "stabilization_done method=%s compile=%s test=%s runnable=%s source=%s",
                context.method_key,
                baseline.compile_success,
                baseline.test_success,
                baseline.runnable,
                baseline.source,
            )

        if not baseline.runnable:
            self.logger.info("building_zero_coverage_template method=%s", context.method_key)
            baseline = self.validation.build_zero_coverage_template(context, baseline)
            logs.append("zero_coverage_template_created")
            self.logger.info(
                "zero_coverage_template_done method=%s compile=%s test=%s runnable=%s",
                context.method_key,
                baseline.compile_success,
                baseline.test_success,
                baseline.runnable,
            )

        iterations = 0
        no_gain_rounds = 0
        failed_plan_keys: set[tuple[int, ...]] = set()
        failed_strategy_feedback: Dict[str, Requirement] = {}
        stop_reason = "budget_exhausted"

        while iterations < self.max_iterations:
            self.logger.info(
                "iteration_start method=%s iteration=%s/%s no_gain=%s/%s",
                context.method_key,
                iterations + 1,
                self.max_iterations,
                no_gain_rounds,
                self.max_no_gain_rounds,
            )
            refreshed = self.coverage.refresh_coverage(baseline, context=context)
            baseline = baseline.with_coverage(refreshed)
            self.logger.info(
                "coverage_refreshed method=%s %s",
                context.method_key,
                self._coverage_summary(refreshed),
            )

            if refreshed.done:
                stop_reason = "coverage_complete"
                self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                break

            if not refreshed.has_locatable_targets:
                stop_reason = "no_locatable_targets"
                self.logger.info(
                    "stop method=%s reason=%s hint=%s",
                    context.method_key,
                    stop_reason,
                    refreshed.metadata.get("coverage_error_hint", ""),
                )
                break

            target_plan = self.select_next_target_plan(
                context,
                baseline,
                failed_plan_keys,
                trace_sink=trace_events.append,
            )
            if (
                target_plan is None
                and failed_plan_keys
                and self._should_reset_failed_plan_epoch(baseline.coverage)
            ):
                self.logger.info(
                    "failed_plan_epoch_reset method=%s retained_failed_plans=%s",
                    context.method_key,
                    [list(key) for key in sorted(failed_plan_keys)],
                )
                logs.append("failed_plan_epoch_reset")
                failed_plan_keys.clear()
                target_plan = self.select_next_target_plan(
                    context,
                    baseline,
                    failed_plan_keys,
                    trace_sink=trace_events.append,
                )
            if target_plan is None:
                stop_reason = "no_target_plan"
                self.logger.info("stop method=%s reason=%s failed_plans=%s", context.method_key, stop_reason, list(failed_plan_keys))
                break
            self.logger.info(
                "target_plan_selected method=%s lines=%s reachability=%s confidence=%s requirements=%s",
                context.method_key,
                list(target_plan.target_lines),
                target_plan.reachability,
                target_plan.confidence,
                self._requirement_summary(target_plan),
            )

            generation_plan = self._with_failure_feedback(target_plan, failed_strategy_feedback.values())
            self.logger.info(
                "patch_generation_start method=%s lines=%s failure_feedback=%s",
                context.method_key,
                list(generation_plan.target_lines),
                len(failed_strategy_feedback),
            )
            candidate = self.generate_patch(context, baseline, generation_plan)
            if candidate is None:
                stop_reason = "patch_generation_not_implemented"
                self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                break
            patch_trace = self._append_patch_trace(trace_events, generation_plan, candidate)
            self.logger.info(
                "patch_generation_done method=%s source=%s chars=%s",
                context.method_key,
                candidate.source,
                len(candidate.test_code or ""),
            )

            if not candidate.test_code and candidate.source == "patch_agent_unconfigured":
                stop_reason = "patch_generation_not_configured"
                logs.append(stop_reason)
                self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                break

            evaluation = self.patch_evaluator.evaluate(context, baseline, candidate)
            self._annotate_patch_trace(patch_trace, evaluation)
            iterations += 1
            logs.append(f"patch_evaluation:{evaluation.reason}")
            self.logger.info(
                "patch_evaluation_done method=%s accepted=%s reason=%s compile=%s test=%s repaired=%s candidate_coverage=%s",
                context.method_key,
                evaluation.accepted,
                evaluation.reason,
                evaluation.candidate.compile_success,
                evaluation.candidate.test_success,
                evaluation.candidate.repaired,
                self._coverage_summary(evaluation.candidate_baseline.coverage) if evaluation.candidate_baseline else "none",
            )

            if evaluation.accepted and evaluation.candidate_baseline is not None:
                baseline = evaluation.candidate_baseline
                no_gain_rounds = 0
                retained_failed_plan_keys = self._retain_relevant_failed_plan_keys(
                    failed_plan_keys,
                    baseline.coverage.uncovered_lines if baseline.coverage else (),
                )
                failed_plan_keys.clear()
                failed_plan_keys.update(retained_failed_plan_keys)
                failed_strategy_feedback.clear()
                logs.append("accepted_candidate")
                self.logger.info("candidate_accepted method=%s new_coverage=%s", context.method_key, self._coverage_summary(baseline.coverage))
                if failed_plan_keys:
                    self.logger.info(
                        "failed_plans_retained_after_accept method=%s count=%s plans=%s",
                        context.method_key,
                        len(failed_plan_keys),
                        [list(key) for key in sorted(failed_plan_keys)],
                    )
                if baseline.coverage.done:
                    stop_reason = "coverage_complete"
                    self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                    break
                continue

            failure_signal = getattr(evaluation, "failure_signal", None)
            failure_feedback_added = self._record_failure_feedback(failed_strategy_feedback, failure_signal)
            if failure_feedback_added:
                logs.append(f"failure_feedback_added:{failure_signal.category}")
                self.logger.info(
                    "failure_feedback_added method=%s category=%s signature=%s size=%s",
                    context.method_key,
                    failure_signal.category,
                    failure_signal.strategy_signature,
                    len(failed_strategy_feedback),
                )
            else:
                failed_plan_keys.add(self._target_plan_key(target_plan))
            if evaluation.reason == "candidate_not_runnable":
                no_gain_rounds += 1
                self.logger.info(
                    "candidate_rejected method=%s reason=%s failed_plan=%s no_gain=%s/%s",
                    context.method_key,
                    evaluation.reason,
                    list(self._target_plan_key(target_plan)),
                    no_gain_rounds,
                    self.max_no_gain_rounds,
                )
                if no_gain_rounds >= self.max_no_gain_rounds:
                    stop_reason = "candidate_not_runnable"
                    self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                    break
                continue

            no_gain_rounds += 1
            self.logger.info(
                "candidate_rejected method=%s reason=%s failed_plan=%s no_gain=%s/%s",
                context.method_key,
                evaluation.reason,
                list(self._target_plan_key(target_plan)),
                no_gain_rounds,
                self.max_no_gain_rounds,
            )
            if no_gain_rounds >= self.max_no_gain_rounds:
                stop_reason = evaluation.reason
                self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)
                break

        else:
            stop_reason = "budget_exhausted"
            self.logger.info("stop method=%s reason=%s", context.method_key, stop_reason)

        return MethodOutcome(
            context=context,
            baseline=baseline,
            success=baseline.runnable,
            iterations=iterations,
            stop_reason=stop_reason,
            logs=tuple(logs),
            trace_events=tuple(trace_events),
        )

    def normalize_method_context(self, method_result: Dict[str, Any], project_name: str = "") -> MethodContext:
        normalized = self.normalizer.normalize_method_result(
            method_result,
            result_project_name=str(project_name or method_result.get("project_name") or ""),
            repo_project_name=str(method_result.get("repo_project_name") or method_result.get("project_name") or project_name or ""),
        )
        return MethodContext(
            project_name=str(normalized.get("repo_project_name") or normalized.get("project_name") or project_name or ""),
            method_key=str(normalized.get("method_key") or ""),
            class_name=str(normalized.get("class_name") or ""),
            method_name=str(normalized.get("method_name") or ""),
            test_code=str(normalized.get("test_code") or ""),
            raw_result=normalized,
        )

    def generate_patch(
        self,
        context: MethodContext,
        baseline: Baseline,
        target_plan: TargetPlan,
    ) -> Optional[PatchCandidate]:
        return self.patch_agent.generate(context, baseline, target_plan)

    def select_next_target_plan(
        self,
        context: MethodContext,
        baseline: Baseline,
        failed_plan_keys: set[tuple[int, ...]],
        trace_sink: Optional[Any] = None,
    ) -> Optional[TargetPlan]:
        lazy_selector = getattr(self.target_planner, "select_enriched_plan", None)
        if callable(lazy_selector):
            try:
                return lazy_selector(
                    context,
                    baseline,
                    failed_plan_keys,
                    self.max_plans_per_baseline,
                    trace_sink=trace_sink,
                )
            except TypeError:
                return lazy_selector(
                    context,
                    baseline,
                    failed_plan_keys,
                    self.max_plans_per_baseline,
                )
        try:
            plans = self.target_planner.build_plans(context, baseline, trace_sink=trace_sink)
        except TypeError:
            plans = self.target_planner.build_plans(context, baseline)
        for plan in plans[:self.max_plans_per_baseline]:
            if self._target_plan_key(plan) not in failed_plan_keys:
                return plan
        return None

    @staticmethod
    def _append_patch_trace(
        trace_events: List[Dict[str, Any]],
        target_plan: TargetPlan,
        candidate: PatchCandidate,
    ) -> Optional[Dict[str, Any]]:
        metadata = candidate.metadata or {}
        prompt = str(metadata.get("prompt") or "")
        raw_output = str(metadata.get("raw_output") or "")
        if not prompt and not raw_output:
            return None
        event = {
            "stage": "patch",
            "agent": "PatchAgent",
            "target_lines": list(target_plan.target_lines),
            "reachability": target_plan.reachability,
            "prompt": prompt,
            "raw_output": raw_output,
            "candidate_source": candidate.source,
            "parsed_test_chars": len(candidate.test_code or ""),
        }
        avoid_requirements = [requirement.text for requirement in target_plan.requirements if requirement.kind == "avoid"]
        if avoid_requirements:
            event["avoid_requirements"] = avoid_requirements
        trace_events.append(event)
        return event

    @staticmethod
    def _annotate_patch_trace(patch_trace: Optional[Dict[str, Any]], evaluation) -> None:
        if patch_trace is None:
            return
        candidate = evaluation.candidate
        metadata = candidate.metadata or {}
        patch_trace.update({
            "evaluation_reason": evaluation.reason,
            "candidate_compile_success": candidate.compile_success,
            "candidate_test_success": candidate.test_success,
            "candidate_repaired": candidate.repaired,
            "candidate_final_source": candidate.source,
            "validation_output": PostProcessorV2._trim_trace_text(metadata.get("validation_output")),
            "repair_attempts": metadata.get("repair_attempts", []),
        })
        failure_signal = getattr(evaluation, "failure_signal", None)
        if failure_signal is not None:
            patch_trace.update({
                "failure_category": failure_signal.category,
                "failure_summary": failure_signal.summary,
                "failure_repairable": failure_signal.repairable,
                "failure_strategy_signature": failure_signal.strategy_signature,
            })

    def _with_failure_feedback(
        self,
        target_plan: TargetPlan,
        feedback_requirements: Iterable[Requirement],
    ) -> TargetPlan:
        if self.max_failure_feedback_requirements <= 0:
            return target_plan
        existing_avoid = {requirement.text for requirement in target_plan.requirements if requirement.kind == "avoid"}
        additions = tuple(
            requirement
            for requirement in feedback_requirements
            if requirement.text not in existing_avoid
        )
        if not additions:
            return target_plan
        return TargetPlan(
            target_lines=target_plan.target_lines,
            reachability=target_plan.reachability,
            requirements=target_plan.requirements + additions,
            confidence=target_plan.confidence,
        )

    def _record_failure_feedback(
        self,
        feedback: Dict[str, Requirement],
        failure_signal: FailureSignal | None,
    ) -> bool:
        if self.max_failure_feedback_requirements <= 0:
            return False
        if failure_signal is None or failure_signal.repairable:
            return False
        if not failure_signal.strategy_signature or not failure_signal.summary:
            return False
        if failure_signal.strategy_signature in feedback:
            return False
        while len(feedback) >= self.max_failure_feedback_requirements:
            oldest_key = next(iter(feedback))
            del feedback[oldest_key]
        feedback[failure_signal.strategy_signature] = Requirement(kind="avoid", text=failure_signal.summary)
        return True

    @staticmethod
    def _trim_trace_text(value: Any, max_chars: int = 12000) -> str:
        text = str(value or "")
        if len(text) <= max_chars:
            return text
        return text[:max_chars] + "\n... [truncated]"

    @staticmethod
    def _target_plan_key(target_plan: TargetPlan) -> tuple[int, ...]:
        return tuple(target_plan.target_lines)

    @staticmethod
    def _retain_relevant_failed_plan_keys(
        failed_plan_keys: set[tuple[int, ...]],
        uncovered_lines: Iterable[int],
    ) -> set[tuple[int, ...]]:
        uncovered = set()
        for line in uncovered_lines or ():
            try:
                uncovered.add(int(line))
            except (TypeError, ValueError):
                continue
        if not uncovered:
            return set()
        return {
            tuple(key)
            for key in failed_plan_keys
            if any(line in uncovered for line in key)
        }

    @staticmethod
    def _should_reset_failed_plan_epoch(coverage) -> bool:
        if coverage is None:
            return False
        uncovered = set()
        for line in getattr(coverage, "uncovered_lines", ()) or ():
            try:
                uncovered.add(int(line))
            except (TypeError, ValueError):
                continue
        return len(uncovered) >= 3

    @staticmethod
    def _build_method_key(class_name: str, method_name: str) -> str:
        if class_name and method_name:
            return f"{class_name}#{method_name}"
        return class_name or method_name or "unknown_method"

    @staticmethod
    def _coverage_summary(coverage) -> str:
        if coverage is None:
            return "line=0.00 branch=0.00 uncovered=0 status=unknown"
        metadata = coverage.metadata or {}
        details = []
        percentage_source = str(metadata.get("coverage_percentage_source") or "")
        handles_source = str(metadata.get("coverage_handles_source") or "")
        if percentage_source:
            details.append(f"source={percentage_source}")
        if handles_source:
            details.append(f"handles={handles_source}")
        if "coverage_method_counter_resolved" in metadata:
            details.append(f"method_counter={bool(metadata.get('coverage_method_counter_resolved'))}")
        if "coverage_method_range_resolved" in metadata:
            details.append(f"method_range={bool(metadata.get('coverage_method_range_resolved'))}")
        if metadata.get("coverage_refresh_skipped"):
            details.append(f"refresh={metadata.get('coverage_refresh_skipped')}")
        if metadata.get("coverage_refresh_after_zero_template"):
            details.append(f"refresh={metadata.get('coverage_refresh_after_zero_template')}")
        if metadata.get("coverage_reset_reason"):
            details.append(f"reset={metadata.get('coverage_reset_reason')}")
        if "previous_branch_coverage" in metadata:
            details.append(f"previous_branch={metadata.get('previous_branch_coverage')}")
        suffix = f" {' '.join(details)}" if details else ""
        return (
            f"line={coverage.line_coverage:.2f} "
            f"branch={coverage.branch_coverage:.2f} "
            f"uncovered={len(coverage.uncovered_lines)} "
            f"targets={list(coverage.uncovered_lines[:5])} "
            f"status={coverage.status}"
            f"{suffix}"
        )

    @staticmethod
    def _requirement_summary(target_plan: TargetPlan) -> Dict[str, int]:
        summary: Dict[str, int] = {}
        for requirement in target_plan.requirements:
            summary[requirement.kind] = summary.get(requirement.kind, 0) + 1
        return summary
