"""
Replay maptest artifacts as full test suites and measure project-wide JaCoCo coverage.

This script is intentionally stricter than the per-method validation pipeline:
- it assembles a whole-suite test tree for one project and one stage
- it detects artifact collisions and stale overwritten files
- it runs Maven with UTF-8 settings
- it optionally removes suite-breaking test files until the suite becomes buildable

Supported stages:
- initial:  initial successful suite only
- post:     final suite = initial baseline + post successful replacements
- post_only: post successful artifacts only, for diagnostics
- both:     run initial and post sequentially
"""

from __future__ import annotations

import argparse
import csv
import json
import os
import re
import shutil
import subprocess
import sys
import xml.etree.ElementTree as ET
from dataclasses import asdict, dataclass, field
from pathlib import Path
from typing import Dict, Iterable, List, Optional, Sequence, Tuple


CURRENT_DIR = Path(__file__).resolve().parent
WORKSPACE_DIR = CURRENT_DIR.parent
MAPTEST_DIR = WORKSPACE_DIR / "maptest"
REPOS_DIR = WORKSPACE_DIR / "Repos"
RESULTS_DIR = CURRENT_DIR / "results"
WORKDIRS_DIR = CURRENT_DIR / "workdirs"


PACKAGE_RE = re.compile(r"^\s*package\s+([\w.]+)\s*;", re.MULTILINE)
PUBLIC_CLASS_RE = re.compile(r"\bpublic\s+class\s+([A-Za-z_][A-Za-z0-9_]*)\b")
FALLBACK_CLASS_RE = re.compile(r"\bclass\s+([A-Za-z_][A-Za-z0-9_]*)\b")
HEADER_BOOL_RE = re.compile(r"^//\s*(Compile success|Test success):\s*(True|False|1|0)\s*$", re.MULTILINE)
COMPILE_ERROR_PATH_RE = re.compile(r"\[ERROR\]\s+([A-Za-z]:[/\\][^:\n]+?\.java|/[A-Za-z]:/[^:\n]+?\.java)")
SUREFIRE_FQN_RE = re.compile(r"\bin\s+((?:[A-Za-z_][\w$]*\.)+[A-Za-z_][\w$]*)")


def _load_runtime_config():
    if str(WORKSPACE_DIR) not in sys.path:
        sys.path.insert(0, str(WORKSPACE_DIR))
    from maptest.core.config_manager import config  # pylint: disable=import-outside-toplevel
    return config


def _load_config_env() -> Dict[str, str]:
    config = _load_runtime_config()
    env = os.environ.copy()
    java_home = config.get("java.home", "")
    java_tool_options = config.get("java.tool_options", "")
    if java_home:
        env["JAVA_HOME"] = java_home

    utf8_flag = "-Dfile.encoding=UTF-8"
    merged_opts = java_tool_options.strip()
    if utf8_flag not in merged_opts:
        merged_opts = f"{merged_opts} {utf8_flag}".strip()
    env["JAVA_TOOL_OPTIONS"] = merged_opts
    return env


def _resolve_maven_executable() -> str:
    try:
        config = _load_runtime_config()
        configured_command = config.get("maven.test_command", [])
        if isinstance(configured_command, (list, tuple)) and configured_command:
            configured_executable = str(configured_command[0]).strip()
            if configured_executable:
                configured_path = Path(configured_executable)
                if configured_path.exists() or shutil.which(configured_executable):
                    return configured_executable
    except Exception:
        pass

    for candidate in ("mvn.cmd", "mvn"):
        resolved = shutil.which(candidate)
        if resolved:
            return resolved

    return "mvn.cmd" if os.name == "nt" else "mvn"


def _read_json(path: Path) -> Dict:
    return json.loads(path.read_text(encoding="utf-8"))


def _windows_long_path(path: Path) -> Path:
    if os.name != "nt":
        return path
    raw = str(path)
    if raw.startswith("\\\\?\\"):
        return path
    resolved = path if path.is_absolute() else path.resolve()
    return Path("\\\\?\\" + str(resolved))


def _path_exists(path: Path) -> bool:
    return path.exists() or _windows_long_path(path).exists()


