import os.path
import re
from loguru import logger
from tqdm import *

# 使用绝对导入
from tree_sitter_java_parser.entities.CodeEntities import Class
from tree_sitter_java_parser.utils.FileUtils import traverse_files, read_file_with_UTF8
from tree_sitter_java_parser.utils.JavaAnalyzer import parse_class_object_from_file_content
from tree_sitter_java_parser.utils.JsonGenerator import BasicGenerator

# debug = True
debug = False

# 对给定的代码库执行静态分析以提取类信息
def static_analyze(code_base: str, source_code_path: str) -> list[Class]:
    """
    Performs static analysis on the given code base to extract class information.

    Args:
        code_base (str): The base directory of the code.
        source_code_path (str): The path to the source code within the code base.

    Returns:
        list[Class]: A list of Class objects representing the classes found in the source code.
    """
    classes = []
    source_code_path = os.path.join(code_base, source_code_path)
    files = traverse_files(source_code_path, '.java')   # 获取路径下所有 java 文件
    logger.info(f'Found {len(files)} files in the path {source_code_path}')
    for file in tqdm(files, desc='Traversing files'):
        file_name = os.path.basename(file)

        # Java 类文件中必须包含一个同名的 public class。这里提取文件名方便后面处理多类文件。
        target_class_name, extension = os.path.splitext(file_name)
        try:
            assert extension == '.java'     # 确保提取 java 文件内容
            file_content = comment_delete(read_file_with_UTF8(file))  # 提取没有注释的 java 文件内容
            class_instance = parse_class_object_from_file_content(file_content=file_content,
                                                                  target_class_name=target_class_name)
            if class_instance:
                classes.append(class_instance)
        except AssertionError as ae:
            logger.warning(f"Found unexpected file: {file}. Skipped.")
            continue
        pass
    return classes

# 输入类列表，提取焦点方法列表
def load_focal_method(classes: list, project_path, output_dir=None) -> list:
    generator = BasicGenerator(output_dir=output_dir)
    if debug:
        classes = classes[:1]   # debug只测试一个类
    for class_instance in tqdm(classes, desc='Generating test classes'):
        target_methods = class_instance.testable_methods
        if debug:
            target_methods = target_methods[:2]     # debug只测试类中的前两个方法
        logger.info(f'Class {class_instance.name} has {len(target_methods)} to be tested.')
        # 遍历方法内容，提取焦点方法信息
        for method_instance in tqdm(target_methods, desc='\tGenerating'):
            generator.generate_json(
                project_path=project_path,
                method_instance=method_instance,
                class_instance=class_instance,
            )


# 删除代码注释
def comment_delete(code):
    regex = r"/\*(.|\\n)*?\*/"
    noMultilineComments = re.sub(regex, "", code)   # re.sub函数将匹配到的多行注释替换为空字符串

    # 去除单行注释，remove single line comments (// ...)：
    regex = r"//.*"
    non_comment_code = re.sub(regex, "", noMultilineComments)
    # 去除多行注释
    pattern = re.compile(r"(?s)/\*.*?\*/|//.*?[\r\n]")  # 用正则表达式匹配 /**...*/ 样式的注释
    code_without_comment = pattern.sub("", non_comment_code)

    return code_without_comment
