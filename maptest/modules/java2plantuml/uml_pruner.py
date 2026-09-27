import os
import re
import tree_sitter_java as tsjava
from tree_sitter import Language, Parser
from collections import deque
from typing import List, Set, Dict, Optional

# 初始化 Java 解析器
JAVA_LANGUAGE = Language(tsjava.language())
parser = Parser(JAVA_LANGUAGE)

class GlobalIndexer:
    """项目级全局索引器：扫描整个项目并记录所有类名及其对应的文件路径。"""
    def __init__(self, repo_path: Optional[str] = None):
        self.repo_path = repo_path
        self.class_to_path = {}
        # 与旧测试/旧调用保持兼容：index 字段包含结构化类信息
        self.index = {}

    def index_project(self, repo_path: str):
        """遍历目录，将所有 .java 文件映射到类名并提取基础结构信息。"""
        self.repo_path = repo_path
        self.class_to_path.clear()
        self.index.clear()

        for root, _, files in os.walk(repo_path):
            for file in files:
                if file.endswith(".java"):
                    file_path = os.path.join(root, file)
                    class_name = file.replace(".java", "")
                    package_name = self._extract_package_name(file_path)
                    fqcn = f"{package_name}.{class_name}" if package_name else class_name

                    # 同时支持简单类名和全限定类名
                    self.class_to_path[class_name] = file_path
                    self.class_to_path[fqcn] = file_path
                    self.index[fqcn] = {
                        "class_name": class_name,
                        "package_name": package_name,
                        "path": file_path,
                        "fields": self._extract_fields(file_path),
                    }

    # 与旧代码兼容的别名
    def scan_project(self):
        if self.repo_path:
            self.index_project(self.repo_path)

    def _extract_package_name(self, file_path: str) -> str:
        try:
            with open(file_path, "r", encoding="utf-8") as f:
                code = f.read()
            match = re.search(r"package\s+([\w.]+)\s*;", code)
            return match.group(1) if match else ""
        except Exception:
            return ""

    def _extract_fields(self, file_path: str) -> List[Dict[str, str]]:
        try:
            with open(file_path, "r", encoding="utf-8") as f:
                code = f.read()
            code_bytes = bytes(code, "utf8")
            tree = parser.parse(code_bytes)
            root = tree.root_node

            class_node = self._find_first_child(root, ["class_declaration", "interface_declaration", "enum_declaration"])
            class_body = self._find_first_child(class_node, ["class_body", "interface_body", "enum_body"]) if class_node else None

            fields: List[Dict[str, str]] = []
            if not class_body:
                return fields

            for child in class_body.children:
                if child.type != "field_declaration":
                    continue
                # 形如: private ClassB b;
                field_text = child.text.decode("utf-8")
                # 提取变量声明部分
                declarator = self._find_first_child(child, ["variable_declarator"])
                field_name = ""
                if declarator:
                    id_node = self._find_first_child(declarator, ["identifier"])
                    field_name = id_node.text.decode("utf-8") if id_node else ""

                type_node = self._find_first_child(child, ["type_identifier", "integral_type", "floating_point_type", "boolean_type"])
                field_type = type_node.text.decode("utf-8") if type_node else "Unknown"
                fields.append({"name": field_name, "type": field_type, "raw": field_text})

            return fields
        except Exception:
            return []

    def _find_first_child(self, node, types: List[str]):
        if node is None:
            return None
        for child in node.children:
            if child.type in types:
                return child
            res = self._find_first_child(child, types)
            if res:
                return res
        return None

