import sys
import os
from pathlib import Path

# 添加上级目录到Python路径
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from tree_sitter_java_parser.entities.CodeEntities import Class, Method
import json
import os.path

"""
收集焦点类和焦点方法信息，并结构化输出至 json 文件中
"""

json_out_path = str(Path(__file__).resolve().parents[2] / 'RepoData')


class BasicGenerator:
    def __init__(self, output_dir=None):
        self.output_dir = Path(output_dir or json_out_path)
        self.output_dir.mkdir(parents=True, exist_ok=True)

    # 预定义 Java 测试框架的导入语句：
    predefined_imports = ["import org.junit.Test;",
           "import org.junit.Assert;",
           "import org.junit.Before;",
           "import org.junit.After; ",
           "import static org.junit.Assert.*;",
           "import org.junit.Ignore;",
           "import org.junit.BeforeClass;",
           "import org.junit.AfterClass;",
           "import org.junit.runner.RunWith;",
           "import org.junit.runners.JUnit4;",
           "import org.junit.Rule;",
           "import org.junit.rules.ExpectedException;",
           "import static org.mockito.Mockito.*;",
           "import static org.hamcrest.MatcherAssert.assertThat;",
           "import static org.hamcrest.Matchers.*;",]


    # 构建 json，保存焦点方法信息
    def generate_json(self, project_path, **data):
        # 构建 prompt：
        focal_method_instance = data['method_instance']
        focal_class_instance = data['class_instance']
        prompt_exmple = self.base_instruction(focal_method_instance=focal_method_instance,
                              focal_class_instance=focal_class_instance,
                              project_path=project_path)


    '''
    提取方法和类的相关信息, 包括方法名、签名、参数，类名、类的字符串表示、导入内容、字段、其他方法等。
    然后构建提示，用于指导 LLM 生成测试
    '''
    def base_instruction(self, focal_method_instance: Method, focal_class_instance: Class, project_path):
        project_name = os.path.basename(project_path)
        package_path = focal_class_instance.package_name.replace('.', '/')
        root = Path(__file__).resolve().parents[2]
        project_dir = Path(project_path).resolve()
        try:
            portable_project = project_dir.relative_to(root).as_posix()
        except ValueError:
            portable_project = project_dir.as_posix()
        focal_method = str(focal_method_instance)
        focal_method_name = focal_method_instance.name
        focal_method_signature = focal_method_instance.signature
        focal_method_visibility = getattr(focal_method_instance, 'access_level', 'public')
        focal_method_parameters = [p.signature for p in focal_method_instance.parameters]
        # 提取方法声明：
        if '@' and '\n' in focal_method.split(' {')[0]:
            focal_method_statement = focal_method.split(' {')[0].split('\n')[1].strip()
        else: focal_method_statement = focal_method.split(' {')[0].strip()

        # print("################## 焦点方法信息 ##################")
        # for k, v in focal_method_instance.__dict__.items():
        #     print(f"\t{k}:")
        #     print('\t\t' + str(v).replace('\\n', '\n') + '\n')

        focal_class_name = focal_class_instance.name
        focal_class = str(focal_class_instance)
        focal_class_imports = focal_class_instance.imports
        focal_class_fields = list(focal_class_instance.fields.values())
        focal_class_other_methods = [m for m in focal_class_instance.public_methods if
                                     m.signature != focal_method_signature]
        focal_class_other_methods_str = '\n'.join([m.short_definition for m in focal_class_other_methods])  # 提取类中其他方法的签名
        class_declaration = focal_class_instance.text.split('{')[0] + '{'   # 提取类声明
        fields_str = '\n'.join([f.text for f in focal_class_fields])    # 类字段

        # print("################## 类信息 ##################")
        # for k, v in focal_class_instance.__dict__.items():
        #     print(f"\t{k}:")
        #     print('\t\t' + str(v).replace('\\n', '\n') + '\n')


    # 构建 Prompt：
        prompt = "Your Task is to write some unit tests, I will give you some contextual information, and the method you are going to test is at the end of the instruction.\n"
        prompt += '\n```\n'  # Comment style
        prompt += f'// The method is defined in the {focal_class} class, Here are the defined fields and methods in the class:\n'
        prompt += f'public class {focal_class_name} {focal_class_instance.superclass} {focal_class_instance.interface} ' + '{\n'
        prompt += f'{fields_str}\n'
        prompt += f'{focal_class_other_methods_str}\n}}\n\n'
        prompt += '// Below is the details of the method you are going to test:\n'
        prompt += f'{focal_method}\n'
        prompt += '\n```\n'  # Comment style
        if focal_method_visibility != 'public':
            prompt += (
                f"The focal method has {focal_method_visibility} visibility. "
                "Generate the test in the same package so it can call the method directly when appropriate, "
                "or cover it through a public inherited/template API when that is the natural entry point.\n"
            )
        prompt += "Please write some unit tests in Java 1.7 and JUnit 4 with maximizing both branch and line coverage.\n"
        # print("################## Prompt ##################\n" + prompt)

        # 将信息保存到 JSON 格式
        test_info_json_path = self.output_dir / f'{project_name}.json'

        test_info = {
            "Under_test_method": {
                "Method_body": focal_method + "\n",
                "sub_project_name": project_name,
                "Filed": fields_str + "\n",
                "Method_statement": focal_method_statement,
                "Method_name": focal_method_name,
                "Method_visibility": focal_method_visibility,
                "is_non_public_testable": focal_method_visibility != 'public',
                "Class_declaration": class_declaration + "\n",
                "constructors": "\n".join(focal_class_instance.constructors) + "\n",
                "all_method_signature": focal_class_other_methods_str + "\n",
                "Class_name": focal_class_name,
                "project_path": f"{portable_project}/src/main/java/{package_path}/{focal_class_name}.java###{focal_method_name}",
                "contextMethod": ";".join([m.text for m in focal_class_other_methods]) + "\n",
                "all_Import_statements": "\n".join(focal_class_imports) + "\n",
                "packageName": focal_class_instance.package_name,
                "Junit_version": "4"
            },
            "Test_method": {
                "TestInfo": f"{portable_project}/src/test/java/{package_path}/{focal_class_name}Test.java###{focal_method_name}Test",
                "Test_import": '\n'.join(self.predefined_imports) + "\n"
            }
        }

        # 读取文件内容
        try:
            with open(test_info_json_path, "r", encoding="utf-8") as json_file:
                existing_data = json.load(json_file)
        except (FileNotFoundError, json.JSONDecodeError):
            existing_data = []
        # 合并新的测试信息到已存在的内容中
        existing_data.append(test_info)

        # 将JSON数据写入文件
        with open(test_info_json_path, "w", encoding="utf-8") as json_file:
            json.dump(existing_data, json_file, indent=4)


        return prompt
