"""Portable command-line entry point for the complete MAPTest workflow."""
from __future__ import annotations

import argparse
import json
import os
from pathlib import Path
import sys

ROOT = Path(__file__).resolve().parent


def parse_args(argv=None):
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--project', nargs='+', help='Dataset ID(s); see --list-projects.')
    parser.add_argument('--list-projects', action='store_true')
    parser.add_argument('--stage', choices=('initial', 'refine', 'all'), default='all')
    parser.add_argument('--config', type=Path, help='Local YAML configuration.')
    parser.add_argument('--model', help='Override the configured model name.')
    parser.add_argument('--temperature', type=float, help='Override the configured temperature.')
    parser.add_argument('--max-iterations', type=int)
    parser.add_argument('--max-no-gain-rounds', type=int)
    parser.add_argument('--dry-run', action='store_true', help='Check inputs without calling an LLM or Maven.')
    parser.add_argument('--overwrite', action='store_true', help='Allow replacing existing stage output for these projects.')
    args = parser.parse_args(argv)
    if not args.list_projects and not args.project:
        parser.error('--project is required unless --list-projects is used')
    return args


def main(argv=None):
    args = parse_args(argv)
    manifest = json.loads((ROOT / 'RepoData' / 'manifest.json').read_text(encoding='utf-8'))
    projects = {row['id']: row for row in manifest['datasets']}
    if args.list_projects:
        for name, row in projects.items():
            print(f"{name}: {row['focal_methods']} focal methods")
        return 0
    if args.config:
        os.environ['MAPTEST_CONFIG'] = str(args.config.resolve())
    os.chdir(ROOT)
    if str(ROOT) not in sys.path:
        sys.path.insert(0, str(ROOT))
    from maptest.core import config, setup_logging

    if args.model:
        config._set_nested_value(['default_model', 'name'], args.model)
    if args.temperature is not None:
        config._set_nested_value(['default_model', 'temperature'], args.temperature)
    model = args.model or config.get('default_model.name')
    temperature = config.get('default_model.temperature', 0.2)
    initial_root = ROOT / 'maptest' / 'experiment_results' / 'uml-based_generation'
    refine_root = ROOT / 'maptest' / 'experiment_results' / 'post-process-v2'
    for name in args.project:
        if name not in projects:
            raise SystemExit(f'Unknown project: {name}; use --list-projects.')
        if not (ROOT / projects[name]['metadata']).is_file() or not (ROOT / projects[name]['source'] / 'pom.xml').is_file():
            raise SystemExit(f'Missing source or metadata for {name}')
        if args.stage == 'refine' and not (initial_root / name / 'overall_results.json').is_file():
            raise SystemExit(f'Run the initial stage for {name} before refinement.')
        if not args.dry_run and not args.overwrite:
            outputs = []
            if args.stage in ('initial', 'all'):
                outputs.append(initial_root / name)
            if args.stage in ('refine', 'all'):
                outputs.append(refine_root / name)
            for output in outputs:
                if output.exists() and any(output.iterdir()):
                    raise SystemExit(f'Output already exists: {output}. Back it up or use --overwrite.')
        print(f"{name}: stage={args.stage}, methods={projects[name]['focal_methods']}, model={model}")
    if args.dry_run:
        print('Dry run complete. No model requests or Java builds were executed.')
        return 0
    model_config = config.get_model_config(model)
    if not model_config['api_key'] or not model_config['base_url']:
        raise SystemExit('Set MAPTEST_API_KEY and MAPTEST_BASE_URL (a local server may use a dummy key).')
    setup_logging('INFO')
    from maptest.modules.initial_generation import InitialGenerator
    from maptest.modules.postprocessor import build_postprocessor_v2_with_llm_patch_and_planning

    overrides = {}
    if args.max_iterations is not None:
        overrides['max_iterations'] = args.max_iterations
    if args.max_no_gain_rounds is not None:
        overrides['max_no_gain_rounds'] = args.max_no_gain_rounds
    success = True
    for name in args.project:
        if args.stage in ('initial', 'all'):
            generator = InitialGenerator('maptest', model_name=model, context_mode='uml')
            initial_ok = generator.process_project(str(ROOT / projects[name]['metadata']))
            if not initial_ok:
                success = False
                continue
        if args.stage in ('refine', 'all'):
            processor = build_postprocessor_v2_with_llm_patch_and_planning(
                model_name=model, temperature=temperature, config_overrides=overrides,
                results_root=initial_root, output_root=refine_root, repos_dir=ROOT / 'Repos',
            )
            outcomes = processor.process_project(name)
            success = success and all(outcome.success for outcome in outcomes)
    return 0 if success else 1


if __name__ == '__main__':
    raise SystemExit(main())
