import tree_sitter_java as ts_java
from loguru import logger
from tree_sitter import Parser, Language, Node
from typing import Optional, List

# 使用绝对导入
from tree_sitter_java_parser.entities.CodeEntities import Method, Class, Field

# 初始化一个用于解析Java代码的Parser对象, Initialize the parser with the Java language
parser = Parser(Language(ts_java.language()))

# 5、获取修饰符
def get_modifier(node: Node) -> str:
    return ' '.join([n.text.decode('utf-8') for n in node.children if n.type == 'modifiers'])

# 2、获取包名
def _find_package_name(root_node: Node) -> str:
    # 查找 package 定义
    package_decl_node = next(filter(lambda n: n.type == 'package_declaration', root_node.children))

    if package_decl_node:
        try:
            assert isinstance(package_decl_node, Node)
            assert package_decl_node.child(1).type == 'scoped_identifier'  # 防止 magic number 出错
            pkg_name = package_decl_node.child(1).text.decode('utf-8')
        except AssertionError:
            # just in case.
            logger.error(
                f'Package declaration node is not as expected. Expected: package_declaration, got: {package_decl_node.type}; Expected child type: scoped_identifier, got: {package_decl_node.child(1).type}.')
            pkg_name = ''
        pass
    else:
        pkg_name = ''

    return pkg_name

# 3、获取类声明节点（支持class、interface、enum、annotation）
def _find_class_declaration_node(root_node: Node, target_class_name: Optional[str]) -> Optional[Node]:
    # 查找目标类定义节点，支持class、interface、enum、annotation
    class_decl_node = None
    # 支持多种声明类型，包括注解接口
    supported_types = ['class_declaration', 'interface_declaration', 'enum_declaration', 'annotation_type_declaration']
    class_decl_nodes = [n for n in root_node.children if n.type in supported_types]
    
    if target_class_name:
        # 如果有目标类的名字（根据文件名获得），那么根据名字查找，应该只有一个，用 next 获取
        class_decl_node = next(
            filter(lambda n: n.child_by_field_name('name').text.decode('utf-8') == target_class_name, class_decl_nodes),
            None)
    elif len(class_decl_nodes) >= 1:
        # 没有设定目标名字，默认取第一个 public 且非 abstract 的 class/interface/enum node
        for node in class_decl_nodes:
            # 从当前类节点的子节点中，查找第一个包含modifiers（修饰符）的子节点：
            modifiers = next(filter(lambda n: n.child.type == 'modifiers', node.children), None)
            if modifiers:
                modifier_text = modifiers.text.decode('utf-8')
                if 'public' in modifier_text and 'abstract' not in modifier_text:
                    class_decl_node = node
                    break

    # 判断是否找到
    if not class_decl_node:
        logger.warning(f'Class/Interface/Enum declaration not found. Please refer to the debug information for debugging.')
        logger.debug(root_node.text.decode('utf-8'))

    return class_decl_node

# 4、获取导入语句
def _find_imports(root_node: Node) -> List[str]:
    import_nodes = [n for n in root_node.children if n.type == 'import_declaration']
    imports = []
    for node in import_nodes:
        try:
            # 获取完整的导入语句
            import_text = node.text.decode('utf-8').strip()
            imports.append(import_text)
        except Exception as e:
            logger.error(f'Error processing import node: {e}')
            continue
    return imports  # 返回完整import语句

    # try:
    #     for node in import_nodes:
    #         assert node.child(1).type == 'scoped_identifier'
    #         break
    # except AssertionError:
    #     logger.error('The imported class is not the second child in the import declaration node anymore, please check.')
    #     return [n.text.decode('utf-8') for n in import_nodes]
    # return [n.child(1).text.decode('utf-8') for n in import_nodes] if len(import_nodes) != 0 else []  # 返回不完整语句


