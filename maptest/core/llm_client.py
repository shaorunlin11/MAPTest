"""
统一LLM客户端
支持多种API提供商，提供统一的调用接口
"""

import time
import re
from typing import Dict, List, Optional, Any, Union
try:
    from openai import OpenAI
except ImportError:
    OpenAI = None
    import openai as _legacy_openai
from .config_manager import config
from .logger import LoggerMixin, log_function_call, log_performance


class _LegacyChatCompletions:
    def __init__(self, api_key: str, base_url: str):
        self.api_key = api_key
        self.base_url = base_url

    def create(self, **params):
        _legacy_openai.api_key = self.api_key
        _legacy_openai.api_base = self.base_url
        if "timeout" in params and "request_timeout" not in params:
            params["request_timeout"] = params.pop("timeout")
        return _legacy_openai.ChatCompletion.create(**params)


class _LegacyChat:
    def __init__(self, api_key: str, base_url: str):
        self.completions = _LegacyChatCompletions(api_key, base_url)


class _LegacyOpenAIClient:
    def __init__(self, api_key: str, base_url: str):
        self.chat = _LegacyChat(api_key, base_url)


class LLMClient(LoggerMixin):
    """统一的LLM客户端，支持多种API提供商"""
    
    def __init__(self, model_name: Optional[str] = None, **kwargs):
        """
        初始化LLM客户端
        
        Args:
            model_name: 模型名称，如果为None则使用默认模型
            **kwargs: 额外的配置参数
        """
        if model_name is None:
            model_name = config.get('default_model.name')
        self.model_config = config.get_model_config(model_name)
        self.model_config.update(kwargs)
        
        # 初始化OpenAI客户端
        client_class = OpenAI or _LegacyOpenAIClient
        self.client = client_class(
            api_key=self.model_config['api_key'],
            base_url=self.model_config['base_url']
        )
        
        # self.logger.info(f"初始化LLM客户端: {self.model_config['model_name']}")
    
    @log_function_call()
    @log_performance()
    def chat_completion(self, 
                       messages: List[Dict[str, str]], 
                       temperature: Optional[float] = None,
                       top_p: Optional[float] = None,
                       max_tokens: Optional[int] = None,
                       **kwargs) -> str:
        """
        调用聊天完成API
        
        Args:
            messages: 消息列表
            temperature: 温度参数
            top_p: top_p参数
            max_tokens: 最大token数
            **kwargs: 其他参数
            
        Returns:
            API响应内容
        """
        # 合并参数
        params = {
            'model': self.model_config['model_name'],
            'messages': messages,
            'temperature': temperature or self.model_config['temperature'],
            'top_p': top_p or self.model_config['top_p'],
            'timeout': self.model_config['timeout'],
            **kwargs
        }
        
        if max_tokens:
            params['max_tokens'] = max_tokens

        
        # 重试机制
        max_retries = self.model_config['max_retries']
        last_error = None
        
        for attempt in range(max_retries):
            try:
                self.logger.debug(f"第{attempt + 1}次调用LLM API")
                
                completion = self.client.chat.completions.create(**params)
                
                # 记录使用情况
                # if completion.usage:
                    # usage = completion.usage
                    # self.logger.info(
                    #     f"API调用成功 - "
                    #     f"输入Token: {usage.prompt_tokens}, "
                    #     f"输出Token: {usage.completion_tokens}, "
                    #     f"总Token: {usage.total_tokens}"
                    # )
                
                response = self._completion_content(completion)
                if response:
                    response = self._clean_response(response)
                
                return response
                
            except Exception as e:
                last_error = e
                self.logger.warning(f"第{attempt + 1}次调用失败: {e}")
                
                if attempt < max_retries - 1:
                    wait_time = 5 * (attempt + 1)  # 递增等待时间
                    self.logger.info(f"等待{wait_time}秒后重试...")
                    time.sleep(wait_time)
        
        # 所有重试都失败了
        self.logger.error(f"LLM API调用失败，已重试{max_retries}次: {last_error}")
        return ""
    
    def _clean_response(self, response: str) -> str:
        """清理响应内容"""
        if not response:
            return ""
        if '<think>' in response:
            response = re.sub(r'<think>.*?</think>', '', response, flags=re.DOTALL)  #删除<think>内容
        
        # 移除多余的空白字符
        response = re.sub(r'\n\s*\n', '\n\n', response)
        response = response.strip()
        
        return response

    @staticmethod
    def _completion_content(completion: Any) -> str:
        try:
            return completion.choices[0].message.content or ""
        except Exception:
            pass

        try:
            choice = completion["choices"][0]
        except Exception:
            choice = getattr(completion, "choices", [{}])[0]

        try:
            message = choice["message"]
        except Exception:
            message = getattr(choice, "message", {})

        if isinstance(message, dict):
            return str(message.get("content") or "")
        return str(getattr(message, "content", "") or "")

# 便捷函数
def get_llm_client(model_name: Optional[str] = None, **kwargs) -> LLMClient:
    """
    获取LLM客户端实例的便捷函数
    
    Args:
        model_name: 模型名称
        **kwargs: 其他参数
        
    Returns:
        LLMClient实例
    """
    return LLMClient(model_name, **kwargs)
