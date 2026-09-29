package humanevaltest.original.task147;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiongetMaxTriplesTest {
    @Test
    public void testGetMaxTriples() {
        Solution solution = new Solution();

        // Test case 1: n = 0, should return 0
        assertEquals(0, solution.getMaxTriples(0));

        // Test case 2: n = 1, should return 0
        assertEquals(0, solution.getMaxTriples(1));

        // Test case 3: n = 2, should return 0
        assertEquals(0, solution.getMaxTriples(2));

        // Test case 4: n = 3
        // A = [1, 3, 7]
        // Possible triplet: (1, 3, 7) sum = 11, not divisible by 3
        // Expected result: 0
        assertEquals(0, solution.getMaxTriples(3));

        // Test case 5: n = 4
        // A = [1, 3, 7, 13]
        // Possible triplets:
        // (1, 3, 7) sum = 11 -> not divisible by 3
        // (1, 3, 13) sum = 17 -> not divisible by 3
        // (1, 7, 13) sum = 21 -> divisible by 3
        // (3, 7, 13) sum = 23 -> not divisible by 3
        // Expected result: 1
        assertEquals(1, solution.getMaxTriples(4));

        // Test case 6: n = 5
        // A = [1, 3, 7, 13, 21]
        // Possible triplets:
        // (1, 3, 7) sum = 11 -> not divisible by 3
        // (1, 3, 13) sum = 17 -> not divisible by 3
        // (1, 3, 21) sum = 25 -> not divisible by 3
        // (1, 7, 13) sum = 21 -> divisible by 3
        // (1, 7, 21) sum = 29 -> not divisible by 3
        // (1, 13, 21) sum = 35 -> not divisible by 3
        // (3, 7, 13) sum = 23 -> not divisible by 3
        // (3, 7, 21) sum = 31 -> not divisible by 3
        // (3, 13, 21) sum = 37 -> not divisible by 3
        // (7, 13, 21) sum = 41 -> not divisible by 3
        // Expected result: 1
        assertEquals(1, solution.getMaxTriples(5));

        // Test case 7: n = 6
        // A = [1, 3, 7, 13, 21, 31]
        // Possible triplets:
        // (1, 3, 7) sum = 11 -> not divisible by 3
        // (1, 3, 13) sum = 17 -> not divisible by 3
        // (1, 3, 21) sum = 25 -> not divisible by 3
        // (1, 3, 31) sum = 35 -> not divisible by 3
        // (1, 7, 13) sum = 21 -> divisible by 3
        // (1, 7, 21) sum = 29 -> not divisible by 3
        // (1, 7, 31) sum = 39 -> divisible by 3
        // (1, 13, 21) sum = 35 -> not divisible by 3
        // (1, 13, 31) sum = 45 -> divisible by 3
        // (1, 21, 31) sum = 53 -> not divisible by 3
        // (3, 7, 13) sum = 23 -> not divisible by 3
        // (3, 7, 21) sum = 31 -> not divisible by 3
        // (3, 7, 31) sum = 41 -> not divisible by 3
        // (3, 13, 21) sum = 37 -> not divisible by 3
        // (3, 13, 31) sum = 47 -> not divisible by 3
        // (3, 21, 31) sum = 55 -> not divisible by 3
        // (7, 13, 21) sum = 41 -> not divisible by 3
        // (7, 13, 31) sum = 51 -> divisible by 3
        // (7, 21, 31) sum = 59 -> not divisible by 3
        // (13, 21, 31) sum = 65 -> not divisible by 3
        // Expected result: 4
        assertEquals(4, solution.getMaxTriples(6));
    }
}