# 6、将一个方法声明节点（node）转换为一个表示方法的对象（Method）
def method_decl_node_to_method_obj(node: Node, pkg_name: str, class_name: str, ) -> Method:
    """
    Converts a method declaration node to a Method object.

    Args:
        node (Node): The method declaration node.
        pkg_name (str): The package name of the class containing the method.
        class_name (str): The name of the class containing the method.

    Returns:
        Method: An object representing the method.
    """
    docstring = node.prev_sibling.text.decode('utf-8') if node.prev_sibling.type == 'block_comment' else ''
    modifier = get_modifier(node)
    method_text = node.text.decode('utf-8')
    parameter_nodes = [n for n in node.child_by_field_name('parameters').children if n.type not in ['(', ')', ',']]
    parameters = []
    for p in parameter_nodes:
        name_node = p.child_by_field_name('name')
        type_node = p.child_by_field_name('type')
        if not name_node or not type_node:
            continue  # 跳过无效参数节点
        parameters.append(
            Field(
                name=name_node.text.decode('utf-8'),
                type=type_node.text.decode('utf-8'),
                modifier='',
                value='',
                docstring=''
            ))
    return_type = node.child_by_field_name('type').text.decode('utf-8')
    return Method(
        name=node.child_by_field_name('name').text.decode('utf-8'),
        modifier=modifier,
        text=method_text,
        return_type=return_type,
        params=parameters,
        class_sig=pkg_name + '.' + class_name,
        docstring=docstring
    )

# 7、获取构造器完整声明
def get_constructors(class_body_node: Node) -> list[str]:
    class_constructors = []
    for child in class_body_node.children:
        if child.type == 'constructor_declaration':
            constructor_text = child.text.decode('utf-8')
            class_constructors.append(constructor_text)
    return class_constructors


# 8、将一个字段声明节点（field declaration node）转换为一个Field对象
def field_decl_node_to_field_obj(node: Node) -> Optional[Field]:
    """
    Converts a field declaration node to a Field object.

    Args:
        node (Node): The field declaration node.

    Returns:
        Field | None: An object representing the field, or None if no declarator is found.
    """
    docstring = node.prev_sibling.text.decode('utf-8') if node.prev_sibling.type == 'block_comment' else ''
    modifier = get_modifier(node)
    type = node.child_by_field_name('type').text.decode('utf-8')
    declarator = node.child_by_field_name('declarator')
    if declarator:
        name = declarator.child_by_field_name('name').text.decode('utf-8')
        value_node = declarator.child_by_field_name('value')
        value = value_node.text.decode('utf-8') if value_node else ''
        return Field(
            docstring=docstring,
            name=name,
            modifier=modifier,
            type=type,
            value=value,
            text=node.text.decode('utf-8')
        )
    else:
        logger.warning(f"No declarator found for {node.text.decode('utf-8')}")
        return None


# 打印解析树的结构
def print_tree(node: Node, level: int = 0):
    indent = '  ' * level
    logger.debug(f"{indent}Node Type: {node.type}, Text: {node.text.decode('utf-8')}")
    for child in node.children:
        print_tree(child, level + 1)

