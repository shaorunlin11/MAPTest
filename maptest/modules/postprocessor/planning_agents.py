from __future__ import annotations

import json
import re
from dataclasses import dataclass
from typing import Any, Callable, Dict, List, Optional, Protocol, Tuple

from .mock_evidence import MockEvidence, MockEvidenceBuilder
from .models import Baseline, MethodContext, Requirement
from .state_evidence import StateEvidence, StateEvidenceBuilder

PlanningRequirementGenerator = Callable[[str], str]


@dataclass(frozen=True)
class PlanningAgentInput:
    context: MethodContext
    baseline: Baseline
    target_lines: Tuple[int, ...]
    reachability: str
    requirements_so_far: Tuple[Requirement, ...] = ()
    cfg_path: Tuple[str, ...] = ()
    path_signature: Tuple[str, ...] = ()
    uncovered_line_details: Tuple[Dict[str, Any], ...] = ()
    focal_method_code: str = ""
    trace_sink: Optional[Callable[[Dict[str, Any]], None]] = None


class PlanningRequirementAgent(Protocol):
    def build_requirements(self, planning_input: PlanningAgentInput) -> List[Requirement]:
        ...


class EmptyPlanningAgent:
    def build_requirements(self, planning_input: PlanningAgentInput) -> List[Requirement]:
        return []


