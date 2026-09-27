import re
from pathlib import Path
from typing import Any, Dict, List, Optional

from maptest.modules.Optimizer.Repairer import repair_test_code
from maptest.core import FileUtils


class InitialGeneratorRepairPipelineHelper:
    @staticmethod
    def _normalize_failure_signature(signature: str) -> str:
        text = str(signature or "")
        if not text:
            return ""
        text = re.sub(r"[A-Za-z]:\\[^\s:]+", "", text)
        text = re.sub(r"(?<![A-Za-z])/[\w.\-\\/]+", "", text)
        text = re.sub(r":\d+(?::\d+)?", "", text)
        text = re.sub(r"\b\d{4}-\d{2}-\d{2}[ T]\d{2}:\d{2}:\d{2}(?:,\d+)?\b", "", text)
        text = re.sub(r"\b\d{2}:\d{2}:\d{2}(?:,\d+)?\b", "", text)
        text = re.sub(r"\s+", " ", text).strip()
        return text[:160]

    @classmethod
    def _build_repeat_key(cls, failure_stage: str, failure_category: str,
                          failure_signature: str) -> str:
        normalized_signature = cls._normalize_failure_signature(failure_signature)
        return "|".join([
            str(failure_stage or "").strip(),
            str(failure_category or "").strip(),
            normalized_signature,
        ]).strip("|")

    @classmethod
    def _record_attempt(cls, attempt_history: List[Dict[str, Any]],
                        repair_mode: str, delete_test: bool,
                        classified: Dict[str, str],
                        no_effect_reason: str = "") -> bool:
        classified = classified or {}
        repeat_key = cls._build_repeat_key(
            classified.get("failure_stage", ""),
            classified.get("failure_category", ""),
            classified.get("failure_signature", ""),
        )
        is_repeat = bool(
            repeat_key
            and attempt_history
            and attempt_history[-1].get("repeat_key") == repeat_key
        )
        attempt_history.append({
            "attempt_index": len(attempt_history) + 1,
            "repair_mode": repair_mode,
            "delete_test": delete_test,
            "failure_stage": classified.get("failure_stage", ""),
            "failure_category": classified.get("failure_category", ""),
            "failure_signature": classified.get("failure_signature", ""),
            "no_effect_reason": no_effect_reason,
            "repeat_key": repeat_key,
        })
        return is_repeat

    @staticmethod
    def _extract_failed_symbol_names(failure_signature: str) -> List[str]:
        names: List[str] = []
        for pattern in (
            r"symbol:\s+class\s+([A-Za-z_][A-Za-z0-9_]*)",
            r"unresolved simple types:\s*([A-Za-z0-9_,\s]+)",
        ):
            match = re.search(pattern, failure_signature or "", re.IGNORECASE)
            if not match:
                continue
            raw_value = match.group(1)
            tokens = raw_value.split(",") if "," in raw_value else [raw_value]
            for token in tokens:
                candidate = token.strip()
                if candidate and candidate not in names:
                    names.append(candidate)
        return names

    @staticmethod
    def _extract_rejected_member_names(failure_signature: str) -> List[str]:
        names: List[str] = []
        for pattern in (
            r"method\s+'([A-Za-z_][A-Za-z0-9_]*)'",
            r"NoSuchMethodException:\s+[A-Za-z_][A-Za-z0-9_.$]*\.([A-Za-z_][A-Za-z0-9_]*)\(",
            r"constructor\s+([A-Za-z_][A-Za-z0-9_]*)\(",
        ):
            for match in re.finditer(pattern, failure_signature or "", re.IGNORECASE):
                candidate = match.group(1).strip()
                if candidate and candidate not in names:
                    names.append(candidate)
        return names

    @staticmethod
    def _extract_exception_name(failure_signature: str) -> str:
        for pattern in (
            r"Expected exception:\s*([A-Za-z_][A-Za-z0-9_.$]*Exception)",
            r"expected:\s*<?([A-Za-z_][A-Za-z0-9_.$]*Exception)>?",
            r"([A-Za-z_][A-Za-z0-9_.$]*Exception)",
        ):
            match = re.search(pattern, failure_signature or "", re.IGNORECASE)
            if match:
                return match.group(1).strip()
        return ""

    @classmethod
    def _build_do_not_repeat(cls, recent_attempts: List[Dict[str, Any]]) -> List[str]:
        constraints: List[str] = []
        for attempt in reversed(recent_attempts):
            category = attempt.get("failure_category", "")
            signature = attempt.get("failure_signature", "")
            if category in {"compile_missing_import", "compile_unknown_symbol"}:
                for symbol_name in cls._extract_failed_symbol_names(signature):
                    constraints.append(
                        f"Do not leave type `{symbol_name}` unresolved; use a real import or same-package visibility."
                    )
                constraints.append(
                    "Do not define helper classes to replace unresolved project types."
                )
            elif category == "compile_signature_mismatch":
                member_names = cls._extract_rejected_member_names(signature)
                if member_names:
                    constraints.append(
                        f"Do not reuse rejected method or constructor names like `{member_names[0]}` unless they exist in the focal source."
                    )
                constraints.append(
                    "Do not keep constructor or method signatures that were already rejected by preflight or compilation."
                )
            elif category == "test_wrong_exception":
                exception_name = cls._extract_exception_name(signature)
                if exception_name:
                    constraints.append(
                        f"Do not keep asserting `{exception_name}` unless the focal source proves it."
                    )
                constraints.append(
                    "Do not reuse an assertion or expected exception that the failing test log already disproved."
                )
            elif category == "test_reflection_setup":
                member_names = cls._extract_rejected_member_names(signature)
                if member_names:
                    constraints.append(
                        f"Do not use reflection target `{member_names[0]}` again unless it matches a real constructor or method signature."
                    )
                constraints.append(
                    "Do not use Object.class placeholders or invented reflection call chains."
                )
            elif attempt.get("no_effect_reason") == "delete_test_no_match":
                constraints.append(
                    "Do not retry delete fallback for the same failure unless the next failure is a timeout."
                )
            elif attempt.get("no_effect_reason"):
                constraints.append(
                    f"Do not repeat the same no-effect repair pattern (`{attempt['no_effect_reason']}`)."
                )

        deduped: List[str] = []
        for constraint in constraints:
            cleaned = re.sub(r"\s+", " ", str(constraint or "")).strip()
            if cleaned and cleaned not in deduped:
                deduped.append(cleaned[:180])
            if len(deduped) >= 4:
                break
        return deduped

    @classmethod
    def _compute_repeat_count(cls, attempt_history: List[Dict[str, Any]]) -> int:
        if len(attempt_history) < 2:
            return 0
        last_key = attempt_history[-1].get("repeat_key", "")
        if not last_key:
            return 0
        repeat_count = 1
        for attempt in reversed(attempt_history[:-1]):
            if attempt.get("repeat_key") != last_key:
                break
            repeat_count += 1
        return repeat_count

    @classmethod
    def _build_anti_repeat_summary(cls, attempt_history: List[Dict[str, Any]]) -> Optional[Dict[str, Any]]:
        if not attempt_history:
            return None
        recent_attempts = attempt_history[-2:]
        summary_attempts = [{
            "repair_mode": attempt.get("repair_mode", ""),
            "failure_category": attempt.get("failure_category", ""),
            "failure_signature": cls._normalize_failure_signature(
                attempt.get("failure_signature", "")
            )[:120],
        } for attempt in recent_attempts]
        do_not_repeat = cls._build_do_not_repeat(recent_attempts)
        repeat_count = cls._compute_repeat_count(attempt_history)
        if not summary_attempts and not do_not_repeat:
            return None
        return {
            "recent_attempts": summary_attempts,
            "do_not_repeat": do_not_repeat[:4],
            "repeat_count": repeat_count,
        }

    @staticmethod
    def _sync_project_context(owner: Any, repo_project_name: str,
                              generation_context: Dict[str, Any],
                              test_result_text: str) -> None:
        if not test_result_text:
            return
        owner._update_java_levels_from_output(repo_project_name, test_result_text)
        project_context = owner._get_project_context(repo_project_name)
        generation_context["project_context"] = project_context
        generation_context["java_source_level"] = project_context.get(
            "java_source_level",
            generation_context.get("java_source_level", "1.8"),
        )
        generation_context["java_target_level"] = project_context.get(
            "java_target_level",
            generation_context.get("java_target_level", "1.8"),
        )

    @staticmethod
    def _apply_preflight(owner: Any, generated_test: str,
                         test_file_path: Path, test_file_saved: Path,
                         package_name: str, class_name: str,
                         expected_test_class_name: str,
                         generation_context: Dict[str, Any]) -> Dict[str, Any]:
        preflight = owner._run_preflight_checks(
            generated_test,
            package_name,
            expected_test_class_name,
            generation_context,
        )
        for note in preflight.get("notes", []):
            owner.logger.info(note)
        generated_test = preflight.get("code", generated_test)
        return {
            "generated_test": generated_test,
            "passed": preflight.get("passed", True),
            "error_info": preflight.get("error_info", ""),
        }

    @staticmethod
    def _build_repair_context(focal_method_code: str, method_intention: str,
                              generation_context: Dict[str, Any],
                              package_name: str, expected_test_class_name: str,
                              focal_method_name: str,
                              anti_repeat_summary: Optional[Dict[str, Any]] = None) -> Dict[str, Any]:
        context = {
            "focal_method_code": focal_method_code,
            "method_intention": method_intention,
            "generation_context": generation_context,
            "package_name": package_name,
            "expected_test_class_name": expected_test_class_name,
            "focal_method_name": focal_method_name,
            "focal_signature": generation_context.get("focal_signature", ""),
            "java_source_level": generation_context.get("java_source_level", "1.8"),
            "java_target_level": generation_context.get("java_target_level", "1.8"),
        }
        if anti_repeat_summary:
            context["anti_repeat_summary"] = anti_repeat_summary
        return context

    @classmethod
    def run(cls, owner: Any, generated_test: str, test_prompt: str,
            repo_project_name: str, test_file_path: Path, test_file_saved: Path,
            package_name: str, class_name: str,
            expected_test_class_name: str, focal_method_code: str,
            focal_method_name: str, method_intention: str,
            generation_context: Dict[str, Any],
            failure_state: Dict[str, str]) -> Dict[str, Any]:
        compile_success = 0
        test_success = 0
        test_result = ""
        current_stage_hint = ""
        repair_route_history: List[str] = []
        repair_count = 0
        used_delete_fallback = False
        attempt_history: List[Dict[str, Any]] = []
        repair_repeat_count = 0
        repair_repeat_guard_hits = 0

        if not (test_file_saved and isinstance(test_file_saved, Path)):
            return {
                "generated_test": generated_test,
                "compile_success": compile_success,
                "test_success": test_success,
                "repair_route_history": repair_route_history,
                "repair_count": repair_count,
                "used_delete_fallback": used_delete_fallback,
                "repair_repeat_count": repair_repeat_count,
                "repair_repeat_guard_hits": repair_repeat_guard_hits,
            }

        if owner.enable_preflight:
            preflight = cls._apply_preflight(
                owner=owner,
                generated_test=generated_test,
                test_file_path=test_file_path,
                test_file_saved=test_file_saved,
                package_name=package_name,
                class_name=class_name,
                expected_test_class_name=expected_test_class_name,
                generation_context=generation_context,
            )
            generated_test = preflight["generated_test"]
            if not preflight["passed"]:
                test_result = preflight["error_info"]
                current_stage_hint = "preflight"
            else:
                generated_test = owner._save_test_file(generated_test, test_file_path, package_name, class_name)
                FileUtils.write_file(test_file_saved, generated_test)
                compile_success, test_success, test_result = owner._execute_test(
                    test_file_path,
                    repo_project_name,
                    test_file_saved,
                )
        else:
            generated_test = owner._save_test_file(generated_test, test_file_path, package_name, class_name)
            FileUtils.write_file(test_file_saved, generated_test)
            compile_success, test_success, test_result = owner._execute_test(
                test_file_path,
                repo_project_name,
                test_file_saved,
            )

        temperature = 0.1
        max_repair_count = owner.repair_max_rounds
        test_result_text = str(test_result or "")
        cls._sync_project_context(owner, repo_project_name, generation_context, test_result_text)
        if "timeout" in test_result_text.lower() or "超时" in test_result_text:
            owner.logger.warning("测试执行超时，尝试删除可能导致超时的测试用例")

        if not test_success:
            owner.logger.warning("测试执行失败，开始迭代修复")
            classified = owner._classify_failure(
                test_result_text,
                compile_success,
                test_success,
                generation_context.get("candidate_type_map"),
                stage_hint=current_stage_hint,
            )
            owner._merge_failure_state(failure_state, classified, first_only=True)
            owner._merge_failure_state(failure_state, classified)

            while repair_count < max_repair_count:
                repair_mode = owner._select_repair_mode(
                    compile_success,
                    test_success,
                    test_result_text,
                    current_stage_hint,
                )
                delete_test = False
                if "timeout" in test_result_text.lower() or "超时" in test_result_text:
                    delete_test = True
                elif repair_mode == "semantic_test_repair" and repair_count == max_repair_count - 1:
                    delete_test = True

                used_delete_fallback = used_delete_fallback or delete_test
                repair_route_history.append(repair_mode + ("+delete" if delete_test else ""))
                anti_repeat_summary = cls._build_anti_repeat_summary(attempt_history)
                if anti_repeat_summary:
                    repair_repeat_guard_hits += 1

                owner.logger.info(f"第{repair_count + 1}次修复尝试")
                repair_error_info = test_result_text
                if compile_success and current_stage_hint != "preflight":
                    repair_error_info = "########## Compile SUCCESS but Test fail ##########\n" + test_result_text
                    owner.logger.info("raw_test_log_forwarded")

                repair_result = repair_test_code(
                    test_code=generated_test,
                    test_prompt=test_prompt,
                    error_info=repair_error_info,
                    model_name=owner.model_name,
                    delete_test=delete_test,
                    temperature=temperature,
                    repair_mode=repair_mode,
                    repair_context=cls._build_repair_context(
                        focal_method_code=focal_method_code,
                        method_intention=method_intention,
                        generation_context=generation_context,
                        package_name=package_name,
                        expected_test_class_name=expected_test_class_name,
                        focal_method_name=focal_method_name,
                        anti_repeat_summary=anti_repeat_summary,
                    ),
                )

                if repair_result.success:
                    generated_test = repair_result.repaired_code
                    if not generated_test.startswith("package"):
                        generated_test = f"package {package_name};\n\n{generated_test}"

                    if owner.enable_preflight:
                        preflight = cls._apply_preflight(
                            owner=owner,
                            generated_test=generated_test,
                            test_file_path=test_file_path,
                            test_file_saved=test_file_saved,
                            package_name=package_name,
                            class_name=class_name,
                            expected_test_class_name=expected_test_class_name,
                            generation_context=generation_context,
                        )
                        generated_test = preflight["generated_test"]
                        if not preflight["passed"]:
                            generated_test = owner._save_test_file(generated_test, test_file_path, package_name, class_name)
                            FileUtils.write_file(test_file_saved, generated_test)
                            compile_success, test_success = 0, 0
                            test_result = preflight["error_info"]
                            test_result_text = str(test_result or "")
                            current_stage_hint = "preflight"
                            classified = owner._classify_failure(
                                test_result_text,
                                compile_success,
                                test_success,
                                generation_context.get("candidate_type_map"),
                                stage_hint=current_stage_hint,
                            )
                            if cls._record_attempt(
                                attempt_history,
                                repair_mode,
                                delete_test,
                                classified,
                                no_effect_reason="preflight_failed_after_repair",
                            ):
                                repair_repeat_count += 1
                            owner._merge_failure_state(failure_state, classified)
                            repair_count += 1
                            temperature += 0.2
                            continue

                    generated_test = owner._save_test_file(generated_test, test_file_path, package_name, class_name)
                    FileUtils.write_file(test_file_saved, generated_test)
                    compile_success, test_success, test_result = owner._execute_test(
                        test_file_path,
                        repo_project_name,
                        test_file_saved,
                    )
                    test_result_text = str(test_result or "")
                    cls._sync_project_context(owner, repo_project_name, generation_context, test_result_text)
                    current_stage_hint = ""
                    if "timeout" in test_result_text.lower() or "超时" in test_result_text:
                        owner.logger.warning("测试执行超时，下次修复将尝试删除超时测试用例")

                    if not test_success:
                        classified = owner._classify_failure(
                            test_result_text,
                            compile_success,
                            test_success,
                            generation_context.get("candidate_type_map"),
                            stage_hint=current_stage_hint,
                        )
                        if cls._record_attempt(
                            attempt_history,
                            repair_mode,
                            delete_test,
                            classified,
                            no_effect_reason="repaired_but_still_failed",
                        ):
                            repair_repeat_count += 1
                        owner._merge_failure_state(failure_state, classified)

                    if test_success:
                        owner._clear_failure_state(failure_state)
                        break
                else:
                    if repair_result.no_effect_reason == "delete_test_no_match":
                        owner.logger.warning("delete_test_no_match")
                    classified = owner._classify_failure(
                        test_result_text,
                        compile_success,
                        test_success,
                        generation_context.get("candidate_type_map"),
                        stage_hint=current_stage_hint,
                    )
                    if cls._record_attempt(
                        attempt_history,
                        repair_mode,
                        delete_test,
                        classified,
                        no_effect_reason=repair_result.no_effect_reason,
                    ):
                        repair_repeat_count += 1
                    owner.logger.warning("修复尝试失败")

                repair_count += 1
                temperature += 0.2

        return {
            "generated_test": generated_test,
            "compile_success": compile_success,
            "test_success": test_success,
            "repair_route_history": repair_route_history,
            "repair_count": repair_count,
            "used_delete_fallback": used_delete_fallback,
            "repair_repeat_count": repair_repeat_count,
            "repair_repeat_guard_hits": repair_repeat_guard_hits,
        }
