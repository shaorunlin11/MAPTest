package humanevaltest.original.task39;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionprimeFibTest {
    @Test
    public void testPrimeFib() {
        Solution solution = new Solution();

        // Test case 1: n = 1, expected prime Fibonacci number is 2
        assertEquals(2, solution.primeFib(1));

        // Test case 2: n = 2, expected prime Fibonacci number is 3
        assertEquals(3, solution.primeFib(2));

        // Test case 3: n = 3, expected prime Fibonacci number is 5
        assertEquals(5, solution.primeFib(3));

        // Test case 4: n = 4, expected prime Fibonacci number is 13
        assertEquals(13, solution.primeFib(4));

        // Test case 5: n = 5, expected prime Fibonacci number is 89
        assertEquals(89, solution.primeFib(5));

        // Test case 6: n = 6, expected prime Fibonacci number is 233
        assertEquals(233, solution.primeFib(6));

        // Test case 7: n = 7, expected prime Fibonacci number is 1597
        assertEquals(1597, solution.primeFib(7));

        // Test case 8: n = 8, expected prime Fibonacci number is 28657
        assertEquals(28657, solution.primeFib(8));

        // Test case 9: n = 9, expected prime Fibonacci number is 514229
        assertEquals(514229, solution.primeFib(9));

        // Test case 10: n = 10, expected prime Fibonacci number is 433494437 (actual output)
        assertEquals(433494437, solution.primeFib(10));
    }
}
