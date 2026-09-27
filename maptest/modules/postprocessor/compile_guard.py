from __future__ import annotations

import re
from typing import List

from .models import Baseline, MethodContext, Requirement


class CompileGuard:
    def build_requirements(self, context: MethodContext, baseline: Baseline) -> List[Requirement]:
        code = baseline.test_code
        if not code:
            return []

        requirements: List[Requirement] = []

        package_match = re.search(r"^\s*package\s+([\w\.]+)\s*;", code, re.MULTILINE)
        if package_match:
            requirements.append(Requirement(kind="compile", text=f"preserve package {package_match.group(1)}"))
        else:
            package_name = str(context.raw_result.get("package_name") or "").strip()
            if package_name:
                requirements.append(Requirement(kind="compile", text=f"use package {package_name}"))

        if "org.junit.jupiter.api.Test" in code:
            requirements.append(Requirement(kind="compile", text="use JUnit 5 imports (org.junit.jupiter.api.Test)"))
        elif "org.junit.Test" in code:
            requirements.append(Requirement(kind="compile", text="use JUnit 4 imports (org.junit.Test)"))

        class_match = re.search(r"\bpublic\s+class\s+([A-Za-z_][A-Za-z0-9_]*)", code)
        if class_match:
            requirements.append(Requirement(kind="compile", text=f"preserve public test class name {class_match.group(1)}"))

        if "org.mockito" in code or "@Mock" in code or "Mockito." in code:
            requirements.append(Requirement(kind="compile", text="use Mockito for mocking dependencies"))

        return requirements