class PathRequirementAgent:
    def __init__(
        self,
        generator: Optional[PlanningRequirementGenerator] = None,
        max_requirements: int = 3,
    ) -> None:
        self.generator = generator
        self.max_requirements = max(1, int(max_requirements))

    def build_requirements(self, planning_input: PlanningAgentInput) -> List[Requirement]:
        if self.generator is None:
            return []
        prompt = self.build_prompt(planning_input)
        raw_output = self.generator(prompt)
        requirements = self._parse_requirements(raw_output, allowed_kind="condition")[:self.max_requirements]
        self._emit_trace(planning_input, "PathRequirementAgent", prompt, raw_output, requirements)
        return requirements

    def build_prompt(self, planning_input: PlanningAgentInput) -> str:
        return "\n\n".join(
            [
                self._system_prompt(),
                self._user_prompt(planning_input),
            ]
        ).strip()

    @staticmethod
    def _system_prompt() -> str:
        return "\n".join(
            [
                "Analyze the CFG path below and state only the conditions a unit test must satisfy to reach its target source lines.",
                "",
                "You will receive a Java focal method, a group of target source lines, and the entry-to-target CFG path that reaches those lines.",
                "",
                "The target lines are grouped by CFG reachability path, not by line-number adjacency. Treat them as one path target only because the planner found the same or very similar entry-to-target path.",
                "",
                "Return only requirements that tell a later test generator what input or branch condition must be satisfied to execute this path.",
                "If evidence is incomplete, leave the requirement out rather than turning absence of evidence into a constraint.",
                "",
                "Allowed output is a JSON array. Each item must be:",
                '{"kind": "condition", "text": "..."}',
                "",
                "Do not write Java code.",
                "Do not describe mocks, object construction, assertions, expected results, routes, fallback modes, retries, or confidence.",
                "If no reliable condition is supported by the method code and CFG path, return [].",
            ]
        )

    @classmethod
    def _user_prompt(cls, planning_input: PlanningAgentInput) -> str:
        context = planning_input.context
        return "\n".join(
            [
                "# Path Condition Extraction",
                "",
                "## Target Path Group",
                f"- Focal class: {context.class_name}",
                f"- Focal method: {context.method_name}",
                f"- Target source lines: {list(planning_input.target_lines)}",
                f"- Primary target line: {planning_input.target_lines[0]}",
                f"- Reachability: {planning_input.reachability}",
                "- Grouping basis: same or very similar CFG entry-to-target path",
                "",
                "## Target Line Mapping",
                cls._format_target_line_mapping(planning_input),
                "",
                "## Path Signature",
                cls._format_path_signature(planning_input.path_signature),
                "",
                "## CFG Path To Target",
                cls._format_cfg_path(planning_input),
                "",
                "## Focal Method Code",
                "```java",
                cls._add_source_line_numbers(planning_input),
                "```",
                "",
                "## Task",
                "Identify the minimum input or branch conditions needed to execute the CFG path above and reach the target source lines.",
                "",
                "Look for:",
                "- null or non-null parameter values",
                "- boolean outcomes",
                "- string values such as empty, non-empty, or equal to a visible literal",
                "- numeric comparisons such as less than, equal to, greater than, or range checks",
                "- enum constants visible in the method",
                "- collection or map conditions such as empty, non-empty, contains key, or missing key",
                "- exception conditions only when the path enters a catch or throw branch",
                "",
                "Use the CFG path first, then confirm it against the numbered method code.",
                "Do not infer conditions only because target lines are adjacent.",
                "Do not restate the target line as a condition.",
                "Use only values, helper methods, fields, APIs, or collaborators that are visible here.",
                "",
                "## Output",
                "Return only a JSON array:",
                "[",
                '  {"kind": "condition", "text": "..."}',
                "]",
            ]
        )

    @staticmethod
    def _format_path_signature(path_signature: Tuple[str, ...]) -> str:
        return " -> ".join(path_signature) if path_signature else "No CFG path signature was resolved."

    @staticmethod
    def _format_cfg_path(planning_input: PlanningAgentInput) -> str:
        if planning_input.cfg_path:
            return "\n".join(f"- {step}" for step in planning_input.cfg_path)
        return (
            "No CFG path was resolved for this target. Extract only obvious branch/input "
            "conditions visible near the target line. If the condition is not clear, return []."
        )

    @staticmethod
    def _add_line_numbers(code: str) -> str:
        lines = str(code or "").strip().splitlines()
        if not lines:
            return ""
        width = len(str(len(lines)))
        return "\n".join(f"{str(index).rjust(width)} | {line}" for index, line in enumerate(lines, 1))

    @classmethod
    def _add_source_line_numbers(cls, planning_input: PlanningAgentInput) -> str:
        lines = str(planning_input.focal_method_code or "").strip().splitlines()
        if not lines:
            return ""
        start_line = cls._focal_start_line(planning_input)
        last_line = start_line + len(lines) - 1 if start_line else len(lines)
        width = len(str(last_line))
        numbered_lines = []
        for index, line in enumerate(lines, 1):
            display_line = start_line + index - 1 if start_line else index
            numbered_lines.append(f"{str(display_line).rjust(width)} | {line}")
        return "\n".join(numbered_lines)

    @classmethod
    def _format_target_line_mapping(cls, planning_input: PlanningAgentInput) -> str:
        start_line = cls._focal_start_line(planning_input)
        code_lines = str(planning_input.focal_method_code or "").strip().splitlines()
        rows = []
        for source_line in planning_input.target_lines:
            relative_line = source_line - start_line + 1 if start_line and source_line >= start_line else source_line
            code = code_lines[relative_line - 1].strip() if 1 <= relative_line <= len(code_lines) else ""
            if start_line:
                rows.append(
                    f"- source line {source_line} -> focal method line {relative_line}"
                    + (f": `{code}`" if code else "")
                )
            else:
                rows.append(
                    f"- target line {source_line}; no focal_method_start_line was available, "
                    "so the method code below is numbered from 1."
                    + (f" Code at displayed line {relative_line}: `{code}`" if code else "")
                )
        return "\n".join(rows) if rows else "- none"

    @staticmethod
    def _focal_start_line(planning_input: PlanningAgentInput) -> int:
        raw_result = getattr(planning_input.context, "raw_result", {}) or {}
        try:
            return int(raw_result.get("focal_method_start_line") or 0)
        except (TypeError, ValueError):
            return 0

    @staticmethod
    def _emit_trace(
        planning_input: PlanningAgentInput,
        agent_name: str,
        prompt: str,
        raw_output: str,
        requirements: List[Requirement],
    ) -> None:
        if planning_input.trace_sink is None:
            return
        planning_input.trace_sink({
            "stage": "planning",
            "agent": agent_name,
            "target_lines": list(planning_input.target_lines),
            "reachability": planning_input.reachability,
            "prompt": prompt,
            "raw_output": str(raw_output or ""),
            "parsed_requirements": [requirement.to_dict() for requirement in requirements],
        })

    @classmethod
    def _parse_requirements(cls, raw_output: str, allowed_kind: str) -> List[Requirement]:
        payload = cls._extract_json_payload(raw_output)
        try:
            parsed = json.loads(payload)
        except (TypeError, ValueError):
            return []
        if not isinstance(parsed, list):
            return []

        requirements: List[Requirement] = []
        seen: set[tuple[str, str]] = set()
        for item in parsed:
            if not isinstance(item, dict):
                continue
            kind = str(item.get("kind") or "").strip().lower()
            text = str(item.get("text") or "").strip()
            if kind != allowed_kind or not cls._valid_requirement_text(text):
                continue
            key = (kind, re.sub(r"\s+", " ", text).lower())
            if key in seen:
                continue
            seen.add(key)
            try:
                requirements.append(Requirement(kind=kind, text=text))
            except ValueError:
                continue
        return requirements

    @staticmethod
    def _extract_json_payload(raw_output: str) -> str:
        text = str(raw_output or "").strip()
        if not text:
            return "[]"
        json_block = re.search(r"```(?:json)?\s*(.*?)```", text, re.DOTALL | re.IGNORECASE)
        if json_block:
            return json_block.group(1).strip()
        decoder = json.JSONDecoder()
        for match in re.finditer(r"\[", text):
            try:
                parsed, end = decoder.raw_decode(text[match.start():])
            except ValueError:
                continue
            if isinstance(parsed, list):
                return text[match.start():match.start() + end].strip()
        return text

    @staticmethod
    def _valid_requirement_text(text: str) -> bool:
        if not text or "```" in text:
            return False
        lowered = text.strip().lower()
        return not (
            lowered.startswith("cover line")
            or lowered.startswith("cover target")
            or lowered.startswith("write a test")
            or lowered.startswith("add a test")
        )


