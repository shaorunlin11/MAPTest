"""
文件操作工具类
统一处理文件读写、目录操作等
"""

import os
import shutil
import json
import re
from typing import Dict, List, Optional, Any, Union
from pathlib import Path
from .config_manager import config
from .logger import LoggerMixin, log_function_call, log_performance


class FileUtils(LoggerMixin):
    """文件操作工具类"""

    @staticmethod
    def _long_path(path: Union[str, Path]) -> str:
        """Return a filesystem path usable for long Windows paths."""
        path = Path(path)
        raw_path = str(path)
        if os.name != "nt" or raw_path.startswith("\\\\?\\"):
            return raw_path

        absolute_path = str(path.resolve(strict=False))
        if absolute_path.startswith("\\\\?\\"):
            return absolute_path
        if absolute_path.startswith("\\\\"):
            return "\\\\?\\UNC\\" + absolute_path.lstrip("\\")
        return "\\\\?\\" + absolute_path

    @staticmethod
    @log_function_call()
    def ensure_directory(path: Union[str, Path]) -> Path:
        """
        确保目录存在，如果不存在则创建
        
        Args:
            path: 目录路径
            
        Returns:
            Path对象
        """
        path = Path(path)
        long_path = FileUtils._long_path(path)
        if not os.path.exists(long_path):
            os.makedirs(long_path, exist_ok=True)
            logger = FileUtils._get_logger()
            logger.info(f"创建目录: {path}")
        return path
    
    @staticmethod
    @log_function_call()
    def clean_directory(path: Union[str, Path]) -> None:
        """
        清空目录内容
        
        Args:
            path: 目录路径
        """
        path = Path(path)
        logger = FileUtils._get_logger()
        long_path = FileUtils._long_path(path)
        
        if os.path.exists(long_path):
            shutil.rmtree(long_path)
            logger.info(f"清空目录: {path}")
        
        os.makedirs(long_path, exist_ok=True)
        logger.info(f"重新创建目录: {path}")
    
    @staticmethod
    @log_function_call()
    def read_file(file_path: Union[str, Path], encoding: str = 'utf-8') -> str:
        """
        读取文件内容
        
        Args:
            file_path: 文件路径
            encoding: 文件编码
            
        Returns:
            文件内容
        """
        path = Path(file_path)
        logger = FileUtils._get_logger()
        long_path = FileUtils._long_path(path)
        
        if not os.path.exists(long_path):
            logger.warning(f"文件不存在: {path}")
            return ""
        
        try:
            with open(long_path, 'r', encoding=encoding) as f:
                content = f.read()
            logger.debug(f"读取文件: {path}")
            return content
        except Exception as e:
            logger.error(f"读取文件失败 {path}: {e}")
            return ""
    
    @staticmethod
    @log_function_call()
    def write_file(file_path: Union[str, Path], content: str, 
                   encoding: str = 'utf-8', backup: bool = False) -> bool:
        """
        写入文件内容
        
        Args:
            file_path: 文件路径
            content: 文件内容
            encoding: 文件编码
            backup: 是否备份原文件
            
        Returns:
            是否成功
        """
        path = Path(file_path)
        logger = FileUtils._get_logger()
        
        try:
            # 确保目录存在
            FileUtils.ensure_directory(path.parent)
            long_path = FileUtils._long_path(path)
            
            # 备份原文件
            if backup and os.path.exists(long_path):
                backup_path = path.with_suffix(f"{path.suffix}.bak")
                shutil.copy2(long_path, FileUtils._long_path(backup_path))
                logger.debug(f"备份文件: {path} -> {backup_path}")
            
            with open(long_path, 'w', encoding=encoding) as f:
                f.write(content)
            
            logger.debug(f"写入文件: {path}")
            return True
            
        except Exception as e:
            logger.error(f"写入文件失败 {path}: {e}")
            return False
    
    @staticmethod
    @log_function_call()
    def read_json(file_path: Union[str, Path]) -> Union[Dict[str, Any], List[Any]]:
        """
        读取JSON文件
        
        Args:
            file_path: 文件路径
            
        Returns:
            JSON数据
        """
        path = Path(file_path)
        logger = FileUtils._get_logger()
        long_path = FileUtils._long_path(path)
        
        if not os.path.exists(long_path):
            logger.warning(f"JSON文件不存在: {path}")
            return {}
        
        try:
            with open(long_path, 'r', encoding='utf-8') as f:
                data = json.load(f)
            logger.debug(f"读取JSON文件: {path}")
            return data
        except Exception as e:
            logger.error(f"读取JSON文件失败 {path}: {e}")
            return {}
    
    @staticmethod
    @log_function_call()
    def write_json(file_path: Union[str, Path], data: Union[Dict[str, Any], List[Any]],
                   indent: int = 4, backup: bool = False) -> bool:
        """
        写入JSON文件
        
        Args:
            file_path: 文件路径
            data: JSON数据
            indent: 缩进空格数
            backup: 是否备份原文件
            
        Returns:
            是否成功
        """
        path = Path(file_path)
        logger = FileUtils._get_logger()
        
        try:
            # 确保目录存在
            FileUtils.ensure_directory(path.parent)
            long_path = FileUtils._long_path(path)
            
            # 备份原文件
            if backup and os.path.exists(long_path):
                backup_path = path.with_suffix(f"{path.suffix}.bak")
                shutil.copy2(long_path, FileUtils._long_path(backup_path))
                logger.debug(f"备份JSON文件: {path} -> {backup_path}")
            
            with open(long_path, 'w', encoding='utf-8') as f:
                json.dump(data, f, indent=indent, ensure_ascii=False)
            
            logger.debug(f"写入JSON文件: {path}")
            return True
            
        except Exception as e:
            logger.error(f"写入JSON文件失败 {path}: {e}")
            return False
    
    @staticmethod
    @log_function_call()
    def append_json(file_path: Union[str, Path], data: Union[Dict[str, Any], List[Any]]) -> bool:
        """
        向JSON文件追加数据
        
        Args:
            file_path: 文件路径
            data: 要追加的数据
            
        Returns:
            是否成功
        """
        path = Path(file_path)
        logger = FileUtils._get_logger()
        
        try:
            # 读取现有数据
            existing_data = []
            if path.exists():
                existing_data = FileUtils.read_json(path)
                if not isinstance(existing_data, list):
                    existing_data = [existing_data]
            
            # 追加新数据
            if isinstance(data, list):
                existing_data.extend(data)
            else:
                existing_data.append(data)
            
            # 写回文件
            return FileUtils.write_json(path, existing_data)
            
        except Exception as e:
            logger.error(f"追加JSON数据失败 {path}: {e}")
            return False
    
    @staticmethod
    @log_function_call()
    def copy_file(src: Union[str, Path], dst: Union[str, Path]) -> bool:
        """
        复制文件
        
        Args:
            src: 源文件路径
            dst: 目标文件路径
            
        Returns:
            是否成功
        """
        src_path = Path(src)
        dst_path = Path(dst)
        logger = FileUtils._get_logger()
        
        try:
            # 确保目标目录存在
            FileUtils.ensure_directory(dst_path.parent)
            
            shutil.copy2(FileUtils._long_path(src_path), FileUtils._long_path(dst_path))
            logger.debug(f"复制文件: {src_path} -> {dst_path}")
            return True
            
        except Exception as e:
            logger.error(f"复制文件失败 {src_path} -> {dst_path}: {e}")
            return False
    
    @staticmethod
    @log_function_call()
    def copy_directory(src: Union[str, Path], dst: Union[str, Path], 
                      ignore_patterns: Optional[List[str]] = None) -> bool:
        """
        复制目录
        
        Args:
            src: 源目录路径
            dst: 目标目录路径
            ignore_patterns: 忽略的文件模式列表
            
        Returns:
            是否成功
        """
        src_path = Path(src)
        dst_path = Path(dst)
        logger = FileUtils._get_logger()
        
        try:
            if ignore_patterns:
                ignore_func = shutil.ignore_patterns(*ignore_patterns)
                shutil.copytree(
                    FileUtils._long_path(src_path),
                    FileUtils._long_path(dst_path),
                    ignore=ignore_func,
                    dirs_exist_ok=True,
                )
            else:
                shutil.copytree(
                    FileUtils._long_path(src_path),
                    FileUtils._long_path(dst_path),
                    dirs_exist_ok=True,
                )
            
            logger.debug(f"复制目录: {src_path} -> {dst_path}")
            return True
            
        except Exception as e:
            logger.error(f"复制目录失败 {src_path} -> {dst_path}: {e}")
            return False
    
    @staticmethod
    @log_function_call()
    def find_files(directory: Union[str, Path], pattern: str = "*",
                   recursive: bool = True) -> List[Path]:
        """
        查找文件
        
        Args:
            directory: 搜索目录
            pattern: 文件模式
            recursive: 是否递归搜索
            
        Returns:
            文件路径列表
        """
        dir_path = Path(directory)
        logger = FileUtils._get_logger()
        
        if not dir_path.exists():
            logger.warning(f"目录不存在: {dir_path}")
            return []
        
        try:
            if recursive:
                files = list(dir_path.rglob(pattern))
            else:
                files = list(dir_path.glob(pattern))
            
            # 只返回文件，不包括目录
            files = [f for f in files if f.is_file()]
            logger.debug(f"在{dir_path}中找到{len(files)}个匹配文件: {pattern}")
            return files
            
        except Exception as e:
            logger.error(f"查找文件失败 {dir_path}: {e}")
            return []
    
    @staticmethod
    def extract_code_from_response(response: str) -> str:
        """
        从LLM响应中提取代码块
        
        Args:
            response: LLM响应内容
            
        Returns:
            提取的代码
        """
        if not response:
            return ""
        
        # 清理常见的前缀和后缀
        response = response.replace("java\r\n", "").replace("...", "").replace("java\n", "")
        
        # 使用正则表达式匹配反引号内的代码块
        pattern = r"```(?:java)?(.*?)```"
        matches = re.findall(pattern, response, re.DOTALL)
        
        if matches:
            return matches[-1].strip()
        
        return response.strip()

    @staticmethod
    def _extract_package(test_code: str) -> str:
        """
        从测试文件中提取package语句

        Args:
            test_code: Java代码

        Returns:
            package语句
        """
        package_pattern = r'(package\s+[\w.]+\s*;)'
        match = re.search(package_pattern, test_code)
        return match.group(1) if match else ""
    
    @staticmethod
    def extract_imports_from_code(code: str) -> List[str]:
        """
        从代码中提取import语句
        
        Args:
            code: Java代码
            
        Returns:
            import语句列表
        """
        import_lines = []
        for line in code.split('\n'):
            line = line.strip()
            if line.startswith('import '):
                import_lines.append(line)
        return import_lines
    
    @staticmethod
    def extract_test_methods(code: str) -> List[str]:
        """
        从代码中提取测试方法
        
        Args:
            code: Java代码
            
        Returns:
            测试方法列表
        """
        test_methods = []
        lines = code.split('\n')
        
        in_test_method = False
        method_lines = []
        annotation_lines = []
        brace_count = 0
        
        for line in lines:
            stripped = line.strip()

            # 检测@Test注解
            if stripped.startswith('@Test') or '@Test' in stripped:
                annotation_lines.append(line)
                continue
            
            # 检测void方法
            if 'void test' in stripped and 'public' in stripped:
                in_test_method = True
                method_lines = annotation_lines + [line]
                brace_count = line.count('{') - line.count('}')
                continue

            # 清空注解缓存
            annotation_lines = []
            
            if in_test_method:
                method_lines.append(line)
                brace_count += line.count('{') - line.count('}')
                
                # 当大括号平衡时，方法结束
                if brace_count == 0:
                    test_methods.append('\n'.join(method_lines))
                    in_test_method = False
                    method_lines = []
        
        return test_methods
    
    @staticmethod
    def add_indent(code: str, indent: str = "\t") -> str:
        """
        为代码添加缩进
        
        Args:
            code: 代码内容
            indent: 缩进字符
            
        Returns:
            添加缩进后的代码
        """
        return '\n'.join([indent + line for line in code.split('\n')])
    
    @staticmethod
    def remove_comments(code: str) -> str:
        """
        移除代码注释
        
        Args:
            code: 代码内容
            
        Returns:
            移除注释后的代码
        """
        # 移除多行注释
        code = re.sub(r'/\*.*?\*/', '', code, flags=re.DOTALL)
        
        # 移除单行注释
        code = re.sub(r'//.*', '', code)
        
        return code
    
    @staticmethod
    def _get_logger():
        """获取日志器"""
        from .logger import get_logger
        return get_logger("FileUtils")

    @staticmethod
    def extract_testmethod_from_testclass(test_class_code: str, target_line: int) -> tuple[str, str]:
        """
        根据行号，从测试类中提取该行所在的测试方法，分别返回除去了该方法的测试类和该测试方法
        
        Args:
            test_class_code: 完整的测试类代码
            target_line: 目标行号（1-based）
            
        Returns:
            tuple: (修改后的测试类代码, 被移除的测试方法代码)
        """
        if not test_class_code or target_line < 1:
            return test_class_code, ""
            
        lines = test_class_code.split('\n')
        if target_line > len(lines):
            return test_class_code, ""
        
        # 找到目标行所在的测试方法
        test_method_info = FileUtils._find_test_method_by_line(lines, target_line)
        if not test_method_info:
            return test_class_code, ""
        
        start_line, end_line = test_method_info
        
        # 提取测试方法代码
        test_method_code = '\n'.join(lines[start_line:end_line + 1])
        
        # 构建新的测试类代码（移除该测试方法）
        new_test_class_lines = lines[:start_line] + lines[end_line + 1:]
        new_test_class_code = '\n'.join(new_test_class_lines)
        
        # 清理多余的空行
        new_test_class_code = FileUtils._clean_extra_empty_lines(new_test_class_code)
        
        return new_test_class_code, test_method_code
    
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
            
            # 检测void测试方法开始
            if 'void test' in stripped and 'public' in stripped:
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
