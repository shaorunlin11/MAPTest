package humanevaltest.original.task103;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionroundedAvgTest {
    @Test
    public void testRoundedAvg() {
        Solution solution = new Solution();

        // Test case 1: n > m should return -1
        assertEquals(-1, solution.roundedAvg(5, 3));

        // Test case 2: n == m should return binary string of the number
        assertEquals("101", solution.roundedAvg(5, 5));

        // Test case 3: n < m, even sum
        assertEquals("1010", solution.roundedAvg(8, 12));

        // Test case 4: n < m, odd sum
        assertEquals("110", solution.roundedAvg(4, 7));

        // Test case 5: n < m, rounded average is 0
        assertEquals("0", solution.roundedAvg(0, 0));

        // Test case 6: n < m, rounded average is 1
        assertEquals("1", solution.roundedAvg(0, 2));

        // Test case 7: n < m, rounded average is 2
        assertEquals("10", solution.roundedAvg(1, 3));

        // Test case 8: n < m, rounded average is 3
        assertEquals("11", solution.roundedAvg(2, 4));

        // Test case 9: n < m, rounded average is 4
        assertEquals("100", solution.roundedAvg(3, 5));

        // Test case 10: n < m, large values
        assertEquals("1000000000000000000000000000000", solution.roundedAvg(1073741823, 1073741824));
    }
}
