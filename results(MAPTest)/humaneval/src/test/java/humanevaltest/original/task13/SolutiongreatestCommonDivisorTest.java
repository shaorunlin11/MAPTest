package humanevaltest.original.task13;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiongreatestCommonDivisorTest {

    @Test
    public void testGreatestCommonDivisor() {
        Solution solution = new Solution();

        // Test case: a = 0, b = 5
        assertEquals(5, solution.greatestCommonDivisor(0, 5));

        // Test case: a = 5, b = 0
        assertEquals(5, solution.greatestCommonDivisor(5, 0));

        // Test case: a = 0, b = 0
        assertEquals(0, solution.greatestCommonDivisor(0, 0));

        // Test case: a = 7, b = 7
        assertEquals(7, solution.greatestCommonDivisor(7, 7));

        // Test case: a = 12, b = 18
        assertEquals(6, solution.greatestCommonDivisor(12, 18));

        // Test case: a = 18, b = 12
        assertEquals(6, solution.greatestCommonDivisor(18, 12));

        // Test case: a = 48, b = 18
        assertEquals(6, solution.greatestCommonDivisor(48, 18));

        // Test case: a = 17, b = 5
        assertEquals(1, solution.greatestCommonDivisor(17, 5));

        // Test case: a = 21, b = 14
        assertEquals(7, solution.greatestCommonDivisor(21, 14));
    }
}
