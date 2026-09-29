package humanevaltest.original.task61;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioncorrectBracketing_f10c5853_127Test {
    @Test
    public void testEmptyString() {
        Solution solution = new Solution();
        assertTrue(solution.correctBracketing(""));
    }

    @Test
    public void testBalancedBrackets() {
        Solution solution = new Solution();
        assertTrue(solution.correctBracketing("()"));
        assertTrue(solution.correctBracketing("(())"));
        assertTrue(solution.correctBracketing("(()())"));
    }

    @Test
    public void testUnbalancedBrackets() {
        Solution solution = new Solution();
        assertFalse(solution.correctBracketing("("));
        assertFalse(solution.correctBracketing(")"));
        assertFalse(solution.correctBracketing("(()"));
        assertFalse(solution.correctBracketing("())"));
        assertFalse(solution.correctBracketing("(()))"));
    }

    @Test
    public void testMixedBrackets() {
        Solution solution = new Solution();
        assertFalse(solution.correctBracketing("(()())("));
        assertFalse(solution.correctBracketing("())("));
    }
}
