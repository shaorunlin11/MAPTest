"""
Maven执行器
统一处理Maven编译、测试等操作
"""

import csv
import os
import re
import subprocess
import shutil
import xml.etree.ElementTree as ET
from typing import Dict, Tuple, Optional, List, Any
from pathlib import Path
from .config_manager import config
from .logger import LoggerMixin, log_function_call, log_performance


class MavenResult:
    """Maven执行结果"""
    
    def __init__(self, success: bool, output: str, error: str = "", 
                 compile_success: bool = False, test_success: bool = False):
        self.success = success
        self.output = output
        self.error = error
        self.compile_success = compile_success
        self.test_success = test_success
    
    def __bool__(self):
        return self.success


class MavenExecutor(LoggerMixin):
    """Maven执行器，统一处理Maven相关操作"""

    JACOCO_PLUGIN = "org.jacoco:jacoco-maven-plugin:0.8.12"
    
    def __init__(self, project_path: str, timeout: Optional[int] = None):
        """
        初始化Maven执行器
        
        Args:
            project_path: Maven项目路径
            timeout: 超时时间（秒），None表示不超时
        """
        self.project_path = Path(project_path)
        self.java_home = config.get('java.home')
        self.java_tool_options = config.get('java.tool_options')
        self.timeout = timeout or config.get('maven.timeout', 120)
        self._jacoco_cache_key = None
        self._jacoco_cache_root = None
        
        # 设置环境变量
        self.env = os.environ.copy()
        if self.java_home:
            self.env['JAVA_HOME'] = self.java_home
        self.env['JAVA_TOOL_OPTIONS'] = self.java_tool_options
        
        if not self.project_path.exists():
            raise FileNotFoundError(f"项目路径不存在: {project_path}")
        
        # self.logger.info(f"初始化Maven执行器: {project_path}, 超时: {self.timeout}秒")

    def _normalize_command(self, command: List[str]) -> List[str]:
        normalized = list(command)
        if os.name == 'nt' and normalized and normalized[0].lower() == 'mvn':
            normalized[0] = 'mvn.cmd'
        return normalized

    def _terminate_process_tree(self, pid: int):
        if os.name == 'nt':
            subprocess.run(
                ['taskkill', '/F', '/T', '/PID', str(pid)],
                capture_output=True,
                text=True,
                encoding="utf-8",
                errors="replace",
            )
        else:
            try:
                os.kill(pid, 9)
            except OSError:
                pass

    def _run_command(self, command: List[str]) -> subprocess.CompletedProcess:
        normalized_command = self._normalize_command(command)
        process = subprocess.Popen(
            normalized_command,
            cwd=self.project_path,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            encoding="utf-8",
            errors="replace",
            env=self.env,
            shell=False
        )
        try:
            stdout, stderr = process.communicate(timeout=self.timeout)
            return subprocess.CompletedProcess(normalized_command, process.returncode, stdout, stderr)
        except subprocess.TimeoutExpired as e:
            self._terminate_process_tree(process.pid)
            stdout, stderr = process.communicate()
            raise subprocess.TimeoutExpired(
                cmd=normalized_command,
                timeout=self.timeout,
                output=stdout,
                stderr=stderr
            ) from e

    def _extend_command(self, command: List[str], extra_args: List[str]) -> List[str]:
        normalized = list(command)
        for arg in extra_args:
            if arg not in normalized:
                normalized.append(arg)
        return normalized

    def _jacoco_test_command(self, junit_version: int = 4, test_class_name: str = "") -> List[str]:
        maven_config = config.get_maven_config(junit_version)
        test_cmd = list(maven_config['test'])
        if not test_cmd:
            test_cmd = ["mvn", "test"]

        executable = test_cmd[0]
        passthrough_args = [arg for arg in test_cmd[1:] if arg != "test"]
        command = [
            executable,
            f"{self.JACOCO_PLUGIN}:prepare-agent",
            "test",
            f"{self.JACOCO_PLUGIN}:report",
            *passthrough_args,
        ]
        if test_class_name:
            command = self._extend_command(command, [f"-Dtest={test_class_name}", "-DfailIfNoTests=false"])
        return command

    def generate_jacoco_report(self, test_class_name: str = "", junit_version: int = 4) -> MavenResult:
        command = self._jacoco_test_command(junit_version=junit_version, test_class_name=test_class_name)
        try:
            result = self._run_command(command)
            output = result.stdout + result.stderr
            build_success = "BUILD SUCCESS" in output
            test_success = build_success and self._has_executed_tests(output)
            if build_success and not test_success:
                output += "\n[MAPTest] Treating BUILD SUCCESS as coverage failure because Maven ran 0 tests.\n"
            self.logger.info(f"JaCoCo report {'generated' if test_success else 'generation failed'}")
            return MavenResult(
                success=test_success,
                output=output,
                error=result.stderr,
                compile_success=build_success,
                test_success=test_success,
            )
        except subprocess.TimeoutExpired as e:
            self.logger.error(f"JaCoCo report generation timed out ({self.timeout}s): {e}")
            return MavenResult(
                success=False,
                output=f"JaCoCo report generation timed out ({self.timeout}s)",
                error=f"JaCoCo report generation timed out ({self.timeout}s): {str(e)}",
                compile_success=False,
                test_success=False,
            )
        except Exception as e:
            self.logger.error(f"JaCoCo report generation failed: {e}")
            return MavenResult(
                success=False,
                output="",
                error=str(e),
                compile_success=False,
                test_success=False,
            )

    def ensure_jacoco_report(self, test_class_name: str = "", junit_version: int = 4) -> MavenResult:
        if self.get_jacoco_report_path() or self.get_jacoco_html_report_dir():
            return MavenResult(
                success=True,
                output="[MAPTest] Existing JaCoCo report found.\n",
                compile_success=True,
                test_success=True,
            )
        return self.generate_jacoco_report(test_class_name=test_class_name, junit_version=junit_version)

    @staticmethod
    def _executed_test_count(output: str) -> Optional[int]:
        lowered = (output or "").lower()
        if "no tests to run" in lowered or "no tests were executed" in lowered:
            return 0
        matches = re.findall(r"Tests run:\s*(\d+)", output or "")
        if not matches:
            return None
        return max(int(value) for value in matches)

    @classmethod
    def _has_executed_tests(cls, output: str) -> bool:
        count = cls._executed_test_count(output)
        return count is not None and count > 0

    def clear_test_reports(self) -> None:
        for relative_path in [
            ("target", "jacoco.exec"),
            ("target", "site", "jacoco"),
            ("target", "site", "jacoco-ut"),
            ("target", "site", "jacoco-aggregate"),
            ("target", "surefire-reports"),
            ("target", "failsafe-reports"),
            ("target", "test-classes"),
        ]:
            path = self.project_path.joinpath(*relative_path)
            try:
                if path.is_dir():
                    shutil.rmtree(path, ignore_errors=True)
                elif path.exists():
                    path.unlink()
            except OSError:
                self.logger.warning("Failed to clear stale test report: %s", path)
    
    @log_function_call()
    @log_performance()
    def compile_project(self, junit_version: int = 4) -> MavenResult:
        """
        编译项目
        
        Args:
            junit_version: JUnit版本
            
        Returns:
            编译结果
        """
        maven_config = config.get_maven_config(junit_version)
        compile_cmd = maven_config['compile']
        
        # self.logger.info(f"开始编译项目，JUnit版本: {junit_version}")
        self.logger.debug(f"编译命令: {' '.join(compile_cmd)}")
        
        try:
            result = self._run_command(compile_cmd)
            
            output = result.stdout + result.stderr
            compile_success = "BUILD SUCCESS" in output
            
            self.logger.info(f"编译{'成功' if compile_success else '失败'}")
            
            return MavenResult(
                success=compile_success,
                output=output,
                error=result.stderr,
                compile_success=compile_success
            )
            
        except subprocess.TimeoutExpired as e:
            self.logger.error(f"编译超时（{self.timeout}秒）: {e}")
            return MavenResult(
                success=False,
                output=f"编译超时（{self.timeout}秒）",
                error=f"编译超时（{self.timeout}秒）: {str(e)}",
                compile_success=False
            )
        except Exception as e:
            self.logger.error(f"编译过程中出现异常: {e}")
            return MavenResult(
                success=False,
                output="",
                error=str(e),
                compile_success=False
            )
    
    @log_function_call()
    @log_performance()
    def test_project(self, junit_version: int = 4) -> MavenResult:
        """
        运行测试
        
        Args:
            junit_version: JUnit版本
            
        Returns:
            测试结果
        """
        maven_config = config.get_maven_config(junit_version)
        test_cmd = maven_config['test']
        
        # self.logger.info(f"开始运行测试，JUnit版本: {junit_version}")
        # self.logger.debug(f"测试命令: {' '.join(test_cmd)}")
        
        try:
            result = self._run_command(test_cmd)
            
            output = result.stdout + result.stderr
            build_success = "BUILD SUCCESS" in output
            test_success = build_success and self._has_executed_tests(output)
            if build_success and not test_success:
                output += "\n[MAPTest] Treating BUILD SUCCESS as test failure because Maven ran 0 tests.\n"
            
            self.logger.info(f"测试{'成功' if test_success else '失败'}")
            
            return MavenResult(
                success=test_success,
                output=output,
                error=result.stderr,
                test_success=test_success
            )
            
        except subprocess.TimeoutExpired as e:
            self.logger.error(f"测试超时（{self.timeout}秒）: {e}")
            return MavenResult(
                success=False,
                output=f"测试超时（{self.timeout}秒）",
                error=f"测试超时（{self.timeout}秒）: {str(e)}",
                test_success=False
            )
        except Exception as e:
            self.logger.error(f"测试过程中出现异常: {e}")
            return MavenResult(
                success=False,
                output="",
                error=str(e),
                test_success=False
            )
    
    @log_function_call()
    @log_performance()
    def compile_and_test(self, junit_version: int = 4) -> MavenResult:
        """
        编译并测试项目
        
        Args:
            junit_version: JUnit版本
            
        Returns:
            完整的执行结果
        """
        self.logger.info("开始编译和测试项目")
        
        # 先编译
        compile_result = self.compile_project(junit_version)
        # if not compile_result.compile_success:
            # self.logger.warning("编译失败，尝试清理后重新安装")
            # fallback_result = self._fallback_install()
            # if not fallback_result.success:
            #     return compile_result
            # # 重新编译
            # compile_result = self.compile_project(junit_version)
        
        # 如果编译成功，运行测试
        if compile_result.compile_success:
            test_result = self.test_project(junit_version)
            # 精简测试错误报告
            if not test_result.test_success:
                marker = "[INFO] Results:"
                if marker in test_result.output:
                    test_result_output = test_result.output.split(marker, 1)[1]
                    test_result.output = test_result_output.split("[ERROR] Failed to execute goal")[0]
            
            combined_output = (
                f"########## Compile INFO ##########\n"
                f"{compile_result.output}\n"
                f"########## Test INFO ##########\n"
                f"{test_result.output}"
            )
            
            return MavenResult(
                success=test_result.test_success,
                output=combined_output,
                error=test_result.error,
                compile_success=compile_result.compile_success,
                test_success=test_result.test_success
            )
        else:
            # 精简编译错误报告
            marker = "[ERROR] COMPILATION ERROR : "
            if marker in compile_result.output:
                compile_result_output = "[ERROR] COMPILATION ERROR :" + compile_result.output.split(marker, 1)[1]
                compile_result.output = compile_result_output.split("[ERROR] Failed to execute goal")[0]
            return compile_result

    @log_function_call()
    @log_performance()
    def compile_and_test_targeted(self, test_class_name: str, junit_version: int = 4) -> MavenResult:
        """
        仅编译并运行指定测试类，避免其他历史/并发测试污染验证结果。

        Args:
            test_class_name: 目标测试类名
            junit_version: JUnit版本

        Returns:
            完整的执行结果
        """
        targeted_test = (test_class_name or "").strip()
        if not targeted_test:
            return self.compile_and_test(junit_version=junit_version)

        self.logger.debug("开始定向编译和测试项目")
        maven_config = config.get_maven_config(junit_version)
        compile_cmd = self._extend_command(
            maven_config['compile'],
            [f"-Dtest={targeted_test}", "-DfailIfNoTests=false"]
        )
        test_cmd = self._extend_command(
            maven_config['test'],
            [f"-Dtest={targeted_test}", "-DfailIfNoTests=false"]
        )

        try:
            compile_result = self._run_command(compile_cmd)
            compile_output = compile_result.stdout + compile_result.stderr
            compile_success = "BUILD SUCCESS" in compile_output
            self.logger.info(f"编译{'成功' if compile_success else '失败'}")

            compile_maven_result = MavenResult(
                success=compile_success,
                output=compile_output,
                error=compile_result.stderr,
                compile_success=compile_success,
            )

            if not compile_success:
                marker = "[ERROR] COMPILATION ERROR : "
                if marker in compile_maven_result.output:
                    compile_result_output = "[ERROR] COMPILATION ERROR :" + compile_maven_result.output.split(marker, 1)[1]
                    compile_maven_result.output = compile_result_output.split("[ERROR] Failed to execute goal")[0]
                return compile_maven_result

            test_result = self._run_command(test_cmd)
            test_output = test_result.stdout + test_result.stderr
            build_success = "BUILD SUCCESS" in test_output
            test_success = build_success and self._has_executed_tests(test_output)
            if build_success and not test_success:
                test_output += "\n[MAPTest] Treating BUILD SUCCESS as test failure because Maven ran 0 tests.\n"
            self.logger.info(f"测试{'成功' if test_success else '失败'}")

            test_maven_result = MavenResult(
                success=test_success,
                output=test_output,
                error=test_result.stderr,
                test_success=test_success,
            )

            if not test_success:
                marker = "[INFO] Results:"
                if marker in test_maven_result.output:
                    test_result_output = test_maven_result.output.split(marker, 1)[1]
                    test_maven_result.output = test_result_output.split("[ERROR] Failed to execute goal")[0]

            combined_output = (
                f"########## Compile INFO ##########\n"
                f"{compile_maven_result.output}\n"
                f"########## Test INFO ##########\n"
                f"{test_maven_result.output}"
            )

            return MavenResult(
                success=test_maven_result.test_success,
                output=combined_output,
                error=test_maven_result.error,
                compile_success=compile_maven_result.compile_success,
                test_success=test_maven_result.test_success,
            )
        except subprocess.TimeoutExpired as e:
            self.logger.error(f"定向测试超时（{self.timeout}秒）: {e}")
            return MavenResult(
                success=False,
                output=f"定向测试超时（{self.timeout}秒）",
                error=f"定向测试超时（{self.timeout}秒）: {str(e)}",
                compile_success=False,
                test_success=False,
            )
        except Exception as e:
            self.logger.error(f"定向测试过程中出现异常: {e}")
            return MavenResult(
                success=False,
                output="",
                error=str(e),
                compile_success=False,
                test_success=False,
            )
    
    def _fallback_install(self) -> MavenResult:
        """回退安装：执行mvn clean install"""
        maven_config = config.get_maven_config()
        fallback_cmd = maven_config['fallback']
        
        self.logger.info("执行回退安装: mvn clean install")
        
        try:
            result = self._run_command(fallback_cmd)
            
            output = result.stdout + result.stderr
            success = "BUILD SUCCESS" in output
            
            self.logger.info(f"回退安装{'成功' if success else '失败'}")
            
            return MavenResult(success=success, output=output, error=result.stderr)
            
        except subprocess.TimeoutExpired as e:
            self.logger.error(f"回退安装超时（{self.timeout}秒）: {e}")
            return MavenResult(
                success=False,
                output=f"回退安装超时（{self.timeout}秒）",
                error=f"回退安装超时（{self.timeout}秒）: {str(e)}"
            )
        except Exception as e:
            self.logger.error(f"回退安装过程中出现异常: {e}")
            return MavenResult(success=False, output="", error=str(e))
    
    def get_surefire_reports_path(self) -> Optional[Path]:
        """获取Surefire测试报告路径"""
        surefire_path = self.project_path / "target" / "surefire-reports"
        return surefire_path if surefire_path.exists() else None
    
    def copy_surefire_reports(self, destination: Path) -> List[Path]:
        """
        复制Surefire测试报告到目标目录
        
        Args:
            destination: 目标目录
            
        Returns:
            复制的文件列表
        """
        source_path = self.get_surefire_reports_path()
        if not source_path:
            self.logger.warning("未找到Surefire测试报告")
            return []
        
        destination.mkdir(parents=True, exist_ok=True)
        copied_files = []
        
        try:
            for file_path in source_path.glob("*.xml"):
                dest_file = destination / file_path.name
                shutil.copy2(file_path, dest_file)
                copied_files.append(dest_file)
                self.logger.debug(f"复制测试报告: {file_path} -> {dest_file}")
            
            self.logger.info(f"复制了{len(copied_files)}个测试报告文件")
            
        except Exception as e:
            self.logger.error(f"复制测试报告失败: {e}")
        
        return copied_files
    
    def extract_test_info_from_output(self, output: str) -> Dict[str, str]:
        """
        从Maven输出中提取测试信息
        
        Args:
            output: Maven输出内容
            
        Returns:
            测试信息字典
        """
        test_info = {}
        
        # 提取Surefire报告路径
        import re
        pattern = r"\[ERROR\] Please refer to (.+?) for the individual test results\."
        match = re.search(pattern, output)
        if match:
            test_info['surefire_path'] = match.group(1).strip()
        
        # 提取测试统计信息
        tests_run_pattern = r"Tests run: (\d+), Failures: (\d+), Errors: (\d+), Skipped: (\d+)"
        test_matches = re.findall(tests_run_pattern, output)
        if test_matches:
            test_info['test_stats'] = test_matches
        
        return test_info
    
    def is_maven_project(self) -> bool:
        """检查是否为Maven项目"""
        pom_xml = self.project_path / "pom.xml"
        return pom_xml.exists()
    
    def get_project_info(self) -> Dict[str, str]:
        """Get basic metadata from pom.xml."""
        pom_xml = self.project_path / "pom.xml"

        if not pom_xml.exists():
            return {}

        try:
            tree = ET.parse(pom_xml)
            root = tree.getroot()

            namespace = ""
            if root.tag.startswith("{"):
                namespace = root.tag.split("}")[0] + "}"

            def get_text(tag_name):
                element = root.find(f"{namespace}{tag_name}")
                return element.text if element is not None else ""

            return {
                'group_id': get_text("groupId"),
                'artifact_id': get_text("artifactId"),
                'version': get_text("version"),
                'name': get_text("name"),
                'description': get_text("description")
            }

        except Exception as e:
            self.logger.error(f"Failed to parse pom.xml: {e}")
            return {}

    def get_jacoco_report_path(self) -> Optional[Path]:
        """Locate the JaCoCo XML report generated by the latest test run."""
        candidates = [
            self.project_path / "target" / "site" / "jacoco" / "jacoco.xml",
            self.project_path / "target" / "site" / "jacoco-ut" / "jacoco.xml",
            self.project_path / "target" / "jacoco.xml",
            self.project_path / "target" / "site" / "jacoco-aggregate" / "jacoco.xml",
        ]

        for candidate in candidates:
            if candidate.exists():
                return candidate

        target_dir = self.project_path / "target"
        if target_dir.exists():
            try:
                discovered = sorted(target_dir.rglob("jacoco.xml"))
            except OSError:
                discovered = []
            if discovered:
                return discovered[0]
        return None

    def get_jacoco_csv_report_path(self) -> Optional[Path]:
        xml_path = self.get_jacoco_report_path()
        candidates = []
        if xml_path is not None:
            candidates.append(xml_path.with_name("jacoco.csv"))
        candidates.extend([
            self.project_path / "target" / "site" / "jacoco" / "jacoco.csv",
            self.project_path / "target" / "site" / "jacoco-ut" / "jacoco.csv",
            self.project_path / "target" / "site" / "jacoco-aggregate" / "jacoco.csv",
            self.project_path / "target" / "jacoco.csv",
        ])

        for candidate in candidates:
            if candidate and candidate.exists():
                return candidate

        target_dir = self.project_path / "target"
        if target_dir.exists():
            try:
                discovered = sorted(target_dir.rglob("jacoco.csv"))
            except OSError:
                discovered = []
            if discovered:
                return discovered[0]
        return None

    def get_jacoco_html_report_dir(self) -> Optional[Path]:
        xml_path = self.get_jacoco_report_path()
        candidates = []
        if xml_path is not None:
            candidates.append(xml_path.parent)
        candidates.extend([
            self.project_path / "target" / "site" / "jacoco",
            self.project_path / "target" / "site" / "jacoco-ut",
            self.project_path / "target" / "site" / "jacoco-aggregate",
        ])

        for candidate in candidates:
            if candidate and candidate.exists():
                return candidate
        return None

    def _empty_focal_coverage_result(
        self,
        status: str = "coverage_unresolved",
        error_hint: str = "",
        report_fresh: bool = False,
    ) -> Dict[str, Any]:
        return {
            "line_coverage": None,
            "branch_coverage": None,
            "uncovered_lines": [],
            "uncovered_line_details": [],
            "coverage_status": status,
            "coverage_error_hint": error_hint,
            "coverage_report_fresh": report_fresh,
            "coverage_percentage_source": "",
            "coverage_handles_source": "",
            "coverage_file_line_coverage": None,
            "coverage_file_branch_coverage": None,
            "coverage_method_counter_resolved": False,
            "coverage_method_range_resolved": False,
        }

    def _latest_test_artifact_mtime_ns(self) -> Optional[int]:
        candidates = []
        for relative_dir in [
            ("target", "surefire-reports"),
            ("target", "failsafe-reports"),
        ]:
            report_dir = self.project_path.joinpath(*relative_dir)
            if not report_dir.exists():
                continue
            try:
                candidates.extend(path for path in report_dir.rglob("*") if path.is_file())
            except OSError:
                continue

        jacoco_exec = self.project_path / "target" / "jacoco.exec"
        if jacoco_exec.exists():
            candidates.append(jacoco_exec)

        if not candidates:
            return None

        latest_mtime = None
        for path in candidates:
            try:
                stat = path.stat()
            except OSError:
                continue
            latest_mtime = stat.st_mtime_ns if latest_mtime is None else max(latest_mtime, stat.st_mtime_ns)
        return latest_mtime

    def _is_report_fresh(self, report_path: Optional[Path], reference_mtime_ns: Optional[int]) -> bool:
        if report_path is None or not report_path.exists():
            return False
        if reference_mtime_ns is None:
            return True

        tolerance_ns = 5_000_000_000
        report_mtime_ns = self._resolve_report_mtime_ns(report_path)
        if report_mtime_ns is None:
            return False
        return report_mtime_ns + tolerance_ns >= reference_mtime_ns

    def _resolve_report_mtime_ns(self, report_path: Optional[Path]) -> Optional[int]:
        if report_path is None or not report_path.exists():
            return None

        try:
            if report_path.is_dir():
                latest_mtime_ns = None
                for child in report_path.rglob("*"):
                    if not child.is_file():
                        continue
                    try:
                        child_mtime_ns = child.stat().st_mtime_ns
                    except OSError:
                        continue
                    latest_mtime_ns = (
                        child_mtime_ns
                        if latest_mtime_ns is None
                        else max(latest_mtime_ns, child_mtime_ns)
                    )
                if latest_mtime_ns is not None:
                    return latest_mtime_ns
            return report_path.stat().st_mtime_ns
        except OSError:
            return None

    def _resolve_source_html_path(
        self,
        html_root: Optional[Path],
        package_name: str,
        source_filename: str,
    ) -> Optional[Path]:
        if html_root is None or not source_filename:
            return None

        direct_candidates = []
        if package_name:
            direct_candidates.extend([
                html_root / Path(*package_name.split(".")) / f"{source_filename}.html",
                html_root / package_name / f"{source_filename}.html",
                html_root / package_name.replace("/", ".") / f"{source_filename}.html",
            ])
        direct_candidates.append(html_root / f"{source_filename}.html")
        for direct_candidate in direct_candidates:
            if direct_candidate.exists():
                return direct_candidate

        try:
            discovered = list(html_root.rglob(f"{source_filename}.html"))
        except OSError:
            discovered = []
        if not discovered:
            return None
        if package_name:
            normalized_package = package_name.replace("/", ".")
            matched = [
                path for path in discovered
                if path.parent.name == normalized_package
                or path.parent.as_posix().replace("/", ".").endswith(normalized_package)
            ]
            if len(matched) == 1:
                return matched[0]
        return discovered[0] if len(discovered) == 1 else None

    @staticmethod
    def _csv_percentage(missed_value: Any, covered_value: Any,
                        zero_total_value: Optional[float] = None) -> Optional[float]:
        try:
            missed = int(missed_value)
            covered = int(covered_value)
        except (TypeError, ValueError):
            return None

        total = missed + covered
        if total <= 0:
            return zero_total_value
        return round((covered / total) * 100.0, 2)

    @staticmethod
    def _normalize_csv_class_name(class_name: str) -> str:
        return (class_name or "").replace("$", ".").strip()

    def _read_jacoco_csv_row(
        self,
        csv_path: Optional[Path],
        package_name: str,
        class_candidates: List[str],
    ) -> Optional[Dict[str, str]]:
        if csv_path is None or not csv_path.exists():
            return None

        normalized_candidates = {
            self._normalize_csv_class_name(candidate).split(".")[-1]
            for candidate in class_candidates
            if candidate
        }
        exact_candidates = {
            self._normalize_csv_class_name(candidate)
            for candidate in class_candidates
            if candidate
        }

        try:
            with csv_path.open("r", encoding="utf-8", newline="") as file:
                reader = csv.DictReader(file)
                for row in reader:
                    row_package = (row.get("PACKAGE") or "").replace("/", ".").strip()
                    row_class = self._normalize_csv_class_name(row.get("CLASS") or "")
                    if row_package != package_name:
                        continue
                    if row_class in exact_candidates or row_class.split(".")[-1] in normalized_candidates:
                        return row
        except Exception as exc:
            self.logger.warning(f"Failed to parse JaCoCo CSV report: {exc}")
        return None

    def _extract_html_line_coverage(self, html_source_path: Optional[Path]) -> Dict[str, List[int]]:
        coverage = {
            "lines_not_covered": [],
            "lines_partially_covered": [],
            "branch_not_covered": [],
            "branch_partially_covered": [],
            "lines_fully_covered": [],
        }
        if html_source_path is None or not html_source_path.exists():
            return coverage

        try:
            html_content = html_source_path.read_text(encoding="utf-8", errors="replace")
        except Exception as exc:
            self.logger.warning(f"Failed to read JaCoCo HTML report: {exc}")
            return coverage

        line_class_map: Dict[int, set] = {}
        for match in re.finditer(r'<[^>]*\bid="L(\d+)"[^>]*\bclass="([^"]+)"[^>]*>|<[^>]*\bclass="([^"]+)"[^>]*\bid="L(\d+)"[^>]*>', html_content):
            line_number = match.group(1) or match.group(4)
            class_attr = match.group(2) or match.group(3) or ""
            try:
                parsed_line = int(line_number)
            except (TypeError, ValueError):
                continue
            line_class_map.setdefault(parsed_line, set()).update(class_attr.split())

        for line_number, classes in sorted(line_class_map.items()):
            if "nc" in classes:
                coverage["lines_not_covered"].append(line_number)
            if "pc" in classes:
                coverage["lines_partially_covered"].append(line_number)
            if "bnc" in classes:
                coverage["branch_not_covered"].append(line_number)
            if "bpc" in classes:
                coverage["branch_partially_covered"].append(line_number)
            if "fc" in classes:
                coverage["lines_fully_covered"].append(line_number)
        return coverage

    def _build_uncovered_line_details_from_html(
        self,
        html_coverage: Dict[str, List[int]],
        ranges: List[Tuple[int, int]],
    ) -> List[Dict[str, Any]]:
        if not ranges:
            return []

        line_candidates = sorted({
            *html_coverage.get("lines_not_covered", []),
            *html_coverage.get("lines_partially_covered", []),
            *html_coverage.get("branch_not_covered", []),
            *html_coverage.get("branch_partially_covered", []),
        })

        details = []
        for line_number in line_candidates:
            if not any(start <= line_number <= end for start, end in ranges):
                continue

            instruction_gap = (
                line_number in (html_coverage.get("lines_not_covered", []) or []) or
                line_number in (html_coverage.get("lines_partially_covered", []) or [])
            )
            branch_gap = (
                line_number in (html_coverage.get("branch_not_covered", []) or []) or
                line_number in (html_coverage.get("branch_partially_covered", []) or [])
            )
            if not instruction_gap and not branch_gap:
                continue

            if instruction_gap and branch_gap:
                reason = "mixed"
            elif branch_gap:
                reason = "branch_gap"
            else:
                reason = "instruction_gap"

            details.append({
                "line": line_number,
                "instruction_gap": instruction_gap,
                "branch_gap": branch_gap,
                "reason": reason,
            })

        return details

    def _build_method_lookup_targets(self, class_fqn: str, method_name: str) -> List[Tuple[str, str]]:
        lookup_targets = [(class_fqn, method_name)]

        target_simple_name = class_fqn.split(".")[-1] if class_fqn else ""
        if method_name == target_simple_name:
            lookup_targets.append((class_fqn, "<init>"))

        inner_class_fqn = f"{class_fqn}.{method_name}" if class_fqn and method_name else ""
        if inner_class_fqn:
            lookup_targets.append((inner_class_fqn, "<init>"))
        return lookup_targets

    def _resolve_java_source_path(self, source_filename: str, package_name: str = "") -> Optional[Path]:
        if not source_filename:
            return None

        package_path = Path(*package_name.split(".")) if package_name else Path()
        direct_candidates = [
            self.project_path / "src" / "main" / "java" / package_path / source_filename,
            self.project_path / "src" / "test" / "java" / package_path / source_filename,
        ]
        for candidate in direct_candidates:
            if candidate.exists():
                return candidate

        for source_root in [
            self.project_path / "src" / "main" / "java",
            self.project_path / "src" / "test" / "java",
        ]:
            if not source_root.exists():
                continue
            try:
                discovered = sorted(source_root.rglob(source_filename))
            except OSError:
                discovered = []
            if discovered:
                return discovered[0]
        return None

    def _detect_unsupported_target_kind(
        self,
        class_fqn: str,
        method_name: str,
        target_context: Optional[Dict[str, Any]],
        method_nodes: List[ET.Element],
    ) -> Optional[Tuple[str, str]]:
        if target_context is None or method_nodes:
            return None

        class_elem = target_context.get("class_elem")
        if class_elem is None:
            return None

        source_filename = class_elem.get("sourcefilename", "")
        if not source_filename:
            return None

        canonical_class_name = target_context.get("canonical_class_name", class_fqn)
        package_name = ".".join(canonical_class_name.split(".")[:-1]) if canonical_class_name else ""
        source_path = self._resolve_java_source_path(source_filename, package_name)
        if source_path is None:
            return None

        try:
            source_text = source_path.read_text(encoding="utf-8", errors="replace")
        except OSError:
            return None

        target_name = (method_name or "").strip()
        if not target_name:
            return None

        simple_class_name = target_context.get("simple_class_name", class_fqn.split(".")[-1] if class_fqn else "")
        declaration_patterns = [
            (
                "enum",
                rf"(?m)^\s*(?:@\w+\s+)*(?:public|protected|private|static|final|abstract|sealed|non-sealed|\s)*enum\s+{re.escape(target_name)}\b",
            ),
            (
                "annotation",
                rf"(?m)^\s*(?:@\w+\s+)*(?:public|protected|private|static|final|abstract|sealed|non-sealed|\s)*@interface\s+{re.escape(target_name)}\b",
            ),
            (
                "interface",
                rf"(?m)^\s*(?:@\w+\s+)*(?:public|protected|private|static|final|abstract|sealed|non-sealed|\s)*interface\s+{re.escape(target_name)}\b",
            ),
            (
                "record",
                rf"(?m)^\s*(?:@\w+\s+)*(?:public|protected|private|static|final|abstract|sealed|non-sealed|\s)*record\s+{re.escape(target_name)}\b",
            ),
        ]

        for target_kind, pattern in declaration_patterns:
            if re.search(pattern, source_text):
                return (
                    "coverage_unsupported_target_kind",
                    f"目标为{target_kind}声明，当前方法级覆盖率采集器不支持",
                )

        if target_name != simple_class_name:
            class_pattern = rf"(?m)^\s*(?:@\w+\s+)*(?:public|protected|private|static|final|abstract|sealed|non-sealed|\s)*class\s+{re.escape(target_name)}\b"
            if re.search(class_pattern, source_text):
                return (
                    "coverage_unsupported_target_kind",
                    "目标为内部类型声明，当前方法级覆盖率采集器不支持",
                )

        return None

    def _filter_method_nodes_by_line_range(
        self,
        method_nodes: List[ET.Element],
        focal_method_range: Optional[Tuple[int, int]],
    ) -> List[ET.Element]:
        normalized_range = self._normalize_line_range(focal_method_range)
        if normalized_range is None or len(method_nodes) <= 1:
            return method_nodes

        start_line, end_line = normalized_range
        in_range_nodes: List[ET.Element] = []
        for method_elem in method_nodes:
            try:
                method_line = int(method_elem.get("line", "0") or 0)
            except ValueError:
                continue
            if start_line <= method_line <= end_line:
                in_range_nodes.append(method_elem)

        return in_range_nodes or method_nodes

    def _resolve_method_nodes(
        self,
        root: Optional[ET.Element],
        class_fqn: str,
        method_name: str,
        focal_method_range: Optional[Tuple[int, int]] = None,
    ) -> Tuple[Optional[Dict[str, Any]], List[ET.Element]]:
        if root is None:
            return None, []

        fallback_context = None
        for lookup_class_fqn, lookup_method_name in self._build_method_lookup_targets(class_fqn, method_name):
            class_contexts = self._find_class_contexts(root, lookup_class_fqn)
            if class_contexts and fallback_context is None:
                fallback_context = class_contexts[0]
            for context in class_contexts:
                nodes = self._find_method_nodes(context["class_elem"], lookup_method_name)
                if nodes:
                    return context, self._filter_method_nodes_by_line_range(nodes, focal_method_range)
        return fallback_context, []

    def _counter_percentage(self, method_nodes: List[ET.Element], counter_type: str) -> Tuple[Optional[float], bool]:
        missed = 0
        covered = 0
        resolved = False
        for method_elem in method_nodes:
            for counter_elem in method_elem.findall("counter"):
                if counter_elem.get("type") != counter_type:
                    continue
                resolved = True
                try:
                    missed += int(counter_elem.get("missed", "0") or 0)
                    covered += int(counter_elem.get("covered", "0") or 0)
                except ValueError:
                    continue
        if not resolved:
            return None, False
        zero_total_value = 0.0 if counter_type == "BRANCH" else None
        return self._csv_percentage(missed, covered, zero_total_value=zero_total_value), True

    @staticmethod
    def _normalize_line_range(focal_method_range: Optional[Tuple[int, int]]) -> Optional[Tuple[int, int]]:
        if not focal_method_range or len(focal_method_range) != 2:
            return None
        try:
            start_line = int(focal_method_range[0])
            end_line = int(focal_method_range[1])
        except (TypeError, ValueError):
            return None
        if start_line <= 0 or end_line < start_line:
            return None
        return start_line, end_line

    def collect_focal_method_coverage(
        self,
        class_fqn: str,
        method_name: str,
        focal_method_range: Optional[Tuple[int, int]] = None,
    ) -> Dict[str, Any]:
        xml_path = self.get_jacoco_report_path()
        csv_path = self.get_jacoco_csv_report_path()
        html_root = self.get_jacoco_html_report_dir()
        if not any(path and path.exists() for path in [xml_path, csv_path, html_root]):
            return self._empty_focal_coverage_result(
                status="coverage_missing_report",
                error_hint="未找到 JaCoCo XML/CSV/HTML 报告",
                report_fresh=False,
            )

        reference_mtime_ns = self._latest_test_artifact_mtime_ns()
        freshness_targets = [path for path in [xml_path, csv_path, html_root] if path and path.exists()]
        report_fresh = bool(freshness_targets) and all(
            self._is_report_fresh(path, reference_mtime_ns) for path in freshness_targets
        )
        if not report_fresh:
            return self._empty_focal_coverage_result(
                status="coverage_stale_report",
                error_hint="JaCoCo 报告未在最近一次测试执行后更新",
                report_fresh=False,
            )

        try:
            root = self._load_jacoco_root() if xml_path and xml_path.exists() else None
        except Exception as exc:
            return self._empty_focal_coverage_result(
                status="coverage_parse_failed",
                error_hint=f"JaCoCo XML 解析失败: {exc}",
                report_fresh=report_fresh,
            )

        target_context, method_nodes = self._resolve_method_nodes(
            root,
            class_fqn,
            method_name,
            focal_method_range=focal_method_range,
        )
        package_name = ".".join(class_fqn.split(".")[:-1]) if "." in class_fqn else ""
        csv_class_candidates = [class_fqn.split(".")[-1]] if class_fqn else []
        source_filename = f"{class_fqn.split('.')[-1].split('$')[0]}.java" if class_fqn else ""

        if target_context is not None:
            canonical_class_name = target_context["canonical_class_name"]
            normalized_class_name = target_context["normalized_class_name"]
            package_name = ".".join(canonical_class_name.split(".")[:-1])
            csv_class_candidates = [
                canonical_class_name.split(".")[-1],
                normalized_class_name.split(".")[-1],
                target_context["simple_class_name"],
            ]
            source_filename = target_context["class_elem"].get("sourcefilename", source_filename)

        coverage_result = self._empty_focal_coverage_result(
            status="coverage_unresolved",
            error_hint="覆盖率解析完成但未得到有效抓手",
            report_fresh=report_fresh,
        )

        csv_row = self._read_jacoco_csv_row(csv_path, package_name, csv_class_candidates)
        if csv_row:
            coverage_result["coverage_file_line_coverage"] = self._csv_percentage(
                csv_row.get("LINE_MISSED"), csv_row.get("LINE_COVERED")
            )
            coverage_result["coverage_file_branch_coverage"] = self._csv_percentage(
                csv_row.get("BRANCH_MISSED"), csv_row.get("BRANCH_COVERED"),
                zero_total_value=0.0,
            )
            coverage_result["coverage_percentage_source"] = "jacoco_csv_class"

        unsupported_target = self._detect_unsupported_target_kind(
            class_fqn=class_fqn,
            method_name=method_name,
            target_context=target_context,
            method_nodes=method_nodes,
        )
        if unsupported_target is not None:
            coverage_result["coverage_status"] = unsupported_target[0]
            coverage_result["coverage_error_hint"] = unsupported_target[1]
            return coverage_result

        line_coverage, line_counter_resolved = self._counter_percentage(method_nodes, "LINE")
        branch_coverage, branch_counter_resolved = self._counter_percentage(method_nodes, "BRANCH")
        if line_counter_resolved or branch_counter_resolved:
            coverage_result["coverage_method_counter_resolved"] = True
            coverage_result["coverage_percentage_source"] = "jacoco_xml_method_counter"
            coverage_result["line_coverage"] = line_coverage
            coverage_result["branch_coverage"] = branch_coverage

        method_ranges = []
        normalized_range = self._normalize_line_range(focal_method_range)
        if normalized_range is not None:
            method_ranges = [normalized_range]
            coverage_result["coverage_method_range_resolved"] = True
        elif target_context is not None and method_nodes:
            method_ranges = self._method_line_ranges(
                target_context["class_elem"],
                method_nodes,
                target_context["sourcefile_elem"],
            )
            coverage_result["coverage_method_range_resolved"] = bool(method_ranges)

        html_source_path = self._resolve_source_html_path(html_root, package_name, source_filename)
        if html_source_path and method_ranges:
            html_coverage = self._extract_html_line_coverage(html_source_path)
            uncovered_line_details = self._build_uncovered_line_details_from_html(html_coverage, method_ranges)
            coverage_result["coverage_handles_source"] = "jacoco_html_source_range"
            coverage_result["uncovered_line_details"] = uncovered_line_details
            coverage_result["uncovered_lines"] = [item["line"] for item in uncovered_line_details]

        if coverage_result["coverage_method_counter_resolved"] and coverage_result["coverage_handles_source"]:
            coverage_result["coverage_status"] = "coverage_valid"
            coverage_result["coverage_error_hint"] = ""
            return coverage_result

        if coverage_result["coverage_handles_source"]:
            coverage_result["coverage_status"] = "coverage_handles_only"
            coverage_result["coverage_error_hint"] = ""
            return coverage_result

        if coverage_result["coverage_file_line_coverage"] is not None or coverage_result["coverage_file_branch_coverage"] is not None:
            coverage_result["coverage_error_hint"] = "已获取文件级覆盖率，但未能稳定映射到焦点方法"

        if root is None and coverage_result["coverage_percentage_source"] == "":
            coverage_result["coverage_status"] = "coverage_parse_failed"
            coverage_result["coverage_error_hint"] = "JaCoCo XML 报告缺失或无法解析"

        return coverage_result

    def _load_jacoco_root(self) -> Optional[ET.Element]:
        report_path = self.get_jacoco_report_path()
        if not report_path or not report_path.exists():
            return None

        stat = report_path.stat()
        cache_key = (str(report_path.resolve()), stat.st_mtime_ns, stat.st_size)
        if self._jacoco_cache_key == cache_key and self._jacoco_cache_root is not None:
            return self._jacoco_cache_root

        tree = ET.parse(report_path)
        root = tree.getroot()
        self._jacoco_cache_key = cache_key
        self._jacoco_cache_root = root
        return root

    def _normalize_report_class_name(self, report_class_name: str) -> Dict[str, str]:
        dotted_name = report_class_name.replace("/", ".")
        return {
            "canonical": dotted_name,
            "normalized": dotted_name.replace("$", "."),
            "simple": dotted_name.split(".")[-1].replace("$", "."),
        }

    def _iter_jacoco_class_contexts(self, root: ET.Element) -> List[Dict[str, Any]]:
        contexts = []
        for package_elem in root.findall("package"):
            for class_elem in package_elem.findall("class"):
                report_class_name = class_elem.get("name", "")
                normalized = self._normalize_report_class_name(report_class_name)
                source_filename = class_elem.get("sourcefilename", "")
                sourcefile_elem = None
                if source_filename:
                    for candidate in package_elem.findall("sourcefile"):
                        if candidate.get("name") == source_filename:
                            sourcefile_elem = candidate
                            break
                contexts.append({
                    "class_elem": class_elem,
                    "sourcefile_elem": sourcefile_elem,
                    "canonical_class_name": normalized["canonical"],
                    "normalized_class_name": normalized["normalized"],
                    "simple_class_name": normalized["simple"],
                })
        return contexts

    def _find_class_contexts(self, root: ET.Element, class_fqn: str) -> List[Dict[str, Any]]:
        wanted = (class_fqn or "").strip()
        if not wanted:
            return []

        contexts = self._iter_jacoco_class_contexts(root)
        exact_matches = [
            context for context in contexts
            if wanted in {context["canonical_class_name"], context["normalized_class_name"]}
        ]
        if exact_matches:
            return exact_matches

        wanted_simple = wanted.split(".")[-1]
        simple_matches = [
            context for context in contexts
            if context["simple_class_name"] == wanted_simple
        ]
        return simple_matches if len(simple_matches) == 1 else []

    def _find_method_nodes(self, class_elem: ET.Element, method_name: str) -> List[ET.Element]:
        return [
            method_elem
            for method_elem in class_elem.findall("method")
            if method_elem.get("name") == method_name
        ]

    def _counter_totals(self, method_nodes: List[ET.Element], counter_type: str) -> Tuple[int, int]:
        missed = 0
        covered = 0
        for method_elem in method_nodes:
            for counter_elem in method_elem.findall("counter"):
                if counter_elem.get("type") != counter_type:
                    continue
                try:
                    missed += int(counter_elem.get("missed", "0") or 0)
                    covered += int(counter_elem.get("covered", "0") or 0)
                except ValueError:
                    continue
        return missed, covered

    def _percentage_from_counter(self, missed: int, covered: int, default_when_absent: float = 0.0) -> float:
        total = missed + covered
        if total <= 0:
            return default_when_absent
        return round((covered / total) * 100.0, 2)

    def _method_line_ranges(
        self,
        class_elem: ET.Element,
        method_nodes: List[ET.Element],
        sourcefile_elem: Optional[ET.Element],
    ) -> List[Tuple[int, int]]:
        if not method_nodes:
            return []

        method_starts = []
        for method_elem in class_elem.findall("method"):
            try:
                line_number = int(method_elem.get("line", "0") or 0)
            except ValueError:
                continue
            if line_number > 0:
                method_starts.append(line_number)
        method_starts = sorted(set(method_starts))

        max_source_line = 0
        if sourcefile_elem is not None:
            for line_elem in sourcefile_elem.findall("line"):
                try:
                    max_source_line = max(max_source_line, int(line_elem.get("nr", "0") or 0))
                except ValueError:
                    continue

        ranges = []
        for method_elem in method_nodes:
            try:
                start_line = int(method_elem.get("line", "0") or 0)
            except ValueError:
                continue
            if start_line <= 0:
                continue
            next_starts = [line for line in method_starts if line > start_line]
            end_line = next_starts[0] - 1 if next_starts else max_source_line
            if end_line < start_line:
                end_line = start_line
            ranges.append((start_line, end_line))
        return ranges

    def _build_uncovered_line_details(
        self,
        class_elem: ET.Element,
        method_nodes: List[ET.Element],
        sourcefile_elem: Optional[ET.Element],
    ) -> List[Dict[str, Any]]:
        if sourcefile_elem is None:
            return []

        ranges = self._method_line_ranges(class_elem, method_nodes, sourcefile_elem)
        if not ranges:
            return []

        details = []
        seen_lines = set()
        for line_elem in sourcefile_elem.findall("line"):
            try:
                line_number = int(line_elem.get("nr", "0") or 0)
                missed_instructions = int(line_elem.get("mi", "0") or 0)
                missed_branches = int(line_elem.get("mb", "0") or 0)
            except ValueError:
                continue

            if line_number in seen_lines:
                continue
            if not any(start <= line_number <= end for start, end in ranges):
                continue

            instruction_gap = missed_instructions > 0
            branch_gap = missed_branches > 0
            if not instruction_gap and not branch_gap:
                continue

            if instruction_gap and branch_gap:
                reason = "mixed"
            elif branch_gap:
                reason = "branch_gap"
            else:
                reason = "instruction_gap"

            details.append({
                "line": line_number,
                "instruction_gap": instruction_gap,
                "branch_gap": branch_gap,
                "reason": reason,
            })
            seen_lines.add(line_number)

        details.sort(key=lambda item: item["line"])
        return details

    def parse_jacoco_for_method(self, class_fqn: str, method_name: str) -> Dict[str, Any]:
        """Backward-compatible wrapper for focal-method coverage collection."""
        return self.collect_focal_method_coverage(class_fqn, method_name)
