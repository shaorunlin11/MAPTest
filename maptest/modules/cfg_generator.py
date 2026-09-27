import tree_sitter_java as tsjava
from tree_sitter import Language, Parser
import networkx as nx
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Set, Union

JAVA_LANGUAGE = Language(tsjava.language())
parser = Parser(JAVA_LANGUAGE)


@dataclass
class CFGNode:
    node_type: str
    label: str
    start_line: int
    end_line: int
    status: str = "COVERED"


@dataclass
class FlowResult:
    normal_exits: List[int] = field(default_factory=list)
    exception_exits: List[int] = field(default_factory=list)
    return_exits: List[int] = field(default_factory=list)
    break_exits: List[int] = field(default_factory=list)
    continue_exits: List[int] = field(default_factory=list)

    @classmethod
    def from_normal(cls, nodes: List[int]):
        return cls(normal_exits=list(nodes))

    @classmethod
    def empty(cls):
        return cls()


class JavaCFGGenerator:
    def __init__(self, code: str = ""):
        self.code_bytes = b""
        self.tree = None
        self.graph = nx.DiGraph()
        self.compat_graph = nx.DiGraph()
        self.node_count = 0
        self.line_to_nodes: Dict[int, List[int]] = {}
        self.compat_line_to_nodes: Dict[int, List[int]] = {}
        self.break_stack = []
        self.continue_stack = []
        if code:
            self._load_code(code)

    def _load_code(self, code: str):
        self.code_bytes = bytes(code, "utf8")
        self.tree = parser.parse(self.code_bytes)
        self.graph = nx.DiGraph()
        self.compat_graph = nx.DiGraph()
        self.node_count = 0
        self.line_to_nodes = {}
        self.compat_line_to_nodes = {}
        self.break_stack = []
        self.continue_stack = []

    def _find_method_node(self):
        root = self.tree.root_node if self.tree else None
        if root is None:
            return None
        stack = [root]
        while stack:
            node = stack.pop()
            if node.type == "method_declaration":
                return node
            stack.extend(reversed(node.children))
        return None

    def generate(self, code: str) -> nx.DiGraph:
        self._load_code(code)
        method_node = self._find_method_node()
        if not method_node:
            return nx.DiGraph()
        self.generate_cfg(method_node)
        return self._build_compat_graph()

    def generate_cfg(self, method_node_or_code: Union[str, object]) -> nx.DiGraph:
        if isinstance(method_node_or_code, str):
            self._load_code(method_node_or_code)
            method_node = self._find_method_node()
            if not method_node:
                return self.graph
        else:
            method_node = method_node_or_code
            if self.tree is None:
                self.graph = nx.DiGraph()
                self.compat_graph = nx.DiGraph()
                self.node_count = 0
                self.line_to_nodes = {}
                self.compat_line_to_nodes = {}
                self.break_stack = []
                self.continue_stack = []

        start_line = method_node.start_point[0] + 1
        start_id = self._add_node("START", start_line, "Method entry")
        params = self._find_child_by_type(method_node, "formal_parameters")
        if params:
            param_id = self._add_node("PARAMS", start_line, f"Initialize parameters: {params.text.decode('utf-8')}")
            self.graph.add_edge(start_id, param_id)
            last_node = param_id
        else:
            last_node = start_id

        body = self._find_child_by_type(method_node, "block")
        final_flow = self._parse_statement(body, [last_node]) if body else FlowResult.from_normal([last_node])
        end_id = self._add_node("END", method_node.end_point[0] + 1, "Method exit")
        for previous in final_flow.normal_exits:
            self.graph.add_edge(previous, end_id)
        return self.graph

    def _add_node(self, node_type: str, line: int, description: str = "", synthetic: bool = False) -> int:
        node_id = self.node_count
        self.graph.add_node(
            node_id,
            type=node_type,
            line=line,
            description=description,
            synthetic=bool(synthetic),
        )
        if not synthetic:
            self.line_to_nodes.setdefault(line, []).append(node_id)
        self.node_count += 1
        return node_id

    @staticmethod
    def _dedupe_nodes(nodes: List[int]) -> List[int]:
        return list(dict.fromkeys(nodes))

    def _merge_flow_results(self, *results: FlowResult) -> FlowResult:
        merged = FlowResult.empty()
        for result in results:
            if not result:
                continue
            merged.normal_exits.extend(result.normal_exits)
            merged.exception_exits.extend(result.exception_exits)
            merged.return_exits.extend(result.return_exits)
            merged.break_exits.extend(result.break_exits)
            merged.continue_exits.extend(result.continue_exits)
        merged.normal_exits = self._dedupe_nodes(merged.normal_exits)
        merged.exception_exits = self._dedupe_nodes(merged.exception_exits)
        merged.return_exits = self._dedupe_nodes(merged.return_exits)
        merged.break_exits = self._dedupe_nodes(merged.break_exits)
        merged.continue_exits = self._dedupe_nodes(merged.continue_exits)
        return merged

    def _chain_flow(self, current: FlowResult, next_result: FlowResult) -> FlowResult:
        return FlowResult(
            normal_exits=self._dedupe_nodes(next_result.normal_exits),
            exception_exits=self._dedupe_nodes(current.exception_exits + next_result.exception_exits),
            return_exits=self._dedupe_nodes(current.return_exits + next_result.return_exits),
            break_exits=self._dedupe_nodes(current.break_exits + next_result.break_exits),
            continue_exits=self._dedupe_nodes(current.continue_exits + next_result.continue_exits),
        )

    def _parse_sequence(self, nodes: List[object], prev_nodes: List[int]) -> FlowResult:
        result = FlowResult.from_normal(prev_nodes)
        for child in nodes:
            if child is None or not result.normal_exits:
                continue
            result = self._chain_flow(result, self._parse_statement(child, result.normal_exits))
        return result

    def _parse_statement(self, node, prev_nodes: List[int]) -> FlowResult:
        if node is None or (not prev_nodes and node.type != "block"):
            return FlowResult.empty()

        node_type = node.type
        if self._is_trivial_expression(node):
            return FlowResult.from_normal(prev_nodes)
        if node_type == "block":
            current_exits = list(prev_nodes)
            collected = FlowResult.empty()
            for child in node.children:
                if not current_exits:
                    break
                if child.type.endswith("_statement") or child.type in {
                    "local_variable_declaration",
                    "if_statement",
                    "switch_statement",
                    "switch_expression",
                    "expression_statement",
                }:
                    child_result = self._parse_statement(child, current_exits)
                    collected = self._merge_flow_results(
                        collected,
                        FlowResult(
                            exception_exits=child_result.exception_exits,
                            return_exits=child_result.return_exits,
                            break_exits=child_result.break_exits,
                            continue_exits=child_result.continue_exits,
                        ),
                    )
                    current_exits = child_result.normal_exits
            collected.normal_exits = self._dedupe_nodes(current_exits)
            return collected

        if node_type == "expression_statement":
            return self._parse_statement(node.children[0], prev_nodes) if node.children else FlowResult.from_normal(prev_nodes)
        if node_type == "method_invocation":
            return self._handle_method_invocation(node, prev_nodes)
        if node_type == "lambda_expression":
            return self._handle_lambda(node, prev_nodes)
        if node_type == "try_with_resources_statement":
            return self._handle_try(node, prev_nodes)
        if node_type == "synchronized_statement":
            return self._handle_synchronized(node, prev_nodes)
        if node_type == "assert_statement":
            return self._handle_assert(node, prev_nodes)
        if node_type == "yield_statement":
            return self._handle_yield(node, prev_nodes)
        if node_type == "parenthesized_expression":
            return self._handle_parenthesized_expression(node, prev_nodes)
        if node_type == "local_variable_declaration":
            return self._handle_local_variable_declaration(node, prev_nodes)
        if node_type == "variable_declarator":
            return self._handle_variable_declarator(node, prev_nodes)
        if node_type == "resource_specification":
            return self._handle_resource_specification(node, prev_nodes)
        if node_type == "resource":
            return self._handle_resource(node, prev_nodes)
        if node_type == "assignment_expression":
            return self._handle_assignment_expression(node, prev_nodes)
        if node_type == "object_creation_expression":
            return self._handle_object_creation(node, prev_nodes)
        if node_type == "argument_list":
            return self._parse_sequence(list(node.named_children), prev_nodes)
        if node_type == "cast_expression":
            return self._handle_cast_expression(node, prev_nodes)
        if node_type == "array_access":
            return self._handle_array_access(node, prev_nodes)
        if node_type == "binary_expression":
            return self._handle_binary_expression(node, prev_nodes)
        if node_type == "ternary_expression":
            return self._handle_ternary_expression(node, prev_nodes)

        if node_type == "if_statement":
            return self._handle_if(node, prev_nodes)
        if node_type in {"switch_statement", "switch_expression"}:
            return self._handle_switch(node, prev_nodes)
        if node_type == "for_statement":
            return self._handle_for(node, prev_nodes)
        if node_type in {"while_statement", "enhanced_for_statement"}:
            return self._handle_loop_generic(node, prev_nodes)
        if node_type == "do_statement":
            return self._handle_do_while(node, prev_nodes)
        if node_type == "try_statement":
            return self._handle_try(node, prev_nodes)

        if node_type == "return_statement":
            expression_result = FlowResult.from_normal(prev_nodes)
            expression_node = None
            for child in node.children:
                if child.type not in {"return", ";"}:
                    expression_node = child
                    break
            if expression_node is not None:
                expression_result = self._parse_statement(expression_node, prev_nodes)
            return_node = self._add_node("RETURN", node.start_point[0] + 1, f"Return: {node.text.decode('utf-8').strip()[:50]}...")
            for previous in expression_result.normal_exits:
                self.graph.add_edge(previous, return_node)
            return self._merge_flow_results(
                FlowResult(
                    exception_exits=expression_result.exception_exits,
                    break_exits=expression_result.break_exits,
                    continue_exits=expression_result.continue_exits,
                ),
                FlowResult(return_exits=[return_node]),
            )

        if node_type == "throw_statement":
            thrown_value = next((child for child in node.named_children if child.type != "throw"), None)
            thrown_result = self._parse_statement(thrown_value, prev_nodes) if thrown_value is not None else FlowResult.from_normal(prev_nodes)
            throw_node = self._add_node("THROW", node.start_point[0] + 1, f"Throw: {node.text.decode('utf-8').strip()}")
            for previous in thrown_result.normal_exits:
                self.graph.add_edge(previous, throw_node)
            return self._merge_flow_results(
                FlowResult(
                    exception_exits=thrown_result.exception_exits,
                    return_exits=thrown_result.return_exits,
                    break_exits=thrown_result.break_exits,
                    continue_exits=thrown_result.continue_exits,
                ),
                FlowResult(exception_exits=[throw_node]),
            )

        if node_type == "break_statement":
            break_node = self._add_node("BREAK", node.start_point[0] + 1, "break")
            for previous in prev_nodes:
                self.graph.add_edge(previous, break_node)
            return FlowResult(break_exits=[break_node])

        if node_type == "continue_statement":
            continue_node = self._add_node("CONTINUE", node.start_point[0] + 1, "continue")
            for previous in prev_nodes:
                self.graph.add_edge(previous, continue_node)
            return FlowResult(continue_exits=[continue_node])

        line = node.start_point[0] + 1
        stmt_node = self._add_node("STMT", line, f"Execute: {node.text.decode('utf-8').strip()[:50]}...")
        for previous in prev_nodes:
            self.graph.add_edge(previous, stmt_node)
        if self._node_may_throw(node):
            return FlowResult(normal_exits=[stmt_node], exception_exits=[stmt_node])
        return FlowResult(normal_exits=[stmt_node])

    def _handle_if(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        cond = node.child_by_field_name("condition") or self._find_child_by_type(node, "parenthesized_expression")
        cond_text = cond.text.decode("utf-8") if cond else "condition"
        cond_result = self._parse_statement(cond, prev_nodes) if cond is not None else FlowResult.from_normal(prev_nodes)

        decision = self._add_node("IF_DECISION", line, f"IF condition: {cond_text}")
        for previous in cond_result.normal_exits:
            self.graph.add_edge(previous, decision)

        parts = self._split_if_parts(node)
        true_entry = self._add_node("IF_TRUE", line, f"Branch: {cond_text} is TRUE")
        false_entry = self._add_node("IF_FALSE", line, f"Branch: {cond_text} is FALSE")
        self.graph.add_edge(decision, true_entry)
        self.graph.add_edge(decision, false_entry)

        true_result = self._parse_statement(parts["consequent"], [true_entry]) if parts["consequent"] else FlowResult.from_normal([true_entry])
        false_result = self._parse_statement(parts["alternative"], [false_entry]) if parts["alternative"] else FlowResult.from_normal([false_entry])
        return self._merge_flow_results(
            FlowResult(
                exception_exits=cond_result.exception_exits,
                return_exits=cond_result.return_exits,
                break_exits=cond_result.break_exits,
                continue_exits=cond_result.continue_exits,
            ),
            true_result,
            false_result,
        )

    def _handle_parenthesized_expression(self, node, prev_nodes: List[int]) -> FlowResult:
        inner = next((child for child in node.named_children), None)
        if inner is None:
            return FlowResult.from_normal(prev_nodes)
        return self._parse_statement(inner, prev_nodes)

    def _handle_resource_specification(self, node, prev_nodes: List[int]) -> FlowResult:
        resources = [child for child in node.named_children if child.type == "resource"]
        return self._parse_sequence(resources, prev_nodes) if resources else FlowResult.from_normal(prev_nodes)

    def _handle_resource(self, node, prev_nodes: List[int]) -> FlowResult:
        value_node = node.child_by_field_name("value")
        value_result = self._parse_statement(value_node, prev_nodes) if value_node is not None else FlowResult.from_normal(prev_nodes)
        line = node.start_point[0] + 1
        resource_node = self._add_node("STMT", line, f"Acquire resource: {node.text.decode('utf-8').strip()[:80]}")
        for previous in value_result.normal_exits:
            self.graph.add_edge(previous, resource_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=value_result.exception_exits + [resource_node],
                return_exits=value_result.return_exits,
                break_exits=value_result.break_exits,
                continue_exits=value_result.continue_exits,
            ),
            FlowResult(normal_exits=[resource_node]),
        )

    def _handle_local_variable_declaration(self, node, prev_nodes: List[int]) -> FlowResult:
        declarators = [child for child in node.named_children if child.type == "variable_declarator"]
        if not declarators:
            line = node.start_point[0] + 1
            decl_node = self._add_node("STMT", line, f"Declare: {node.text.decode('utf-8').strip()[:80]}")
            for previous in prev_nodes:
                self.graph.add_edge(previous, decl_node)
            return FlowResult(normal_exits=[decl_node])
        return self._parse_sequence(declarators, prev_nodes)

    def _handle_variable_declarator(self, node, prev_nodes: List[int]) -> FlowResult:
        value_node = node.child_by_field_name("value")
        value_result = self._parse_statement(value_node, prev_nodes) if value_node is not None else FlowResult.from_normal(prev_nodes)
        line = node.start_point[0] + 1
        decl_node = self._add_node("STMT", line, f"Declare: {node.text.decode('utf-8').strip()[:80]}")
        for previous in value_result.normal_exits:
            self.graph.add_edge(previous, decl_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=value_result.exception_exits,
                return_exits=value_result.return_exits,
                break_exits=value_result.break_exits,
                continue_exits=value_result.continue_exits,
            ),
            FlowResult(normal_exits=[decl_node]),
        )

    def _handle_assignment_expression(self, node, prev_nodes: List[int]) -> FlowResult:
        flow = FlowResult.from_normal(prev_nodes)
        left_node = node.child_by_field_name("left")
        right_node = node.child_by_field_name("right")

        if left_node is not None and left_node.type not in {"identifier", "field_identifier", "this"}:
            flow = self._chain_flow(flow, self._parse_statement(left_node, flow.normal_exits))
        if right_node is not None:
            flow = self._chain_flow(flow, self._parse_statement(right_node, flow.normal_exits))

        line = node.start_point[0] + 1
        assign_node = self._add_node("STMT", line, f"Assign: {node.text.decode('utf-8').strip()[:80]}")
        for previous in flow.normal_exits:
            self.graph.add_edge(previous, assign_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=flow.exception_exits,
                return_exits=flow.return_exits,
                break_exits=flow.break_exits,
                continue_exits=flow.continue_exits,
            ),
            FlowResult(normal_exits=[assign_node]),
        )

    def _handle_object_creation(self, node, prev_nodes: List[int]) -> FlowResult:
        flow = FlowResult.from_normal(prev_nodes)
        receiver_node = node.child_by_field_name("object")
        if receiver_node is not None:
            flow = self._chain_flow(flow, self._parse_statement(receiver_node, flow.normal_exits))

        args = self._find_child_by_type(node, "argument_list")
        if args is not None:
            non_lambda_args = [child for child in args.named_children if child.type != "lambda_expression"]
            if non_lambda_args:
                flow = self._chain_flow(flow, self._parse_sequence(non_lambda_args, flow.normal_exits))

        line = node.start_point[0] + 1
        create_node = self._add_node("STMT", line, f"Instantiate: {node.text.decode('utf-8').strip()[:80]}")
        for previous in flow.normal_exits:
            self.graph.add_edge(previous, create_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=flow.exception_exits + [create_node],
                return_exits=flow.return_exits,
                break_exits=flow.break_exits,
                continue_exits=flow.continue_exits,
            ),
            FlowResult(normal_exits=[create_node]),
        )

    def _handle_cast_expression(self, node, prev_nodes: List[int]) -> FlowResult:
        value_node = node.child_by_field_name("value")
        value_result = self._parse_statement(value_node, prev_nodes) if value_node is not None else FlowResult.from_normal(prev_nodes)
        line = node.start_point[0] + 1
        cast_node = self._add_node("STMT", line, f"Cast: {node.text.decode('utf-8').strip()[:80]}")
        for previous in value_result.normal_exits:
            self.graph.add_edge(previous, cast_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=value_result.exception_exits,
                return_exits=value_result.return_exits,
                break_exits=value_result.break_exits,
                continue_exits=value_result.continue_exits,
            ),
            FlowResult(normal_exits=[cast_node]),
        )

    def _handle_array_access(self, node, prev_nodes: List[int]) -> FlowResult:
        flow = FlowResult.from_normal(prev_nodes)
        index_node = node.child_by_field_name("index")
        array_node = next((child for child in node.named_children if not self._same_node(child, index_node)), None)

        if array_node is not None and array_node.type not in {"identifier", "field_identifier", "this", "super"}:
            flow = self._chain_flow(flow, self._parse_statement(array_node, flow.normal_exits))
        if index_node is not None:
            flow = self._chain_flow(flow, self._parse_statement(index_node, flow.normal_exits))

        line = node.start_point[0] + 1
        access_node = self._add_node("STMT", line, f"Access: {node.text.decode('utf-8').strip()[:80]}")
        for previous in flow.normal_exits:
            self.graph.add_edge(previous, access_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=flow.exception_exits,
                return_exits=flow.return_exits,
                break_exits=flow.break_exits,
                continue_exits=flow.continue_exits,
            ),
            FlowResult(normal_exits=[access_node]),
        )

    def _handle_binary_expression(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        left_node = node.child_by_field_name("left")
        right_node = node.child_by_field_name("right")
        operator = next((child.type for child in node.children if not child.is_named), "")

        if operator in {"&&", "||"} and left_node is not None and right_node is not None:
            left_result = self._parse_statement(left_node, prev_nodes)
            decision_node = self._add_node("STMT", line, f"Evaluate: {node.text.decode('utf-8').strip()[:80]}")
            for previous in left_result.normal_exits:
                self.graph.add_edge(previous, decision_node)

            skip_node = self._add_node("STMT", line, f"Short-circuit: {operator}", synthetic=True)
            self.graph.add_edge(decision_node, skip_node)
            right_result = self._parse_statement(right_node, [decision_node])
            return self._merge_flow_results(
                FlowResult(
                    normal_exits=[skip_node],
                    exception_exits=left_result.exception_exits,
                    return_exits=left_result.return_exits,
                    break_exits=left_result.break_exits,
                    continue_exits=left_result.continue_exits,
                ),
                right_result,
            )

        flow = FlowResult.from_normal(prev_nodes)
        for child in (left_node, right_node):
            if child is not None:
                flow = self._chain_flow(flow, self._parse_statement(child, flow.normal_exits))

        expr_node = self._add_node("STMT", line, f"Evaluate: {node.text.decode('utf-8').strip()[:80]}")
        for previous in flow.normal_exits:
            self.graph.add_edge(previous, expr_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=flow.exception_exits,
                return_exits=flow.return_exits,
                break_exits=flow.break_exits,
                continue_exits=flow.continue_exits,
            ),
            FlowResult(normal_exits=[expr_node]),
        )

    def _handle_ternary_expression(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        condition_node = node.child_by_field_name("condition")
        consequence_node = node.child_by_field_name("consequence")
        alternative_node = node.child_by_field_name("alternative")

        condition_result = self._parse_statement(condition_node, prev_nodes) if condition_node is not None else FlowResult.from_normal(prev_nodes)
        decision_node = self._add_node("STMT", line, f"Evaluate: {node.text.decode('utf-8').strip()[:80]}")
        for previous in condition_result.normal_exits:
            self.graph.add_edge(previous, decision_node)

        consequence_result = self._parse_statement(consequence_node, [decision_node]) if consequence_node is not None else FlowResult.from_normal([decision_node])
        alternative_result = self._parse_statement(alternative_node, [decision_node]) if alternative_node is not None else FlowResult.from_normal([decision_node])
        return self._merge_flow_results(
            FlowResult(
                exception_exits=condition_result.exception_exits,
                return_exits=condition_result.return_exits,
                break_exits=condition_result.break_exits,
                continue_exits=condition_result.continue_exits,
            ),
            consequence_result,
            alternative_result,
        )

    def _handle_synchronized(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        monitor_node = node.child_by_field_name("condition") or self._find_child_by_type(node, "parenthesized_expression")
        monitor_text = monitor_node.text.decode("utf-8").strip() if monitor_node is not None else "monitor"
        monitor_result = self._parse_statement(monitor_node, prev_nodes) if monitor_node is not None else FlowResult.from_normal(prev_nodes)

        enter_node = self._add_node("STMT", line, f"Enter synchronized: {monitor_text[:80]}")
        for previous in monitor_result.normal_exits:
            self.graph.add_edge(previous, enter_node)

        body = node.child_by_field_name("body") or self._find_child_by_type(node, "block")
        body_result = self._parse_statement(body, [enter_node]) if body is not None else FlowResult.from_normal([enter_node])
        return self._merge_flow_results(
            FlowResult(
                exception_exits=monitor_result.exception_exits,
                return_exits=monitor_result.return_exits,
                break_exits=monitor_result.break_exits,
                continue_exits=monitor_result.continue_exits,
            ),
            body_result,
        )

    def _handle_assert(self, node, prev_nodes: List[int]) -> FlowResult:
        parts = list(node.named_children)
        flow = self._parse_sequence(parts, prev_nodes) if parts else FlowResult.from_normal(prev_nodes)
        line = node.start_point[0] + 1
        assert_node = self._add_node("STMT", line, f"Assert: {node.text.decode('utf-8').strip()[:80]}")
        for previous in flow.normal_exits:
            self.graph.add_edge(previous, assert_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=flow.exception_exits,
                return_exits=flow.return_exits,
                break_exits=flow.break_exits,
                continue_exits=flow.continue_exits,
            ),
            FlowResult(normal_exits=[assert_node]),
        )

    def _handle_yield(self, node, prev_nodes: List[int]) -> FlowResult:
        value_node = next(iter(node.named_children), None)
        value_result = self._parse_statement(value_node, prev_nodes) if value_node is not None else FlowResult.from_normal(prev_nodes)
        line = node.start_point[0] + 1
        yield_node = self._add_node("STMT", line, f"Yield: {node.text.decode('utf-8').strip()[:80]}")
        for previous in value_result.normal_exits:
            self.graph.add_edge(previous, yield_node)
        return self._merge_flow_results(
            FlowResult(
                exception_exits=value_result.exception_exits,
                return_exits=value_result.return_exits,
                break_exits=value_result.break_exits,
                continue_exits=value_result.continue_exits,
            ),
            FlowResult(normal_exits=[yield_node]),
        )

    def _handle_for(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        components = self._extract_for_components(node)
        init_result = self._parse_sequence(components["init"], prev_nodes) if components["init"] else FlowResult.from_normal(prev_nodes)
        update_result = FlowResult.empty()

        init_node = self._add_node("FOR_INIT", line, "For initialization")
        for previous in init_result.normal_exits:
            self.graph.add_edge(previous, init_node)

        cond_entry = self._add_node("FOR_COND_ENTRY", line, "For condition entry", synthetic=True)
        self.graph.add_edge(init_node, cond_entry)
        condition_result = self._parse_statement(components["condition"], [cond_entry]) if components["condition"] is not None else FlowResult.from_normal([cond_entry])

        head_node = self._add_node("FOR_HEAD", line, "For condition")
        exit_node = self._add_node("FOR_EXIT", line, "For exit")
        for previous in condition_result.normal_exits:
            self.graph.add_edge(previous, head_node)

        body_result = FlowResult.empty()
        body = components["body"]
        if body:
            body_result = self._parse_statement(body, [head_node])
            update_sources = self._dedupe_nodes(body_result.normal_exits + body_result.continue_exits)
            if components["update"]:
                update_result = self._parse_sequence(components["update"], update_sources)
                update_node = self._add_node("FOR_UPDATE", line, "For update")
                for previous in update_result.normal_exits:
                    self.graph.add_edge(previous, update_node)
                self.graph.add_edge(update_node, cond_entry)
            else:
                for body_exit in update_sources:
                    self.graph.add_edge(body_exit, cond_entry)
            for break_exit in body_result.break_exits:
                self.graph.add_edge(break_exit, exit_node)
        elif components["update"]:
            update_result = self._parse_sequence(components["update"], [])
        else:
            update_result = FlowResult.empty()

        self.graph.add_edge(head_node, exit_node)
        return FlowResult(
            normal_exits=[exit_node],
            exception_exits=self._dedupe_nodes(
                init_result.exception_exits +
                condition_result.exception_exits +
                body_result.exception_exits +
                update_result.exception_exits
            ),
            return_exits=self._dedupe_nodes(
                init_result.return_exits +
                condition_result.return_exits +
                body_result.return_exits +
                update_result.return_exits
            ),
            break_exits=self._dedupe_nodes(
                init_result.break_exits +
                condition_result.break_exits +
                update_result.break_exits
            ),
            continue_exits=self._dedupe_nodes(
                init_result.continue_exits +
                condition_result.continue_exits +
                update_result.continue_exits
            ),
        )

    def _handle_switch(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        selector_node = node.child_by_field_name("condition") or self._find_child_by_type(node, "parenthesized_expression")
        selector_result = self._parse_statement(selector_node, prev_nodes) if selector_node is not None else FlowResult.from_normal(prev_nodes)
        head_node = self._add_node("SWITCH_HEAD", line, "Switch dispatch")
        exit_node = self._add_node("SWITCH_EXIT", line, "Switch exit")
        for previous in selector_result.normal_exits:
            self.graph.add_edge(previous, head_node)

        collected = FlowResult(
            exception_exits=selector_result.exception_exits,
            return_exits=selector_result.return_exits,
            break_exits=selector_result.break_exits,
            continue_exits=selector_result.continue_exits,
        )
        last_case_exits: List[int] = []
        block = self._find_child_by_type(node, "switch_block")
        if block:
            for child in block.children:
                if child.type == "switch_rule":
                    label_node = self._find_child_by_type(child, "switch_label")
                    case_node = self._create_case_node(label_node, head_node, [])
                    rule_body = next((grandchild for grandchild in child.named_children if grandchild.type != "switch_label"), None)
                    rule_result = self._parse_statement(rule_body, [case_node]) if rule_body is not None else FlowResult.from_normal([case_node])
                    for normal_exit in rule_result.normal_exits:
                        self.graph.add_edge(normal_exit, exit_node)
                    for break_exit in rule_result.break_exits:
                        self.graph.add_edge(break_exit, exit_node)
                    collected = self._merge_flow_results(
                        collected,
                        FlowResult(
                            exception_exits=rule_result.exception_exits,
                            return_exits=rule_result.return_exits,
                            continue_exits=rule_result.continue_exits,
                        ),
                    )
                    continue

                if child.type == "switch_label":
                    case_node = self._create_case_node(child, head_node, last_case_exits)
                    last_case_exits = [case_node]
                    continue

                if child.type == "switch_block_statement_group":
                    group_exits = list(last_case_exits)
                    for group_child in child.children:
                        if group_child.type == "switch_label":
                            case_node = self._create_case_node(group_child, head_node, last_case_exits)
                            group_exits = [case_node]
                            continue
                        if group_child.type in {":", "{", "}"}:
                            continue
                        if group_child.type.endswith("_statement") or group_child.type == "block":
                            group_result = self._parse_statement(group_child, group_exits)
                            for break_exit in group_result.break_exits:
                                self.graph.add_edge(break_exit, exit_node)
                            collected = self._merge_flow_results(
                                collected,
                                FlowResult(
                                    exception_exits=group_result.exception_exits,
                                    return_exits=group_result.return_exits,
                                    continue_exits=group_result.continue_exits,
                                ),
                            )
                            group_exits = group_result.normal_exits
                    last_case_exits = group_exits
                    continue

                if child.type.endswith("_statement") or child.type == "block":
                    child_result = self._parse_statement(child, last_case_exits)
                    for break_exit in child_result.break_exits:
                        self.graph.add_edge(break_exit, exit_node)
                    collected = self._merge_flow_results(
                        collected,
                        FlowResult(
                            exception_exits=child_result.exception_exits,
                            return_exits=child_result.return_exits,
                            continue_exits=child_result.continue_exits,
                        ),
                    )
                    last_case_exits = child_result.normal_exits

        self.graph.add_edge(head_node, exit_node)
        collected.normal_exits = self._dedupe_nodes(last_case_exits + [exit_node])
        return collected

    def _handle_try(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        resource_spec = self._find_child_by_type(node, "resource_specification")
        resource_result = self._parse_statement(resource_spec, prev_nodes) if resource_spec is not None else FlowResult.from_normal(prev_nodes)

        try_head = self._add_node("TRY_START", line, "Try block entry")
        for previous in resource_result.normal_exits:
            self.graph.add_edge(previous, try_head)

        try_body = self._find_child_by_type(node, "block")
        body_result = self._parse_statement(try_body, [try_head]) if try_body else FlowResult.from_normal([try_head])
        try_result = self._merge_flow_results(
            FlowResult(
                normal_exits=body_result.normal_exits,
                exception_exits=resource_result.exception_exits,
                return_exits=resource_result.return_exits,
                break_exits=resource_result.break_exits,
                continue_exits=resource_result.continue_exits,
            ),
            body_result,
        )

        catch_clauses = [child for child in node.children if child.type == "catch_clause"]
        catch_results: List[FlowResult] = []
        for catch_clause in catch_clauses:
            catch_head = self._add_node("CATCH_HEAD", catch_clause.start_point[0] + 1, "Catch handler")
            for exception_source in try_result.exception_exits:
                self.graph.add_edge(exception_source, catch_head)
            catch_body = self._find_child_by_type(catch_clause, "block")
            catch_results.append(self._parse_statement(catch_body, [catch_head]) if catch_body else FlowResult.from_normal([catch_head]))

        merged_catches = self._merge_flow_results(*catch_results)
        combined = FlowResult(
            normal_exits=self._dedupe_nodes(try_result.normal_exits + merged_catches.normal_exits),
            exception_exits=self._dedupe_nodes(([] if catch_clauses else try_result.exception_exits) + merged_catches.exception_exits),
            return_exits=self._dedupe_nodes(try_result.return_exits + merged_catches.return_exits),
            break_exits=self._dedupe_nodes(try_result.break_exits + merged_catches.break_exits),
            continue_exits=self._dedupe_nodes(try_result.continue_exits + merged_catches.continue_exits),
        )

        finally_clause = next((child for child in node.children if child.type == "finally_clause"), None)
        if finally_clause is None:
            return combined
        return self._route_flow_through_finally(combined, finally_clause)

    def _route_flow_through_finally(self, incoming: FlowResult, finally_clause) -> FlowResult:
        return self._merge_flow_results(
            self._apply_finally_clause(incoming.normal_exits, "normal", finally_clause),
            self._apply_finally_clause(incoming.exception_exits, "exception", finally_clause),
            self._apply_finally_clause(incoming.return_exits, "return", finally_clause),
            self._apply_finally_clause(incoming.break_exits, "break", finally_clause),
            self._apply_finally_clause(incoming.continue_exits, "continue", finally_clause),
        )

    def _apply_finally_clause(self, source_nodes: List[int], continuation_kind: str, finally_clause) -> FlowResult:
        if not source_nodes:
            return FlowResult.empty()

        line = finally_clause.start_point[0] + 1
        finally_head = self._add_node("FINALLY", line, "Finally block")
        for source_node in self._dedupe_nodes(source_nodes):
            self.graph.add_edge(source_node, finally_head)

        finally_body = self._find_child_by_type(finally_clause, "block")
        finally_result = self._parse_statement(finally_body, [finally_head]) if finally_body else FlowResult.from_normal([finally_head])
        routed = FlowResult(
            exception_exits=finally_result.exception_exits,
            return_exits=finally_result.return_exits,
            break_exits=finally_result.break_exits,
            continue_exits=finally_result.continue_exits,
        )
        if not finally_result.normal_exits:
            return routed

        if continuation_kind == "normal":
            routed.normal_exits = finally_result.normal_exits
            return routed

        proxy_type, field_name, description = {
            "exception": ("THROW", "exception_exits", "Propagate exception after finally"),
            "return": ("RETURN", "return_exits", "Return after finally"),
            "break": ("BREAK", "break_exits", "Break after finally"),
            "continue": ("CONTINUE", "continue_exits", "Continue after finally"),
        }[continuation_kind]
        proxy_node = self._add_node(proxy_type, line, description, synthetic=True)
        for normal_exit in finally_result.normal_exits:
            self.graph.add_edge(normal_exit, proxy_node)
        setattr(routed, field_name, self._dedupe_nodes(getattr(routed, field_name) + [proxy_node]))
        return routed

    def _handle_loop_generic(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        if node.type == "enhanced_for_statement":
            iterable_node = node.child_by_field_name("value")
            iterable_result = self._parse_statement(iterable_node, prev_nodes) if iterable_node is not None else FlowResult.from_normal(prev_nodes)
            head_inputs = iterable_result.normal_exits
            carried_exception_exits = iterable_result.exception_exits
            carried_return_exits = iterable_result.return_exits
            carried_break_exits = iterable_result.break_exits
            carried_continue_exits = iterable_result.continue_exits
        else:
            cond_entry = self._add_node("LOOP_COND_ENTRY", line, "Loop condition entry", synthetic=True)
            for previous in prev_nodes:
                self.graph.add_edge(previous, cond_entry)
            condition_node = node.child_by_field_name("condition") or self._find_child_by_type(node, "parenthesized_expression")
            condition_result = self._parse_statement(condition_node, [cond_entry]) if condition_node is not None else FlowResult.from_normal([cond_entry])
            head_inputs = condition_result.normal_exits
            carried_exception_exits = condition_result.exception_exits
            carried_return_exits = condition_result.return_exits
            carried_break_exits = condition_result.break_exits
            carried_continue_exits = condition_result.continue_exits

        head_node = self._add_node("LOOP_HEAD", line, f"Loop condition: {node.type}")
        exit_node = self._add_node("LOOP_EXIT", line, "Loop exit")
        for previous in head_inputs:
            self.graph.add_edge(previous, head_node)

        body_result = FlowResult.empty()
        body = node.child_by_field_name("body") or self._find_body_in_container(node)
        if body:
            body_result = self._parse_statement(body, [head_node])
            for body_exit in self._dedupe_nodes(body_result.normal_exits + body_result.continue_exits):
                if node.type == "enhanced_for_statement":
                    self.graph.add_edge(body_exit, head_node)
                else:
                    self.graph.add_edge(body_exit, cond_entry)
            for break_exit in body_result.break_exits:
                self.graph.add_edge(break_exit, exit_node)

        self.graph.add_edge(head_node, exit_node)
        return FlowResult(
            normal_exits=[exit_node],
            exception_exits=self._dedupe_nodes(carried_exception_exits + body_result.exception_exits),
            return_exits=self._dedupe_nodes(carried_return_exits + body_result.return_exits),
            break_exits=self._dedupe_nodes(carried_break_exits),
            continue_exits=self._dedupe_nodes(carried_continue_exits),
        )

    def _handle_do_while(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        body = node.child_by_field_name("body") or self._find_body_in_container(node)
        body_line = body.start_point[0] + 1 if body is not None else line
        body_entry = self._add_node("DO_BODY_ENTRY", body_line, "Do-while body entry", synthetic=True)
        for previous in prev_nodes:
            self.graph.add_edge(previous, body_entry)

        body_result = self._parse_statement(body, [body_entry]) if body is not None else FlowResult.from_normal([body_entry])
        cond_entry = self._add_node("DO_COND_ENTRY", line, "Do-while condition entry", synthetic=True)
        for body_exit in self._dedupe_nodes(body_result.normal_exits + body_result.continue_exits):
            self.graph.add_edge(body_exit, cond_entry)

        condition_node = node.child_by_field_name("condition") or self._find_child_by_type(node, "parenthesized_expression")
        condition_result = self._parse_statement(condition_node, [cond_entry]) if condition_node is not None else FlowResult.from_normal([cond_entry])
        head_node = self._add_node("LOOP_HEAD", line, f"Loop condition: {node.type}")
        exit_node = self._add_node("LOOP_EXIT", line, "Loop exit")

        for break_exit in body_result.break_exits:
            self.graph.add_edge(break_exit, exit_node)
        for previous in condition_result.normal_exits:
            self.graph.add_edge(previous, head_node)
        self.graph.add_edge(head_node, body_entry)
        self.graph.add_edge(head_node, exit_node)

        return FlowResult(
            normal_exits=[exit_node],
            exception_exits=self._dedupe_nodes(body_result.exception_exits + condition_result.exception_exits),
            return_exits=self._dedupe_nodes(body_result.return_exits + condition_result.return_exits),
        )

    def _handle_method_invocation(self, node, prev_nodes: List[int]) -> FlowResult:
        current_result = FlowResult.from_normal(prev_nodes)

        receiver_node = node.child_by_field_name("object")
        if receiver_node is not None:
            current_result = self._chain_flow(current_result, self._parse_statement(receiver_node, current_result.normal_exits))

        args = self._find_child_by_type(node, "argument_list")
        lambda_args = []
        if args is not None:
            non_lambda_args = []
            for child in args.named_children:
                if child.type == "lambda_expression":
                    lambda_args.append(child)
                else:
                    non_lambda_args.append(child)
            if non_lambda_args:
                current_result = self._chain_flow(current_result, self._parse_sequence(non_lambda_args, current_result.normal_exits))

        line = node.start_point[0] + 1
        name_node = self._find_child_by_type(node, "identifier")
        function_name = name_node.text.decode("utf-8") if name_node else "method_call"
        invocation_text = node.text.decode("utf-8").strip().replace("\n", " ")
        invocation_node = self._add_node("STMT", line, f"Invoke: {function_name} | {invocation_text[:80]}")
        for previous in current_result.normal_exits:
            self.graph.add_edge(previous, invocation_node)

        invocation_result = FlowResult(
            normal_exits=[invocation_node],
            exception_exits=self._dedupe_nodes(current_result.exception_exits + [invocation_node]),
            return_exits=current_result.return_exits,
            break_exits=current_result.break_exits,
            continue_exits=current_result.continue_exits,
        )

        if not lambda_args:
            return invocation_result

        lambda_flow = FlowResult.from_normal(invocation_result.normal_exits)
        abrupt_flow = FlowResult(
            exception_exits=invocation_result.exception_exits,
            return_exits=invocation_result.return_exits,
            break_exits=invocation_result.break_exits,
            continue_exits=invocation_result.continue_exits,
        )
        for child in lambda_args:
            lambda_result = self._handle_lambda(child, lambda_flow.normal_exits)
            abrupt_flow = self._merge_flow_results(
                abrupt_flow,
                FlowResult(
                    exception_exits=lambda_result.exception_exits,
                    return_exits=lambda_result.return_exits,
                    break_exits=lambda_result.break_exits,
                    continue_exits=lambda_result.continue_exits,
                ),
            )
            lambda_flow = FlowResult.from_normal(lambda_result.normal_exits)

        abrupt_flow.normal_exits = lambda_flow.normal_exits
        return abrupt_flow

    def _handle_lambda(self, node, prev_nodes: List[int]) -> FlowResult:
        line = node.start_point[0] + 1
        lambda_entry = self._add_node("LAMBDA_ENTRY", line, "Lambda/Stream Execution Start")
        for previous in prev_nodes:
            self.graph.add_edge(previous, lambda_entry)

        body = node.children[-1]
        if body.type == "block":
            return self._parse_statement(body, [lambda_entry])

        body_node = self._add_node("STMT", body.start_point[0] + 1, f"Lambda Expr: {body.text.decode('utf-8')[:50]}...")
        self.graph.add_edge(lambda_entry, body_node)
        if self._node_may_throw(body):
            return FlowResult(normal_exits=[body_node], exception_exits=[body_node])
        return FlowResult(normal_exits=[body_node])

    def _node_may_throw(self, node) -> bool:
        stack = [node]
        while stack:
            current = stack.pop()
            if current.type in {"method_invocation", "object_creation_expression", "explicit_constructor_invocation"}:
                return True
            stack.extend(reversed(current.children))
        return False

    def _create_case_node(self, label_node, head_node: int, previous_case_exits: List[int]) -> int:
        label_text = label_node.text.decode("utf-8")
        description = f"Default: {label_text}" if label_text.strip().startswith("default") else f"Case: {label_text}"
        case_node = self._add_node("CASE", label_node.start_point[0] + 1, description)
        self.graph.add_edge(head_node, case_node)
        for previous in previous_case_exits:
            self.graph.add_edge(previous, case_node)
        return case_node

    def _find_child_by_type(self, node, node_type: str):
        for child in node.children:
            if child.type == node_type:
                return child
        return None

    @staticmethod
    def _is_trivial_expression(node) -> bool:
        if node is None:
            return True
        trivial_types = {
            "identifier",
            "field_identifier",
            "type_identifier",
            "integral_type",
            "floating_point_type",
            "void_type",
            "boolean_type",
            "this",
            "super",
            "true",
            "false",
        }
        return node.type in trivial_types or node.type.endswith("_literal")

    @staticmethod
    def _same_node(left, right) -> bool:
        if left is None or right is None:
            return left is right
        return (
            left.type == right.type and
            left.start_byte == right.start_byte and
            left.end_byte == right.end_byte
        )

    def _find_body_in_container(self, node):
        for child in node.children:
            if child.type == "block" or child.type.endswith("_statement"):
                return child
        return None

    def _extract_for_components(self, node):
        body = node.child_by_field_name("body") or self._find_body_in_container(node)
        condition = node.child_by_field_name("condition")
        update = node.child_by_field_name("update")

        init_nodes = []
        for child in node.named_children:
            if self._same_node(child, body) or self._same_node(child, condition) or self._same_node(child, update):
                continue
            init_nodes.append(child)

        return {
            "init": init_nodes,
            "condition": condition,
            "update": [update] if update is not None else [],
            "body": body,
        }

    def _split_if_parts(self, node):
        return {
            "consequent": node.child_by_field_name("consequence"),
            "alternative": node.child_by_field_name("alternative"),
        }

    def _node_query_priority(self, node_id: int) -> int:
        node_data = self.graph.nodes[node_id]
        node_type = node_data["type"]
        description = node_data.get("description", "")
        priority = {
            "THROW": 100,
            "RETURN": 100,
            "CATCH_HEAD": 80,
            "FINALLY": 80,
            "CASE": 75,
            "IF_TRUE": 70,
            "IF_FALSE": 70,
            "IF_DECISION": 60,
            "FOR_HEAD": 60,
            "LOOP_HEAD": 60,
            "SWITCH_HEAD": 60,
            "FOR_INIT": 55,
            "FOR_UPDATE": 55,
            "TRY_START": 50,
            "LAMBDA_ENTRY": 45,
            "START": 10,
            "PARAMS": 10,
            "END": 0,
        }
        if node_type == "STMT":
            if description.startswith("Invoke:"):
                return 95
            if description.startswith(("Assign:", "Declare:", "Instantiate:", "Acquire resource:", "Yield:", "Throw:", "Return:")):
                return 93
            if description.startswith("Evaluate:"):
                return 85
            if description.startswith("Execute:"):
                return 70
            return 90
        return priority.get(node_type, 40)

    def _find_best_path(self, graph: nx.DiGraph, source: int, candidates: List[int]) -> Optional[List[int]]:
        best_path = None
        best_score = None
        for candidate in candidates:
            try:
                path = nx.shortest_path(graph, source=source, target=candidate)
            except Exception:
                continue
            score = (self._node_query_priority(candidate), -len(path))
            if best_score is None or score > best_score:
                best_score = score
                best_path = path
        return best_path

    def get_path_to_uncovered_line(self, line: int) -> List[str]:
        candidates = self.line_to_nodes.get(line)
        if not candidates:
            return [f"Line {line} is unreachable or has no executable node."]
        source = 0 if 0 in self.graph.nodes else list(self.graph.nodes)[0]
        path = self._find_best_path(self.graph, source, candidates)
        if not path:
            return ["Path analysis failed."]
        return [f"Step: {self.graph.nodes[node_id]['description']} (L{self.graph.nodes[node_id]['line']})" for node_id in path]

    def _to_compat_node_type(self, internal_type: str) -> str:
        if internal_type in {"IF_DECISION", "FOR_HEAD", "LOOP_HEAD", "SWITCH_HEAD", "CASE"}:
            return "condition"
        if internal_type in {"BREAK", "CONTINUE"}:
            return "jump"
        if internal_type in {"START", "END", "PARAMS"}:
            return "entry"
        return "statement"

    def _to_compat_label(self, internal_type: str, description: str) -> str:
        if internal_type == "IF_DECISION":
            return description
        if internal_type == "FOR_HEAD":
            return "FOR_COND"
        if internal_type == "LOOP_HEAD":
            if "while_statement" in description:
                return "WHILE_COND"
            if "do_statement" in description:
                return "DO_WHILE_COND"
            return "LOOP_COND"
        if internal_type == "SWITCH_HEAD":
            return "SWITCH"
        if internal_type == "CASE":
            return description
        if internal_type == "BREAK":
            return "break"
        if internal_type == "CONTINUE":
            return "continue"
        return description

    def _build_compat_graph(self) -> nx.DiGraph:
        self.compat_graph = nx.DiGraph()
        self.compat_line_to_nodes = {}

        for node_id, data in self.graph.nodes(data=True):
            line = data["line"]
            compat_node = CFGNode(
                node_type=self._to_compat_node_type(data["type"]),
                label=self._to_compat_label(data["type"], data["description"]),
                start_line=line,
                end_line=line,
            )
            self.compat_graph.add_node(node_id, data=compat_node)
            if not data.get("synthetic", False):
                self.compat_line_to_nodes.setdefault(line, []).append(node_id)

        for source, target in self.graph.edges():
            label = None
            source_type = self.graph.nodes[source]["type"]
            target_type = self.graph.nodes[target]["type"]
            target_desc = self.graph.nodes[target]["description"]
            if source_type == "IF_DECISION":
                if target_type == "IF_TRUE" or "TRUE" in target_desc:
                    label = "True"
                elif target_type == "IF_FALSE" or "FALSE" in target_desc:
                    label = "False"
            elif source_type in {"FOR_HEAD", "LOOP_HEAD"}:
                label = "False" if target_type in {"FOR_EXIT", "LOOP_EXIT"} else "True"
            self.compat_graph.add_edge(source, target, label=label)
        return self.compat_graph

    def add_coverage_mapping(self, uncovered_lines: Set[int]):
        if not self.compat_graph.nodes:
            self._build_compat_graph()
        for line in uncovered_lines:
            for node_id in self.compat_line_to_nodes.get(line, []):
                self.compat_graph.nodes[node_id]["data"].status = "UNCOVERED"

    def get_path_to_uncovered_node(self, line: int) -> List[str]:
        if not self.compat_graph.nodes:
            self._build_compat_graph()
        candidates = self.compat_line_to_nodes.get(line)
        if not candidates:
            return [f"Line {line} is unreachable."]

        source = 0 if 0 in self.compat_graph.nodes else list(self.compat_graph.nodes)[0]
        path = self._find_best_path(self.compat_graph, source, candidates)
        if not path:
            return ["Path analysis failed."]

        steps: List[str] = []
        for index, node_id in enumerate(path):
            node_data: CFGNode = self.compat_graph.nodes[node_id]["data"]
            if index == 0:
                steps.append(f"{node_data.label} (L{node_data.start_line})")
                continue
            previous = path[index - 1]
            previous_data: CFGNode = self.compat_graph.nodes[previous]["data"]
            edge_label = self.compat_graph.edges[previous, node_id].get("label")
            if edge_label:
                steps.append(f"{previous_data.label} -> {node_data.label} [{edge_label}] (L{node_data.start_line})")
            else:
                steps.append(f"{node_data.label} (L{node_data.start_line})")
        return steps
