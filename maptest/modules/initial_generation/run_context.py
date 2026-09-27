import hashlib
import json
import re
from typing import Any, Dict, List


class InitialRunContext:
    ARTIFACT_VERSION = "initial_generator_v2"

    @staticmethod
    def normalize_run_mode(run_mode: str) -> str:
        mode = (run_mode or "fresh").strip().lower()
        return mode if mode in {"fresh", "incremental"} else "fresh"

    @staticmethod
    def compute_input_digest(project_data: List[Dict[str, Any]]) -> str:
        payload = json.dumps(project_data, ensure_ascii=False, sort_keys=True, separators=(",", ":"))
        return hashlib.sha256(payload.encode("utf-8")).hexdigest()

    @staticmethod
    def build_method_key(class_name: str, method_name: str) -> str:
        return f"{class_name}#{method_name}"

    @staticmethod
    def normalize_method_signature(signature: Any) -> str:
        text = str(signature or "").strip()
        if not text:
            return ""
        text = re.sub(r"\s+", " ", text)
        text = re.sub(r"\s*([(),])\s*", r"\1", text)
        text = re.sub(r"\(\s*", "(", text)
        text = re.sub(r"\s*\)", ")", text)
        return text.strip()

    @classmethod
    def build_signature_hash(cls, signature: Any) -> str:
        normalized = cls.normalize_method_signature(signature) or "__missing_signature__"
        return hashlib.sha256(normalized.encode("utf-8")).hexdigest()[:8]

    @classmethod
    def build_method_variant_key(cls, class_name: str, method_name: str, signature: Any) -> str:
        return f"{cls.build_method_key(class_name, method_name)}::{cls.build_signature_hash(signature)}"

    @staticmethod
    def sanitize_file_name(raw_name: str) -> str:
        return re.sub(r'[<>:"/\\\\|?*]', "_", raw_name)

    @classmethod
    def build_expected_test_class_name(
        cls,
        class_name: str,
        method_name: str,
        signature: Any,
        overloaded: bool = False,
    ) -> str:
        base_name = f"{class_name}{method_name}Test"
        if overloaded and cls.normalize_method_signature(signature):
            return f"{base_name}_{cls.build_signature_hash(signature)}"
        return base_name

    @classmethod
    def build_method_intention_file_name(
        cls,
        class_name: str,
        method_name: str,
        method_variant_key: str = "",
    ) -> str:
        artifact_key = method_variant_key or cls.build_method_key(class_name, method_name)
        return f"{cls.sanitize_file_name(artifact_key)}_intention.md"

    @classmethod
    def build_test_prompt_file_name(
        cls,
        class_name: str,
        method_name: str,
        method_variant_key: str = "",
    ) -> str:
        artifact_key = method_variant_key or cls.build_method_key(class_name, method_name)
        return f"{cls.sanitize_file_name(artifact_key)}_prompt.md"

    @classmethod
    def prepare_reused_result(
        cls,
        result: Dict[str, Any],
        run_id: str,
        method_key: str,
        method_variant_key: str = "",
        focal_signature: str = "",
    ) -> Dict[str, Any]:
        reused = dict(result or {})
        reused["run_id"] = run_id
        reused["method_key"] = method_key
        if method_variant_key:
            reused["method_variant_key"] = method_variant_key
        elif not reused.get("method_variant_key"):
            reused["method_variant_key"] = cls.build_method_variant_key(
                reused.get("class_name", ""),
                reused.get("method_name", ""),
                focal_signature or reused.get("focal_signature", ""),
            )
        if focal_signature and not reused.get("focal_signature"):
            reused["focal_signature"] = focal_signature
        reused["artifact_version"] = cls.ARTIFACT_VERSION
        reused.setdefault("coverage_status", "missing")
        reused.setdefault("coverage_error_hint", "")
        reused.setdefault("coverage_last_checked_at", "")
        return reused
