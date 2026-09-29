package humanevaltest.original.task154;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutioncycpatternCheckTest {
    @Test
    public void testExample() {
        // This is a placeholder test method. Replace with actual test logic.
        assertTrue(true);
    }

@Test
    public void testCycpatternCheck() {
        Solution solution = new Solution();
        assertTrue(solution.cycpatternCheck("abc", "abc"));
        assertTrue(solution.cycpatternCheck("abc", "bca"));
        assertTrue(solution.cycpatternCheck("abc", "cab"));
        assertFalse(solution.cycpatternCheck("abc", "def"));
        assertTrue(solution.cycpatternCheck("abcd", "cdab"));
        assertTrue(solution.cycpatternCheck("abcd", "dabc"));
        assertTrue(solution.cycpatternCheck("abcd", "bcda"));
        assertTrue(solution.cycpatternCheck("abcd", "abcd"));
        assertTrue(solution.cycpatternCheck("abab", "ab"));
        assertTrue(solution.cycpatternCheck("abab", "ba"));
    }
}