def _path_is_file(path: Path) -> bool:
    return path.is_file() or _windows_long_path(path).is_file()


def _safe_unlink_tree(path: Path) -> None:
    target = path.resolve()
    root = WORKDIRS_DIR.resolve()
    if target == root or root not in target.parents:
        raise ValueError(f"Refusing to remove a path outside the coverage work directory: {path}")
    if path.exists():
        shutil.rmtree(path)


def _copy_repo(source_repo: Path, target_repo: Path) -> None:
    _safe_unlink_tree(target_repo)

    def _ignore(_: str, names: List[str]) -> set:
        ignored = {".git", "target", ".idea", ".gradle", ".settings", ".pytest_cache", "__pycache__"}
        return {name for name in names if name in ignored}

    shutil.copytree(source_repo, target_repo, ignore=_ignore)


def _normalize_text_path(path: Path) -> str:
    return str(path.resolve()).replace("\\", "/").lower()


def _parse_java_identity(content: str, fallback_name: str = "") -> Tuple[str, str]:
    package_match = PACKAGE_RE.search(content)
    class_match = PUBLIC_CLASS_RE.search(content) or FALLBACK_CLASS_RE.search(content)
    package_name = package_match.group(1) if package_match else ""
    class_name = class_match.group(1) if class_match else fallback_name
    if not class_name:
        raise ValueError("Cannot resolve Java class name from artifact")
    return package_name, class_name


def _parse_header_success(content: str) -> Dict[str, Optional[bool]]:
    result = {"compile_success": None, "test_success": None}
    for key, raw_value in HEADER_BOOL_RE.findall(content):
        value = raw_value in {"True", "1"}
        if key == "Compile success":
            result["compile_success"] = value
        elif key == "Test success":
            result["test_success"] = value
    return result


def _resolve_existing_initial_source(raw_path: str, generated_root: Path) -> Optional[Path]:
    # Resolve within this run's artifact tree, even when JSON stores an old
    # absolute path. Never prefer a same-named file from another workspace.
    raw_norm = str(raw_path or "").replace("\\", "/")
    if not raw_norm or ".." in Path(raw_norm).parts:
        return None
    root = generated_root.resolve()
    marker = "GeneratedTest/"
    relative = raw_norm.split(marker, 1)[1] if marker in raw_norm else Path(raw_norm).name
    rebuilt = (generated_root / relative).resolve()
    if root in rebuilt.parents and _path_is_file(rebuilt):
        return _windows_long_path(rebuilt)
    matches = [p for p in generated_root.rglob(Path(raw_norm).name)
               if p.is_file() and root in p.resolve().parents]
    if len(matches) == 1:
        return _windows_long_path(matches[0])
    return None


def _is_success(value: object) -> bool:
    return value is True or value == 1 or str(value).strip().lower() in {"1", "true"}


def _artifact_key(item: Dict, package_name: str = "") -> str:
    namespace = str(item.get("package_name") or package_name)
    variant = str(item.get("method_variant_key") or "")
    if variant:
        return f"{namespace}::{variant}"
    signature = str(item.get("focal_signature") or "")
    return f"{namespace}::{item.get('method_key', '')}::{signature}" if signature else ""


def _validate_project_name(project_name: str) -> None:
    if not project_name or project_name in {".", ".."} or re.search(r"[/\\:]", project_name):
        raise ValueError(f"Invalid project name: {project_name!r}")


@dataclass
class Candidate:
    stage: str
    project_name: str
    method_key: str
    method_name: str
    class_name: str
    source_path: Path
    suite_rel_path: Path
    package_name: str
    generated_class_name: str
    json_success: bool
    artifact_key: str = ""
    source_header_compile_success: Optional[bool] = None
    source_header_test_success: Optional[bool] = None
    score: float = 0.0
    warnings: List[str] = field(default_factory=list)
    dropped_reason: str = ""

    @property
    def suite_abs_suffix(self) -> str:
        return ("src/test/java/" + self.suite_rel_path.as_posix()).lower()

    @property
    def fqn(self) -> str:
        return f"{self.package_name}.{self.generated_class_name}" if self.package_name else self.generated_class_name

    def to_report(self) -> Dict:
        data = asdict(self)
        data["source_path"] = str(self.source_path)
        data["suite_rel_path"] = self.suite_rel_path.as_posix()
        return data


