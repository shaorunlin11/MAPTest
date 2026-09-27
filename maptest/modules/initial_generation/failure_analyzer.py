import re
from typing import Dict, List, Optional


class InitialGeneratorFailureAnalyzer:
    @staticmethod
    def new_failure_state() -> Dict[str, str]:
        return {
            "failure_stage": "",
            "failure_category": "",
            "failure_signature": "",
            "initial_failure_stage": "",
            "initial_failure_category": "",
            "initial_failure_signature": "",
            "final_failure_stage": "",
            "final_failure_category": "",
            "final_failure_signature": "",
        }

    @staticmethod
    def extract_failure_signature(error_info: str) -> str:
        for line in str(error_info or "").splitlines():
            stripped = line.strip()
            if not stripped:
                continue
            lowered = stripped.lower()
            if any(token in lowered for token in [
                "error", "exception", "cannot find symbol", "not applicable",
                "timeout", "assertion", "expected", "no such"
            ]):
                return stripped[:300]
        return str(error_info or "").strip()[:300]

    @classmethod
    def classify_failure(
        cls,
        error_info: str,
        compile_success: int,
        test_success: int,
        candidate_type_map: Optional[Dict[str, List[Dict[str, str]]]] = None,
        stage_hint: Optional[str] = None
    ) -> Dict[str, str]:
        text = str(error_info or "")
        lowered = text.lower()
        signature = cls.extract_failure_signature(text)
        candidate_type_map = candidate_type_map or {}

        if stage_hint == "generation":
            return {
                "failure_stage": "generation",
                "failure_category": "other",
                "failure_signature": signature or "generation returned empty test code",
            }

        if stage_hint == "preflight":
            if "shadowing helper class" in lowered or "unresolved simple types" in lowered:
                category = "compile_missing_import"
            elif "java level" in lowered or "lambda" in lowered or "method reference" in lowered:
                category = "compile_java_level"
            elif "reflection signature mismatch" in lowered:
                category = "compile_signature_mismatch"
            else:
                category = "other"
            return {
                "failure_stage": "preflight",
                "failure_category": category,
                "failure_signature": signature,
            }

        if "pom.xml" in lowered and ("not found" in lowered or "there is no pom" in lowered):
            return {
                "failure_stage": "compile",
                "failure_category": "repo_path",
                "failure_signature": signature,
            }

        if "timeout" in lowered or "超时" in text:
            return {
                "failure_stage": "test" if compile_success else "compile",
                "failure_category": "timeout",
                "failure_signature": signature,
            }

        if not compile_success:
            if any(token in lowered for token in [
                "lambda expressions are not supported",
                "method references are not supported",
                "source option",
                "target option",
                "diamond operator is not supported",
                "stream()"
            ]):
                category = "compile_java_level"
            elif any(token in lowered for token in [
                "not applicable for the arguments",
                "actual and formal argument lists differ",
                "cannot be applied to given types",
                "constructor",
                "no suitable method found",
            ]):
                category = "compile_signature_mismatch"
            elif "cannot find symbol" in lowered:
                symbol_match = re.search(r'symbol:\s+class\s+([A-Za-z_][A-Za-z0-9_]*)', text, re.IGNORECASE)
                symbol_name = symbol_match.group(1) if symbol_match else ""
                if symbol_name and len(candidate_type_map.get(symbol_name, [])) == 1:
                    category = "compile_missing_import"
                else:
                    category = "compile_unknown_symbol"
            else:
                category = "other"
            return {
                "failure_stage": "compile",
                "failure_category": category,
                "failure_signature": signature,
            }

        if not test_success:
            if any(token in text for token in ["NoSuchMethodException", "IllegalArgumentException", "InvocationTargetException"]):
                category = "test_reflection_setup"
            elif any(token in lowered for token in [
                "expected:<", "comparisonfailure", "assertionerror", "expected exception"
            ]):
                category = "test_wrong_exception"
            else:
                category = "other"
            return {
                "failure_stage": "test",
                "failure_category": category,
                "failure_signature": signature,
            }

        return {
            "failure_stage": "",
            "failure_category": "",
            "failure_signature": "",
        }

    @staticmethod
    def merge_failure_state(state: Dict[str, str], classified: Dict[str, str], first_only: bool = False) -> Dict[str, str]:
        if not classified:
            return state
        stage = classified.get("failure_stage", "")
        category = classified.get("failure_category", "")
        signature = classified.get("failure_signature", "")
        if first_only:
            if stage and not state.get("initial_failure_stage"):
                state["initial_failure_stage"] = stage
                state["initial_failure_category"] = category
                state["initial_failure_signature"] = signature
            return state
        state["failure_stage"] = stage
        state["failure_category"] = category
        state["failure_signature"] = signature
        state["final_failure_stage"] = stage
        state["final_failure_category"] = category
        state["final_failure_signature"] = signature
        return state

    @staticmethod
    def clear_failure_state(state: Dict[str, str]) -> Dict[str, str]:
        for key in ("failure_stage", "failure_category", "failure_signature", "final_failure_stage", "final_failure_category", "final_failure_signature"):
            state[key] = ""
        return state
