"""
统一配置管理器
负责加载和管理所有配置项，支持环境变量覆盖和配置验证
"""

import os
import shutil
import yaml
import logging
from typing import Dict, Any, Optional
from pathlib import Path


class ConfigManager:
    """配置管理器，负责加载和管理所有配置项"""
    
    _instance = None
    _config = None
    
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(ConfigManager, cls).__new__(cls)
        return cls._instance
    
    def __init__(self):
        if self._config is None:
            self._load_config()
    
    def _load_config(self):
        """加载配置文件"""
        try:
            # 获取配置文件路径
            config_path = self._get_config_path()
            
            # 加载YAML配置
            with open(config_path, 'r', encoding='utf-8') as f:
                self._config = yaml.safe_load(f)
            
            # 应用环境变量覆盖
            self._apply_env_overrides()
            
            # 验证配置
            self._validate_config()
            
            # 设置环境变量
            self._setup_environment()
            
        except Exception as e:
            raise RuntimeError(f"配置加载失败: {e}")
    
    def _get_config_path(self) -> Path:
        """获取配置文件路径"""
        # 首先检查环境变量
        env_config = os.getenv('MAPTEST_CONFIG') or os.getenv('AGENT_TESTER_CONFIG')
        if env_config:
            if not Path(env_config).is_file():
                raise FileNotFoundError(f"Configuration does not exist: {env_config}")
            return Path(env_config)
        
        # 检查默认路径
        base_dir = Path(__file__).parent.parent
        workspace_dir = base_dir.parent
        config_paths = [
            base_dir / 'config' / 'config.yaml',
            base_dir / 'config.yaml',
            workspace_dir / 'config' / 'config.yaml',
            workspace_dir / 'config.yaml',
            workspace_dir / 'config' / 'config.example.yaml',
        ]
        
        for path in config_paths:
            if path.exists():
                return path
        
        raise FileNotFoundError("未找到配置文件")
    
    def _apply_env_overrides(self):
        """应用环境变量覆盖配置"""
        env_mappings = {
            'JAVA_HOME': ['java', 'home'],
            'AGENT_TESTER_JAVA_HOME': ['java', 'home'],
            'AGENT_TESTER_MAVEN_OPTS': ['java', 'tool_options'],
            'AGENT_TESTER_LOG_LEVEL': ['logging', 'level'],
            'AGENT_TESTER_LOG_FILE': ['logging', 'file'],
            'MAPTEST_MODEL': ['default_model', 'name'],
        }
        
        for env_var, config_path in env_mappings.items():
            value = os.getenv(env_var)
            if value:
                self._set_nested_value(config_path, value)

        provider = self.get('default_model.provider', 'openai')
        api_key = os.getenv('MAPTEST_API_KEY') or os.getenv('AGENT_TESTER_API_KEY')
        base_url = os.getenv('MAPTEST_BASE_URL')
        if api_key:
            self._set_nested_value(['api', provider, 'api_key'], api_key)
        if base_url:
            self._set_nested_value(['api', provider, 'base_url'], base_url)

        # Resolve executable names without embedding a machine-specific Maven path.
        command_paths = [
            ['maven', 'compile_command'], ['maven', 'test_command'],
            ['maven', 'fallback_command'], ['maven', 'junit5', 'compile_command'],
            ['maven', 'junit5', 'test_command'],
        ]
        for key_path in command_paths:
            command = self.get('.'.join(key_path))
            if isinstance(command, list) and command:
                executable = os.getenv('MAPTEST_MAVEN') or command[0]
                command[0] = shutil.which(executable) or executable
    
    def _set_nested_value(self, path: list, value: Any):
        """设置嵌套配置值"""
        current = self._config
        for key in path[:-1]:
            if key not in current:
                current[key] = {}
            current = current[key]
        current[path[-1]] = value
    
    def _validate_config(self):
        """验证配置的完整性"""
        required_sections = ['api', 'java', 'maven', 'paths', 'logging']
        
        for section in required_sections:
            if section not in self._config:
                raise ValueError(f"缺少必需的配置节: {section}")
        
        # 验证Java路径
        java_home = self._config['java']['home']
        if java_home and not os.path.exists(java_home):
            logging.warning(f"Java路径不存在: {java_home}")
        
        # 验证API配置
        for provider, config in self._config['api'].items():
            if not config.get('api_key') and provider != 'ollama':
                logging.warning(f"API提供商 {provider} 缺少API密钥")
    
    def _setup_environment(self):
        """设置环境变量"""
        # 设置Java环境
        java_home = self._config['java']['home']
        if java_home:
            os.environ['JAVA_HOME'] = java_home
        os.environ['JAVA_TOOL_OPTIONS'] = self._config['java']['tool_options']
        
        # 创建日志目录
        log_file = self._config['logging']['file']
        if log_file and not Path(log_file).is_absolute():
            log_file = str(Path(__file__).resolve().parents[2] / log_file)
            self._config['logging']['file'] = log_file
        log_dir = os.path.dirname(log_file)
        if log_dir and not os.path.exists(log_dir):
            os.makedirs(log_dir, exist_ok=True)
    
    def get(self, key: str, default: Any = None) -> Any:
        """
        获取配置值，支持点号分隔的嵌套键
        
        Args:
            key: 配置键，支持 'section.subsection.key' 格式
            default: 默认值
            
        Returns:
            配置值
        """
        keys = key.split('.')
        current = self._config
        
        try:
            for k in keys:
                current = current[k]
            return current
        except (KeyError, TypeError):
            return default
    
    def get_api_config(self, provider: str) -> Dict[str, str]:
        """获取API提供商配置"""
        return self.get(f'api.{provider}', {})

    def get_model_config(self, model_name: Optional[str] = None) -> Dict[str, Any]:
        """获取模型配置"""
        provider_name = self.get('default_model.provider')
        if provider_name:
            api_config = self.get_api_config(provider_name)
        elif model_name:
            # 根据模型名称推断提供商
            for provider in ['lmstudio', 'local', 'glm', 'deepseek', 'qwen', 'minimax', 'mimo']:
                if provider in model_name.lower():
                    api_config = self.get_api_config(provider)
                    break
            else:
                api_config = self.get_api_config('glm')
        else:
            api_config = self.get_api_config('glm')
        
        return {
            'model_name': model_name or self.get('default_model.name'),
            'api_key': api_config.get('api_key', ''),
            'base_url': api_config.get('base_url', ''),
            'temperature': self.get('default_model.temperature'),
            'top_p': self.get('default_model.top_p'),
            'timeout': self.get('default_model.timeout'),
            'max_retries': self.get('default_model.max_retries'),
        }
    
    def get_maven_config(self, junit_version: int = 4) -> Dict[str, list]:
        """获取Maven配置"""
        if junit_version == 5:
            return {
                'compile': self.get('maven.junit5.compile_command'),
                'test': self.get('maven.junit5.test_command'),
                'fallback': self.get('maven.fallback_command'),
            }
        else:
            return {
                'compile': self.get('maven.compile_command'),
                'test': self.get('maven.test_command'),
                'fallback': self.get('maven.fallback_command'),
            }
    
    def get_path(self, path_name: str) -> str:
        """获取路径配置"""
        base_dir = self.get('paths.base_dir', '.')
        relative_path = self.get(f'paths.{path_name}', '')
        return os.path.join(base_dir, relative_path)
    
    def reload(self):
        """重新加载配置"""
        self._load_config()
    
    def to_dict(self) -> Dict[str, Any]:
        """返回完整配置字典"""
        return self._config.copy()


# 全局配置实例
config = ConfigManager()