class DependencyStrategyAgent:
    def __init__(
        self,
        generator: Optional[PlanningRequirementGenerator] = None,
        evidence_builder: Optional[MockEvidenceBuilder] = None,
        max_requirements: int = 3,
    ) -> None:
        self.generator = generator
        self.evidence_builder = evidence_builder or MockEvidenceBuilder()
        self.max_requirements = max(1, int(max_requirements))

    def build_requirements(self, planning_input: PlanningAgentInput) -> List[Requirement]:
        if self.generator is None:
            return []
        evidence = self.evidence_builder.build(planning_input)
        prompt = self.build_prompt(planning_input, evidence)
        raw_output = self.generator(prompt)
        requirements = self._parse_dependency_requirements(raw_output)[:self.max_requirements]
        PathRequirementAgent._emit_trace(planning_input, "DependencyStrategyAgent", prompt, raw_output, requirements)
        return requirements

    def build_prompt(self, planning_input: PlanningAgentInput, evidence: Optional[MockEvidence] = None) -> str:
        evidence = evidence or self.evidence_builder.build(planning_input)
        return "\n\n".join(
            [
                self._system_prompt(),
                self._user_prompt(planning_input, evidence),
            ]
        ).strip()

    @staticmethod
    def _system_prompt() -> str:
        return "\n".join(
            [
                "Decide how listed receiver or collaborator calls should be handled to reach the target CFG path.",
                "",
                "Use the target plan requirements so far and CFG path as fixed route evidence. Do not choose a different route.",
                "",
                "Analyze only the candidate calls listed in the prompt. Do not invent new calls, receivers, collaborators, or helper APIs.",
                "",
                "Use UML Dependency Evidence as the only static dependency identity source.",
                "If UML does not identify a receiver as a focal-class field or UML-declared class, treat its static dependency identity as unresolved.",
                "Do not infer dependency or mockability from receiver names, naming conventions, or low-confidence guesses.",
                "",
                "Prefer real construction, method inputs, and object state setup over mocks when they can satisfy the path.",
                "Use a mock requirement only when UML identifies a collaborator/static dependency and path evidence shows its return value or exception controls whether the target path can continue.",
                "Use a dependency requirement for non-mock handling guidance such as using real instances, class literals, public APIs, or existing object state.",
                "Use an avoid requirement only to prevent a likely bad dependency strategy, such as mocking local values, method parameters, self calls, JDK utilities, or receivers unresolved by UML.",
                "",
                "Return only a JSON array.",
                "",
                "Allowed items are:",
                '{"kind": "dependency", "text": "..."}',
                '{"kind": "mock", "text": "..."}',
                '{"kind": "avoid", "text": "..."}',
                "",
                "If no listed call needs dependency handling guidance, return [].",
                "",
                "Do not write Java code or Mockito syntax.",
                "Do not output state requirements; the state agent handles concrete state setup.",
                "Do not output input conditions; the path agent handles conditions.",
                "Do not describe assertions, expected results, routes, fallback modes, retries, or confidence.",
            ]
        )

    @classmethod
    def _user_prompt(cls, planning_input: PlanningAgentInput, evidence: MockEvidence) -> str:
        context = planning_input.context
        return "\n".join(
            [
                "# Dependency Strategy Extraction",
                "",
                "## Target Path",
                f"- Focal class: {context.class_name}",
                f"- Focal method: {context.method_name}",
                f"- Target source lines: {list(planning_input.target_lines)}",
                "- Grouping basis: same or very similar CFG entry-to-target path",
                "",
                "## Target Line Mapping",
                PathRequirementAgent._format_target_line_mapping(planning_input),
                "",
                "## Target Plan Requirements So Far",
                cls._format_requirements_so_far(planning_input.requirements_so_far),
                "",
                "## CFG Path To Target",
                PathRequirementAgent._format_cfg_path(planning_input),
                "",
                "## Candidate Calls",
                cls._format_lines(evidence.candidate_calls),
                "",
                "## Path Call Dependency Facts",
                cls._format_lines(evidence.path_call_dependency_facts),
                "",
                "## UML Dependency Evidence",
                cls._format_lines(evidence.uml_dependency_facts),
                "",
                "## Receiver Roles",
                cls._format_lines(evidence.receiver_roles),
                "",
                "## Focal Method Code",
                "```java",
                PathRequirementAgent._add_source_line_numbers(planning_input),
                "```",
                "",
                "## Task",
                "For each listed candidate call that affects reaching the target path, decide the least intrusive dependency handling strategy.",
                "",
                "Only output requirements useful to the later test generator.",
                "If no candidate call needs special dependency handling, return [].",
                "",
                "Decision order:",
                "1. If UML does not identify the receiver, do not create a mock requirement for it.",
                "2. If the receiver is controlled by a method parameter, local value, self call, or JDK/value API, prefer no dependency requirement; use avoid only to prevent a likely bad mock.",
                "3. If a UML-identified dependency can be handled through real objects, class literals, public APIs, or existing state, output a dependency requirement.",
                "4. If UML identifies a collaborator/static dependency and path evidence shows its result or exception must be controlled, output a mock requirement.",
                "",
                "Do not mock calls only because they appear in the method.",
                "Do not mock downstream local value calls when an upstream collaborator creates that value.",
                "Do not repeat existing requirements from Target Plan Requirements So Far.",
                "Use only calls from the candidate list.",
                "Use UML Dependency Evidence for static dependency identity; use Receiver Roles only as a readable summary of UML and local/parameter facts.",
                "Do not generate Mockito code.",
                "",
                "## Output",
                "Return only a JSON array:",
                "[",
                '  {"kind": "dependency", "text": "..."},',
                '  {"kind": "mock", "text": "..."},',
                '  {"kind": "avoid", "text": "..."}',
                "]",
            ]
        )

    @classmethod
    def _parse_dependency_requirements(cls, raw_output: str) -> List[Requirement]:
        requirements = cls._parse_strategy_requirements(raw_output)
        normalized_requirements: List[Requirement] = []
        seen: set[tuple[str, str]] = set()
        for requirement in requirements:
            if requirement.kind == "mock":
                text = cls._normalize_mock_requirement_text(requirement.text)
            else:
                text = cls._normalize_dependency_requirement_text(requirement.text)
            if not text:
                continue
            key = (requirement.kind, re.sub(r"\s+", " ", text).lower())
            if key in seen:
                continue
            seen.add(key)
            normalized_requirements.append(Requirement(kind=requirement.kind, text=text))
        return normalized_requirements

    @classmethod
    def _parse_strategy_requirements(cls, raw_output: str) -> List[Requirement]:
        payload = PathRequirementAgent._extract_json_payload(raw_output)
        try:
            parsed = json.loads(payload)
        except (TypeError, ValueError):
            return []
        if not isinstance(parsed, list):
            return []

        requirements: List[Requirement] = []
        allowed_kinds = {"dependency", "mock", "avoid"}
        for item in parsed:
            if not isinstance(item, dict):
                continue
            kind = str(item.get("kind") or "").strip().lower()
            text = str(item.get("text") or "").strip()
            if kind not in allowed_kinds or not PathRequirementAgent._valid_requirement_text(text):
                continue
            try:
                requirements.append(Requirement(kind=kind, text=text))
            except ValueError:
                continue
        return requirements

    @staticmethod
    def _valid_mock_requirement_text(text: str) -> bool:
        return bool(MockRequirementAgent._normalize_mock_requirement_text(text))

    @staticmethod
    def _normalize_mock_requirement_text(text: str) -> str:
        original = str(text or "").strip()
        lowered = original.lower()
        if not lowered:
            return ""
        if lowered in {"mock all dependencies", "mock dependencies", "mock all collaborators"}:
            return ""
        if re.search(r"\bverify\s*\(", original, re.IGNORECASE):
            return ""
        simplified = re.sub(r"\bMockito\.", "", original)
        match = re.search(
            r"\b(?:when|given)\s*\(\s*(?P<call>.*?)\s*\)\s*\.thenReturn\s*\(\s*(?P<value>.*?)\s*\)",
            simplified,
            re.DOTALL | re.IGNORECASE,
        )
        if match:
            return f"mock {match.group('call').strip()} to return {match.group('value').strip()}"
        match = re.search(
            r"\b(?:doThrow|doReturn)\s*\(\s*(?P<value>.*?)\s*\)\s*\.when\s*\(\s*(?P<receiver>.*?)\s*\)\s*\.\s*(?P<member>[A-Za-z_][A-Za-z0-9_]*\s*\(.*?\))",
            simplified,
            re.DOTALL | re.IGNORECASE,
        )
        if match:
            action = "throw" if "dothrow" in match.group(0).lower() else "return"
            return (
                f"mock {match.group('receiver').strip()}.{match.group('member').strip()} "
                f"to {action} {match.group('value').strip()}"
            )
        if re.search(r"\b(?:when|given|doReturn|doThrow|doAnswer|thenReturn)\s*\(", simplified, re.IGNORECASE):
            return ""
        return original

    @staticmethod
    def _normalize_dependency_requirement_text(text: str) -> str:
        original = str(text or "").strip()
        if not original or "```" in original:
            return ""
        if re.search(r"\b(?:when|given|doReturn|doThrow|doAnswer|thenReturn|Mockito\.)\s*\(", original, re.IGNORECASE):
            return ""
        return original

    @staticmethod
    def _format_requirements_so_far(requirements: Tuple[Requirement, ...]) -> str:
        visible_requirements = [
            requirement
            for requirement in requirements
            if not (
                requirement.kind == "condition"
                and str(requirement.text or "").strip().lower().startswith("cfg path:")
            )
        ]
        if not visible_requirements:
            return "- none"
        return "\n".join(f"- [{requirement.kind}] {requirement.text}" for requirement in visible_requirements)

    @staticmethod
    def _format_lines(lines: Tuple[str, ...]) -> str:
        return "\n".join(lines) if lines else "- none"


