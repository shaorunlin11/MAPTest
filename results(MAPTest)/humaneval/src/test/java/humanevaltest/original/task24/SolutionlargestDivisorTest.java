package humanevaltest.original.task24;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionlargestDivisorTest {
    @Test
    public void testLargestDivisor() {
        Solution solution = new Solution();

        // Test case: n = 1, expected output is 1
        assertEquals(1, solution.largestDivisor(1));

        // Test case: n = 7 (prime), expected output is 1
        assertEquals(1, solution.largestDivisor(7));

        // Test case: n = 12 (composite), expected output is 6
        assertEquals(6, solution.largestDivisor(12));

        // Test case: n = 2, expected output is 1
        assertEquals(1, solution.largestDivisor(2));

        // Test case: n = 100, expected output is 50
        assertEquals(50, solution.largestDivisor(100));

        // Test case: n = 15, expected output is 5
        assertEquals(5, solution.largestDivisor(15));

        // Test case: n = 9, expected output is 3
        assertEquals(3, solution.largestDivisor(9));

        // Test case: n = 6, expected output is 3
        assertEquals(3, solution.largestDivisor(6));

        // Test case: n = 8, expected output is 4
        assertEquals(4, solution.largestDivisor(8));

        // Test case: n = 10, expected output is 5
        assertEquals(5, solution.largestDivisor(10));
    }
}
