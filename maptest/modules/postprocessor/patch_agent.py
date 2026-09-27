from __future__ import annotations

import re
from dataclasses import dataclass
from typing import Callable, Dict, Iterable, List, Optional

from .models import Baseline, MethodContext, PatchCandidate, TargetPlan
from .source_api import SourceApiExtractor, format_source_api_summary


PatchGenerator = Callable[[str], str]


@dataclass(frozen=True)
class PatchPromptBuilder:
    def __init__(self, source_api_extractor: Optional[SourceApiExtractor] = None) -> None:
        object.__setattr__(self, "source_api_extractor", source_api_extractor or SourceApiExtractor())

    def build(self, context: MethodContext, baseline: Baseline, plan: TargetPlan) -> str:
        return "\n\n".join(
            [
                self._task_section(context),
                self._target_plan_section(plan),
                self._requirements_section(plan),
                self._focal_method_section(context),
                self._source_api_section(context),
                self._current_test_section(baseline),
                self._output_contract_section(),
            ]
        )

    @staticmethod
    def _task_section(context: MethodContext) -> str:
        return "\n".join(
            [
                "## Task",
                "You are improving an existing Java unit test for one focal method.",
                "Modify the current test just enough to satisfy the target plan and requirements.",
                "Keep the test compileable and runnable in the same project context.",
                "Prefer extending the current test over rewriting unrelated setup.",
                "Avoid broad fallback behavior, unrelated assertions, or tests for other methods.",
                "Do not mutate private or final fields through reflection to satisfy dependency or state requirements.",
                "When dependency handling requires object state, prefer constructors, public setters, public methods, class literals, or existing fixtures.",
                f"Method: {context.method_key}",
            ]
        )

    @staticmethod
    def _target_plan_section(plan: TargetPlan) -> str:
        reachability = "CFG path group" if plan.reachability == "cfg" else "direct target"
        return "\n".join(
            [
                "## Target Plan",
                f"Target lines: {', '.join(str(line) for line in plan.target_lines)}",
                f"Reachability: {reachability}",
                f"Confidence: {plan.confidence}",
                f"Goal: execute target lines {', '.join(str(line) for line in plan.target_lines)} through the selected target plan.",
            ]
        )

    @classmethod
    def _requirements_section(cls, plan: TargetPlan) -> str:
        grouped = cls._group_requirements(plan.requirements)
        sections = [
            ("Input conditions", grouped.get("condition", []), 5),
            ("Dependency handling", grouped.get("dependency", []), 5),
            ("Required mocks", grouped.get("mock", []), 5),
            ("Required object state", grouped.get("state", []), 5),
            ("Avoid previous failed strategies", grouped.get("avoid", []), 3),
            ("Coverage goal", grouped.get("coverage", []), 3),
            ("Compile constraints", grouped.get("compile", []), 6),
        ]

        lines = ["## Requirements"]
        for title, items, limit in sections:
            lines.append("")
            lines.append(f"{title}:")
            lines.extend(cls._format_requirement_items(items, limit))

        other_items = [
            requirement.text
            for kind, requirements in grouped.items()
            if kind not in {"condition", "dependency", "mock", "state", "avoid", "coverage", "compile"}
            for requirement in requirements
        ]
        if other_items:
            lines.append("")
            lines.append("Other requirements:")
            lines.extend(cls._format_requirement_items(other_items, 5))
        return "\n".join(lines)

    @staticmethod
    def _group_requirements(requirements: Iterable) -> Dict[str, List]:
        grouped: Dict[str, List] = {}
        for requirement in requirements:
            grouped.setdefault(requirement.kind, []).append(requirement)
        return grouped

    @staticmethod
    def _format_requirement_items(items: Iterable, limit: int) -> List[str]:
        texts = [
            str(getattr(item, "text", item) or "").strip()
            for item in list(items)[:limit]
            if str(getattr(item, "text", item) or "").strip()
        ]
        if not texts:
            return ["- None."]
        return [f"- {text}" for text in texts]

    @staticmethod
    def _focal_method_section(context: MethodContext) -> str:
        focal_method = (
            context.raw_result.get("focal_method_code")
            or context.raw_result.get("method_body")
            or (context.raw_result.get("Under_test_method") or {}).get("Method_body")
            or ""
        )
        return "\n".join(
            [
                "## Focal Method",
                "```java",
                str(focal_method).strip(),
                "```",
            ]
        )

    def _source_api_section(self, context: MethodContext) -> str:
        return format_source_api_summary(self.source_api_extractor.extract(context))

    @staticmethod
    def _current_test_section(baseline: Baseline) -> str:
        return "\n".join(
            [
                "## Current Test",
                "```java",
                baseline.test_code.strip(),
                "```",
            ]
        )

    @staticmethod
    def _output_contract_section() -> str:
        return "\n".join(
            [
                "## Output Contract",
                "Return exactly one complete Java test file as plain text.",
                "Include the package declaration when the current test or project requires one, plus imports, class declaration, fields, helpers, and test methods needed to compile.",
                "Preserve compile-related requirements such as package name, test class name, JUnit version, and mocking style when provided.",
                "Do not return markdown fences, explanations, diffs, patch hunks, comments about your changes, or partial snippets.",
                "Do not omit existing useful tests unless they must change to satisfy the target plan.",
            ]
        )


class PatchAgent:
    def __init__(
        self,
        prompt_builder: Optional[PatchPromptBuilder] = None,
        generator: Optional[PatchGenerator] = None,
    ) -> None:
        self.prompt_builder = prompt_builder or PatchPromptBuilder()
        self.generator = generator

    def generate(self, context: MethodContext, baseline: Baseline, plan: TargetPlan) -> PatchCandidate:
        prompt = self.prompt_builder.build(context, baseline, plan)
        if self.generator is None:
            return PatchCandidate(
                test_code="",
                source="patch_agent_unconfigured",
                metadata={"prompt": prompt, "error": "patch generator is not configured"},
            )

        generated = self.generator(prompt)
        test_code = self._parse_complete_test_file(generated)
        if not test_code:
            return PatchCandidate(
                test_code="",
                source="patch_agent_parse_error",
                metadata={"prompt": prompt, "raw_output": generated},
            )

        return PatchCandidate(
            test_code=test_code,
            source="patch_agent",
            metadata={"prompt": prompt, "raw_output": generated},
        )

    @staticmethod
    def _parse_complete_test_file(output: str) -> str:
        code = str(output or "").strip()
        if not code:
            return ""
        code = PatchAgent._extract_java_block(code)
        if "```" in code:
            return ""
        if "class " not in code:
            return ""
        if "@" not in code and "Test" not in code:
            return ""
        return code

    @staticmethod
    def _unwrap_markdown_fence(code: str) -> str:
        match = re.match(r"^```(?:java)?\s*(.*?)\s*```$", code, re.DOTALL | re.IGNORECASE)
        if not match:
            return code
        return match.group(1).strip()

    @staticmethod
    def _extract_java_block(code: str) -> str:
        unwrapped = PatchAgent._unwrap_markdown_fence(code)
        if unwrapped != code:
            return unwrapped

        blocks = re.findall(r"```(?:java)?\s*(.*?)\s*```", code, re.DOTALL | re.IGNORECASE)
        if len(blocks) == 1:
            return blocks[0].strip()
        return code