class StateRequirementAgent:
    def __init__(
        self,
        generator: Optional[PlanningRequirementGenerator] = None,
        evidence_builder: Optional[StateEvidenceBuilder] = None,
        max_requirements: int = 3,
    ) -> None:
        self.generator = generator
        self.evidence_builder = evidence_builder or StateEvidenceBuilder()
        self.max_requirements = max(1, int(max_requirements))

    def build_requirements(self, planning_input: PlanningAgentInput) -> List[Requirement]:
        if self.generator is None:
            return []
        evidence = self.evidence_builder.build(planning_input)
        prompt = self.build_prompt(planning_input, evidence)
        raw_output = self.generator(prompt)
        requirements = self._parse_state_requirements(raw_output)[:self.max_requirements]
        PathRequirementAgent._emit_trace(planning_input, "StateRequirementAgent", prompt, raw_output, requirements)
        return requirements

    def build_prompt(self, planning_input: PlanningAgentInput, evidence: Optional[StateEvidence] = None) -> str:
        evidence = evidence or self.evidence_builder.build(planning_input)
        return "\n\n".join(
            [
                self._system_prompt(),
                self._user_prompt(planning_input, evidence),
            ]
        ).strip()

    @staticmethod
    def _system_prompt() -> str:
        return "\n".join(
            [
                "Decide whether the target CFG path requires specific real object state before invoking the focal method.",
                "",
                "Use the target plan requirements so far and CFG path as fixed route evidence. Do not choose a different route.",
                "",
                "Look only for state setup that can be satisfied by real object construction, fields, setters, collection contents, map entries, configuration flags, or initialization order.",
                "Treat construction hints as partial evidence; missing constructors or setters in the snippet do not prove they are absent.",
                "",
                "Return only state requirements for a later test-generation agent.",
                "",
                "Allowed output is a JSON array. Each item must be:",
                '{"kind": "state", "text": "..."}',
                "",
                "If no special object state is required, return [].",
                "",
                "Do not write Java code.",
                "Do not describe mocks, input conditions, assertions, expected results, routes, fallback modes, retries, or confidence.",
                "Use only constructors, setters, helper methods, fields, or configuration APIs visible in the prompt or already present in the current test.",
            ]
        )

    @classmethod
    def _user_prompt(cls, planning_input: PlanningAgentInput, evidence: StateEvidence) -> str:
        context = planning_input.context
        return "\n".join(
            [
                "# State Requirement Extraction",
                "",
                "## Target Path",
                f"- Focal class: {context.class_name}",
                f"- Focal method: {context.method_name}",
                f"- Target source lines: {list(planning_input.target_lines)}",
                "- Grouping basis: same or very similar CFG entry-to-target path",
                "",
                "## Target Line Mapping",
                PathRequirementAgent._format_target_line_mapping(planning_input),
                "",
                "## Target Plan Requirements So Far",
                MockRequirementAgent._format_requirements_so_far(planning_input.requirements_so_far),
                "",
                "## CFG Path To Target",
                PathRequirementAgent._format_cfg_path(planning_input),
                "",
                "## Target Line Intentions",
                MockRequirementAgent._format_lines(evidence.target_line_intentions),
                "",
                "## Object Construction Hints",
                MockRequirementAgent._format_lines(evidence.object_construction_hints),
                "",
                "## State Access Facts",
                MockRequirementAgent._format_lines(evidence.state_access_facts),
                "",
                "## Path State Dependency Facts",
                MockRequirementAgent._format_lines(evidence.path_state_dependency_facts),
                "",
                "## Focal Method Code",
                "```java",
                PathRequirementAgent._add_source_line_numbers(planning_input),
                "```",
                "",
                "## Task",
                "Determine whether the target path requires specific real object state before invoking the focal method.",
                "",
                "Only output positive state requirements.",
                "If no special state is required, return [].",
                "",
                "A state requirement is appropriate when the target path depends on:",
                "- a field being null or non-null",
                "- a map or collection containing or missing a value",
                "- a configuration flag or mode",
                "- an object having been initialized through a visible constructor or setter",
                "- state that must exist before the focal method call and is not just a method parameter",
                "",
                "Do not restate input conditions.",
                "Do not restate mock requirements.",
                "Do not repeat existing requirements from Target Plan Requirements So Far.",
                "Use target line intentions only as hints; confirm state requirements against the CFG path and focal method code.",
                "Do not generate Java setup code.",
                "Use constructors, setters, helper APIs, or fields only when supported by the evidence above or already present in the current test.",
                "",
                "## Output",
                "Return only a JSON array:",
                "[",
                '  {"kind": "state", "text": "..."}',
                "]",
            ]
        )

    @classmethod
    def _parse_state_requirements(cls, raw_output: str) -> List[Requirement]:
        requirements = PathRequirementAgent._parse_requirements(raw_output, allowed_kind="state")
        normalized_requirements: List[Requirement] = []
        seen: set[str] = set()
        for requirement in requirements:
            text = cls._normalize_state_requirement_text(requirement.text)
            if not text:
                continue
            key = re.sub(r"\s+", " ", text).lower()
            if key in seen:
                continue
            seen.add(key)
            normalized_requirements.append(Requirement(kind="state", text=text))
        return normalized_requirements

    @staticmethod
    def _valid_state_requirement_text(text: str) -> bool:
        return bool(StateRequirementAgent._normalize_state_requirement_text(text))

    @staticmethod
    def _normalize_state_requirement_text(text: str) -> str:
        original = str(text or "").strip()
        lowered = original.lower()
        if not lowered:
            return ""
        if re.search(r"\b(?:mock|when|thenreturn|doreturn|dothrow|mockito)\b", lowered):
            return ""
        if lowered.startswith("pass ") or lowered.startswith("use parameter") or lowered.startswith("provide parameter"):
            return ""
        if "```" in lowered:
            return ""
        if ";" in original:
            statements = [statement.strip() for statement in original.split(";") if statement.strip()]
            if len(statements) != 1:
                return ""
            return f"set up state using {statements[0]}"
        return original


def default_planning_agents() -> Tuple[PlanningRequirementAgent, ...]:
    return (
        PathRequirementAgent(),
        DependencyStrategyAgent(),
        StateRequirementAgent(),
    )


class MockRequirementAgent(DependencyStrategyAgent):
    pass