def _initial_score(item: Dict) -> float:
    line_cov = float(item.get("line_coverage") or 0.0)
    branch_cov = float(item.get("branch_coverage") or 0.0)
    return line_cov * 1000.0 + branch_cov


def _post_score(item: Dict) -> float:
    return _initial_score(item)


def collect_initial_candidates(project_name: str) -> Tuple[List[Candidate], List[Dict]]:
    _validate_project_name(project_name)
    result_path = MAPTEST_DIR / "experiment_results" / "uml-based_generation" / project_name / "overall_results.json"
    generated_root = MAPTEST_DIR / "experiment_results" / "uml-based_generation" / project_name / "GeneratedTest"
    data = _read_json(result_path)
    details = data.get("detailed_results", [])
    path_groups: Dict[str, List[Dict]] = {}
    for item in details:
        raw_path = str(item.get("test_file_saved") or "")
        if raw_path:
            path_groups.setdefault(raw_path, []).append(item)

    candidates: List[Candidate] = []
    issues: List[Dict] = []
    for item in details:
        if not _is_success(item.get("compile_success")) or not _is_success(item.get("test_success")):
            continue

        raw_path = str(item.get("test_file_saved") or "")
        source_path = _resolve_existing_initial_source(raw_path, generated_root)
        if source_path is None:
            issues.append({
                "type": "missing_initial_source",
                "method_key": item.get("method_key", ""),
                "raw_path": raw_path,
            })
            continue

        content = source_path.read_text(encoding="utf-8", errors="ignore")
        package_name, generated_class_name = _parse_java_identity(content, f"{item.get('class_name', '')}{item.get('method_name', '')}Test")
        suite_rel_path = (Path(*package_name.split(".")) / f"{generated_class_name}.java") if package_name else Path(f"{generated_class_name}.java")

        warnings: List[str] = []
        sibling_results = path_groups.get(raw_path, [])
        if len(sibling_results) > 1:
            statuses = {(_is_success(x.get("compile_success")), _is_success(x.get("test_success")))
                        for x in sibling_results}
            warnings.append("shared_saved_path")
            if len(statuses) > 1:
                warnings.append("ambiguous_saved_path_status")

        candidates.append(Candidate(
            stage="initial",
            project_name=project_name,
            method_key=str(item.get("method_key") or ""),
            method_name=str(item.get("method_name") or ""),
            class_name=str(item.get("class_name") or ""),
            source_path=source_path,
            suite_rel_path=suite_rel_path,
            package_name=package_name,
            generated_class_name=generated_class_name,
            json_success=True,
            artifact_key=_artifact_key(item, package_name),
            score=_initial_score(item),
            warnings=warnings,
        ))

    return candidates, issues


