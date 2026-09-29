package humanevaltest.original.task72;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SolutionwillItFlyTest {
    @Test
    public void testWillItFly() {
        Solution solution = new Solution();

        // Test case 1: Empty list, sum is 0 which is <= w
        assertTrue(solution.willItFly(new ArrayList<>(), 0));

        // Test case 2: Single element list, sum is 5 which is <= w
        assertTrue(solution.willItFly(new ArrayList<>(Arrays.asList(5)), 5));

        // Test case 3: Palindrome list, sum is 6 which is <= w
        assertTrue(solution.willItFly(new ArrayList<>(Arrays.asList(1, 2, 1)), 6));

        // Test case 4: Non-palindrome list
        assertFalse(solution.willItFly(new ArrayList<>(Arrays.asList(1, 2, 3)), 6));

        // Test case 5: Sum exceeds w
        assertFalse(solution.willItFly(new ArrayList<>(Arrays.asList(1, 2, 3)), 5));

        // Test case 6: Palindrome list, sum is exactly equal to w
        assertTrue(solution.willItFly(new ArrayList<>(Arrays.asList(2, 3, 2)), 7));

        // Test case 7: Palindrome list with even number of elements
        assertTrue(solution.willItFly(new ArrayList<>(Arrays.asList(1, 2, 2, 1)), 6));

        // Test case 8: Non-palindrome list with sum less than w
        assertFalse(solution.willItFly(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), 10));
    }
}
