package humanevaltest.original.task54;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SolutionsameCharsTest {
    @Test
    public void testSameChars() {
        Solution solution = new Solution();

        // Test case 1: Both strings have the same characters
        assertTrue(solution.sameChars("abc", "cab"));

        // Test case 2: Strings have different characters
        assertFalse(solution.sameChars("abc", "abd"));

        // Test case 3: One string is empty
        assertTrue(solution.sameChars("", ""));

        // Test case 4: One string is empty, other is not
        assertFalse(solution.sameChars("", "a"));

        // Test case 5: Strings with duplicate characters
        assertTrue(solution.sameChars("aab", "abb"));

        // Test case 6: Case-sensitive comparison
        assertFalse(solution.sameChars("Abc", "abc"));

        // Test case 7: Null input
        try {
            solution.sameChars(null, "abc");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }

        // Test case 8: Both strings are null
        try {
            solution.sameChars(null, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }

        // Test case 9: Strings with different lengths but same characters
        assertTrue(solution.sameChars("abcd", "dcba"));

        // Test case 10: Strings with different characters
        assertFalse(solution.sameChars("abcd", "abce"));
    }
}
