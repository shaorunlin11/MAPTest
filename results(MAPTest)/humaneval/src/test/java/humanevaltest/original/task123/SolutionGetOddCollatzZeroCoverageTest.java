package humanevaltest.original.task123;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class SolutionGetOddCollatzZeroCoverageTest {
    @Test
    public void testGetOddCollatz() {
        Solution solution = new Solution();
        int n = 6;
        List<Integer> result = solution.getOddCollatz(n);
        // This test ensures that the code path for line 8 is executed
        // Line 8: if (n % 2 == 1) { odd_collatz.add(n); }
        // We ensure this by passing an odd number
        n = 5;
        result = solution.getOddCollatz(n);
    }
}
