"""
Compute project-wide coverage for the maptest generation pipeline.

This wrapper reuses the full-suite replay engine to compare:
- initial_generator baseline suite coverage
- PostProcessor final suite coverage

The output is a compact comparison report focused on the final project-level
coverage impact of the pipeline, instead of the lower-level replay details.
"""

from __future__ import annotations

import argparse
import json
import sys
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, Optional, Sequence, Tuple


CURRENT_DIR = Path(__file__).resolve().parent
WORKSPACE_DIR = CURRENT_DIR.parent
MODULES_DIR = WORKSPACE_DIR / "maptest" / "modules"

if str(CURRENT_DIR) not in sys.path:
    sys.path.insert(0, str(CURRENT_DIR))

import maptest_full_suite_coverage as full_suite


PCT_METRIC_KEYS = (
    "line_coverage_pct",
    "branch_coverage_pct",
    "method_coverage_pct",
    "class_coverage_pct",
)

COUNT_METRIC_KEYS = (
    "lines_covered",
    "lines_missed",
    "branches_covered",
    "branches_missed",
    "methods_covered",
    "methods_missed",
    "classes_covered",
    "classes_missed",
)


def _select_coverage_metrics(report: Dict[str, object]) -> Dict[str, Optional[float]]:
    coverage = report.get("coverage") or {}
    return {
        key: coverage.get(key)
        for key in (*PCT_METRIC_KEYS, *COUNT_METRIC_KEYS)
    }


def _build_stage_summary(report: Dict[str, object]) -> Dict[str, object]:
    return {
        "stage": report.get("stage"),
        "build_success": bool(report.get("build_success")),
        "raw_candidate_count": int(report.get("raw_candidate_count") or 0),
        "accepted_candidate_count": int(report.get("accepted_candidate_count") or 0),
        "final_candidate_count": int(report.get("final_candidate_count") or 0),
        "collection_issue_count": len(report.get("collection_issues") or []),
        "dropped_by_policy_count": len(report.get("dropped_by_policy") or []),
        "dropped_by_suite_path_collision_count": len(report.get("dropped_by_suite_path_collision") or []),
        "merge_replacements_count": len(report.get("merge_replacements") or []),
        "merge_additions_count": len(report.get("merge_additions") or []),
        "salvage_round_count": len(report.get("salvage_rounds") or []),
        "coverage": _select_coverage_metrics(report),
        "source_candidate_stats": report.get("source_candidate_stats") or {},
    }


def _delta(before: Optional[float], after: Optional[float]) -> Optional[float]:
    if before is None or after is None:
        return None
    return after - before


def _build_coverage_delta(initial_report: Dict[str, object], final_report: Dict[str, object]) -> Dict[str, Optional[float]]:
    initial_coverage = initial_report.get("coverage") or {}
    final_coverage = final_report.get("coverage") or {}
    return {
        key: _delta(initial_coverage.get(key), final_coverage.get(key))
        for key in PCT_METRIC_KEYS
    }


def build_pipeline_summary(
    project_name: str,
    initial_report: Dict[str, object],
    final_report: Dict[str, object],
    include_raw_stage_reports: bool = False,
) -> Dict[str, object]:
    payload: Dict[str, object] = {
        "project_name": project_name,
        "coverage_scope": "project_wide_jacoco_full_suite",
        "generated_at_utc": datetime.now(timezone.utc).isoformat(),
        "pipeline_modules": {
            "initial_generator": str(MODULES_DIR / "initial_generator.py"),
            "postprocessor": str(MODULES_DIR / "PostProcessor.py"),
            "coverage_engine": str(CURRENT_DIR / "maptest_full_suite_coverage.py"),
        },
        "comparison": {
            "baseline_stage": "initial",
            "final_stage": "post",
            "description": "Compare initial_generator baseline coverage against the final PostProcessor-replayed suite.",
        },
        "stages": {
            "initial_generator_suite": _build_stage_summary(initial_report),
            "postprocessor_final_suite": _build_stage_summary(final_report),
        },
        "candidate_delta": {
            "accepted_candidate_count": int(final_report.get("accepted_candidate_count") or 0)
            - int(initial_report.get("accepted_candidate_count") or 0),
            "final_candidate_count": int(final_report.get("final_candidate_count") or 0)
            - int(initial_report.get("final_candidate_count") or 0),
        },
        "coverage_delta_pct": _build_coverage_delta(initial_report, final_report),
    }

    if include_raw_stage_reports:
        payload["raw_stage_reports"] = {
            "initial": initial_report,
            "post": final_report,
        }

    return payload


