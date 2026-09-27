import argparse
import sys
from pathlib import Path

MODULE_DIR = Path(__file__).resolve().parent
PROJECT_ROOT = MODULE_DIR.parent
WORKSPACE_ROOT = PROJECT_ROOT.parent


def _bootstrap_sys_path() -> None:
    for path in (WORKSPACE_ROOT,):
        path_str = str(path)
        if path_str not in sys.path:
            sys.path.insert(0, path_str)


_bootstrap_sys_path()

from maptest.core import get_logger, setup_logging  # noqa: E402
from maptest.modules.postprocessor import build_postprocessor_v2_with_llm_patch_and_planning  # noqa: E402


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Run PostProcessor V2.")
    parser.add_argument(
        "projects",
        nargs="+",
        help="Project folder name(s) under experiment_results/uml-based_generation.",
    )
    parser.add_argument("--model", default=None, help="Optional model name; defaults to global LLM config.")
    parser.add_argument("--patch-model", default=None, help="Optional patch-agent model override.")
    parser.add_argument("--planning-model", default=None, help="Optional planning-agent model override.")
    parser.add_argument("--temperature", type=float, default=0.2)
    parser.add_argument("--max-tokens", type=int, default=None)
    parser.add_argument("--max-iterations", type=int, default=None)
    parser.add_argument("--max-no-gain-rounds", type=int, default=None)
    parser.add_argument("--max-plans-per-baseline", type=int, default=None)
    parser.add_argument(
        "--results-root",
        type=Path,
        default=None,
        help="Defaults to experiment_results/uml-based_generation.",
    )
    parser.add_argument("--output-root", type=Path, default=None)
    parser.add_argument("--repos-dir", type=Path, default=None)
    parser.add_argument("--debug-planning", action="store_true", help="Show per-agent planning logs for every built plan.")
    return parser.parse_args()


def config_overrides_from_args(args: argparse.Namespace) -> dict:
    overrides = {}
    if args.max_iterations is not None:
        overrides["max_iterations"] = args.max_iterations
    if args.max_no_gain_rounds is not None:
        overrides["max_no_gain_rounds"] = args.max_no_gain_rounds
    if args.max_plans_per_baseline is not None:
        overrides["max_plans_per_baseline"] = args.max_plans_per_baseline
    return overrides


def main() -> int:
    args = parse_args()
    setup_logging("DEBUG" if args.debug_planning else "INFO")
    logger = get_logger("PostProcessorV2")

    processor = build_postprocessor_v2_with_llm_patch_and_planning(
        model_name=args.model,
        patch_model_name=args.patch_model,
        planning_model_name=args.planning_model,
        temperature=args.temperature,
        max_tokens=args.max_tokens,
        config_overrides=config_overrides_from_args(args),
        results_root=args.results_root,
        output_root=args.output_root,
        repos_dir=args.repos_dir,
    )

    success = True
    for project_name in args.projects:
        logger.info(f"Processing project with PostProcessor V2: {project_name}")
        outcomes = processor.process_project(project_name)
        project_success = all(outcome.success for outcome in outcomes)
        success = success and project_success
        logger.info(
            "PostProcessor V2 finished %s: methods=%s success=%s",
            project_name,
            len(outcomes),
            project_success,
        )

    return 0 if success else 1


if __name__ == "__main__":
    raise SystemExit(main())
