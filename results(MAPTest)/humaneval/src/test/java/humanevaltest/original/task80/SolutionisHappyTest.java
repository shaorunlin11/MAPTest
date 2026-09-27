package humanevaltest.original.task80;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionisHappyTest {
    @Test
    public void testIsHappyShortString() {
        Solution solution = new Solution();
        assertFalse(solution.isHappy("a"));
        assertFalse(solution.isHappy("ab"));
        assertFalse(solution.isHappy(""));
    }

    @Test
    public void testIsHappyWithRepeatingCharacters() {
        Solution solution = new Solution();
        assertFalse(solution.isHappy("aab"));
        assertFalse(solution.isHappy("aba"));
        assertFalse(solution.isHappy("abb"));
        assertFalse(solution.isHappy("aaa"));
        assertFalse(solution.isHappy("abba"));
        assertFalse(solution.isHappy("abcc"));
    }

    @Test
    public void testIsHappyWithNoRepeatingTriplets() {
        Solution solution = new Solution();
        assertTrue(solution.isHappy("abc"));
        assertTrue(solution.isHappy("abcd"));
        assertTrue(solution.isHappy("abcdefg"));
        assertTrue(solution.isHappy("abcde"));
    }
}
