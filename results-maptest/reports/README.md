# Saved JaCoCo report data

Each subdirectory contains unchanged copies of `jacoco.csv` and `jacoco.xml`
from the corresponding saved project's `target/site/jacoco/` directory.
See the [artifact index](../README.md) for links and benchmark IDs.

- `jacoco.csv`: class-level covered/missed counters for instructions, branches,
  lines, complexity, and methods.
- `jacoco.xml`: report-, package-, class-, method-, and source-line-level counters.

These files are stored outside `target/` so they remain available in the public
repository. Compilation products, execution data, and the full HTML report tree
are not duplicated here. No new experiment was run to produce these exports.
