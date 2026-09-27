"""
统一日志框架
提供结构化日志记录，支持文件和控制台输出
"""

import logging
import logging.handlers
import os
import sys
from typing import Optional
from pathlib import Path
from .config_manager import config


class MAPTestLogger:
    """MAPTest统一日志管理器"""
    
    _instance = None
    _loggers = {}
    
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MAPTestLogger, cls).__new__(cls)
        return cls._instance
    
    def __init__(self):
        if not hasattr(self, '_initialized'):
            self._setup_logging()
            self._initialized = True
    
    def _setup_logging(self):
        """设置日志配置"""
        # 获取日志配置
        log_level = config.get('logging.level', 'INFO')
        log_format = config.get('logging.format', '%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        log_file = config.get('logging.file', 'logs/MAPTest.log')
        max_size = config.get('logging.max_size', '10MB')
        backup_count = config.get('logging.backup_count', 5)
        
        # 创建日志目录
        log_dir = os.path.dirname(log_file)
        if log_dir and not os.path.exists(log_dir):
            os.makedirs(log_dir, exist_ok=True)
        
        # 解析文件大小
        max_bytes = self._parse_size(max_size)
        
        # 配置根日志器
        root_logger = logging.getLogger()
        root_logger.setLevel(getattr(logging, log_level.upper()))
        
        # 清除现有处理器
        root_logger.handlers.clear()
        
        # 创建格式化器
        formatter = logging.Formatter(log_format)
        
        # 添加控制台处理器
        console_handler = logging.StreamHandler(sys.stdout)
        console_handler.setFormatter(formatter)
        root_logger.addHandler(console_handler)
        
        # 添加文件处理器（带轮转）
        if log_file:
            file_handler = logging.handlers.RotatingFileHandler(
                log_file,
                maxBytes=max_bytes,
                backupCount=backup_count,
                encoding='utf-8'
            )
            file_handler.setFormatter(formatter)
            root_logger.addHandler(file_handler)
    
    def _parse_size(self, size_str: str) -> int:
        """解析大小字符串为字节数"""
        size_str = size_str.upper()
        if size_str.endswith('KB'):
            return int(size_str[:-2]) * 1024
        elif size_str.endswith('MB'):
            return int(size_str[:-2]) * 1024 * 1024
        elif size_str.endswith('GB'):
            return int(size_str[:-2]) * 1024 * 1024 * 1024
        else:
            return int(size_str)
    
    def get_logger(self, name: str) -> logging.Logger:
        """
        获取指定名称的日志器
        
        Args:
            name: 日志器名称
            
        Returns:
            日志器实例
        """
        if name not in self._loggers:
            logger = logging.getLogger(name)
            self._loggers[name] = logger
        return self._loggers[name]
    
    def set_level(self, level: str):
        """设置日志级别"""
        log_level = getattr(logging, level.upper(), logging.INFO)
        logging.getLogger().setLevel(log_level)
    
    def add_file_handler(self, file_path: str, level: str = 'INFO'):
        """添加额外的文件处理器"""
        logger = logging.getLogger()
        handler = logging.FileHandler(file_path, encoding='utf-8')
        handler.setLevel(getattr(logging, level.upper()))
        formatter = logging.Formatter(config.get('logging.format'))
        handler.setFormatter(formatter)
        logger.addHandler(handler)


class LoggerMixin:
    """日志混入类，为其他类提供日志功能"""
    
    @property
    def logger(self) -> logging.Logger:
        """获取当前类的日志器"""
        if not hasattr(self, '_logger'):
            class_name = self.__class__.__name__
            module_name = self.__class__.__module__
            logger_name = f"{module_name}.{class_name}"
            self._logger = logger_manager.get_logger(logger_name)
        return self._logger


# 全局日志管理器实例
logger_manager = MAPTestLogger()

# 便捷函数
def get_logger(name: str) -> logging.Logger:
    """获取日志器的便捷函数"""
    return logger_manager.get_logger(name)


def setup_logging(level: Optional[str] = None):
    """设置日志的便捷函数"""
    if level:
        logger_manager.set_level(level)


# 日志装饰器
def log_function_call(logger: Optional[logging.Logger] = None):
    """记录函数调用的装饰器"""
    def decorator(func):
        def wrapper(*args, **kwargs):
            func_logger = logger or get_logger(func.__module__)
            func_logger.debug(f"调用函数 {func.__name__} with args={args}, kwargs={kwargs}")
            try:
                result = func(*args, **kwargs)
                func_logger.debug(f"函数 {func.__name__} 执行成功")
                return result
            except Exception as e:
                func_logger.error(f"函数 {func.__name__} 执行失败: {e}", exc_info=True)
                raise
        return wrapper
    return decorator


def log_performance(logger: Optional[logging.Logger] = None):
    """记录函数执行时间的装饰器"""
    import time
    
    def decorator(func):
        def wrapper(*args, **kwargs):
            func_logger = logger or get_logger(func.__module__)
            start_time = time.time()
            try:
                result = func(*args, **kwargs)
                execution_time = time.time() - start_time
                func_logger.info(f"函数 {func.__name__} 执行时间: {execution_time:.2f}秒")
                return result
            except Exception as e:
                execution_time = time.time() - start_time
                func_logger.error(f"函数 {func.__name__} 执行失败，耗时: {execution_time:.2f}秒, 错误: {e}", exc_info=True)
                raise
        return wrapper
    return decorator
