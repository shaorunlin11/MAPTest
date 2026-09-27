import re
from typing import Any, Dict, List, Set, Tuple

import javalang


class InitialGeneratorPreflightHelper:
    @staticmethod
    def build_standard_simple_types() -> Set[str]:
        return {
            "String", "Object", "Class", "Integer", "Long", "Double", "Float", "Boolean",
            "Byte", "Short", "Character", "Number", "Exception", "RuntimeException",
            "IllegalArgumentException", "IllegalStateException", "NullPointerException",
            "Iterable", "Collection", "List", "ArrayList", "Map", "HashMap", "Set",
            "HashSet", "Collections", "Arrays", "Optional", "Reader", "Writer",
            "StringReader", "StringWriter", "StringBuilder", "StringBuffer",
            "Method", "Field", "Constructor", "Cloneable", "IOException",
            "InvocationTargetException", "Test", "Before", "After", "Ignore",
            "Assert", "RunWith", "JUnit4", "ExpectedException", "System",
            "ClassCastException", "NumberFormatException", "NoSuchFieldException",
            "NoSuchMethodException", "IllegalAccessException",
            "UnsupportedOperationException",
        }

    @staticmethod
    def rewrite_package_statement(code: str, expected_package: str) -> str:
        if not expected_package:
            return code
        package_line = f"package {expected_package};"
        if re.search(r'^\s*package\s+[A-Za-z0-9_.]+\s*;', code, re.MULTILINE):
            return re.sub(r'^\s*package\s+[A-Za-z0-9_.]+\s*;', package_line, code, count=1, flags=re.MULTILINE)
        return f"{package_line}\n\n{code.lstrip()}"

    @staticmethod
    def inject_imports(code: str, imports: List[str]) -> str:
        lines = code.splitlines()
        if not lines:
            return code
        existing = {line.strip() for line in lines if line.strip().startswith("import ")}
        pending = [imp for imp in imports if imp not in existing]
        if not pending:
            return code

        package_index = -1
        last_import_index = -1
        for index, line in enumerate(lines):
            stripped = line.strip()
            if stripped.startswith("package "):
                package_index = index
            if stripped.startswith("import "):
                last_import_index = index

        if last_import_index >= 0:
            insert_index = last_import_index + 1
        elif package_index >= 0:
            insert_index = package_index + 1
        else:
            insert_index = 0

        prefix = lines[:insert_index]
        suffix = lines[insert_index:]
        if prefix and prefix[-1].strip():
            prefix.append("")
        prefix.extend(sorted(pending))
        prefix.append("")
        return "\n".join(prefix + suffix)

    @classmethod
    def normalize_test_header(cls, code: str, expected_package: str) -> Tuple[str, List[str]]:
        notes: List[str] = []
        fixed = cls.rewrite_package_statement(code, expected_package)
        if fixed != code:
            notes.append(f"preflight: normalized package to {expected_package}")

        lines = fixed.splitlines()
        seen_package = False
        seen_imports: Set[str] = set()
        normalized_lines: List[str] = []
        for line in lines:
            stripped = line.strip()
            if stripped.startswith("package "):
                if seen_package:
                    notes.append("preflight: removed duplicate package statement")
                    continue
                seen_package = True
            if stripped.startswith("import "):
                if stripped in seen_imports:
                    notes.append(f"preflight: removed duplicate import {stripped}")
                    continue
                seen_imports.add(stripped)
            normalized_lines.append(line)
        return "\n".join(normalized_lines), notes

    @staticmethod
    def rewrite_public_class_name(code: str, expected_class_name: str) -> str:
        return re.sub(
            r'(\bpublic\s+class\s+)([A-Za-z_][A-Za-z0-9_]*)',
            rf'\1{expected_class_name}',
            code,
            count=1,
        )

    @staticmethod
    def _extract_terminal_simple_type_name(raw_name: str) -> str:
        text = str(raw_name or "").strip()
        if not text:
            return ""
        simple_name = text.rsplit(".", 1)[-1].strip()
        return simple_name if re.match(r'^[A-Z][A-Za-z0-9_]*$', simple_name) else ""

    @classmethod
    def _extract_referenced_type_names(cls, test_code: str) -> Set[str]:
        try:
            tree = javalang.parse.parse(test_code)
            type_names: Set[str] = set()
            for _, node in tree.filter(javalang.tree.ReferenceType):
                simple_name = cls._extract_terminal_simple_type_name(getattr(node, "name", ""))
                if simple_name:
                    type_names.add(simple_name)
            return type_names
        except (javalang.parser.JavaSyntaxError, TypeError):
            return set(re.findall(r'\b[A-Z][A-Za-z0-9_]*\b', test_code))

    @staticmethod
    def _build_explicitly_resolved_names(generation_context: Dict[str, Any]) -> Set[str]:
        resolved_names: Set[str] = set()
        class_name = generation_context.get("class_name", "")
        if class_name:
            resolved_names.add(class_name)

        package_name = generation_context.get("package_name", "")
        candidate_map = generation_context.get("candidate_type_map", {})
        if package_name:
            same_package_prefix = f"{package_name}."
            for simple_name, matches in candidate_map.items():
                if any((match.get("fqcn") or "").startswith(same_package_prefix) for match in matches):
                    resolved_names.add(simple_name)
        return resolved_names

    @classmethod
    def collect_missing_imports(cls, test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        candidate_map = generation_context.get("candidate_type_map", {})
        standard_types = cls.build_standard_simple_types()
        imports_needed: List[str] = []
        explicitly_resolved = cls._build_explicitly_resolved_names(generation_context)

        imported_simple_names = {
            match.group(1).rsplit(".", 1)[-1]
            for match in re.finditer(r'^\s*import\s+([A-Za-z0-9_.]+)\s*;', test_code, re.MULTILINE)
            if not match.group(1).endswith(".*")
        }
        defined_names = set(re.findall(r'\b(?:class|interface|enum)\s+([A-Za-z_][A-Za-z0-9_]*)\b', test_code))
        package_name = generation_context.get("package_name", "")
        type_names = cls._extract_referenced_type_names(test_code)

        for type_name in sorted(type_names):
            if type_name in standard_types or type_name in imported_simple_names or type_name in defined_names or type_name in explicitly_resolved:
                continue
            matches = candidate_map.get(type_name, [])
            if len(matches) != 1:
                continue
            fqcn = matches[0].get("fqcn", "")
            if package_name and fqcn.startswith(f"{package_name}."):
                continue
            imports_needed.append(f"import {fqcn};")
        return imports_needed

    @staticmethod
    def detect_shadowing_helper_classes(test_code: str, expected_class_name: str,
                                        generation_context: Dict[str, Any]) -> List[str]:
        issues: List[str] = []
        candidate_map = generation_context.get("candidate_type_map", {})
        for match in re.finditer(r'\bclass\s+([A-Za-z_][A-Za-z0-9_]*)\b', test_code):
            class_name = match.group(1)
            if class_name == expected_class_name:
                continue
            if len(candidate_map.get(class_name, [])) == 1:
                issues.append(
                    f"shadowing helper class detected: {class_name} conflicts with project type {candidate_map[class_name][0].get('fqcn', class_name)}"
                )
        return issues

    @staticmethod
    def detect_java_level_issues(test_code: str, java_source_level: str) -> List[str]:
        issues: List[str] = []
        try:
            normalized = float(java_source_level.replace("1.", "")) if java_source_level.startswith("1.") else float(java_source_level)
        except ValueError:
            normalized = 8.0
        if normalized <= 7.0:
            if "->" in test_code:
                issues.append("java level incompatibility: lambda syntax is not supported below Java 8")
            if "::" in test_code:
                issues.append("java level incompatibility: method references are not supported below Java 8")
            if ".stream(" in test_code:
                issues.append("java level incompatibility: stream API style usage is not supported by source level 1.7")
        return issues

    @classmethod
    def detect_unresolved_simple_types(cls, test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        candidate_map = generation_context.get("candidate_type_map", {})
        standard_types = cls.build_standard_simple_types()
        explicitly_resolved = cls._build_explicitly_resolved_names(generation_context)
        imported_simple_names = {
            match.group(1).rsplit(".", 1)[-1]
            for match in re.finditer(r'^\s*import\s+([A-Za-z0-9_.]+)\s*;', test_code, re.MULTILINE)
            if not match.group(1).endswith(".*")
        }
        defined_names = set(re.findall(r'\b(?:class|interface|enum)\s+([A-Za-z_][A-Za-z0-9_]*)\b', test_code))
        type_names = cls._extract_referenced_type_names(test_code)

        unresolved = []
        for type_name in sorted(type_names):
            if type_name in standard_types or type_name in imported_simple_names or type_name in defined_names or type_name in explicitly_resolved:
                continue
            if type_name in candidate_map:
                continue
            unresolved.append(type_name)
        return unresolved

    @staticmethod
    def detect_reflection_signature_issues(test_code: str, generation_context: Dict[str, Any]) -> List[str]:
        issues: List[str] = []
        known_method_names = set(generation_context.get("known_method_names", []))
        known_constructor_names = set(generation_context.get("known_constructor_names", []))
        class_name = generation_context.get("class_name", "")
        if not class_name:
            return issues

        class_pattern = re.escape(class_name)
        for match in re.finditer(
            rf'\b{class_pattern}\s*\.class\s*\.\s*get(?:Declared)?Method\(\s*"([A-Za-z_][A-Za-z0-9_]*)"',
            test_code,
        ):
            method_name = match.group(1)
            if method_name not in known_method_names:
                issues.append(f"reflection signature mismatch: method '{method_name}' not found in focal source")

        constructor_calls = re.findall(
            rf'\b{class_pattern}\s*\.class\s*\.\s*get(?:Declared)?Constructor\(',
            test_code,
        )
        if constructor_calls and class_name not in known_constructor_names and known_constructor_names:
            issues.append(f"reflection signature mismatch: constructor usage should match one of {', '.join(sorted(known_constructor_names))}")

        return issues

    @classmethod
    def run_preflight_checks(cls, test_code: str, expected_package: str, expected_class_name: str,
                             generation_context: Dict[str, Any]) -> Dict[str, Any]:
        fixed_code, notes = cls.normalize_test_header(test_code, expected_package)

        rewritten = cls.rewrite_public_class_name(fixed_code, expected_class_name)
        if rewritten != fixed_code:
            fixed_code = rewritten
            notes.append(f"preflight: rewrote public class name to {expected_class_name}")

        missing_imports = cls.collect_missing_imports(fixed_code, generation_context)
        if missing_imports:
            fixed_code = cls.inject_imports(fixed_code, missing_imports)
            notes.append(f"preflight: injected imports {', '.join(missing_imports)}")

        issues: List[str] = []
        issues.extend(cls.detect_shadowing_helper_classes(fixed_code, expected_class_name, generation_context))
        issues.extend(cls.detect_java_level_issues(fixed_code, generation_context.get("java_source_level", "1.8")))
        unresolved_types = cls.detect_unresolved_simple_types(fixed_code, generation_context)
        if unresolved_types:
            issues.append(f"unresolved simple types: {', '.join(unresolved_types[:8])}")
        issues.extend(cls.detect_reflection_signature_issues(fixed_code, generation_context))

        if issues:
            error_info = "########## Preflight FAILURE ##########\n" + "\n".join(f"- {issue}" for issue in issues)
        else:
            error_info = ""

        return {
            "passed": not issues,
            "code": fixed_code,
            "notes": notes,
            "issues": issues,
            "error_info": error_info,
        }