# 1、从给定的文件内容解析类对象
def parse_class_object_from_file_content(file_content: str, target_class_name: Optional[str]) -> Optional[Class]:
    """
    Parses the class object from the given file content.

    Args:
        file_content (str): The content of the file to parse.
        target_class_name (str | None): The name of the target class to find. If None, the first public non-abstract class is used.

    Returns:
        Class | None: An object representing the class, or None if the class declaration is not found.
    """
    tree = parser.parse(bytes(file_content, 'utf-8'))   # 生成解析树
    # print_tree(tree.root_node)    # 打印解析树结构

    pkg_name = _find_package_name(tree.root_node)
    class_decl_node = _find_class_declaration_node(tree.root_node, target_class_name)  # 获取类声明节点
    imports = _find_imports(tree.root_node)
    if not class_decl_node:
        logger.debug(f"No class declaration found in file content:\n{file_content[:200]}...")  # 记录前200字节内容
        return None

    # 获取父类节点：
    superclass_node = class_decl_node.child_by_field_name('superclass')
    if superclass_node:
        superclass = class_decl_node.child_by_field_name('superclass').text.decode('utf-8')
    else:
        superclass = ''

    # 获取接口节点：
    super_interface_node = class_decl_node.child_by_field_name('interfaces')
    if super_interface_node:
        interface = class_decl_node.child_by_field_name('interfaces').text.decode('utf-8')
    else:
        interface = ''

    # 获取类声明节点的 body 部分
    class_body = class_decl_node.child_by_field_name('body')
    
    # 获取构造器（注解类型没有构造器）
    constructors = []
    if class_decl_node.type != 'annotation_type_declaration':
        constructors = get_constructors(class_body)

    class_obj = Class(
        package_name=pkg_name,
        name=class_decl_node.child_by_field_name('name').text.decode('utf-8'),
        modifier=get_modifier(class_decl_node),
        text=class_decl_node.text.decode('utf-8'),
        imports=imports,
        interface=interface,
        superclass=superclass,
        constructors=constructors
    )

    # 遍历类声明body中所有子节点
    for child in class_body.children:
        if child.type == 'method_declaration':
            # 只接受块注释的 docstring
            method_obj = method_decl_node_to_method_obj(child, pkg_name, class_obj.name)
            class_obj.add_method(method_obj)
        elif child.type == 'field_declaration':
            field_obj = field_decl_node_to_field_obj(child)
            if field_obj: class_obj.add_field(field_obj)
        elif child.type in ['class_declaration', 'interface_declaration', 'enum_declaration', 'annotation_type_declaration']:
            # 处理内部类、接口、枚举、注解
            inner_class_obj = parse_class_declaration_node(child, pkg_name, imports, class_obj.name)
            if inner_class_obj:
                # 将内部类作为特殊方法处理，保持JSON格式兼容性
                inner_class_method = Method(
                    name=inner_class_obj.name,
                    modifier=inner_class_obj.modifier,
                    text=inner_class_obj.text,
                    return_type=inner_class_obj.name,  # 内部类名作为返回类型
                    params=[],
                    class_sig=class_obj.signature,
                    docstring=inner_class_obj.docstring
                )
                class_obj.add_method(inner_class_method)
        elif child.type == 'local_variable_declaration':
            # 处理匿名类（通常在局部变量声明中）
            anonymous_class_obj = parse_anonymous_class_from_variable(child, pkg_name, class_obj.name)
            if anonymous_class_obj:
                class_obj.add_method(anonymous_class_obj)
        elif child.type in ['expression_statement', 'statement']:
            # 处理语句中的匿名类
            anonymous_class_obj = parse_anonymous_class_from_statement(child, pkg_name, class_obj.name)
            if anonymous_class_obj:
                class_obj.add_method(anonymous_class_obj)
        elif child.type == 'static_initializer':
            # 处理静态初始化块
            static_init_method = parse_static_initializer(child, pkg_name, class_obj.name)
            if static_init_method:
                class_obj.add_method(static_init_method)
        elif child.type == 'instance_initializer':
            # 处理实例初始化块
            instance_init_method = parse_instance_initializer(child, pkg_name, class_obj.name)
            if instance_init_method:
                class_obj.add_method(instance_init_method)

    return class_obj

