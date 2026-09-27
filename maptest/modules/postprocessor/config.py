from __future__ import annotations

from dataclasses import dataclass, replace
from typing import Any, Dict, Optional

try:
    from maptest.core import config as global_config
except Exception:
    global_config = None


@dataclass(frozen=True)
class PostProcessorV2Config:
    max_iterations: int = 8
    max_no_gain_rounds: int = 4
    stabilization_repair_attempt_limit: int = 1
    patch_repair_attempt_limit: int = 2
    maven_timeout_seconds: int = 120
    target_line_coverage: float = 100.0
    target_group_size: int = 3
    max_plans_per_baseline: int = 16
    max_failure_feedback_requirements: int = 3

    @classmethod
    def from_global_config(cls, overrides: Optional[Dict[str, Any]] = None) -> "PostProcessorV2Config":
        values = {
            "max_iterations": cls._get("postprocessor_v2.max_iterations", cls.max_iterations),
            "max_no_gain_rounds": cls._get("postprocessor_v2.max_no_gain_rounds", cls.max_no_gain_rounds),
            "stabilization_repair_attempt_limit": cls._get(
                "postprocessor_v2.stabilization_repair_attempt_limit",
                cls.stabilization_repair_attempt_limit,
            ),
            "patch_repair_attempt_limit": cls._get(
                "postprocessor_v2.patch_repair_attempt_limit",
                cls.patch_repair_attempt_limit,
            ),
            "maven_timeout_seconds": cls._get("postprocessor_v2.maven_timeout_seconds", cls._get("maven.timeout", cls.maven_timeout_seconds)),
            "target_line_coverage": cls._get("postprocessor_v2.target_line_coverage", cls.target_line_coverage),
            "target_group_size": cls._get("postprocessor_v2.target_group_size", cls.target_group_size),
            "max_plans_per_baseline": cls._get("postprocessor_v2.max_plans_per_baseline", cls.max_plans_per_baseline),
            "max_failure_feedback_requirements": cls._get(
                "postprocessor_v2.max_failure_feedback_requirements",
                cls.max_failure_feedback_requirements,
            ),
        }
        if overrides:
            values.update(overrides)
        return cls(**values).normalized()

    def with_overrides(self, overrides: Optional[Dict[str, Any]] = None) -> "PostProcessorV2Config":
        if not overrides:
            return self.normalized()
        return replace(self, **overrides).normalized()

    def normalized(self) -> "PostProcessorV2Config":
        return PostProcessorV2Config(
            max_iterations=max(0, int(self.max_iterations)),
            max_no_gain_rounds=max(1, int(self.max_no_gain_rounds)),
            stabilization_repair_attempt_limit=max(0, int(self.stabilization_repair_attempt_limit)),
            patch_repair_attempt_limit=max(0, int(self.patch_repair_attempt_limit)),
            maven_timeout_seconds=max(1, int(self.maven_timeout_seconds)),
            target_line_coverage=float(self.target_line_coverage),
            target_group_size=max(1, int(self.target_group_size)),
            max_plans_per_baseline=max(1, int(self.max_plans_per_baseline)),
            max_failure_feedback_requirements=max(0, int(self.max_failure_feedback_requirements)),
        )

    @staticmethod
    def _get(key: str, default: Any) -> Any:
        if global_config is None:
            return default
        try:
            return global_config.get(key, default)
        except Exception:
            return default
