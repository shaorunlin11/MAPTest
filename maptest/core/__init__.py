"""
MAPTest核心模块
提供统一的配置管理、日志记录、LLM客户端、Maven执行器和文件操作工具
"""

from .config_manager import config, ConfigManager
from .logger import (
    get_logger, 
    setup_logging, 
    LoggerMixin,
    log_function_call,
    log_performance
)
from .llm_client import LLMClient
from .maven_executor import MavenExecutor, MavenResult
from .file_utils import FileUtils

__all__ = [
    # 配置管理
    'config',
    'ConfigManager',
    
    # 日志
    'get_logger',
    'setup_logging',
    'LoggerMixin',
    'log_function_call',
    'log_performance',
    
    # LLM客户端
    'LLMClient',
    
    # Maven执行器
    'MavenExecutor',
    'MavenResult',
    
    # 文件工具
    'FileUtils',
]

# 版本信息
__version__ = "2.0.0"
