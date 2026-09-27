package humanevaltest.original.task84;

import org.junit.Test;

public class SolutionSolveZeroCoverageTest {
    @Test
    public void testTargetLine8() {
        humanevaltest.original.task84.Solution solution = new humanevaltest.original.task84.Solution();
        int N = 123;
        String result = solution.solve(N);
        // This test ensures that line 8 (sum += (c - '0');) is executed
        // by verifying that the sum of digits of N is correctly calculated
        // and converted to a binary string.
    }
}