def collect_post_candidates(project_name: str) -> Tuple[List[Candidate], List[Dict]]:
    _validate_project_name(project_name)
    result_path = MAPTEST_DIR / "experiment_results" / "post-process-v2" / project_name / "optimized_results.json"
    optimized_root = result_path.parent / "GeneratedTest"
    # A missing refinement result must not silently replay only the initial suite.
    data = _read_json(result_path)
    details = data.get("detailed_results", [])

    source_groups: Dict[str, List[Dict]] = {}
    for item in details:
        raw_path = str(item.get("test_file") or "")
        source_groups.setdefault(raw_path, []).append(item)
    shared_paths = [path for path, records in source_groups.items() if path and len(records) > 1]
    if shared_paths:
        raise ValueError(f"Refinement metadata references the same test for multiple methods: {shared_paths}. "
                         "Re-export results with the current package-aware output writer before replaying.")

    candidates: List[Candidate] = []
    issues: List[Dict] = []
    for item in details:
        if not _is_success(item.get("compile_success")) or not _is_success(item.get("test_success")):
            continue
        raw_path = str(item.get("test_file") or "")
        source_path = _resolve_existing_initial_source(raw_path, optimized_root)
        if source_path is None:
            issues.append({
                "type": "missing_post_source",
                "method_key": item.get("method_key", ""),
                "raw_path": raw_path,
            })
            continue

        content = source_path.read_text(encoding="utf-8", errors="ignore")
        package_name, generated_class_name = _parse_java_identity(content, f"{item.get('class_name', '')}Test")
        suite_rel_path = (Path(*package_name.split(".")) / f"{generated_class_name}.java") if package_name else Path(f"{generated_class_name}.java")
        header = _parse_header_success(content)

        warnings: List[str] = []
        if header["compile_success"] is False or header["test_success"] is False:
            warnings.append("stale_overwritten_artifact")

        candidates.append(Candidate(
            stage="post",
            project_name=project_name,
            method_key=str(item.get("method_key") or ""),
            method_name=str(item.get("method_name") or ""),
            class_name=str(item.get("class_name") or ""),
            source_path=source_path,
            suite_rel_path=suite_rel_path,
            package_name=package_name,
            generated_class_name=generated_class_name,
            json_success=True,
            artifact_key=_artifact_key(item, package_name),
            source_header_compile_success=header["compile_success"],
            source_header_test_success=header["test_success"],
            score=_post_score(item),
            warnings=warnings,
        ))

    return candidates, issues


def apply_filter_policy(
    candidates: List[Candidate],
    skip_ambiguous_initial: bool,
    skip_stale_post: bool,
) -> Tuple[List[Candidate], List[Dict]]:
    accepted: List[Candidate] = []
    skipped: List[Dict] = []

    for candidate in candidates:
        if skip_ambiguous_initial and candidate.stage == "initial" and "ambiguous_saved_path_status" in candidate.warnings:
            candidate.dropped_reason = "ambiguous_initial_saved_path"
            skipped.append(candidate.to_report())
            continue

        if skip_stale_post and candidate.stage == "post" and "stale_overwritten_artifact" in candidate.warnings:
            candidate.dropped_reason = "stale_overwritten_post_artifact"
            skipped.append(candidate.to_report())
            continue

        accepted.append(candidate)

    return accepted, skipped


def resolve_suite_path_collisions(candidates: List[Candidate]) -> Tuple[List[Candidate], List[Dict]]:
    grouped: Dict[str, List[Candidate]] = {}
    for candidate in candidates:
        grouped.setdefault(candidate.suite_rel_path.as_posix(), []).append(candidate)

    kept: List[Candidate] = []
    dropped: List[Dict] = []
    for suite_path, group in grouped.items():
        ordered = sorted(group, key=lambda item: (-item.score, item.method_key))
        chosen = ordered[0]
        kept.append(chosen)
        if len(ordered) > 1:
            for extra in ordered[1:]:
                extra.dropped_reason = f"suite_path_collision:{suite_path}"
                dropped.append(extra.to_report())

    return kept, dropped


def write_suite_files(repo_path: Path, candidates: Sequence[Candidate]) -> None:
    test_root = repo_path / "src" / "test" / "java"
    _safe_unlink_tree(test_root)
    test_root.mkdir(parents=True, exist_ok=True)

    for candidate in candidates:
        destination = test_root / candidate.suite_rel_path
        destination.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(candidate.source_path, destination)