class UMLPruner:
    """
    AST 驱动的 UML 裁剪引擎。
    
    运作流程:
    1. 基于 tree-sitter 深度扫描 Java 类定义。
    2. 利用 BFS (广度优先搜索) 发现类之间的引用关系 (字段类型、构造函数参数、'new' 表达式)。
    3. 裁剪代码：仅保留类名、字段声明和构造函数签名，严格移除所有方法体。
    4. 结果：为 LLM 提供精简且语义完备的上下文，防止 Token 浪费。
    """
    def __init__(self, indexer: GlobalIndexer):
        self.indexer = indexer
        self.visited_classes = set()

    def prune(self, focal_class: str, focal_method_name: str = "", focal_method_code: str = "",
              depth_limit: int = 2) -> str:
        """
        兼容主流程与测试的统一入口。

        Args:
            focal_class: 焦点类（简单类名或全限定类名）
            focal_method_name: 焦点方法名（用于聚焦方法级依赖）
            focal_method_code: 焦点方法代码（用于提取 new/静态调用依赖）
            depth_limit: BFS 深度限制
        """
        self.visited_classes.clear()

        queue: deque = deque()
        class_blocks: Dict[str, str] = {}
        class_order: List[str] = []
        edges: Set[tuple] = set()
        resolved_focal = self._resolve_class_name(focal_class)
        if resolved_focal:
            queue.append((resolved_focal, 0))

        # 从焦点方法代码中提取显式依赖（new / 静态调用）
        for cls in self._extract_class_refs_from_method(focal_method_code):
            resolved = self._resolve_class_name(cls)
            if resolved:
                queue.append((resolved, 0))

        while queue:
            current_class, current_depth = queue.popleft()
            if current_class in self.visited_classes or current_depth > depth_limit:
                continue
            self.visited_classes.add(current_class)

            path = self.indexer.class_to_path.get(current_class)
            if not path or not os.path.exists(path):
                continue

            is_focal_class = current_class == resolved_focal
            current_display_name = self._display_class_name(current_class)
            external_calls = self._extract_external_called_methods(focal_method_code)
            class_signature = self._extract_class_signature(
                path,
                focal_method_name=focal_method_name if is_focal_class else "",
                focal_method_code=focal_method_code if is_focal_class else "",
                compact=not is_focal_class,
                external_calls=external_calls
            )
            if class_signature and current_display_name not in class_blocks:
                class_blocks[current_display_name] = class_signature
                class_order.append(current_display_name)
            if current_depth >= depth_limit:
                continue

            dependencies = self._discover_dependencies(
                path,
                focal_method_name=focal_method_name if is_focal_class else "",
                focal_method_code=focal_method_code if is_focal_class else "",
                focus_on_method=is_focal_class
            )
            for dep, relation in dependencies:
                dep_resolved = self._resolve_class_name(dep)
                if dep_resolved:
                    edges.add((current_display_name, relation, self._display_class_name(dep_resolved)))
                if dep_resolved and dep_resolved not in self.visited_classes:
                    queue.append((dep_resolved, current_depth + 1))

        return self._render_pruned_uml(class_order, class_blocks, edges)

    def prune_uml_context(self, focal_class: str, depth: int = 2) -> str:
        """
        执行 BFS 发现依赖并提取裁剪后的代码。
        
        Args:
            focal_class: 焦点类名。
            depth: 扫描深度，默认 2 层，防止依赖图爆炸。
            
        Returns:
            拼接后的裁剪代码字符串。
        """
        return self.prune(focal_class=focal_class, depth_limit=depth)

    @staticmethod
    def _extract_direct_self_called_helpers(method_code: str, focal_method_name: str = "") -> Set[str]:
        helper_names: Set[str] = set()
        if not method_code:
            return helper_names

        ignored_tokens = {
            "if",
            "for",
            "while",
            "switch",
            "catch",
            "return",
            "throw",
            "new",
            "super",
            "this",
            "synchronized",
            "assert",
        }
        for match in re.finditer(r"(?<![\w.])([a-z_][A-Za-z0-9_]*)\s*\(", method_code):
            helper_name = match.group(1)
            if helper_name in ignored_tokens or helper_name == focal_method_name:
                continue
            helper_names.add(helper_name)
        return helper_names

    @staticmethod
    def _extract_external_called_methods(method_code: str) -> Set[str]:
        method_names: Set[str] = set()
        if not method_code:
            return method_names
        for match in re.finditer(r"\b[A-Za-z0-9_]+\.([a-z_][A-Za-z0-9_]*)\s*\(", method_code):
            method_names.add(match.group(1))
        return method_names

    def _declaration_signature_text(self, node, code_bytes) -> str:
        declaration_text = self._get_node_text(node, code_bytes).strip()
        if "{" in declaration_text:
            declaration_text = declaration_text.split("{", 1)[0].strip()
        if declaration_text and not declaration_text.endswith(";"):
            declaration_text += ";"
        return declaration_text

    def _extract_class_declaration_text(self, class_node, code_bytes) -> str:
        declaration_text = self._get_node_text(class_node, code_bytes).strip()
        if "{" in declaration_text:
            declaration_text = declaration_text.split("{", 1)[0].strip()
        return declaration_text

    def _extract_class_signature(self, file_path: str, focal_method_name: str = "",
                                 focal_method_code: str = "", compact: bool = False, external_calls: Set[str] = None) -> str:
        """
        真正的代码提取逻辑：
        利用 AST 仅保留类名、字段、构造函数，以及焦点方法直接 self-call 的 helper 签名。
        """
        with open(file_path, 'r', encoding='utf-8') as f:
            code = f.read()
        
        code_bytes = bytes(code, "utf8")
        tree = parser.parse(code_bytes)
        root = tree.root_node
        
        # 寻找类定义
        class_node = self._find_first_child(root, ["class_declaration", "interface_declaration", "enum_declaration"])
        if not class_node:
            return ""

        declaration_text = self._extract_class_declaration_text(class_node, code_bytes)
        header = declaration_text if declaration_text else f"class {os.path.basename(file_path).replace('.java', '')}"
        res = [f"{header} {{"]
        if external_calls is None:
            external_calls = set()

        helper_names = self._extract_direct_self_called_helpers(focal_method_code, focal_method_name)
        
        # 遍历类体内容
        class_body = self._find_first_child(class_node, ["class_body", "interface_body", "enum_body"])
        if class_body:
            for child in class_body.children:
                # 保留字段声明和构造函数声明
                if child.type in ["field_declaration", "constructor_declaration"]:
                    if not compact:
                        # 简单清理：如果是构造函数，去掉函数体 {}，保留声明
                        if child.type == "constructor_declaration":
                            decl_text = self._declaration_signature_text(child, code_bytes)
                            res.append(f"    {decl_text}")
                        else:
                            res.append(f"    {self._get_node_text(child, code_bytes)}")
                elif child.type == "method_declaration":
                    decl_text = self._declaration_signature_text(child, code_bytes)
                    method_match = re.search(r"([A-Za-z_][A-Za-z0-9_]*)\s*\(", decl_text)
                    method_name = method_match.group(1) if method_match else ""
                    
                    if not compact:
                        # 焦点类：只保留自身及 Helper
                        if method_name and (method_name == focal_method_name or method_name in helper_names):
                            res.append(f"    {decl_text}")
                    else:
                        # 依赖类：只保留被核心方法调用的签名以供排查
                        if method_name and method_name in external_calls:
                            res.append(f"    {decl_text}")
        
        res.append("}")
        return "\n".join(res)

    def _render_pruned_uml(self, class_order: List[str], class_blocks: Dict[str, str], edges: Set[tuple]) -> str:
        if not class_order:
            return ""

        included = set(class_order)
        lines: List[str] = ["@startuml"]
        for class_name in class_order:
            lines.append(class_blocks[class_name])
            lines.append("")

        relation_to_arrow = {
            "uses": "-->",
            "extends": "--|>",
            "implements": "..|>",
        }
        for src, relation, dst in sorted(edges):
            if src in included and dst in included and src != dst:
                lines.append(f"{src} {relation_to_arrow.get(relation, '-->')} {dst}")
        lines.append("@enduml")
        return "\n".join(lines)

    def _discover_dependencies(self, file_path: str, focal_method_name: str = "",
                               focal_method_code: str = "", focus_on_method: bool = False) -> Set[tuple]:
        """
        真正的依赖扫描：
        扫描类型标识符，识别当前类引用的其他类。
        """
        with open(file_path, 'r', encoding='utf-8') as f:
            code = f.read()
        
        code_bytes = bytes(code, "utf8")
        tree = parser.parse(code_bytes)

        deps: Set[tuple] = set()
        class_node = self._find_first_child(tree.root_node, ["class_declaration", "interface_declaration", "enum_declaration"])
        class_body = self._find_first_child(class_node, ["class_body", "interface_body", "enum_body"]) if class_node else None
        if not class_node:
            return deps
        self._collect_class_declaration_dependencies(class_node, code_bytes, deps)
        if not class_body:
            return {(dep, relation) for dep, relation in deps if not self._is_ignored_type(dep)}

        if focus_on_method:
            for child in class_body.children:
                if child.type in ["field_declaration", "constructor_declaration"]:
                    self._collect_type_identifiers(child, deps, relation="uses")
                elif child.type == "method_declaration":
                    method_id_node = self._find_first_child(child, ["identifier"])
                    method_name = method_id_node.text.decode("utf-8") if method_id_node else ""
                    if method_name and method_name == focal_method_name:
                        self._collect_type_identifiers(child, deps, relation="uses")

            if focal_method_code:
                deps.update((dep, "uses") for dep in self._extract_class_refs_from_method(focal_method_code))

        return {(dep, relation) for dep, relation in deps if not self._is_ignored_type(dep)}

    def _extract_class_refs_from_method(self, method_code: str) -> Set[str]:
        """
        从焦点方法源码中提取类引用：
        - `new ClassName(...)`
        - `ClassName.staticCall(...)`
        """
        refs: Set[str] = set()
        if not method_code:
            return refs

        refs.update(re.findall(r"\bnew\s+([A-Z]\w*)\b", method_code))
        refs.update(re.findall(r"\b([A-Z]\w*)\s*\.", method_code))
        return refs

    @staticmethod
    def _is_ignored_type(type_name: str) -> bool:
        ignored_types = {
            "String", "Integer", "Long", "Boolean", "Double", "Float", "Short", "Byte", "Character",
            "Object", "Class", "Void", "List", "Map", "Set", "Collection", "Iterable", "Optional",
            "int", "long", "boolean", "double", "float", "short", "byte", "char", "void"
        }
        if len(type_name) == 1 and type_name.isupper():
            return True
        return type_name in ignored_types

    def _collect_class_declaration_dependencies(self, class_node, code_bytes, deps: Set[tuple]):
        declaration_text = self._extract_class_declaration_text(class_node, code_bytes)
        if not declaration_text:
            return

        own_name_match = re.search(r'\b(?:class|interface|enum)\s+([A-Za-z_][A-Za-z0-9_]*)', declaration_text)
        own_name = own_name_match.group(1) if own_name_match else ""
        generic_params = set(re.findall(r'<\s*([A-Z])(?:\s|,|>|extends)', declaration_text))

        extends_match = re.search(r'\bextends\s+(.+?)(?:\s+implements\b|$)', declaration_text)
        implements_match = re.search(r'\bimplements\s+(.+)$', declaration_text)

        if extends_match:
            for type_name in re.findall(r'\b([A-Z][A-Za-z0-9_]*)\b', extends_match.group(1)):
                if type_name == own_name or type_name in generic_params:
                    continue
                deps.add((type_name, "extends"))

        if implements_match:
            for type_name in re.findall(r'\b([A-Z][A-Za-z0-9_]*)\b', implements_match.group(1)):
                if type_name == own_name or type_name in generic_params:
                    continue
                deps.add((type_name, "implements"))

    def _collect_type_identifiers(self, node, deps: Set[tuple], relation: str = "uses"):
        if node.type in ["type_identifier", "scoped_type_identifier"]:
            deps.add((node.text.decode("utf-8"), relation))
        for child in node.children:
            self._collect_type_identifiers(child, deps, relation=relation)

    def _resolve_class_name(self, class_name: str) -> Optional[str]:
        """将简单类名/全限定类名解析到 index 中存在的 key。"""
        if class_name in self.indexer.class_to_path:
            return class_name

        # 全限定名后缀匹配（输入可能是 simple name）
        for known in self.indexer.class_to_path.keys():
            if known.endswith(f".{class_name}") or known == class_name:
                return known
        return None

    @staticmethod
    def _display_class_name(class_name: str) -> str:
        return class_name.split(".")[-1]

    def _find_first_child(self, node, types: List[str]):
        for child in node.children:
            if child.type in types: return child
            # 递归查找一层，防止 tree-sitter 嵌套包装
            res = self._find_first_child(child, types) if child.type == "program" else None
            if res: return res
        return None

    def _get_node_text(self, node, code_bytes):
        return node.text.decode('utf-8')
