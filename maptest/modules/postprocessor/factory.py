from __future__ import annotations

from pathlib import Path
from typing import Any, Dict, Optional

from .config import PostProcessorV2Config
from .llm_generator import LLMPlanningRequirementGenerator, LLMPatchGenerator
from .patch_agent import PatchAgent
from .planning_agents import DependencyStrategyAgent, PathRequirementAgent, StateRequirementAgent
from .processor import PostProcessorV2
from .target_planner import TargetPlanner


def build_postprocessor_v2_with_llm_patch(
    model_name: Optional[str] = None,
    temperature: float = 0.1,
    max_tokens: Optional[int] = None,
    config: Optional[PostProcessorV2Config] = None,
    config_overrides: Optional[Dict[str, Any]] = None,
    results_root: Optional[Path] = None,
    output_root: Optional[Path] = None,
    repos_dir: Optional[Path] = None,
    llm_client: Optional[Any] = None,
) -> PostProcessorV2:
    generator = LLMPatchGenerator(
        model_name=model_name,
        temperature=temperature,
        max_tokens=max_tokens,
        llm_client=llm_client,
    )
    return PostProcessorV2(
        config=config,
        config_overrides=config_overrides,
        patch_agent=PatchAgent(generator=generator),
        results_root=results_root,
        output_root=output_root,
        repos_dir=repos_dir,
    )


def build_postprocessor_v2_with_llm_patch_and_planning(
    model_name: Optional[str] = None,
    patch_model_name: Optional[str] = None,
    planning_model_name: Optional[str] = None,
    temperature: float = 0.1,
    patch_temperature: Optional[float] = None,
    planning_temperature: Optional[float] = None,
    max_tokens: Optional[int] = None,
    patch_max_tokens: Optional[int] = None,
    planning_max_tokens: Optional[int] = None,
    config: Optional[PostProcessorV2Config] = None,
    config_overrides: Optional[Dict[str, Any]] = None,
    results_root: Optional[Path] = None,
    output_root: Optional[Path] = None,
    repos_dir: Optional[Path] = None,
    llm_client: Optional[Any] = None,
    patch_llm_client: Optional[Any] = None,
    planning_llm_client: Optional[Any] = None,
) -> PostProcessorV2:
    patch_generator = LLMPatchGenerator(
        model_name=patch_model_name or model_name,
        temperature=temperature if patch_temperature is None else patch_temperature,
        max_tokens=max_tokens if patch_max_tokens is None else patch_max_tokens,
        llm_client=patch_llm_client or llm_client,
    )
    planning_generator = LLMPlanningRequirementGenerator(
        model_name=planning_model_name or model_name,
        temperature=temperature if planning_temperature is None else planning_temperature,
        max_tokens=max_tokens if planning_max_tokens is None else planning_max_tokens,
        llm_client=planning_llm_client or llm_client,
    )
    target_planner = TargetPlanner(
        planning_agents=(
            PathRequirementAgent(generator=planning_generator),
            DependencyStrategyAgent(generator=planning_generator),
            StateRequirementAgent(generator=planning_generator),
        )
    )
    return PostProcessorV2(
        config=config,
        config_overrides=config_overrides,
        patch_agent=PatchAgent(generator=patch_generator),
        target_planner=target_planner,
        results_root=results_root,
        output_root=output_root,
        repos_dir=repos_dir,
    )
