package humanevaltest.original.task118;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutiongetClosestVowelTest {
    @Test
    public void testGetClosestVowelWithShortWord() {
        Solution solution = new Solution();
        assertEquals("", solution.getClosestVowel("ab"));
        assertEquals("", solution.getClosestVowel("a"));
        assertEquals("", solution.getClosestVowel(""));
    }

    @Test
    public void testGetClosestVowelWithNoVowels() {
        Solution solution = new Solution();
        assertEquals("", solution.getClosestVowel("bcdfg"));
        assertEquals("", solution.getClosestVowel("xyz"));
        assertEquals("", solution.getClosestVowel("qrst"));
    }

    @Test
    public void testGetClosestVowelWithVowelAtEnd() {
        Solution solution = new Solution();
        assertEquals("", solution.getClosestVowel("abcde"));
        assertEquals("", solution.getClosestVowel("xyzau"));
        assertEquals("", solution.getClosestVowel("12345a"));
    }

    @Test
    public void testGetClosestVowelWithVowelAtStart() {
        Solution solution = new Solution();
        assertEquals("", solution.getClosestVowel("aefgh"));
        assertEquals("", solution.getClosestVowel("aeiou"));
        assertEquals("", solution.getClosestVowel("AeIou"));
    }

    @Test
    public void testGetClosestVowelWithConsecutiveVowels() {
        Solution solution = new Solution();
        assertEquals("", solution.getClosestVowel("aei"));
        assertEquals("", solution.getClosestVowel("AEIOU"));
        assertEquals("", solution.getClosestVowel("aEiOu"));
    }

    @Test
    public void testGetClosestVowelWithSingleVowelSurroundedByNonVowels() {
        Solution solution = new Solution();
        assertEquals("E", solution.getClosestVowel("bcdEfgh"));
        assertEquals("A", solution.getClosestVowel("bAcD"));
        assertEquals("O", solution.getClosestVowel("xYzOpq"));
    }

}
