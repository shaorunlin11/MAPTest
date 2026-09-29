package humanevaltest.original.task56;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioncorrectBracketing_f10c5853Test {
    @Test
    public void testCorrectBracketing() {
        Solution solution = new Solution();

        // Test case: empty string should be balanced
        assertTrue(solution.correctBracketing(""));

        // Test case: balanced brackets
        assertTrue(solution.correctBracketing("<>"));
        assertTrue(solution.correctBracketing("<<>>"));
        assertTrue(solution.correctBracketing("<><>"));
        assertTrue(solution.correctBracketing("<<<>>>"));

        // Test case: unbalanced brackets
        assertFalse(solution.correctBracketing(">"));
        assertFalse(solution.correctBracketing("<<>"));
        assertFalse(solution.correctBracketing("><"));
        assertFalse(solution.correctBracketing(">>><"));

        // Test case: mixed valid and invalid
        assertFalse(solution.correctBracketing("<<><>"));
        assertFalse(solution.correctBracketing("><<>>"));
    }
}
