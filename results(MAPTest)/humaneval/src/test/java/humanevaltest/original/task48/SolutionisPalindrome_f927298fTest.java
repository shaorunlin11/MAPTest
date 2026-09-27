package humanevaltest.original.task48;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionisPalindrome_f927298fTest {
    @Test
    public void testEmptyString() {
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome(""));
    }

    @Test
    public void testSingleCharacter() {
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome("a"));
    }

    @Test
    public void testStandardPalindrome() {
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome("racecar"));
    }

    @Test
    public void testNonPalindrome() {
        Solution solution = new Solution();
        assertFalse(solution.isPalindrome("hello"));
    }

    @Test
    public void testCaseSensitivity() {
        Solution solution = new Solution();
        assertFalse(solution.isPalindrome("Racecar"));
    }
}
