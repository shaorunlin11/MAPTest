# MAPTest

MAPTest generates Java unit tests in two stages: context-aware initial generation
and coverage-guided, multi-agent refinement. The repository includes eight
benchmark subjects with **3,374 focal-method entries**; see the
[dataset manifest](RepoData/manifest.json).

## Setup

Requirements: Python 3.10+, JDK 8, and Maven. Set `JAVA_HOME` and make Java and
Maven available on `PATH`.

```bash
python -m pip install -r requirements.txt
```

Copy [config/config.example.yaml](config/config.example.yaml) to
`config/config.yaml` and configure:

- `api.openai.api_key`: API key.
- `api.openai.base_url`: OpenAI-compatible endpoint.
- `default_model.name`: model ID.

Alternatively, set `MAPTEST_API_KEY`, `MAPTEST_BASE_URL`, and `MAPTEST_MODEL`.
The local configuration file is ignored by Git.

## Run

Run commands from the repository root:

```bash
# List datasets
python run.py --list-projects

# Run both stages (default)
python run.py --project Cli-40f

# Run stages separately
python run.py --project Cli-40f --stage initial
python run.py --project Cli-40f --stage refine

# Run multiple datasets
python run.py --project Cli-40f Csv-16f
```

Each selected dataset is processed in full. Default settings are temperature 0.2,
two initial repair rounds, up to eight refinement iterations, and stopping after
four rounds without a coverage gain. See the example configuration for all options.

Stage outputs are saved under `maptest/experiment_results/`:

```text
uml-based_generation/<subject>/overall_results.json
post-process-v2/<subject>/optimized_results.json
```

Use `--overwrite` to replace existing stage outputs. The pipeline executes
generated Java code; use an isolated environment and avoid concurrent runs on the
same subject directory.

## Coverage and results

After running both stages, compare their project-level coverage:

```bash
python CoverageCal/maptest_pipeline_final_coverage.py --project Cli-40f
```

Reports are written to `CoverageCal/results/`. Add `--offline` if Maven dependencies
are already cached. This command replays new stage outputs, not the saved snapshots.

[Saved experiments](results%28MAPTest%29/README.md) contain generated Java tests;
[coverage reports](results%28MAPTest%29/reports/README.md) provide the existing
JaCoCo CSV/XML results.

## Repository layout

```text
maptest/                 Main implementation
Repos/                   Benchmark source snapshots
RepoData/                Focal-method metadata, manifest, and checksums
CoverageCal/             Coverage replay tools
config/                  Example configuration
results(MAPTest)/        Saved tests and coverage results
tree_sitter_java_parser/  Optional metadata extraction
run.py                   Entry point
```