# 解析匿名类从局部变量声明
def parse_anonymous_class_from_variable(var_decl_node: Node, pkg_name: str, class_name: str) -> Optional[Method]:
    """从局部变量声明中解析匿名类"""
    if not var_decl_node or var_decl_node.type != 'local_variable_declaration':
        return None
    
    # 查找变量声明器中的匿名类
    for child in var_decl_node.children:
        if child.type == 'variable_declarator':
            # 在变量声明器中查找匿名类
            for subchild in child.children:
                if subchild.type == 'anonymous_class_creation':
                    # 解析匿名类
                    anonymous_class_method = parse_anonymous_class_creation(subchild, pkg_name, class_name)
                    if anonymous_class_method:
                        # 获取变量名作为方法名的一部分
                        var_name_node = child.child_by_field_name('name')
                        var_name = var_name_node.text.decode('utf-8') if var_name_node else 'anonymous'
                        anonymous_class_method.name = f"{var_name}_anonymous_class"
                        return anonymous_class_method
    return None

# 解析匿名类创建表达式
def parse_anonymous_class_creation(anonymous_node: Node, pkg_name: str, class_name: str) -> Optional[Method]:
    """解析匿名类创建表达式"""
    if not anonymous_node or anonymous_node.type != 'anonymous_class_creation':
        return None
    
    # 获取匿名类的body
    class_body = anonymous_node.child_by_field_name('body')
    if not class_body:
        return None
    
    # 获取匿名类实现的类型
    base_type_node = anonymous_node.child_by_field_name('base')
    base_type = base_type_node.text.decode('utf-8') if base_type_node else 'Object'
    
    # 创建一个表示匿名类的Method对象
    anonymous_methods = []
    for child in class_body.children:
        if child.type == 'method_declaration':
            method_obj = method_decl_node_to_method_obj(child, pkg_name, f"{class_name}$Anonymous")
            anonymous_methods.append(method_obj)
        elif child.type == 'field_declaration':
            field_obj = field_decl_node_to_field_obj(child)
            if field_obj:
                # 将字段转换为方法的参数形式，以便在JSON中表示
                anonymous_methods.append(Method(
                    name=field_obj.name,
                    modifier=field_obj.modifier,
                    text=field_obj.text,
                    return_type=field_obj.type,
                    params=[],
                    class_sig=f"{pkg_name}.{class_name}$Anonymous",
                    docstring=field_obj.docstring
                ))
    
    # 如果匿名类有方法，创建一个代表匿名类的方法
    if anonymous_methods:
        # 将所有匿名类的方法合并为一个方法体
        combined_text = f"// Anonymous class implementing {base_type}\n"
        for method in anonymous_methods:
            combined_text += method.text + "\n"
        
        return Method(
            name="anonymous_class",
            modifier="",
            text=combined_text.strip(),
            return_type=base_type,
            params=[],
            class_sig=f"{pkg_name}.{class_name}$Anonymous",
            docstring=f"Anonymous class implementing {base_type}"
        )
    
    return None

