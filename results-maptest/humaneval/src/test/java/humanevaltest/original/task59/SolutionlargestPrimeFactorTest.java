package humanevaltest.original.task59;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionlargestPrimeFactorTest {
    @Test
    public void testLargestPrimeFactor() {
        Solution solution = new Solution();

        // Test case: n = 1, expected output: 1
        assertEquals(1, solution.largestPrimeFactor(1));

        // Test case: n = 2, expected output: 2
        assertEquals(2, solution.largestPrimeFactor(2));

        // Test case: n = 6, expected output: 3
        assertEquals(3, solution.largestPrimeFactor(6));

        // Test case: n = 15, expected output: 5
        assertEquals(5, solution.largestPrimeFactor(15));

        // Test case: n = 29, expected output: 29 (prime number)
        assertEquals(29, solution.largestPrimeFactor(29));

        // Test case: n = 100, expected output: 5
        assertEquals(5, solution.largestPrimeFactor(100));

        // Test case: n = 0, expected output: 1
        assertEquals(1, solution.largestPrimeFactor(0));

        // Test case: n = -5, expected output: 1
        assertEquals(1, solution.largestPrimeFactor(-5));
    }
}