def run_maven(repo_path: Path, env: Dict[str, str]) -> Tuple[bool, str]:
    command = [
        _resolve_maven_executable(),
        "-Dproject.build.sourceEncoding=UTF-8",
        "-Dproject.reporting.outputEncoding=UTF-8",
        "clean",
        "test",
    ]
    if env.get("MAPTEST_COVERAGE_OFFLINE") == "1":
        command.insert(1, "--offline")
    try:
        completed = subprocess.run(
            command, cwd=repo_path, env=env, capture_output=True, text=True,
            encoding="utf-8", errors="replace",
            timeout=float(_load_runtime_config().get("maven.timeout", 120)),
        )
    except subprocess.TimeoutExpired:
        return False, "[MAPTest] Maven timed out; coverage was not measured."
    output = (completed.stdout or "") + "\n" + (completed.stderr or "")
    success = completed.returncode == 0 and "BUILD SUCCESS" in output
    if success:
        suites = [ET.parse(path).getroot()
                  for path in (repo_path / "target" / "surefire-reports").glob("TEST-*.xml")]
        executed_count = sum(int(suite.get("tests", "0")) - int(suite.get("skipped", "0"))
                             for suite in suites)
        failed_count = sum(int(suite.get("failures", "0")) + int(suite.get("errors", "0"))
                           for suite in suites)
        if failed_count:
            return False, output + "\n[MAPTest] Test failures were found despite Maven reporting success."
        if not executed_count:
            return False, output + "\n[MAPTest] Refusing a coverage measurement: Maven executed zero tests."
    return success, output


def parse_jacoco_csv(repo_path: Path) -> Dict[str, object]:
    candidates = [
        repo_path / "target" / "site" / "jacoco" / "jacoco.csv",
        repo_path / "target" / "site" / "jacoco-ut" / "jacoco.csv",
        repo_path / "target" / "jacoco.csv",
    ]
    csv_path = next((path for path in candidates if path.exists()), None)
    if csv_path is None:
        raise FileNotFoundError("jacoco.csv not found after successful build")

    total = {
        "instruction_missed": 0,
        "instruction_covered": 0,
        "branch_missed": 0,
        "branch_covered": 0,
        "line_missed": 0,
        "line_covered": 0,
        "complexity_missed": 0,
        "complexity_covered": 0,
        "method_missed": 0,
        "method_covered": 0,
        "class_missed": 0,
        "class_covered": 0,
    }

    with csv_path.open("r", encoding="utf-8", newline="") as handle:
        reader = csv.DictReader(handle)
        for row in reader:
            total["instruction_missed"] += int(row.get("INSTRUCTION_MISSED", 0) or 0)
            total["instruction_covered"] += int(row.get("INSTRUCTION_COVERED", 0) or 0)
            total["branch_missed"] += int(row.get("BRANCH_MISSED", 0) or 0)
            total["branch_covered"] += int(row.get("BRANCH_COVERED", 0) or 0)
            total["line_missed"] += int(row.get("LINE_MISSED", 0) or 0)
            total["line_covered"] += int(row.get("LINE_COVERED", 0) or 0)
            total["complexity_missed"] += int(row.get("COMPLEXITY_MISSED", 0) or 0)
            total["complexity_covered"] += int(row.get("COMPLEXITY_COVERED", 0) or 0)
            total["method_missed"] += int(row.get("METHOD_MISSED", 0) or 0)
            total["method_covered"] += int(row.get("METHOD_COVERED", 0) or 0)
            total["class_missed"] += int(row.get("CLASS_MISSED", 0) or 0)
            total["class_covered"] += int(row.get("CLASS_COVERED", 0) or 0)
            if "CLASS_MISSED" not in row:
                # Standard JaCoCo CSV has no class counter columns.
                key = "class_covered" if int(row.get("INSTRUCTION_COVERED", 0) or 0) else "class_missed"
                total[key] += 1

    xml_path = csv_path.with_suffix(".xml")
    if xml_path.exists():
        class_counter = ET.parse(xml_path).getroot().find("counter[@type='CLASS']")
        if class_counter is not None:
            total["class_missed"] = int(class_counter.get("missed", "0"))
            total["class_covered"] = int(class_counter.get("covered", "0"))

    def pct(covered: int, missed: int) -> float:
        total_count = covered + missed
        return (covered / total_count * 100.0) if total_count else 0.0

    return {
        "jacoco_csv": str(csv_path),
        "line_coverage_pct": pct(total["line_covered"], total["line_missed"]),
        "branch_coverage_pct": pct(total["branch_covered"], total["branch_missed"]),
        "method_coverage_pct": pct(total["method_covered"], total["method_missed"]),
        "class_coverage_pct": pct(total["class_covered"], total["class_missed"]),
        **{f"{plural}_{state}": total[f"{singular}_{state}"]
           for singular, plural in (("line", "lines"), ("branch", "branches"),
                                   ("method", "methods"), ("class", "classes"))
           for state in ("covered", "missed")},
        "totals": total,
    }


