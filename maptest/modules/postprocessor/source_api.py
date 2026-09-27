from __future__ import annotations

import re
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, Iterable, List, Optional, Sequence, Tuple

try:
    import javalang
except Exception:
    javalang = None

from .models import MethodContext


@dataclass(frozen=True)
class SourceApiSummary:
    status: str = "manual"
    confidence: str = "high"
    source_path: str = ""
    package_name: str = ""
    imports: Tuple[str, ...] = ()
    fields: Tuple[str, ...] = ()
    constructors: Tuple[str, ...] = ()
    methods: Tuple[str, ...] = ()
    enum_constants: Tuple[str, ...] = ()
    class_literal_hints: Tuple[str, ...] = ()

    @property
    def empty(self) -> bool:
        return not any(
            (
                self.source_path,
                self.package_name,
                self.imports,
                self.fields,
                self.constructors,
                self.methods,
                self.enum_constants,
                self.class_literal_hints,
            )
        )


class SourceApiExtractor:
    def __init__(self, repos_dir: Path | None = None) -> None:
        self.repos_dir = repos_dir or self._default_repos_dir()

    def extract(self, context: MethodContext) -> SourceApiSummary:
        source_path = self._resolve_source_path(context)
        if not source_path or not source_path.is_file():
            return SourceApiSummary(status="source_not_found", confidence="none")

        try:
            source_code = source_path.read_text(encoding="utf-8", errors="ignore")
        except OSError:
            return SourceApiSummary(status="source_read_failed", confidence="none")

        if javalang is not None:
            parsed = self._extract_with_javalang(source_code, source_path, context)
            if not parsed.empty:
                return parsed

        return self._extract_with_regex(source_code, source_path)

    @staticmethod
    def _default_repos_dir() -> Path:
        package_root = Path(__file__).resolve().parents[2]
        return package_root.parent / "Repos"

    def _resolve_source_path(self, context: MethodContext) -> Optional[Path]:
        raw = context.raw_result or {}
        project_name = self._text(
            raw.get("repo_project_name")
            or raw.get("project_name")
            or context.project_name
        )
        class_name = self._text(raw.get("class_name") or context.class_name)
        package_name = self._text(raw.get("package_name"))
        if not project_name or not class_name:
            return None

        package_path = Path(*package_name.split(".")) if package_name else Path()
        return self.repos_dir / project_name / "src" / "main" / "java" / package_path / f"{class_name}.java"

    def _extract_with_javalang(
        self,
        source_code: str,
        source_path: Path,
        context: MethodContext,
    ) -> SourceApiSummary:
        try:
            tree = javalang.parse.parse(source_code)
        except Exception:
            return SourceApiSummary()

        class_name = self._text((context.raw_result or {}).get("class_name") or context.class_name)
        target_type = self._find_type(tree.types, class_name)
        if target_type is None:
            return SourceApiSummary()

        package_name = self._text(getattr(getattr(tree, "package", None), "name", ""))
        imports = tuple(self._format_import(import_decl) for import_decl in getattr(tree, "imports", []) or ())
        fields = tuple(self._format_field(field) for field in getattr(target_type, "fields", []) or ())
        constructors = tuple(self._format_constructor(ctor) for ctor in getattr(target_type, "constructors", []) or ())
        methods = tuple(self._format_method(method) for method in getattr(target_type, "methods", []) or ())
        enum_constants = list(self._enum_constants_from_types(self._nested_type_declarations(target_type)))
        enum_constants.extend(self._enum_constants_from_imports(imports, source_path))
        class_literal_hints = self._class_literal_hints(
            source_path,
            imports=imports,
            signatures=fields + constructors + methods,
        )

        return SourceApiSummary(
            status="resolved",
            confidence="high",
            source_path=str(source_path),
            package_name=package_name,
            imports=self._unique(imports, limit=25),
            fields=self._unique(fields, limit=40),
            constructors=self._unique(constructors, limit=20),
            methods=self._unique(methods, limit=70),
            enum_constants=self._unique(enum_constants, limit=50),
            class_literal_hints=self._unique(class_literal_hints, limit=20),
        )

    def _extract_with_regex(self, source_code: str, source_path: Path) -> SourceApiSummary:
        package_match = re.search(r"^\s*package\s+([\w.]+)\s*;", source_code, re.MULTILINE)
        package_name = package_match.group(1) if package_match else ""
        imports = tuple(re.findall(r"^\s*(import\s+(?:static\s+)?[\w.*]+;)", source_code, re.MULTILINE))
        enum_constants = []
        for enum_name, body in re.findall(r"\benum\s+(\w+)\s*\{([^}]*)\}", source_code, re.DOTALL):
            constants = re.split(r",|\s*;", body, maxsplit=1)[0]
            enum_constants.extend(
                f"{enum_name}.{constant.strip()}"
                for constant in constants.split(",")
                if constant.strip() and re.match(r"^[A-Z][A-Z0-9_]*$", constant.strip())
            )
        return SourceApiSummary(
            status="regex_fallback",
            confidence="low",
            source_path=str(source_path),
            package_name=package_name,
            imports=self._unique(imports, limit=25),
            enum_constants=self._unique(enum_constants, limit=50),
        )

    def _class_literal_hints(
        self,
        source_path: Path,
        imports: Iterable[str],
        signatures: Iterable[str],
    ) -> List[str]:
        base_types = self._class_literal_base_types(signatures)
        if not base_types:
            return []

        imported_types = self._import_type_map(imports)
        project_source_root = self._project_source_root(source_path)
        if project_source_root is None:
            return []

        hints: List[str] = []
        for base_type in base_types:
            base_fqcn = imported_types.get(base_type, base_type)
            for fqcn in self._find_concrete_type_candidates(project_source_root, base_type):
                if fqcn == base_fqcn or fqcn.endswith(f".{base_type}"):
                    continue
                hints.append(f"{fqcn}.class assignable to {base_type}")
        return hints

    @staticmethod
    def _class_literal_base_types(signatures: Iterable[str]) -> List[str]:
        base_types: List[str] = []
        for signature in signatures:
            for match in re.finditer(r"Class<\?\s+extends\s+([A-Za-z_][A-Za-z0-9_]*)", signature):
                base_type = match.group(1)
                if base_type not in base_types:
                    base_types.append(base_type)
        return base_types

    @classmethod
    def _import_type_map(cls, imports: Iterable[str]) -> Dict[str, str]:
        type_map: Dict[str, str] = {}
        for import_line in imports:
            imported = str(import_line or "").removeprefix("import ").removesuffix(";").strip()
            if imported.startswith("static ") or imported.endswith(".*") or "." not in imported:
                continue
            type_map[imported.rsplit(".", 1)[-1]] = imported
        return type_map

    def _find_concrete_type_candidates(self, source_root: Path, base_type: str) -> List[str]:
        candidates: List[str] = []
        for source_file in sorted(source_root.rglob("*.java")):
            try:
                source_code = source_file.read_text(encoding="utf-8", errors="ignore")
            except OSError:
                continue
            if base_type not in source_code and f"Abstract{base_type}" not in source_code:
                continue
            package_match = re.search(r"^\s*package\s+([\w.]+)\s*;", source_code, re.MULTILINE)
            package_name = package_match.group(1) if package_match else ""
            for class_match in re.finditer(
                r"\bpublic\s+(?!abstract\b)(?:final\s+)?class\s+([A-Za-z_][A-Za-z0-9_]*)\s+(?:extends|implements)\s+([^{]+)\{",
                source_code,
            ):
                class_name = class_match.group(1)
                inheritance = class_match.group(2)
                if base_type not in inheritance and f"Abstract{base_type}" not in inheritance:
                    continue
                candidates.append(f"{package_name}.{class_name}" if package_name else class_name)
        return candidates

    def _enum_constants_from_imports(self, imports: Iterable[str], source_path: Path) -> List[str]:
        constants: List[str] = []
        for import_line in imports:
            imported = import_line.removeprefix("import ").removesuffix(";").strip()
            if imported.startswith("static ") or imported.endswith(".*"):
                continue
            constants.extend(self._enum_constants_from_import(imported, source_path))
        return constants

    def _enum_constants_from_import(self, imported: str, source_path: Path) -> List[str]:
        parts = imported.split(".")
        project_source_root = self._project_source_root(source_path)
        if not project_source_root or len(parts) < 2:
            return []

        for class_index in range(len(parts) - 1, 0, -1):
            candidate_source = project_source_root / Path(*parts[:class_index]) / f"{parts[class_index]}.java"
            if not candidate_source.is_file():
                continue
            nested_names = parts[class_index + 1:]
            if not nested_names:
                return []
            try:
                source_code = candidate_source.read_text(encoding="utf-8", errors="ignore")
                tree = javalang.parse.parse(source_code) if javalang is not None else None
            except Exception:
                return []
            if tree is None:
                return []
            target = self._find_type(tree.types, nested_names[-1])
            if target is None or target.__class__.__name__ != "EnumDeclaration":
                return []
            return [f"{nested_names[-1]}.{constant.name}" for constant in getattr(target, "body", {}).constants or ()]
        return []

    @staticmethod
    def _project_source_root(source_path: Path) -> Optional[Path]:
        parts = source_path.parts
        marker = ("src", "main", "java")
        for index in range(len(parts) - len(marker) + 1):
            if tuple(parts[index:index + len(marker)]) == marker:
                return Path(*parts[:index + len(marker)])
        return None

    @classmethod
    def _enum_constants_from_types(cls, types: Iterable[Any]) -> List[str]:
        constants: List[str] = []
        for type_decl in types:
            if type_decl.__class__.__name__ == "EnumDeclaration":
                constants.extend(
                    f"{type_decl.name}.{constant.name}"
                    for constant in getattr(getattr(type_decl, "body", None), "constants", []) or ()
                )
            constants.extend(cls._enum_constants_from_types(cls._nested_type_declarations(type_decl)))
        return constants

    @classmethod
    def _find_type(cls, types: Iterable[Any], name: str) -> Any:
        for type_decl in types or ():
            if getattr(type_decl, "name", "") == name:
                return type_decl
            nested = cls._find_type(cls._nested_type_declarations(type_decl), name)
            if nested is not None:
                return nested
        return None

    @staticmethod
    def _nested_type_declarations(type_decl: Any) -> List[Any]:
        nested = list(getattr(type_decl, "types", []) or ())
        body = getattr(type_decl, "body", None)
        if isinstance(body, list):
            nested.extend(item for item in body if item.__class__.__name__.endswith("Declaration"))
        declarations = getattr(body, "declarations", None)
        if declarations:
            nested.extend(item for item in declarations if item.__class__.__name__.endswith("Declaration"))
        return nested

    @classmethod
    def _format_import(cls, import_decl: Any) -> str:
        prefix = "import static " if getattr(import_decl, "static", False) else "import "
        suffix = ".*" if getattr(import_decl, "wildcard", False) else ""
        return f"{prefix}{import_decl.path}{suffix};"

    @classmethod
    def _format_field(cls, field: Any) -> str:
        modifiers = cls._format_modifiers(getattr(field, "modifiers", None))
        type_name = cls._format_type(getattr(field, "type", None))
        names = ", ".join(declarator.name for declarator in getattr(field, "declarators", []) or ())
        return cls._join_signature_parts(modifiers, type_name, names)

    @classmethod
    def _format_constructor(cls, constructor: Any) -> str:
        modifiers = cls._format_modifiers(getattr(constructor, "modifiers", None))
        params = cls._format_parameters(getattr(constructor, "parameters", []) or ())
        throws = cls._format_throws(getattr(constructor, "throws", None))
        return cls._join_signature_parts(modifiers, f"{constructor.name}({params})", throws)

    @classmethod
    def _format_method(cls, method: Any) -> str:
        modifiers = cls._format_modifiers(getattr(method, "modifiers", None))
        return_type = cls._format_type(getattr(method, "return_type", None)) or "void"
        params = cls._format_parameters(getattr(method, "parameters", []) or ())
        throws = cls._format_throws(getattr(method, "throws", None))
        return cls._join_signature_parts(modifiers, return_type, f"{method.name}({params})", throws)

    @classmethod
    def _format_parameters(cls, parameters: Sequence[Any]) -> str:
        return ", ".join(
            cls._join_signature_parts(
                "final" if "final" in (getattr(param, "modifiers", None) or set()) else "",
                cls._format_type(getattr(param, "type", None)),
                param.name,
            )
            for param in parameters
        )

    @staticmethod
    def _format_modifiers(modifiers: Any) -> str:
        if not modifiers:
            return ""
        order = ("public", "protected", "private", "abstract", "static", "final", "synchronized", "native")
        modifier_set = set(modifiers)
        ordered = [modifier for modifier in order if modifier in modifier_set]
        ordered.extend(sorted(modifier_set.difference(order)))
        return " ".join(ordered)

    @classmethod
    def _format_type(cls, type_node: Any) -> str:
        if type_node is None:
            return ""
        name = getattr(type_node, "name", None)
        if name is None:
            return str(type_node)
        arguments = cls._format_type_arguments(getattr(type_node, "arguments", None))
        dimensions = "[]" * len(getattr(type_node, "dimensions", None) or ())
        return f"{name}{arguments}{dimensions}"

    @classmethod
    def _format_type_arguments(cls, arguments: Any) -> str:
        if not arguments:
            return ""
        rendered = []
        for argument in arguments:
            pattern_type = getattr(argument, "pattern_type", None)
            arg_type = cls._format_type(getattr(argument, "type", None))
            if pattern_type == "?":
                rendered.append("?")
            elif pattern_type:
                rendered.append(f"? {pattern_type} {arg_type}".strip())
            else:
                rendered.append(arg_type or "?")
        return f"<{', '.join(rendered)}>"

    @staticmethod
    def _format_throws(throws: Any) -> str:
        if not throws:
            return ""
        return "throws " + ", ".join(str(item) for item in throws)

    @staticmethod
    def _join_signature_parts(*parts: str) -> str:
        return " ".join(part for part in parts if part).strip()

    @staticmethod
    def _unique(items: Iterable[str], limit: int) -> Tuple[str, ...]:
        seen = set()
        result: List[str] = []
        for item in items:
            text = str(item or "").strip()
            if not text or text in seen:
                continue
            seen.add(text)
            result.append(text)
            if len(result) >= limit:
                break
        return tuple(result)

    @staticmethod
    def _text(value: Any) -> str:
        return str(value or "").strip()


