from typing import Any, Dict, List, Optional


class InitialGeneratorPromptBuilder:
    METHOD_INTENTION_SYSTEM = (
        "You are a professional Java code analysis expert. "
        "Analyze the focal method line by line, keep line numbers exact, "
        "and return a structured method-intention summary."
    )

    TEST_GENERATION_SYSTEM = (
        "You are a professional Java unit-test engineer. "
        "Generate tests strictly from the focal method source, project compilation constraints, "
        "visible imports, and method intention. "
        "Do not invent exceptions, methods, types, or placeholder implementations that do not exist. "
        "When a type is needed, prefer importing the real project type, "
        "and do not redefine classes that already exist in production code."
    )

    RAW_TEST_GENERATION_SYSTEM = (
        "You are a professional Java unit-test engineer. "
        "Generate a JUnit 4 test from only the focal target, focal method source, "
        "and real project compilation constraints. "
        "Do not invent exceptions, methods, types, or placeholder implementations that do not exist. "
        "Do not redefine classes that already exist in production code."
    )

    @staticmethod
    def build_method_intention_prompt(code_with_line_numbers: str, focal_method_name: str, plantuml_code: str) -> str:
        uml_section = InitialGeneratorPromptBuilder._format_uml_section(plantuml_code)
        return f"""
# Method Intention Analysis Task

Analyze the focal method below line by line and return structured Markdown.

## Focal Method
- Method Name: {focal_method_name}

## Method Source
```java
{code_with_line_numbers}
```

{uml_section}

## Output Requirements
1. First summarize the method signature, return value, and key boundary conditions.
2. Output a line-by-line intention table with these columns:
   - Line Number
   - Action Description
   - Logical Precondition
3. Then summarize:
   - Core responsibility
   - Key characteristics
   - Boundary conditions
   - Return-value rules
   - Test-critical points
4. Derive behavior from source only. Do not guess.
""".strip()

    @staticmethod
    def build_test_prompt(
        focal_method_code: str,
        focal_method_name: str,
        test_import_info: str,
        plantuml_code: str,
        package_name: str,
        class_name: str,
        expected_test_class_name: str = "",
        method_intention: str = "",
        project_constraints: Optional[Dict[str, Any]] = None,
    ) -> str:
        constraints = project_constraints or {}
        java_source = constraints.get("java_source_level", "1.8")
        java_target = constraints.get("java_target_level", java_source)
        focal_signature = constraints.get("focal_signature", "")
        test_class_name = expected_test_class_name or f"{class_name}{focal_method_name}Test"
        context_mode = str(constraints.get("context_mode") or "").strip().lower().replace("-", "_")
        if context_mode == "raw_llm":
            return InitialGeneratorPromptBuilder.build_raw_llm_test_prompt(
                focal_method_code=focal_method_code,
                focal_method_name=focal_method_name,
                package_name=package_name,
                class_name=class_name,
                expected_test_class_name=test_class_name,
                focal_signature=focal_signature,
                java_source=java_source,
                java_target=java_target,
            )
        visible_types = InitialGeneratorPromptBuilder._format_visible_types(
            constraints.get("available_type_candidates", [])
        )
        import_section = test_import_info or "No additional import information."
        intention_section = method_intention or "No method-intention analysis is available."
        uml_section = InitialGeneratorPromptBuilder._format_uml_section(plantuml_code)
        priority_order = InitialGeneratorPromptBuilder._format_priority_order(bool(str(plantuml_code or "").strip()))

        return f"""
# Test Generation Task

## Focal Target
- Package: {package_name}
- Class: {class_name}
- Method: {focal_method_name}
- Method Signature: {focal_signature or "Unresolved"}

## Real Project Compilation Constraints
- Java source level: {java_source}
- Java target level: {java_target}
- The generated code must be compatible with this source/target level.

## Focal Method Source
```java
{focal_method_code}
```

## Visible Imports
```java
{import_section}
```

## Real Types Available for Direct Use
{visible_types}

## Method Intention
{intention_section}

{uml_section}

## Priority Order
{priority_order}

## Hard Constraints
1. Use JUnit 4.
2. Use only real types, constructors, and methods that exist in the project.
3. Do not define placeholder classes, fake implementations, or helper classes that shadow production classes.
4. Do not assume a call throws an exception unless source or real signatures support that conclusion.
5. Do not use syntax newer than Java {java_source}.
6. Do not reference simple class names that are neither imported nor in the same package.
7. For reflection, invoker, or formatter scenarios, use real constructors and method signatures; do not replace them with `Object.class`, invented helpers, or fake signatures.
8. If an assertion cannot be derived directly from source, omit that assertion.
9. Generate exactly one complete test class named `{test_class_name}`.

## Output Format
Return exactly one complete ```java``` fenced block containing `package`, `import`, `class`, and at least one `@Test` method.
Do not output explanatory text.
""".strip()

    @staticmethod
    def build_raw_llm_test_prompt(
        focal_method_code: str,
        focal_method_name: str,
        package_name: str,
        class_name: str,
        expected_test_class_name: str,
        focal_signature: str,
        java_source: str,
        java_target: str,
    ) -> str:
        return f"""
# Raw LLM Test Generation Task

## Focal Target
- Package: {package_name}
- Class: {class_name}
- Method: {focal_method_name}
- Method Signature: {focal_signature or "Unresolved"}

## Real Project Compilation Constraints
- Java source level: {java_source}
- Java target level: {java_target}
- The generated code must be compatible with this source/target level.

## Focal Method Source
```java
{focal_method_code}
```

## Raw Baseline Rules
1. Use JUnit 4.
2. Generate exactly one complete test class named `{expected_test_class_name}`.
3. Use the real production class `{class_name}` from package `{package_name}`.
4. Do not use UML, method-intention summaries, dependency summaries, or class-level context not shown above.
5. Do not define placeholder classes, fake implementations, or helper classes that shadow production classes.
6. Do not assume a call throws an exception unless the focal method source or Java signature clearly supports it.
7. Do not use syntax newer than Java {java_source}.
8. If an assertion cannot be derived directly from the focal method source, keep the assertion conservative.

## Output Format
Return exactly one complete ```java``` fenced block containing `package`, `import`, `class`, and at least one `@Test` method.
Do not output explanatory text.
""".strip()

    @staticmethod
    def _format_visible_types(candidates: List[Any]) -> str:
        normalized: List[str] = []
        for item in candidates or []:
            if isinstance(item, dict):
                label = item.get("fqcn") or item.get("simple_name")
            else:
                label = str(item).strip()
            if label and label not in normalized:
                normalized.append(label)

        if not normalized:
            return "- No visible type candidates."
        return "\n".join(f"- {item}" for item in normalized[:40])

    @staticmethod
    def _format_uml_section(plantuml_code: str) -> str:
        if not str(plantuml_code or "").strip():
            return """## Pruned UML Context
- No UML context is supplied for this run.""".strip()
        return f"""## Pruned UML Context
- Note: the UML below is the context pruned around the focal method
- Relationship semantics: `-->` means usage/dependency, `--|>` means class inheritance, and `..|>` means interface implementation
{plantuml_code}""".strip()

    @staticmethod
    def _format_priority_order(has_uml_context: bool) -> str:
        lines = [
            "1. Focal method source",
            "2. Real project compilation constraints",
            "3. Imports and visible types",
            "4. Method intention",
        ]
        if has_uml_context:
            lines.append("5. UML context")
        return "\n".join(lines)

    @staticmethod
    def _format_lines(lines: List[str], fallback: str) -> str:
        normalized = [str(item).strip() for item in (lines or []) if str(item).strip()]
        if not normalized:
            return f"- {fallback}"
        return "\n".join(f"- {line}" for line in normalized[:20])