def identify_failing_suite_paths(output: str, repo_path: Path, candidates: Sequence[Candidate]) -> List[str]:
    output_norm = output.replace("\\", "/").lower()
    implicated: List[str] = []
    active_by_path = {candidate.suite_rel_path.as_posix(): candidate for candidate in candidates}
    active_by_fqn = {candidate.fqn.lower(): candidate for candidate in candidates}

    for suite_path, candidate in active_by_path.items():
        absolute_path = _normalize_text_path(repo_path / "src" / "test" / "java" / candidate.suite_rel_path)
        suffix = candidate.suite_abs_suffix
        if absolute_path in output_norm or suffix in output_norm:
            implicated.append(suite_path)

    for matched_path in COMPILE_ERROR_PATH_RE.findall(output):
        norm = matched_path.replace("\\", "/").lower()
        for suite_path, candidate in active_by_path.items():
            suffix = candidate.suite_abs_suffix
            if suffix in norm:
                implicated.append(suite_path)

    for fqn in SUREFIRE_FQN_RE.findall(output):
        candidate = active_by_fqn.get(fqn.lower())
        if candidate is not None:
            implicated.append(candidate.suite_rel_path.as_posix())

    deduped = []
    seen = set()
    for suite_path in implicated:
        if suite_path not in seen:
            seen.add(suite_path)
            deduped.append(suite_path)
    return deduped


def salvage_suite_until_buildable(
    repo_path: Path,
    env: Dict[str, str],
    candidates: List[Candidate],
    max_rounds: int,
) -> Tuple[List[Candidate], List[Dict], bool, str]:
    active = list(candidates)
    removed_rounds: List[Dict] = []

    build_success, output = run_maven(repo_path, env)
    if build_success:
        return active, removed_rounds, True, output

    for round_index in range(1, max_rounds + 1):
        implicated = identify_failing_suite_paths(output, repo_path, active)
        if not implicated:
            break

        removed = []
        remaining = []
        implicated_set = set(implicated)
        for candidate in active:
            if candidate.suite_rel_path.as_posix() in implicated_set:
                removed.append(candidate)
            else:
                remaining.append(candidate)

        if not removed:
            break

        active = remaining
        write_suite_files(repo_path, active)
        removed_rounds.append({
            "round": round_index,
            "removed_suite_paths": [candidate.suite_rel_path.as_posix() for candidate in removed],
            "removed_method_keys": [candidate.method_key for candidate in removed],
        })

        build_success, output = run_maven(repo_path, env)
        if build_success:
            return active, removed_rounds, True, output

    return active, removed_rounds, False, output


def prepare_candidate_set(
    project_name: str,
    source_stage: str,
    skip_ambiguous_initial: bool,
    skip_stale_post: bool,
) -> Dict[str, object]:
    if source_stage == "initial":
        raw_candidates, collect_issues = collect_initial_candidates(project_name)
    elif source_stage == "post":
        raw_candidates, collect_issues = collect_post_candidates(project_name)
    else:
        raise ValueError(f"Unsupported source stage: {source_stage}")

    filtered_candidates, dropped_by_policy = apply_filter_policy(
        raw_candidates,
        skip_ambiguous_initial=skip_ambiguous_initial,
        skip_stale_post=skip_stale_post,
    )
    candidates, dropped_by_collision = resolve_suite_path_collisions(filtered_candidates)
    return {
        "source_stage": source_stage,
        "raw_candidate_count": len(raw_candidates),
        "collection_issues": collect_issues,
        "dropped_by_policy": dropped_by_policy,
        "dropped_by_suite_path_collision": dropped_by_collision,
        "candidates": candidates,
    }


