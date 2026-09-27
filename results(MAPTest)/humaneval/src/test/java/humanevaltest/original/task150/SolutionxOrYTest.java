package humanevaltest.original.task150;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionxOrYTest {
    @Test
    public void testXOrY() {
        Solution solution = new Solution();

        // Test case: n == 1, should return y
        assertEquals("n == 1 should return y", 5, solution.xOrY(1, 3, 5));

        // Test case: n is prime (2), should return x
        assertEquals("n == 2 (prime) should return x", 10, solution.xOrY(2, 10, 20));

        // Test case: n is prime (3), should return x
        assertEquals("n == 3 (prime) should return x", 15, solution.xOrY(3, 15, 25));

        // Test case: n is not prime (4), should return y
        assertEquals("n == 4 (not prime) should return y", 30, solution.xOrY(4, 10, 30));

        // Test case: n is not prime (6), should return y
        assertEquals("n == 6 (not prime) should return y", 40, solution.xOrY(6, 20, 40));

        // Test case: n is prime (5), should return x
        assertEquals("n == 5 (prime) should return x", 50, solution.xOrY(5, 50, 60));

        // Test case: n is not prime (9), should return y
        assertEquals("n == 9 (not prime) should return y", 70, solution.xOrY(9, 30, 70));

        // Test case: n is 0, should return x (since loop doesn't execute)
        assertEquals("n == 0 should return x", 80, solution.xOrY(0, 80, 90));

        // Test case: n is negative, should return x (since loop doesn't execute)
        assertEquals("n == -1 should return x", 100, solution.xOrY(-1, 100, 110));
    }
}
