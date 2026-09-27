from __future__ import annotations

import re
from dataclasses import dataclass
from typing import Any, Dict, List, Optional, Tuple


@dataclass(frozen=True)
class MockEvidence:
    candidate_calls: Tuple[str, ...] = ()
    path_call_dependency_facts: Tuple[str, ...] = ()
    uml_dependency_facts: Tuple[str, ...] = ()
    receiver_roles: Tuple[str, ...] = ()


@dataclass(frozen=True)
class _CallInfo:
    call_id: str
    line: int
    call: str
    receiver: str
    member: str
    args: str
    receiver_type: str
    receiver_kind: str


@dataclass(frozen=True)
class _AssignmentInfo:
    variable: str
    variable_type: str
    call_id: str
    line: int


@dataclass(frozen=True)
class _UMLClassInfo:
    name: str
    declaration: str
    fields: Dict[str, str]
    methods: Tuple[str, ...]


@dataclass(frozen=True)
class _UMLEvidence:
    classes: Dict[str, _UMLClassInfo]
    relations: Tuple[Tuple[str, str, str], ...]


class MockEvidenceBuilder:
    _CALL_PATTERN = re.compile(
        r"\b(?P<receiver>[A-Za-z_][A-Za-z0-9_]*)\s*\.\s*"
        r"(?P<member>[A-Za-z_][A-Za-z0-9_]*)\s*\((?P<args>[^)]*)\)"
    )
    _ASSIGNMENT_PATTERN = re.compile(
        r"(?:(?:final\s+)?(?P<type>[A-Za-z_][A-Za-z0-9_<>\[\].?,\s]*)\s+)?"
        r"(?P<var>[A-Za-z_][A-Za-z0-9_]*)\s*=\s*"
        r"(?P<receiver>[A-Za-z_][A-Za-z0-9_]*)\s*\.\s*"
        r"(?P<member>[A-Za-z_][A-Za-z0-9_]*)\s*\((?P<args>[^)]*)\)"
    )
    _PARAM_PATTERN = re.compile(
        r"(?P<type>[A-Za-z_][A-Za-z0-9_<>\[\].?,]*)\s+(?P<name>[A-Za-z_][A-Za-z0-9_]*)$"
    )
    _JDK_TYPES = {
        "String",
        "Integer",
        "Long",
        "Short",
        "Byte",
        "Double",
        "Float",
        "Boolean",
        "Character",
        "Object",
        "Math",
        "Objects",
        "Collections",
        "Arrays",
        "Optional",
        "List",
        "Map",
        "Set",
        "Collection",
        "StringBuilder",
        "StringBuffer",
    }
    _VISIBILITY_OR_MODIFIER = {
        "public",
        "protected",
        "private",
        "static",
        "final",
        "abstract",
        "synchronized",
        "volatile",
        "transient",
    }

    def build(self, planning_input: Any) -> MockEvidence:
        code = str(getattr(planning_input, "focal_method_code", "") or "")
        lines = code.strip().splitlines()
        if not lines:
            return MockEvidence(
                candidate_calls=("No candidate calls were extracted from the focal method.",),
                path_call_dependency_facts=("No candidate call result appears to control this target path.",),
                uml_dependency_facts=("No UML dependency evidence was available.",),
                receiver_roles=("No receiver roles were inferred.",),
            )

        parameter_types = self._extract_parameters(lines)
        uml_evidence = self._parse_uml_evidence(planning_input)
        calls = self._extract_calls(lines, parameter_types, uml_evidence, planning_input)
        assignments = self._extract_assignments(lines, calls)
        candidate_lines = tuple(self._format_candidate_call(call) for call in calls)
        dependency_facts = tuple(self._dependency_facts(lines, calls, assignments, planning_input))
        uml_dependency_facts = tuple(self._uml_dependency_facts(calls, uml_evidence, planning_input))
        receiver_roles = tuple(self._receiver_roles(calls, assignments, parameter_types, uml_evidence, planning_input))

        return MockEvidence(
            candidate_calls=candidate_lines or ("No candidate calls were extracted from the focal method.",),
            path_call_dependency_facts=dependency_facts or ("No candidate call result appears to control this target path.",),
            uml_dependency_facts=uml_dependency_facts or ("No UML dependency evidence was available.",),
            receiver_roles=receiver_roles or ("No receiver roles were inferred.",),
        )

    def _extract_calls(
        self,
        lines: List[str],
        parameter_types: Dict[str, str],
        uml_evidence: _UMLEvidence,
        planning_input: Any,
    ) -> List[_CallInfo]:
        calls: List[_CallInfo] = []
        local_types: Dict[str, str] = {}
        for line_number, line in enumerate(lines, 1):
            stripped = self._strip_line_comment(line)
            assignment_match = self._ASSIGNMENT_PATTERN.search(stripped)
            if assignment_match:
                var_type = self._clean_type(assignment_match.group("type") or "")
                var_name = assignment_match.group("var")
                if var_type:
                    local_types[var_name] = var_type

            for match in self._CALL_PATTERN.finditer(stripped):
                receiver = match.group("receiver")
                member = match.group("member")
                if receiver in {"if", "for", "while", "switch", "return", "new"}:
                    continue
                args = re.sub(r"\s+", " ", match.group("args").strip())
                receiver_type = self._receiver_type(receiver, local_types, parameter_types, uml_evidence, planning_input)
                receiver_kind = self._receiver_kind(receiver, receiver_type, local_types, parameter_types, uml_evidence, planning_input)
                call_id = f"C{len(calls) + 1}"
                calls.append(
                    _CallInfo(
                        call_id=call_id,
                        line=line_number,
                        call=f"{receiver}.{member}({args})",
                        receiver=receiver,
                        member=member,
                        args=args,
                        receiver_type=receiver_type,
                        receiver_kind=receiver_kind,
                    )
                )
        return calls

    def _extract_assignments(self, lines: List[str], calls: List[_CallInfo]) -> List[_AssignmentInfo]:
        assignments: List[_AssignmentInfo] = []
        call_lookup = {(call.line, call.receiver, call.member, call.args): call for call in calls}
        for line_number, line in enumerate(lines, 1):
            match = self._ASSIGNMENT_PATTERN.search(self._strip_line_comment(line))
            if not match:
                continue
            args = re.sub(r"\s+", " ", match.group("args").strip())
            call = call_lookup.get((line_number, match.group("receiver"), match.group("member"), args))
            if not call:
                continue
            assignments.append(
                _AssignmentInfo(
                    variable=match.group("var"),
                    variable_type=self._clean_type(match.group("type") or ""),
                    call_id=call.call_id,
                    line=line_number,
                )
            )
        return assignments

    def _dependency_facts(
        self,
        lines: List[str],
        calls: List[_CallInfo],
        assignments: List[_AssignmentInfo],
        planning_input: Any,
    ) -> List[str]:
        facts: List[str] = []
        call_by_id = {call.call_id: call for call in calls}
        calls_by_receiver: Dict[str, List[_CallInfo]] = {}
        for call in calls:
            calls_by_receiver.setdefault(call.receiver, []).append(call)

        cfg_text = " ".join(str(step) for step in getattr(planning_input, "cfg_path", ()) or ())
        for assignment in assignments:
            call = call_by_id.get(assignment.call_id)
            if not call:
                continue
            facts.append(
                f"- {assignment.call_id} result is assigned to local variable `{assignment.variable}`."
            )

            null_requirement = self._null_requirement(assignment.variable, cfg_text)
            if null_requirement:
                facts.append(f"- The path requires `{null_requirement}` after {assignment.call_id}.")
            elif self._has_null_check(assignment.variable, lines[assignment.line:]):
                facts.append(
                    f"- The method checks `{assignment.variable}` for null after {assignment.call_id}, "
                    "but no required branch outcome was inferred without CFG evidence."
                )

            downstream_calls = [
                downstream
                for downstream in calls_by_receiver.get(assignment.variable, [])
                if downstream.call_id != assignment.call_id and downstream.line >= assignment.line
            ]
            for downstream in downstream_calls:
                facts.append(
                    f"- {downstream.call_id} is called on `{assignment.variable}` after {assignment.call_id}."
                )

            if null_requirement or downstream_calls:
                facts.append(
                    f"- Therefore {assignment.call_id} controls whether the target path can continue through `{assignment.variable}`."
                )

        if not facts:
            facts.append("- No candidate call result appears to control this target path.")
        return facts

    def _receiver_roles(
        self,
        calls: List[_CallInfo],
        assignments: List[_AssignmentInfo],
        parameter_types: Dict[str, str],
        uml_evidence: _UMLEvidence,
        planning_input: Any,
    ) -> List[str]:
        produced_by = {assignment.variable: assignment.call_id for assignment in assignments}
        roles: List[str] = []
        seen: set[str] = set()
        for call in calls:
            if call.receiver in seen:
                continue
            seen.add(call.receiver)
            if call.receiver_kind == "uml_field_dependency":
                relation_suffix = self._relation_suffix(planning_input, call.receiver_type, uml_evidence)
                roles.append(
                    f"- {call.receiver}: UML-declared field dependency"
                    f"{self._type_suffix(call.receiver_type)}{relation_suffix}, not directly controlled by method input."
                )
            elif call.receiver_kind == "parameter_object":
                roles.append(
                    f"- {call.receiver}: method parameter{self._type_suffix(parameter_types.get(call.receiver, call.receiver_type))}, controlled by test input."
                )
            elif call.receiver_kind == "local_value_call":
                source = f" produced by {produced_by[call.receiver]}" if call.receiver in produced_by else ""
                roles.append(
                    f"- {call.receiver}: local value{source}{self._type_suffix(call.receiver_type)}, usually not mocked directly."
                )
            elif call.receiver_kind == "self_call":
                roles.append(f"- {call.receiver}: same-class receiver, prefer not to mock.")
            elif call.receiver_kind == "static_jdk":
                roles.append(f"- {call.receiver}: JDK/static utility receiver, do not mock.")
            elif call.receiver_kind == "uml_static_dependency":
                roles.append(
                    f"- {call.receiver}: UML-declared external class/static receiver{self._type_suffix(call.receiver_type)}."
                )
            else:
                roles.append(
                    f"- {call.receiver}: receiver identity unresolved by UML{self._type_suffix(call.receiver_type)}; do not infer dependency or mockability from the receiver name."
                )
        return roles or ["- No receiver roles were inferred."]

    def _extract_parameters(self, lines: List[str]) -> Dict[str, str]:
        signature = " ".join(line.strip() for line in lines[:3])
        match = re.search(r"\((?P<params>[^)]*)\)", signature)
        if not match:
            return {}
        params: Dict[str, str] = {}
        for raw_param in match.group("params").split(","):
            cleaned = raw_param.strip()
            if not cleaned:
                continue
            cleaned = re.sub(r"@\w+(?:\([^)]*\))?\s*", "", cleaned)
            cleaned = re.sub(r"\bfinal\s+", "", cleaned).strip()
            param_match = self._PARAM_PATTERN.search(cleaned)
            if param_match:
                params[param_match.group("name")] = self._clean_type(param_match.group("type"))
        return params

    def _receiver_type(
        self,
        receiver: str,
        local_types: Dict[str, str],
        parameter_types: Dict[str, str],
        uml_evidence: _UMLEvidence,
        planning_input: Any,
    ) -> str:
        if receiver in local_types:
            return local_types[receiver]
        if receiver in parameter_types:
            return parameter_types[receiver]
        if receiver in self._JDK_TYPES:
            return receiver
        focal_info = self._focal_uml_class(planning_input, uml_evidence)
        if focal_info and receiver in focal_info.fields:
            return focal_info.fields[receiver]
        if receiver[:1].isupper() and receiver in uml_evidence.classes:
            return receiver
        return "unknown"

    def _receiver_kind(
        self,
        receiver: str,
        receiver_type: str,
        local_types: Dict[str, str],
        parameter_types: Dict[str, str],
        uml_evidence: _UMLEvidence,
        planning_input: Any,
    ) -> str:
        if receiver in {"this", "super"}:
            return "self_call"
        if receiver in local_types:
            return "local_value_call"
        if receiver in parameter_types:
            return "parameter_object"
        if receiver in self._JDK_TYPES or receiver_type in self._JDK_TYPES:
            return "static_jdk" if receiver[:1].isupper() else "parameter_object"
        focal_info = self._focal_uml_class(planning_input, uml_evidence)
        if focal_info and receiver in focal_info.fields:
            return "uml_field_dependency"
        if receiver[:1].isupper() and receiver in uml_evidence.classes:
            return "uml_static_dependency"
        return "uml_unresolved"

    @staticmethod
    def _format_candidate_call(call: _CallInfo) -> str:
        return (
            f"- {call.call_id} L{call.line}: `{call.call}` | "
            f"receiver=`{call.receiver}` | member=`{call.member}` | "
            f"receiver_type=`{call.receiver_type}` | receiver_kind=`{call.receiver_kind}`"
        )

    @staticmethod
    def _strip_line_comment(line: str) -> str:
        return re.sub(r"//.*$", "", line)

    @staticmethod
    def _clean_type(value: str) -> str:
        text = re.sub(r"\s+", " ", str(value or "").strip())
        if not text:
            return ""
        tokens = text.split()
        return tokens[-1] if tokens else text

    @classmethod
    def _parse_uml_evidence(cls, planning_input: Any) -> _UMLEvidence:
        text = cls._plantuml_text(planning_input)
        if not text:
            return _UMLEvidence(classes={}, relations=())

        classes: Dict[str, _UMLClassInfo] = {}
        lines = text.splitlines()
        index = 0
        while index < len(lines):
            raw_line = lines[index].strip()
            class_match = re.match(
                r"(?:(?:public|protected|private|abstract|final)\s+)*"
                r"(class|interface|enum)\s+([A-Za-z_][A-Za-z0-9_]*)\b(.*)\{?\s*$",
                raw_line,
            )
            if not class_match:
                index += 1
                continue
            declaration = raw_line
            class_name = class_match.group(2)
            fields: Dict[str, str] = {}
            methods: List[str] = []
            if "{" not in raw_line:
                index += 1
                while index < len(lines) and "{" not in lines[index]:
                    index += 1
            index += 1
            while index < len(lines):
                member_line = lines[index].strip()
                if member_line.startswith("}"):
                    break
                member = member_line.rstrip(";").strip()
                if member:
                    if "(" in member and ")" in member:
                        methods.append(member)
                    else:
                        parsed_field = cls._parse_uml_field(member)
                        if parsed_field:
                            field_name, field_type = parsed_field
                            fields[field_name] = field_type
                index += 1
            classes[class_name] = _UMLClassInfo(
                name=class_name,
                declaration=declaration,
                fields=fields,
                methods=tuple(methods),
            )
            index += 1

        relations: List[Tuple[str, str, str]] = []
        for raw_line in lines:
            line = raw_line.strip()
            relation_match = re.match(
                r"([A-Za-z_][A-Za-z0-9_]*)\s+(-->|--\|>|\.\.\|>)\s+([A-Za-z_][A-Za-z0-9_]*)",
                line,
            )
            if relation_match:
                relations.append((relation_match.group(1), relation_match.group(2), relation_match.group(3)))

        return _UMLEvidence(classes=classes, relations=tuple(relations))

    @classmethod
    def _parse_uml_field(cls, member: str) -> Optional[Tuple[str, str]]:
        tokens = member.split()
        tokens = [token for token in tokens if token not in cls._VISIBILITY_OR_MODIFIER]
        if len(tokens) < 2:
            return None
        field_name = tokens[-1].strip()
        if not re.match(r"^[A-Za-z_][A-Za-z0-9_]*$", field_name):
            return None
        field_type = " ".join(tokens[:-1]).strip()
        if not field_type:
            return None
        return field_name, field_type

    @staticmethod
    def _plantuml_text(planning_input: Any) -> str:
        raw_result = getattr(getattr(planning_input, "context", None), "raw_result", {}) or {}
        for key in (
            "plantuml_generated",
            "plantuml_code",
            "pruned_uml",
            "pruned_uml_text",
            "uml_context",
        ):
            value = str(raw_result.get(key) or "").strip()
            if value and value.lower() not in {"false", "none", "null"}:
                return value
        prompt = str(raw_result.get("test_prompt") or raw_result.get("prompt") or "")
        match = re.search(r"@startuml\b.*?@enduml", prompt, re.DOTALL | re.IGNORECASE)
        return match.group(0).strip() if match else ""

    @staticmethod
    def _focal_uml_class(planning_input: Any, uml_evidence: _UMLEvidence) -> Optional[_UMLClassInfo]:
        context = getattr(planning_input, "context", None)
        class_name = str(getattr(context, "class_name", "") or "").strip()
        return uml_evidence.classes.get(class_name)

    def _uml_dependency_facts(
        self,
        calls: List[_CallInfo],
        uml_evidence: _UMLEvidence,
        planning_input: Any,
    ) -> List[str]:
        if not uml_evidence.classes:
            return ["- No UML dependency evidence was available."]

        facts: List[str] = []
        focal_info = self._focal_uml_class(planning_input, uml_evidence)
        focal_name = str(getattr(getattr(planning_input, "context", None), "class_name", "") or "").strip()
        if focal_info is None:
            facts.append(f"- Focal class `{focal_name or 'unknown'}` was not resolved in the UML context.")

        seen_receivers: set[str] = set()
        for call in calls:
            if call.receiver in seen_receivers:
                continue
            seen_receivers.add(call.receiver)
            if focal_info and call.receiver in focal_info.fields:
                declared_type = focal_info.fields[call.receiver]
                facts.append(
                    f"- `{call.receiver}` is a UML-declared field of `{focal_info.name}` with type `{declared_type}`."
                )
                relation = self._relation_text(focal_info.name, declared_type, uml_evidence)
                if relation:
                    facts.append(f"- UML relation evidence for `{call.receiver}`: {relation}.")
                api = self._visible_api_text(call.member, declared_type, uml_evidence)
                if api:
                    facts.append(f"- UML-visible API for `{call.receiver}.{call.member}(...)`: {api}.")
                continue
            if call.receiver[:1].isupper() and call.receiver in uml_evidence.classes:
                facts.append(f"- `{call.receiver}` is a UML-declared class/static receiver.")
                api = self._visible_api_text(call.member, call.receiver, uml_evidence)
                if api:
                    facts.append(f"- UML-visible API for `{call.receiver}.{call.member}(...)`: {api}.")
                continue
            facts.append(
                f"- `{call.receiver}` was not identified as a focal-class field or UML class; "
                "static dependency identity is unresolved by UML."
            )
        return facts or ["- No candidate receiver was checked against UML evidence."]

    @classmethod
    def _relation_text(cls, focal_name: str, declared_type: str, uml_evidence: _UMLEvidence) -> str:
        type_names = cls._uml_type_names(declared_type)
        relation_labels = {
            "-->": "uses",
            "--|>": "extends",
            "..|>": "implements",
        }
        for source, arrow, target in uml_evidence.relations:
            if source == focal_name and target in type_names:
                return f"`{source} {arrow} {target}` ({relation_labels.get(arrow, 'relates')})"
        return ""

    @classmethod
    def _relation_suffix(cls, planning_input: Any, declared_type: str, uml_evidence: _UMLEvidence) -> str:
        focal_name = str(getattr(getattr(planning_input, "context", None), "class_name", "") or "").strip()
        relation = cls._relation_text(focal_name, declared_type, uml_evidence)
        return f", {relation}" if relation else ""

    @classmethod
    def _visible_api_text(cls, member: str, declared_type: str, uml_evidence: _UMLEvidence) -> str:
        type_names = cls._uml_type_names(declared_type)
        for type_name in type_names:
            info = uml_evidence.classes.get(type_name)
            if not info:
                continue
            matches = [
                method
                for method in info.methods
                if re.search(rf"\b{re.escape(member)}\s*\(", method)
            ]
            if matches:
                return "; ".join(f"`{type_name}.{method}`" for method in matches[:3])
        return ""

    @classmethod
    def _uml_type_names(cls, declared_type: str) -> Tuple[str, ...]:
        names = []
        for token in re.findall(r"\b[A-Z][A-Za-z0-9_]*\b", declared_type or ""):
            if token in cls._JDK_TYPES or token == "Class":
                continue
            if token not in names:
                names.append(token)
        if not names and declared_type:
            cleaned = declared_type.strip()
            if cleaned:
                names.append(cleaned)
        return tuple(names)

    @staticmethod
    def _type_suffix(receiver_type: Optional[str]) -> str:
        if not receiver_type or receiver_type == "unknown":
            return ""
        return f", type {receiver_type}"

    @staticmethod
    def _null_requirement(variable: str, cfg_text: str) -> str:
        compact_cfg = re.sub(r"\s+", " ", cfg_text)
        if re.search(rf"\b{re.escape(variable)}\s*==\s*null\b.*(?:false|\[false\]|-> false)", compact_cfg, re.IGNORECASE):
            return f"{variable} != null"
        if re.search(rf"\b{re.escape(variable)}\s*!=\s*null\b.*(?:true|\[true\]|-> true)", compact_cfg, re.IGNORECASE):
            return f"{variable} != null"
        if re.search(rf"\b{re.escape(variable)}\s*==\s*null\b.*(?:true|\[true\]|-> true)", compact_cfg, re.IGNORECASE):
            return f"{variable} == null"
        if re.search(rf"\b{re.escape(variable)}\s*!=\s*null\b.*(?:false|\[false\]|-> false)", compact_cfg, re.IGNORECASE):
            return f"{variable} == null"
        return ""

    @staticmethod
    def _has_null_check(variable: str, later_lines: List[str]) -> bool:
        later_text = "\n".join(later_lines)
        return bool(re.search(rf"\b{re.escape(variable)}\s*(?:==|!=)\s*null\b", later_text))
