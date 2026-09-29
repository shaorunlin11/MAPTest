package humanevaltest.original.task139;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionspecialFactorialTest {
    @Test
    public void testSpecialFactorial() {
        Solution solution = new Solution();

        // Test case: n = 0, expected result = 1
        assertEquals(1, solution.specialFactorial(0));

        // Test case: n = 1, expected result = 1
        assertEquals(1, solution.specialFactorial(1));

        // Test case: n = 2, expected result = 1! * 2! = 1 * 2 = 2
        assertEquals(2, solution.specialFactorial(2));

        // Test case: n = 3, expected result = 1! * 2! * 3! = 1 * 2 * 6 = 12
        assertEquals(12, solution.specialFactorial(3));

        // Test case: n = 4, expected result = 1! * 2! * 3! * 4! = 1 * 2 * 6 * 24 = 288
        assertEquals(288, solution.specialFactorial(4));

        // Test case: n = 5, expected result = 1! * 2! * 3! * 4! * 5! = 288 * 120 = 34560
        assertEquals(34560, solution.specialFactorial(5));
    }
}