def merge_initial_and_post_candidates(
    initial_candidates: Sequence[Candidate],
    post_candidates: Sequence[Candidate],
) -> Tuple[List[Candidate], List[Dict[str, str]], List[str]]:
    merged_by_path: Dict[str, Candidate] = {
        candidate.suite_rel_path.as_posix(): candidate for candidate in initial_candidates
    }
    replacements: List[Dict[str, str]] = []
    additions: List[str] = []

    for post_candidate in post_candidates:
        suite_path = post_candidate.suite_rel_path.as_posix()
        # Refinement may rename a test class. Replace the matching focal-method
        # variant, rather than keeping both its old and new tests in the suite.
        matched_paths = [path for path, candidate in merged_by_path.items()
                         if post_candidate.artifact_key and candidate.artifact_key == post_candidate.artifact_key]
        for old_path in matched_paths:
            if old_path != suite_path:
                previous = merged_by_path.pop(old_path)
                replacements.append({"suite_rel_path": suite_path, "previous_suite_rel_path": old_path,
                                     "replaced_method_key": previous.method_key,
                                     "replacement_method_key": post_candidate.method_key})
        previous = merged_by_path.get(suite_path)
        if previous is not None:
            replacements.append({
                "suite_rel_path": suite_path,
                "replaced_method_key": previous.method_key,
                "replacement_method_key": post_candidate.method_key,
            })
        elif not matched_paths:
            additions.append(suite_path)
        merged_by_path[suite_path] = post_candidate

    merged_candidates = sorted(
        merged_by_path.values(),
        key=lambda candidate: candidate.suite_rel_path.as_posix(),
    )
    return merged_candidates, replacements, additions


def execute_stage(
    project_name: str,
    stage: str,
    env: Dict[str, str],
    salvage_on_failure: bool,
    salvage_round_limit: int,
    skip_ambiguous_initial: bool,
    skip_stale_post: bool,
) -> Dict[str, object]:
    _validate_project_name(project_name)
    repo_source = REPOS_DIR / project_name
    if not repo_source.exists():
        raise FileNotFoundError(f"Repository not found: {repo_source}")

    merge_replacements: List[Dict[str, str]] = []
    merge_additions: List[str] = []
    source_sets: List[Dict[str, object]] = []

    if stage == "initial":
        candidate_set = prepare_candidate_set(
            project_name,
            source_stage="initial",
            skip_ambiguous_initial=skip_ambiguous_initial,
            skip_stale_post=skip_stale_post,
        )
        candidates = list(candidate_set["candidates"])
        source_sets = [candidate_set]
    elif stage == "post_only":
        candidate_set = prepare_candidate_set(
            project_name,
            source_stage="post",
            skip_ambiguous_initial=skip_ambiguous_initial,
            skip_stale_post=skip_stale_post,
        )
        candidates = list(candidate_set["candidates"])
        source_sets = [candidate_set]
    elif stage == "post":
        initial_set = prepare_candidate_set(
            project_name,
            source_stage="initial",
            skip_ambiguous_initial=skip_ambiguous_initial,
            skip_stale_post=skip_stale_post,
        )
        post_set = prepare_candidate_set(
            project_name,
            source_stage="post",
            skip_ambiguous_initial=skip_ambiguous_initial,
            skip_stale_post=skip_stale_post,
        )
        candidates, merge_replacements, merge_additions = merge_initial_and_post_candidates(
            initial_candidates=initial_set["candidates"],
            post_candidates=post_set["candidates"],
        )
        source_sets = [initial_set, post_set]
    else:
        raise ValueError(f"Unsupported stage: {stage}")

    issues = [issue for source_set in source_sets for issue in source_set["collection_issues"]]
    if issues:
        raise ValueError(f"Cannot replay {project_name}/{stage}: missing or invalid artifacts: {issues}")
    if not candidates or (stage == "post" and not post_set["candidates"]):
        raise ValueError(f"No runnable test artifacts for {project_name}/{stage}; coverage was not measured.")

    stage_repo = WORKDIRS_DIR / project_name / stage
    _copy_repo(repo_source, stage_repo)
    write_suite_files(stage_repo, candidates)

    build_success, output = run_maven(stage_repo, env)
    salvage_rounds: List[Dict] = []
    final_candidates = list(candidates)

    if not build_success and salvage_on_failure:
        final_candidates, salvage_rounds, build_success, output = salvage_suite_until_buildable(
            stage_repo,
            env,
            candidates,
            max_rounds=salvage_round_limit,
        )

    coverage: Optional[Dict[str, object]] = None
    if build_success:
        coverage = parse_jacoco_csv(stage_repo)

    output_tail = "\n".join(output.splitlines()[-80:])
    source_candidate_stats = {
        candidate_set["source_stage"]: {
            "raw_candidate_count": candidate_set["raw_candidate_count"],
            "accepted_candidate_count": len(candidate_set["candidates"]),
            "collection_issue_count": len(candidate_set["collection_issues"]),
            "dropped_by_policy_count": len(candidate_set["dropped_by_policy"]),
            "dropped_by_suite_path_collision_count": len(candidate_set["dropped_by_suite_path_collision"]),
        }
        for candidate_set in source_sets
    }
    report = {
        "project_name": project_name,
        "stage": stage,
        "repo_source": str(repo_source),
        "stage_repo": str(stage_repo),
        "raw_candidate_count": sum(int(candidate_set["raw_candidate_count"]) for candidate_set in source_sets),
        "accepted_candidate_count": len(candidates),
        "final_candidate_count": len(final_candidates),
        "build_success": build_success,
        "coverage": coverage,
        "source_candidate_stats": source_candidate_stats,
        "collection_issues": [
            issue
            for candidate_set in source_sets
            for issue in candidate_set["collection_issues"]
        ],
        "dropped_by_policy": [
            dropped
            for candidate_set in source_sets
            for dropped in candidate_set["dropped_by_policy"]
        ],
        "dropped_by_suite_path_collision": [
            dropped
            for candidate_set in source_sets
            for dropped in candidate_set["dropped_by_suite_path_collision"]
        ],
        "merge_replacements": merge_replacements,
        "merge_additions": merge_additions,
        "salvage_rounds": salvage_rounds,
        "active_candidates": [candidate.to_report() for candidate in final_candidates],
        "output_tail": output_tail,
    }
    return report