def format_source_api_summary(summary: SourceApiSummary) -> str:
    if summary.empty:
        return "\n".join(
            [
                "## Available Source API",
                f"Source API summary status: {summary.status} (confidence: {summary.confidence}).",
                "No source-derived API evidence was available from this pass.",
                "Treat missing constructors, methods, fields, imports, or enum constants as unknown rather than proven absent.",
            ]
        )

    lines = [
        "## Available Source API",
        f"Source API summary status: {summary.status} (confidence: {summary.confidence}).",
        "Use this source-derived API evidence when writing the Java test.",
        "Listed APIs are evidence; unlisted APIs may simply be unresolved when confidence is not high.",
        "Prefer listed constructors, methods, fields, enum constants, imports, and packages; treat unlisted APIs as unknown unless already used by the current test.",
        "Avoid direct private-field access unless no public construction or state setup path is available.",
    ]
    if summary.source_path:
        lines.append(f"Source file: {summary.source_path}")
    if summary.package_name:
        lines.append(f"Package: {summary.package_name}")

    _append_group(lines, "Visible imports", summary.imports)
    _append_group(lines, "Fields", summary.fields)
    _append_group(lines, "Constructors", summary.constructors)
    _append_group(lines, "Methods", summary.methods)
    _append_group(lines, "Enum constants", summary.enum_constants)
    _append_group(lines, "Assignable class literals", summary.class_literal_hints)

    if summary.enum_constants:
        lines.append("")
        lines.append("Enum guidance:")
        lines.append("- Use one of the listed enum constants; do not instantiate enum types with new.")
    if summary.class_literal_hints:
        lines.append("")
        lines.append("Class literal suggestion:")
        lines.append("- For Class<? extends ...> parameters, prefer a listed assignable concrete class literal when it matches the target setup.")
    return "\n".join(lines)


def _append_group(lines: List[str], title: str, items: Iterable[str]) -> None:
    values = list(items)
    if not values:
        return
    lines.append("")
    lines.append(f"{title}:")
    lines.extend(f"- {item}" for item in values)
