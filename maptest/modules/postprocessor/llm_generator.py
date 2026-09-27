from __future__ import annotations

from typing import Any, Optional

from maptest.core import LLMClient


class LLMTextGenerator:
    system_prompt = ""

    def __init__(
        self,
        model_name: Optional[str] = None,
        temperature: float = 0.1,
        max_tokens: Optional[int] = None,
        llm_client: Optional[Any] = None,
    ) -> None:
        self.model_name = model_name
        self.temperature = float(temperature)
        self.max_tokens = max_tokens
        self.llm_client = llm_client or LLMClient(model_name, max_retries=1)

    def __call__(self, prompt: str) -> str:
        messages = [
            {"role": "system", "content": self.system_prompt},
            {"role": "user", "content": prompt},
        ]
        return str(
            self.llm_client.chat_completion(
                messages,
                temperature=self.temperature,
                max_tokens=self.max_tokens,
            )
            or ""
        ).strip()


class LLMPatchGenerator(LLMTextGenerator):
    system_prompt = (
        "You generate complete Java unit test files. "
        "Return only the complete Java file as plain text."
    )


class LLMPlanningRequirementGenerator(LLMTextGenerator):
    system_prompt = (
        "You extract concise planning requirements for Java unit test generation. "
        "Return only the JSON array requested by the prompt."
    )