def write_pipeline_report(project_name: str, payload: Dict[str, object], output_path: Optional[Path] = None) -> Path:
    target_path = output_path or (full_suite.RESULTS_DIR / f"{project_name}_pipeline_final_coverage.json")
    target_path.parent.mkdir(parents=True, exist_ok=True)
    target_path.write_text(json.dumps(payload, ensure_ascii=False, indent=2), encoding="utf-8")
    return target_path


def run_pipeline_coverage(
    project_name: str,
    salvage_on_failure: bool,
    salvage_round_limit: int,
    skip_ambiguous_initial: bool,
    skip_stale_post: bool,
    include_raw_stage_reports: bool,
    output_path: Optional[Path] = None,
    offline: bool = False,
) -> Tuple[Dict[str, object], Path]:
    env = full_suite._load_config_env()
    if offline:
        env["MAPTEST_COVERAGE_OFFLINE"] = "1"
    initial_report = full_suite.execute_stage(
        project_name=project_name,
        stage="initial",
        env=env,
        salvage_on_failure=salvage_on_failure,
        salvage_round_limit=salvage_round_limit,
        skip_ambiguous_initial=skip_ambiguous_initial,
        skip_stale_post=skip_stale_post,
    )
    final_report = full_suite.execute_stage(
        project_name=project_name,
        stage="post",
        env=env,
        salvage_on_failure=salvage_on_failure,
        salvage_round_limit=salvage_round_limit,
        skip_ambiguous_initial=skip_ambiguous_initial,
        skip_stale_post=skip_stale_post,
    )
    payload = build_pipeline_summary(
        project_name=project_name,
        initial_report=initial_report,
        final_report=final_report,
        include_raw_stage_reports=include_raw_stage_reports,
    )
    report_path = write_pipeline_report(project_name, payload, output_path=output_path)
    return payload, report_path


def parse_args(argv: Optional[Sequence[str]] = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Compute project-wide coverage for the initial_generator baseline and the final PostProcessor suite."
    )
    parser.add_argument("--project", required=True, help="Project name, for example: Zappos_zappos-json")
    parser.add_argument("--offline", action="store_true", help="Run Maven using only locally cached dependencies.")
    parser.add_argument(
        "--salvage-on-failure",
        action="store_true",
        help="Remove suite-breaking test files iteratively until the suite builds.",
    )
    parser.add_argument(
        "--salvage-round-limit",
        type=int,
        default=8,
        help="Maximum salvage rounds when --salvage-on-failure is enabled.",
    )
    parser.add_argument(
        "--include-ambiguous-initial",
        action="store_true",
        help="Keep initial artifacts whose saved path is shared by conflicting overloaded results.",
    )
    parser.add_argument(
        "--include-stale-post-artifacts",
        action="store_true",
        help="Keep post artifacts whose saved file header already shows compile/test failure.",
    )
    parser.add_argument(
        "--include-raw-stage-reports",
        action="store_true",
        help="Embed the raw initial/post stage reports produced by the replay engine.",
    )
    parser.add_argument(
        "--output",
        help="Optional output JSON path. Defaults to CoverageCal/results/<project>_pipeline_final_coverage.json",
    )
    return parser.parse_args(argv)


def main(argv: Optional[Sequence[str]] = None) -> int:
    args = parse_args(argv)
    payload, report_path = run_pipeline_coverage(
        project_name=args.project,
        salvage_on_failure=args.salvage_on_failure,
        salvage_round_limit=args.salvage_round_limit,
        skip_ambiguous_initial=not args.include_ambiguous_initial,
        skip_stale_post=not args.include_stale_post_artifacts,
        include_raw_stage_reports=args.include_raw_stage_reports,
        output_path=Path(args.output) if args.output else None,
        offline=args.offline,
    )
    print(json.dumps(payload, ensure_ascii=False, indent=2))
    print(f"\nSaved report: {report_path}")
    return 0 if all(stage["build_success"] for stage in payload["stages"].values()) else 1


if __name__ == "__main__":
    raise SystemExit(main())