def write_report(project_name: str, payload: Dict[str, object]) -> Path:
    RESULTS_DIR.mkdir(parents=True, exist_ok=True)
    output_path = RESULTS_DIR / f"{project_name}_full_suite_coverage.json"
    output_path.write_text(json.dumps(payload, ensure_ascii=False, indent=2), encoding="utf-8")
    return output_path


def parse_args(argv: Optional[Sequence[str]] = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Replay maptest artifacts and compute full-suite coverage.")
    parser.add_argument("--project", required=True, help="Project name, for example: Zappos_zappos-json")
    parser.add_argument("--offline", action="store_true", help="Run Maven using only locally cached dependencies.")
    parser.add_argument(
        "--stage",
        choices=["initial", "post", "post_only", "both"],
        default="both",
        help="Which artifact stage to replay.",
    )
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
    return parser.parse_args(argv)


def main(argv: Optional[Sequence[str]] = None) -> int:
    args = parse_args(argv)
    env = _load_config_env()
    if args.offline:
        env["MAPTEST_COVERAGE_OFFLINE"] = "1"
    stages = ["initial", "post"] if args.stage == "both" else [args.stage]

    payload: Dict[str, object] = {
        "project_name": args.project,
        "requested_stage": args.stage,
        "salvage_on_failure": args.salvage_on_failure,
        "salvage_round_limit": args.salvage_round_limit,
        "reports": [],
    }

    for stage in stages:
        report = execute_stage(
            project_name=args.project,
            stage=stage,
            env=env,
            salvage_on_failure=args.salvage_on_failure,
            salvage_round_limit=args.salvage_round_limit,
            skip_ambiguous_initial=not args.include_ambiguous_initial,
            skip_stale_post=not args.include_stale_post_artifacts,
        )
        payload["reports"].append(report)

    report_path = write_report(args.project, payload)
    print(json.dumps(payload, ensure_ascii=False, indent=2))
    print(f"\nSaved report: {report_path}")
    return 0 if all(report["build_success"] for report in payload["reports"]) else 1


if __name__ == "__main__":
    raise SystemExit(main())
