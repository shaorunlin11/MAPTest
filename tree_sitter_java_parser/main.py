"""Extract focal-method metadata without a model call."""
import argparse
from pathlib import Path
import sys

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT))


def main(argv=None):
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--project', required=True, help='Directory name under Repos.')
    parser.add_argument('--project-path', type=Path, help='Optional external project directory.')
    parser.add_argument('--source-code-path', default='src/main')
    parser.add_argument('--output-dir', type=Path, default=ROOT / 'RepoData')
    parser.add_argument('--overwrite', action='store_true')
    args = parser.parse_args(argv)
    project = (args.project_path or ROOT / 'Repos' / args.project).resolve()
    if project.name != args.project or not project.is_dir():
        parser.error('--project must match the existing project directory name')
    output = args.output_dir.resolve() / f'{args.project}.json'
    if output.exists() and not args.overwrite:
        parser.error(f'{output} already exists; use a separate --output-dir or --overwrite')
    from tree_sitter_java_parser.utils.Pipeline import static_analyze, load_focal_method
    classes = static_analyze(str(project), args.source_code_path)
    output.parent.mkdir(parents=True, exist_ok=True)
    output.write_text('[]', encoding='utf-8')
    load_focal_method(classes, str(project), output_dir=output.parent)
    count = sum(len(cls.testable_methods) for cls in classes)
    print(f'Extracted {count} focal methods to {output}')
    return 0


if __name__ == '__main__':
    raise SystemExit(main())
