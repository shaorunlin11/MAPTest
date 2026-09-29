package humanevaltest.original.task10;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionisPalindrome_a12467b8Test {

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
    public void testPalindrome() {
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome("racecar"));
        assertTrue(solution.isPalindrome("madam"));
        assertTrue(solution.isPalindrome("abba"));
    }

    @Test
    public void testNonPalindrome() {
        Solution solution = new Solution();
        assertFalse(solution.isPalindrome("hello"));
        assertFalse(solution.isPalindrome("world"));
        assertFalse(solution.isPalindrome("java"));
    }

    @Test
    public void testMixedCharacters() {
        Solution solution = new Solution();
        assertTrue(solution.isPalindrome("a"));
        assertTrue(solution.isPalindrome("aa"));
        assertTrue(solution.isPalindrome("aaa"));
        assertFalse(solution.isPalindrome("aab"));
        assertFalse(solution.isPalindrome("ab"));
    }
}
