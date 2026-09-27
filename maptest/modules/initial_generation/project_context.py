import re
import xml.etree.ElementTree as ET
from pathlib import Path
from typing import Any, Dict, List, Optional, Set, Tuple

import javalang


class InitialGeneratorProjectContextHelper:
    def __init__(self, repos_dir: Path, logger: Any,
                 enable_java_version_detection: bool = True,
                 cache: Optional[Dict[str, Dict[str, Any]]] = None):
        self.repos_dir = repos_dir
        self.logger = logger
        self.enable_java_version_detection = enable_java_version_detection
        self.cache = cache if cache is not None else {}

    def resolve_repo_project_name(self, raw_name: str) -> str:
        normalized = str(raw_name or "").strip()
        candidates = [normalized] if normalized else []

        stripped = re.sub(r"_(small|batch|part|split|chunk|shard)\d+$", "", normalized)
        if stripped and stripped not in candidates:
            candidates.append(stripped)

        for candidate in candidates:
            if (self.repos_dir / candidate / "pom.xml").exists():
                return candidate

        for candidate in candidates:
            if (self.repos_dir / candidate).exists():
                return candidate
        return normalized

    def get_project_context(self, repo_project_name: str) -> Dict[str, Any]:
        cached = self.cache.get(repo_project_name)
        if cached:
            return dict(cached)

        project_path = self.repos_dir / repo_project_name
        pom_path = project_path / "pom.xml"
        source_level, target_level = self.parse_java_levels_from_pom(pom_path)
        context = {
            "repo_project_name": repo_project_name,
            "project_path": project_path,
            "pom_path": pom_path,
            "java_source_level": source_level or "1.8",
            "java_target_level": target_level or source_level or "1.8",
        }
        self.cache[repo_project_name] = dict(context)
        return context

    def parse_java_levels_from_pom(self, pom_path: Path) -> Tuple[str, str]:
        if not self.enable_java_version_detection or not pom_path.exists():
            return "", ""

        try:
            tree = ET.parse(pom_path)
            root = tree.getroot()

            def find_text(local_name: str) -> str:
                for elem in root.iter():
                    if elem.tag.endswith(local_name) and elem.text and elem.text.strip():
                        return elem.text.strip()
                return ""

            source_level = self.normalize_java_level(find_text("maven.compiler.source"))
            target_level = self.normalize_java_level(find_text("maven.compiler.target"))
            return source_level, target_level
        except ET.ParseError as exc:
            self.logger.warning(f"瑙ｆ瀽 pom.xml 澶辫触: {exc}")
            return "", ""

    def update_java_levels_from_output(self, repo_project_name: str, build_output: str) -> Dict[str, Any]:
        context = self.get_project_context(repo_project_name)
        source_from_log, target_from_log = self.extract_java_levels_from_output(build_output)
        if source_from_log and context.get("java_source_level") != source_from_log:
            context["java_source_level"] = source_from_log
        if target_from_log and context.get("java_target_level") != target_from_log:
            context["java_target_level"] = target_from_log
        self.cache[repo_project_name] = dict(context)
        return context

    def extract_java_levels_from_output(self, build_output: str) -> Tuple[str, str]:
        text = str(build_output or "")
        source_match = re.search(r'-source\s+(\d+(?:\.\d+)?)', text)
        target_match = re.search(r'-target\s+(\d+(?:\.\d+)?)', text)
        return (
            self.normalize_java_level(source_match.group(1) if source_match else ""),
            self.normalize_java_level(target_match.group(1) if target_match else "")
        )

    @staticmethod
    def normalize_java_level(value: Any) -> str:
        text = str(value or "").strip()
        if not text:
            return ""
        if text.startswith("1."):
            return text
        if text.isdigit():
            if int(text) <= 8:
                return f"1.{text}"
            return text
        return text

    def build_generation_context(self, under_test_method: Dict[str, Any], focal_method_code: str,
                                 focal_method_info: str, plantuml_code: str,
                                 package_name: str, class_name: str,
                                 repo_project_name: str) -> Dict[str, Any]:
        project_context = self.get_project_context(repo_project_name)
        focal_class_candidates = []
        if class_name:
            focal_class_candidates.append({
                "simple_name": class_name,
                "fqcn": f"{package_name}.{class_name}" if package_name else class_name,
                "source": "focal_class",
            })
        import_candidates = self.parse_import_candidates(under_test_method.get('all_Import_statements', ''), package_name)
        code_candidates = self.extract_type_candidates_from_code(focal_method_code, package_name)
        standard_candidates = self.build_standard_type_candidates()

        candidate_map: Dict[str, List[Dict[str, str]]] = {}
        ordered_candidates: List[Dict[str, str]] = []
        # Keep UML as prompt-only context. Do not treat regex-extracted UML tokens as
        # directly usable project types, or they will pollute visible imports/types.
        for candidate in focal_class_candidates + standard_candidates + import_candidates + code_candidates:
            simple_name = candidate.get("simple_name")
            fqcn = candidate.get("fqcn") or simple_name
            if not simple_name:
                continue
            bucket = candidate_map.setdefault(simple_name, [])
            if any(existing.get("fqcn") == fqcn for existing in bucket):
                continue
            bucket.append(candidate)
            ordered_candidates.append(candidate)

        method_signatures, constructor_signatures = self.extract_member_signatures(focal_method_code)
        return {
            "repo_project_name": repo_project_name,
            "project_context": project_context,
            "java_source_level": project_context.get("java_source_level", "1.8"),
            "java_target_level": project_context.get("java_target_level", "1.8"),
            "focal_signature": self.extract_method_signature(focal_method_info, under_test_method.get('Method_name', '')),
            "focal_file_path": self.resolve_focal_file_path(under_test_method, repo_project_name),
            "available_type_candidates": ordered_candidates,
            "candidate_type_map": candidate_map,
            "known_method_signatures": method_signatures,
            "constructor_signatures": constructor_signatures,
            "known_method_names": self.extract_signature_names(method_signatures),
            "known_constructor_names": self.extract_signature_names(constructor_signatures),
            "package_name": package_name,
            "class_name": class_name,
        }

    def resolve_focal_file_path(self, under_test_method: Dict[str, Any], repo_project_name: str) -> str:
        raw_path = str(under_test_method.get('project_path', '') or '')
        if not raw_path:
            return ""
        normalized = raw_path.split("###", 1)[0]
        normalized = normalized.replace("\\", "/")
        if normalized.endswith(".java"):
            return normalized
        normalized = normalized.split(".java", 1)[0]
        normalized = normalized.replace(".", "/") + ".java"
        if normalized.startswith("/"):
            return normalized
        return str((self.repos_dir / repo_project_name / normalized).resolve())

    @staticmethod
    def parse_import_candidates(import_block: str, package_name: str) -> List[Dict[str, str]]:
        candidates: List[Dict[str, str]] = []
        for match in re.finditer(r'^\s*import\s+([A-Za-z0-9_.*]+)\s*;', import_block or "", re.MULTILINE):
            fqcn = match.group(1).strip()
            if fqcn.endswith(".*"):
                continue
            simple_name = fqcn.rsplit('.', 1)[-1]
            candidates.append({
                "simple_name": simple_name,
                "fqcn": fqcn,
                "source": "import",
            })

        if package_name:
            candidates.append({
                "simple_name": package_name.rsplit('.', 1)[-1],
                "fqcn": package_name,
                "source": "package",
            })
        return candidates

    @staticmethod
    def extract_type_candidates_from_code(code: str, package_name: str) -> List[Dict[str, str]]:
        seen: Set[str] = set()
        candidates: List[Dict[str, str]] = []
        try:
            tree = javalang.parse.parse(code)
            for _, node in tree.filter(javalang.tree.ReferenceType):
                simple_name = getattr(node, "name", "")
                if not simple_name or simple_name in seen:
                    continue
                seen.add(simple_name)
                fqcn = f"{package_name}.{simple_name}" if package_name else simple_name
                candidates.append({
                    "simple_name": simple_name,
                    "fqcn": fqcn,
                    "source": "focal_code",
                })
        except (javalang.parser.JavaSyntaxError, TypeError):
            for simple_name in re.findall(r'\b[A-Z][A-Za-z0-9_]*\b', code or ""):
                if simple_name in seen:
                    continue
                seen.add(simple_name)
                fqcn = f"{package_name}.{simple_name}" if package_name else simple_name
                candidates.append({
                    "simple_name": simple_name,
                    "fqcn": fqcn,
                    "source": "focal_code_regex",
                })
        return candidates

    @staticmethod
    def extract_type_candidates_from_uml(plantuml_code: str, package_name: str) -> List[Dict[str, str]]:
        candidates: List[Dict[str, str]] = []
        seen: Set[str] = set()
        for simple_name in re.findall(r'\b([A-Z][A-Za-z0-9_]*)\b', plantuml_code or ""):
            if simple_name in seen or simple_name in {"StartUML", "EndUML"}:
                continue
            seen.add(simple_name)
            fqcn = f"{package_name}.{simple_name}" if package_name else simple_name
            candidates.append({
                "simple_name": simple_name,
                "fqcn": fqcn,
                "source": "uml",
            })
        return candidates

    @staticmethod
    def build_standard_type_candidates() -> List[Dict[str, str]]:
        standards = [
            "org.junit.Test",
            "org.junit.Before",
            "org.junit.After",
            "org.junit.Rule",
            "org.junit.Ignore",
            "org.junit.Assert",
            "org.junit.rules.ExpectedException",
            "org.junit.runner.RunWith",
            "org.junit.runners.JUnit4",
            "java.lang.reflect.Method",
            "java.lang.reflect.Field",
            "java.lang.reflect.Constructor",
            "java.lang.reflect.InvocationTargetException",
            "java.io.Reader",
            "java.io.Writer",
            "java.io.StringReader",
            "java.io.StringWriter",
            "java.util.List",
            "java.util.ArrayList",
            "java.util.Map",
            "java.util.HashMap",
        ]
        return [
            {
                "simple_name": fqcn.rsplit('.', 1)[-1],
                "fqcn": fqcn,
                "source": "standard",
            }
            for fqcn in standards
        ]

    def extract_member_signatures(self, focal_method_code: str) -> Tuple[List[str], List[str]]:
        methods: List[str] = []
        constructors: List[str] = []
        try:
            tree = javalang.parse.parse(focal_method_code)
            for _, node in tree.filter(javalang.tree.MethodDeclaration):
                methods.append(self.format_member_signature(node))
            for _, node in tree.filter(javalang.tree.ConstructorDeclaration):
                constructors.append(self.format_member_signature(node))
        except (javalang.parser.JavaSyntaxError, TypeError):
            pass
        return methods, constructors

    def format_member_signature(self, node: Any) -> str:
        params = []
        for param in getattr(node, "parameters", []) or []:
            type_name = self.format_type_node(getattr(param, "type", None))
            if getattr(param, "varargs", False):
                type_name += "..."
            params.append(f"{type_name} {param.name}".strip())
        name = getattr(node, "name", "")
        return_type = self.format_type_node(getattr(node, "return_type", None))
        if isinstance(node, javalang.tree.ConstructorDeclaration):
            return f"{name}({', '.join(params)})"
        return f"{return_type} {name}({', '.join(params)})".strip()

    def format_type_node(self, node: Any) -> str:
        if node is None:
            return "void"
        name = getattr(node, "name", "") or str(node)
        dimensions = getattr(node, "dimensions", None) or []
        if dimensions:
            name += "[]" * len(dimensions)
        arguments = getattr(node, "arguments", None) or []
        if arguments:
            rendered = []
            for arg in arguments:
                type_arg = getattr(arg, "type", None)
                rendered.append(self.format_type_node(type_arg))
            name += f"<{', '.join(rendered)}>"
        return name

    @staticmethod
    def extract_signature_names(signatures: List[str]) -> List[str]:
        names: List[str] = []
        for signature in signatures or []:
            match = re.search(r'([A-Za-z_][A-Za-z0-9_]*)\s*\(', signature)
            if match:
                name = match.group(1)
                if name not in names:
                    names.append(name)
        return names

    @staticmethod
    def extract_method_signature(focal_method_info: str, method_name: str) -> str:
        header = (focal_method_info or "").strip().split("{", 1)[0].strip()
        if header:
            return re.sub(r'\s+', ' ', header)
        if method_name:
            return f"{method_name}(...)"
        return ""

