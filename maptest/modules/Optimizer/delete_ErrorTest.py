import json
import logging
import os
import re
from datetime import datetime
from pathlib import Path
from typing import Optional, List, Dict, Tuple

# 添加项目根目录到Python路径
import sys
project_root = Path(__file__).resolve().parent.parent.parent.parent
sys.path.insert(0, str(project_root))

from maptest.core import setup_logging, LoggerMixin, FileUtils

class ErrorLineDeleter(LoggerMixin):
    """
    根据错误行，提取并删除错误测试
    """
    @staticmethod
    def extract_testmethod_from_testclass(test_class_code: str, error_lines: List[int],
                                          test_method_names: Optional[List[str]] = None) -> tuple[str, Dict[int, str]]:
        """
        根据错误行列表，从测试类中删除所有错误测试方法

        Args:
            test_class_code: 完整的测试类代码
            error_lines: 错误行号列表（1-based）
            test_method_names: 从错误日志中提取的测试方法名

        Returns:
            tuple: (修改后的测试类代码, {错误行号: 被移除的测试方法代码})
        """
        # 输入验证
        if not test_class_code or (not error_lines and not test_method_names):
            return test_class_code, {}

        lines = test_class_code.split('\n')
        valid_error_lines = [line for line in error_lines if 1 <= line <= len(lines)]

        # 映射错误行到测试方法
        error_line_to_method = ErrorLineDeleter._map_error_lines_to_methods(lines, valid_error_lines)

        # 第一阶段未命中时，回退到按测试方法名匹配
        if not error_line_to_method and test_method_names:
            error_line_to_method = ErrorLineDeleter._map_method_names_to_methods(lines, test_method_names)

        if not error_line_to_method:
            return test_class_code, {}

        # 收集需要删除的测试方法（去重）
        methods_to_delete = {}  # {(start_line, end_line): [error_line1, error_line2, ...]}
        for error_line, (start_line, end_line, _) in error_line_to_method.items():
            method_key = (start_line, end_line)
            if method_key not in methods_to_delete:
                methods_to_delete[method_key] = []
            methods_to_delete[method_key].append(error_line)

        # 按起始行降序排序，从后往前删除（避免行号错位）
        sorted_methods = sorted(methods_to_delete.keys(), key=lambda x: x[0], reverse=True)

        # 构建错误测试方法集合
        error_methods_dict = {}

        # 依次删除测试方法
        current_lines = lines
        for start_line, end_line in sorted_methods:
            # 提取测试方法代码
            test_method_code = '\n'.join(current_lines[start_line:end_line + 1])
            
            # 为所有指向该方法的错误行添加方法代码
            for error_line in methods_to_delete[(start_line, end_line)]:
                error_methods_dict[error_line] = test_method_code
            
            # 删除该测试方法
            current_lines = current_lines[:start_line] + current_lines[end_line + 1:]

        # 构建新的测试类代码
        new_test_class_code = '\n'.join(current_lines)

        # 清理多余的空行
        new_test_class_code = ErrorLineDeleter._clean_extra_empty_lines(new_test_class_code)

        if not ErrorLineDeleter._looks_like_test_class(new_test_class_code):
            return test_class_code, {}

        return new_test_class_code, error_methods_dict

    @staticmethod
    def _find_test_method_by_line(lines, target_line: int) -> Optional[tuple[int, int]]:
        """
        找到目标行所在的测试方法的起始和结束行号

        Args:
            lines: 代码行列表
            target_line: 目标行号（1-based）

        Returns:
            tuple: (方法起始行号, 方法结束行号) 或 None
        """
        # 转换为0-based索引
        target_index = target_line - 1

        # 首先找到所有测试方法的范围
        test_methods = []
        current_method_start = -1
        current_method_annotations = []
        brace_count = 0
        in_method = False

        for i, line in enumerate(lines):
            stripped = line.strip()

            # 检测@Test注解
            if stripped.startswith('@Test') or '@Test' in stripped:
                if not in_method:
                    current_method_annotations.append(i)
                continue

            # 检测标准 JUnit 测试方法开始
            if ErrorLineDeleter._is_test_method_signature(stripped, current_method_annotations):
                in_method = True
                current_method_start = min(current_method_annotations) if current_method_annotations else i
                brace_count = line.count('{') - line.count('}')

                # 如果这一行就平衡了，说明是单行方法
                if brace_count == 0:
                    test_methods.append((current_method_start, i))
                    in_method = False
                    current_method_start = -1
                    current_method_annotations = []
                    brace_count = 0
                continue

            # 如果在方法中，计算大括号
            if in_method:
                brace_count += line.count('{') - line.count('}')

                if brace_count == 0:
                    test_methods.append((current_method_start, i))
                    in_method = False
                    current_method_start = -1
                    current_method_annotations = []
                    brace_count = 0

            # 重置注解缓存（如果不在方法中）
            if not in_method:
                current_method_annotations = []

        # 检查目标行在哪个测试方法中
        for start_line, end_line in test_methods:
            if start_line <= target_index <= end_line:
                return start_line, end_line

        return None

    @staticmethod
    def _find_all_test_methods(lines: List[str]) -> List[Tuple[int, int, str]]:
        """
        找到所有测试方法的范围和名称

        Args:
            lines: 代码行列表

        Returns:
            List[Tuple]: [(起始行, 结束行, 方法名), ...]
        """
        test_methods = []
        current_method_start = -1
        current_method_annotations = []
        current_method_name = ""
        brace_count = 0
        in_method = False

        for i, line in enumerate(lines):
            stripped = line.strip()

            # 检测@Test注解
            if stripped.startswith('@Test') or '@Test' in stripped:
                if not in_method:
                    current_method_annotations.append(i)
                continue

            # 检测标准 JUnit 测试方法开始
            if ErrorLineDeleter._is_test_method_signature(stripped, current_method_annotations):
                in_method = True
                current_method_start = min(current_method_annotations) if current_method_annotations else i
                # 提取方法名
                method_match = re.search(r'void\s+(\w+)\s*\(', stripped)
                current_method_name = method_match.group(1) if method_match else ""
                brace_count = line.count('{') - line.count('}')

                # 如果这一行就平衡了，说明是单行方法
                if brace_count == 0:
                    test_methods.append((current_method_start, i, current_method_name))
                    in_method = False
                    current_method_start = -1
                    current_method_name = ""
                    current_method_annotations = []
                    brace_count = 0
                continue

            # 如果在方法中，计算大括号
            if in_method:
                brace_count += line.count('{') - line.count('}')

                if brace_count == 0:
                    test_methods.append((current_method_start, i, current_method_name))
                    in_method = False
                    current_method_start = -1
                    current_method_name = ""
                    current_method_annotations = []
                    brace_count = 0

            # 重置注解缓存（如果不在方法中）
            if not in_method:
                current_method_annotations = []

        return test_methods

    @staticmethod
    def _map_error_lines_to_methods(lines: List[str], error_lines: List[int]) -> Dict[int, Tuple[int, int, str]]:
        """
        将错误行映射到对应的测试方法

        Args:
            lines: 代码行列表
            error_lines: 错误行号列表（1-based）

        Returns:
            Dict: {错误行号: (起始行, 结束行, 方法名)}
        """
        # 获取所有测试方法
        all_test_methods = ErrorLineDeleter._find_all_test_methods(lines)
        
        # 映射错误行到测试方法
        error_line_to_method = {}
        
        for error_line in error_lines:
            # 转换为0-based索引
            error_index = error_line - 1
            
            # 检查错误行是否在有效范围内
            if error_index < 0 or error_index >= len(lines):
                continue
            
            # 查找包含该错误行的测试方法
            for start_line, end_line, method_name in all_test_methods:
                if start_line <= error_index <= end_line:
                    error_line_to_method[error_line] = (start_line, end_line, method_name)
                    break
        
        return error_line_to_method

    @staticmethod
    def _map_method_names_to_methods(lines: List[str], test_method_names: List[str]) -> Dict[int, Tuple[int, int, str]]:
        """
        当错误行无法映射时，根据测试方法名回退匹配。

        Returns:
            Dict: {方法起始行号(1-based): (起始行, 结束行, 方法名)}
        """
        all_test_methods = ErrorLineDeleter._find_all_test_methods(lines)
        target_names = {
            name.strip() for name in (test_method_names or [])
            if isinstance(name, str) and name.strip()
        }
        if not target_names:
            return {}

        matched_methods = {}
        for start_line, end_line, method_name in all_test_methods:
            if method_name in target_names:
                matched_methods[start_line + 1] = (start_line, end_line, method_name)

        return matched_methods

    @staticmethod
    def _is_test_method_signature(stripped_line: str, annotations: List[int]) -> bool:
        if not annotations:
            return False
        return bool(re.search(r'\bpublic\s+void\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(', stripped_line))

    @staticmethod
    def _looks_like_test_class(code: str) -> bool:
        stripped = code.strip()
        if not stripped:
            return False
        if "class " not in stripped:
            return False
        if stripped.count("{") != stripped.count("}"):
            return False
        return True

    @staticmethod
    def _clean_extra_empty_lines(code: str) -> str:
        """
        清理多余的空行

        Args:
            code: 代码内容

        Returns:
            清理后的代码
        """
        lines = code.split('\n')
        cleaned_lines = []

        for i, line in enumerate(lines):
            stripped = line.strip()
            # 保留非空行
            if stripped:
                cleaned_lines.append(line)
            # 保留合理的空行（不在类定义前后）
            elif 0 < i < len(lines) - 1:
                prev_stripped = lines[i-1].strip()
                next_stripped = lines[i+1].strip() if i+1 < len(lines) else ""
                
                # 不在类定义、方法定义、import等关键位置保留空行
                if (not prev_stripped.endswith('{') and
                        not next_stripped.startswith('}') and
                        not prev_stripped.startswith('import') and
                        not prev_stripped.startswith('package')):
                    cleaned_lines.append(line)

        return '\n'.join(cleaned_lines).strip()

    @staticmethod
    def find_error_line(error_info: str) -> Optional[int]:
        """
        根据错误信息中的行号，从代码行中找到对应的行号

        Args:
            error_info: 错误信息

        Returns:
            对应的行号（1-based）或None
        """
        error_line_match = re.search(r'<line>(\d+)</line>', error_info)
        if error_line_match:
            error_line = int(error_line_match.group(1))
            return error_line
        return 0
