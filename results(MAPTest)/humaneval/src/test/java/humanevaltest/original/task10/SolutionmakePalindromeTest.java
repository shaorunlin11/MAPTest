package humanevaltest.original.task10;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionmakePalindromeTest {
    @Test
    public void testEmptyString() {
        Solution solution = new Solution();
        assertEquals("", solution.makePalindrome(""));
    }

    @Test
    public void testAlreadyPalindrome() {
        Solution solution = new Solution();
        assertEquals("abba", solution.makePalindrome("abba"));
        assertEquals("a", solution.makePalindrome("a"));
        assertEquals("racecar", solution.makePalindrome("racecar"));
    }



    @Test
    public void testSingleCharacter() {
        Solution solution = new Solution();
        assertEquals("a", solution.makePalindrome("a"));
    }

@Test
    public void testMakePalindromeWithNonPalindromeSuffix() {
        Solution solution = new Solution();
        assertEquals("abcba", solution.makePalindrome("abc"));
        assertEquals("abccba", solution.makePalindrome("abcc"));
        assertEquals("abcdcba", solution.makePalindrome("abcd"));
    }
}
