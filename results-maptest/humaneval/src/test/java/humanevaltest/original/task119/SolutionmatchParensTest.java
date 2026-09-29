package humanevaltest.original.task119;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class SolutionmatchParensTest {
    @Test
    public void testMatchParens_ValidParentheses() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("()", "[]"));
        assertEquals("No", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_InvalidParentheses() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("(()", ")"));
        assertEquals("Yes", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_MixedParentheses() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("(", ")"));
        assertEquals("Yes", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_UnbalancedParentheses() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("(()", "())"));
        assertEquals("Yes", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_EmptyStrings() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("", ""));
        assertEquals("Yes", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_OneEmptyString() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("", "()"));
        assertEquals("Yes", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_MultipleBrackets() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("[]", "{}"));
        assertEquals("No", solution.matchParens(input));
    }

    @Test
    public void testMatchParens_MismatchedBrackets() {
        Solution solution = new Solution();
        List<String> input = new ArrayList<>(Arrays.asList("[]", "}"));
        assertEquals("No", solution.matchParens(input));
    }
}
