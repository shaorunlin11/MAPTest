package humanevaltest.original.task60;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionsumToNTest {
    @Test
    public void testSumToN() {
        Solution solution = new Solution();

        // Test case: n = 0, expected result = 0
        assertEquals(0, solution.sumToN(0));

        // Test case: n = 1, expected result = 1
        assertEquals(1, solution.sumToN(1));

        // Test case: n = 10, expected result = 55
        assertEquals(55, solution.sumToN(10));

        // Test case: n = -5, expected result = 0
        assertEquals(0, solution.sumToN(-5));

        // Test case: n = 100, expected result = 5050
        assertEquals(5050, solution.sumToN(100));
    }
}
