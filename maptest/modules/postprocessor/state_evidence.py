from __future__ import annotations

import re
from dataclasses import dataclass
from typing import Any, Dict, List, Tuple


@dataclass(frozen=True)
class StateEvidence:
    object_construction_hints: Tuple[str, ...] = ()
    state_access_facts: Tuple[str, ...] = ()
    path_state_dependency_facts: Tuple[str, ...] = ()
    target_line_intentions: Tuple[str, ...] = ()


class StateEvidenceBuilder:
    _METHOD_SIGNATURE = re.compile(
        r"\b(?:public|protected|private)\s+"
        r"(?:static\s+)?(?:final\s+)?"
        r"(?P<return>[A-Za-z_][A-Za-z0-9_<>\[\].?,]*)\s+"
        r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
    )
    _FIELD_ACCESS = re.compile(r"\b(?:this\.)?(?P<field>[A-Za-z_][A-Za-z0-9_]*)\s*\.\s*(?P<member>[A-Za-z_][A-Za-z0-9_]*)\s*\(")
    _THIS_FIELD = re.compile(r"\bthis\.(?P<field>[A-Za-z_][A-Za-z0-9_]*)\b")
    _DEPENDENCY_WORDS = (
        "registry",
        "cache",
        "map",
        "list",
        "set",
        "config",
        "configuration",
        "settings",
        "options",
        "state",
        "store",
        "attributes",
        "properties",
    )
    _JDK_OR_VALUE_RECEIVERS = {
        "String",
        "Integer",
        "Long",
        "Double",
        "Float",
        "Boolean",
        "Object",
        "Math",
        "Objects",
        "Collections",
        "Arrays",
        "Optional",
    }

    def build(self, planning_input: Any) -> StateEvidence:
        code = str(getattr(planning_input, "focal_method_code", "") or "")
        lines = code.strip().splitlines()
        if not lines:
            return StateEvidence(
                object_construction_hints=("No construction hints were extracted.",),
                state_access_facts=("No state access facts were extracted.",),
                path_state_dependency_facts=("No object state appears to control this target path beyond ordinary method inputs.",),
                target_line_intentions=("- none",),
            )

        class_name = str(getattr(getattr(planning_input, "context", None), "class_name", "") or "")
        parameter_names = self._extract_parameter_names(lines)
        local_names = self._extract_local_names(lines)
        state_accesses = self._state_accesses(lines, parameter_names, local_names)

        return StateEvidence(
            object_construction_hints=tuple(self._construction_hints(class_name, lines)),
            state_access_facts=tuple(self._state_access_facts(state_accesses)),
            path_state_dependency_facts=tuple(self._path_state_dependency_facts(state_accesses, planning_input)),
            target_line_intentions=tuple(self._target_line_intentions(lines, planning_input)),
        )

    def _construction_hints(self, class_name: str, lines: List[str]) -> List[str]:
        hints: List[str] = []
        if class_name:
            hints.append(f"- Target class: {class_name}")
        constructors = self._visible_constructors(class_name, lines)
        setters = self._visible_setters(lines)
        if constructors:
            hints.append("- Visible constructors:")
            hints.extend(f"  - {constructor}" for constructor in constructors)
        elif class_name:
            hints.append(f"- No constructor was visible in the focal method snippet; use ordinary {class_name} construction only if available to the generator.")
        else:
            hints.append("- No construction hints were extracted.")

        if setters:
            hints.append("- Visible setters:")
            hints.extend(f"  - {setter}" for setter in setters)
        else:
            hints.append("- Visible setters in focal snippet: none observed")
        hints.append("- Factory methods were not scanned from the focal snippet.")
        return hints

    def _state_accesses(
        self,
        lines: List[str],
        parameter_names: Tuple[str, ...],
        local_names: Tuple[str, ...],
    ) -> List[Tuple[int, str, str]]:
        accesses: List[Tuple[int, str, str]] = []
        parameter_set = set(parameter_names)
        local_set = set(local_names)
        for line_number, line in enumerate(lines, 1):
            stripped = self._strip_line_comment(line)
            for match in self._THIS_FIELD.finditer(stripped):
                field = match.group("field")
                if field not in parameter_set and field not in local_set:
                    accesses.append((line_number, field, "field reference"))

            for match in self._FIELD_ACCESS.finditer(stripped):
                field = match.group("field")
                member = match.group("member")
                if field in parameter_set or field in local_set or field in self._JDK_OR_VALUE_RECEIVERS:
                    continue
                if field[:1].isupper():
                    continue
                if field in {"if", "for", "while", "switch", "return", "new", "this", "super"}:
                    continue
                accesses.append((line_number, field, f"calls `{field}.{member}(...)`"))
        return self._dedupe_accesses(accesses)

    @staticmethod
    def _state_access_facts(accesses: List[Tuple[int, str, str]]) -> List[str]:
        if not accesses:
            return ["- No focal object fields or state-like receivers are read on this path."]
        return [f"- L{line}: {description}" for line, _field, description in accesses]

    def _path_state_dependency_facts(self, accesses: List[Tuple[int, str, str]], planning_input: Any) -> List[str]:
        if not accesses:
            return ["- No object state appears to control this target path beyond ordinary method inputs."]

        cfg_text = " ".join(str(step) for step in getattr(planning_input, "cfg_path", ()) or ())
        facts: List[str] = []
        seen_fields: set[str] = set()
        for _line, field, description in accesses:
            if field in seen_fields:
                continue
            seen_fields.add(field)
            lowered_field = field.lower()
            if "containskey" in cfg_text.lower() and re.search(rf"\b{re.escape(field)}\.containsKey\b", cfg_text):
                facts.append(f"- The path requires `{field}.containsKey(...)` to have the branch outcome shown in the CFG path.")
            elif "contains" in description.lower() or "get(" in description.lower():
                facts.append(f"- The path uses `{field}` lookup state before or at the target line.")
            elif any(word in lowered_field for word in self._DEPENDENCY_WORDS):
                facts.append(f"- The path reads state-like receiver `{field}` before or at the target line.")

        if not facts:
            facts.append("- State is accessed on this path, but no specific required state value was inferred.")
        return facts

    def _target_line_intentions(self, lines: List[str], planning_input: Any) -> List[str]:
        target_lines = tuple(getattr(planning_input, "target_lines", ()) or ())
        if not target_lines:
            return ["- none"]

        intentions: List[str] = []
        start_line = self._focal_start_line(planning_input)
        for source_line in target_lines:
            local_line = source_line - start_line + 1 if start_line and source_line >= start_line else source_line
            if local_line < 1 or local_line > len(lines):
                continue
            code = lines[local_line - 1].strip()
            if not code:
                continue
            intentions.append(f"- L{source_line}: {self._line_intention(code)}")
        return intentions or ["- none"]

    @staticmethod
    def _line_intention(code: str) -> str:
        if code.startswith("return "):
            return "returns " + code[len("return "):].rstrip(";")
        if "=" in code and not re.search(r"[=!<>]=", code):
            left = code.split("=", 1)[0].strip()
            return f"assigns {left}"
        if ".put(" in code:
            return "updates map-like state"
        if ".add(" in code:
            return "updates collection state"
        return code.rstrip(";")

    @staticmethod
    def _visible_constructors(class_name: str, lines: List[str]) -> List[str]:
        if not class_name:
            return []
        constructors = []
        pattern = re.compile(rf"\b(?:public|protected|private)?\s*{re.escape(class_name)}\s*\(([^)]*)\)")
        for line in lines:
            match = pattern.search(line.strip())
            if match:
                constructors.append(f"{class_name}({match.group(1).strip()})")
        return constructors

    def _visible_setters(self, lines: List[str]) -> List[str]:
        setters = []
        for line in lines:
            match = self._METHOD_SIGNATURE.search(line.strip())
            if match and match.group("name").startswith("set"):
                params = self._method_params_text(line)
                setters.append(f"{match.group('name')}({params})")
        return setters

    @staticmethod
    def _method_params_text(line: str) -> str:
        match = re.search(r"\(([^)]*)\)", line)
        return match.group(1).strip() if match else ""

    @staticmethod
    def _extract_parameter_names(lines: List[str]) -> Tuple[str, ...]:
        signature = " ".join(line.strip() for line in lines[:3])
        match = re.search(r"\(([^)]*)\)", signature)
        if not match:
            return ()
        names: List[str] = []
        for raw_param in match.group(1).split(","):
            cleaned = re.sub(r"@\w+(?:\([^)]*\))?\s*", "", raw_param.strip())
            cleaned = re.sub(r"\bfinal\s+", "", cleaned).strip()
            if not cleaned:
                continue
            parts = cleaned.split()
            if parts:
                names.append(parts[-1].replace("...", "").replace("[]", ""))
        return tuple(names)

    @staticmethod
    def _extract_local_names(lines: List[str]) -> Tuple[str, ...]:
        names: List[str] = []
        pattern = re.compile(
            r"\b(?:final\s+)?[A-Za-z_][A-Za-z0-9_<>\[\].?,]*\s+"
            r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*="
        )
        for line in lines:
            match = pattern.search(line.strip())
            if match:
                names.append(match.group("name"))
        return tuple(names)

    @staticmethod
    def _dedupe_accesses(accesses: List[Tuple[int, str, str]]) -> List[Tuple[int, str, str]]:
        deduped: List[Tuple[int, str, str]] = []
        seen: set[Tuple[int, str, str]] = set()
        for access in accesses:
            if access in seen:
                continue
            seen.add(access)
            deduped.append(access)
        return deduped

    @staticmethod
    def _focal_start_line(planning_input: Any) -> int:
        raw_result = getattr(getattr(planning_input, "context", None), "raw_result", {}) or {}
        try:
            return int(raw_result.get("focal_method_start_line") or 0)
        except (TypeError, ValueError):
            return 0

    @staticmethod
    def _strip_line_comment(line: str) -> str:
        return re.sub(r"//.*$", "", line)