# 解析类声明节点的通用函数（用于处理内部类）
def parse_class_declaration_node(class_decl_node: Node, pkg_name: str, imports: List[str], parent_class_name: str = '') -> Optional[Class]:
    """解析单个类声明节点，用于处理内部类"""
    if not class_decl_node:
        return None
        
    # 获取父类节点：
    superclass_node = class_decl_node.child_by_field_name('superclass')
    superclass = superclass_node.text.decode('utf-8') if superclass_node else ''
    
    # 获取接口节点：
    super_interface_node = class_decl_node.child_by_field_name('interfaces')
    interface = super_interface_node.text.decode('utf-8') if super_interface_node else ''
    
    # 获取类声明节点的 body 部分
    class_body = class_decl_node.child_by_field_name('body')
    if not class_body:
        return None
        
    # 获取构造器
    constructors = get_constructors(class_body)
    
    # 构建内部类的完整名称
    class_name = class_decl_node.child_by_field_name('name').text.decode('utf-8')
    full_class_name = f"{parent_class_name}${class_name}" if parent_class_name else class_name
    
    class_obj = Class(
        package_name=pkg_name,
        name=class_name,
        modifier=get_modifier(class_decl_node),
        text=class_decl_node.text.decode('utf-8'),
        imports=imports,
        interface=interface,
        superclass=superclass,
        constructors=constructors
    )
    
    # 递归处理内部类的成员
    for child in class_body.children:
        if child.type == 'method_declaration':
            method_obj = method_decl_node_to_method_obj(child, pkg_name, full_class_name)
            class_obj.add_method(method_obj)
        elif child.type == 'field_declaration':
            field_obj = field_decl_node_to_field_obj(child)
            if field_obj: 
                class_obj.add_field(field_obj)
        elif child.type in ['class_declaration', 'interface_declaration', 'enum_declaration', 'annotation_type_declaration']:
            # 递归处理嵌套的内部类
            inner_class_obj = parse_class_declaration_node(child, pkg_name, imports, full_class_name)
            if inner_class_obj:
                inner_class_method = Method(
                    name=inner_class_obj.name,
                    modifier=inner_class_obj.modifier,
                    text=inner_class_obj.text,
                    return_type=inner_class_obj.name,
                    params=[],
                    class_sig=f"{pkg_name}.{full_class_name}",
                    docstring=inner_class_obj.docstring
                )
                class_obj.add_method(inner_class_method)
        elif child.type == 'local_variable_declaration':
            # 处理内部类中的匿名类
            anonymous_class_obj = parse_anonymous_class_from_variable(child, pkg_name, full_class_name)
            if anonymous_class_obj:
                class_obj.add_method(anonymous_class_obj)
        elif child.type in ['expression_statement', 'statement']:
            # 处理内部类语句中的匿名类
            anonymous_class_obj = parse_anonymous_class_from_statement(child, pkg_name, full_class_name)
            if anonymous_class_obj:
                class_obj.add_method(anonymous_class_obj)
        elif child.type == 'static_initializer':
            # 处理静态初始化块
            static_init_method = parse_static_initializer(child, pkg_name, full_class_name)
            if static_init_method:
                class_obj.add_method(static_init_method)
        elif child.type == 'instance_initializer':
            # 处理实例初始化块
            instance_init_method = parse_instance_initializer(child, pkg_name, full_class_name)
            if instance_init_method:
                class_obj.add_method(instance_init_method)
    
    return class_obj

# 解析语句中的匿名类
def parse_anonymous_class_from_statement(statement_node: Node, pkg_name: str, class_name: str) -> Optional[Method]:
    """从语句中解析匿名类"""
    if not statement_node:
        return None
    
    # 递归查找匿名类创建表达式
    def find_anonymous_class_creation(node: Node) -> Optional[Node]:
        if node.type == 'anonymous_class_creation':
            return node
        for child in node.children:
            result = find_anonymous_class_creation(child)
            if result:
                return result
        return None
    
    anonymous_node = find_anonymous_class_creation(statement_node)
    if anonymous_node:
        return parse_anonymous_class_creation(anonymous_node, pkg_name, class_name)
    
    return None

# 解析静态初始化块
def parse_static_initializer(static_init_node: Node, pkg_name: str, class_name: str) -> Optional[Method]:
    """解析静态初始化块"""
    if not static_init_node or static_init_node.type != 'static_initializer':
        return None
    
    init_text = static_init_node.text.decode('utf-8')
    
    return Method(
        name="static_initializer",
        modifier="static",
        text=init_text,
        return_type="void",
        params=[],
        class_sig=f"{pkg_name}.{class_name}",
        docstring="Static initialization block"
    )

# 解析实例初始化块
def parse_instance_initializer(instance_init_node: Node, pkg_name: str, class_name: str) -> Optional[Method]:
    """解析实例初始化块"""
    if not instance_init_node or instance_init_node.type != 'instance_initializer':
        return None
    
    init_text = instance_init_node.text.decode('utf-8')
    
    return Method(
        name="instance_initializer",
        modifier="",
        text=init_text,
        return_type="void",
        params=[],
        class_sig=f"{pkg_name}.{class_name}",
        docstring="Instance initialization block"
    )
