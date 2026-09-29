package humanevaltest.original.task82;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionprimeLengthTest {
    @Test
    public void testPrimeLengthForEmptyString() {
        Solution solution = new Solution();
        assertFalse(solution.primeLength(""));
    }

    @Test
    public void testPrimeLengthForSingleCharacter() {
        Solution solution = new Solution();
        assertFalse(solution.primeLength("a"));
    }

    @Test
    public void testPrimeLengthForPrimeLengths() {
        Solution solution = new Solution();
        assertTrue(solution.primeLength("ab"));    // length 2
        assertTrue(solution.primeLength("abc"));   // length 3
        assertTrue(solution.primeLength("abcde")); // length 5
        assertTrue(solution.primeLength("abcdefg")); // length 7
    }

@Test
    public void testPrimeLengthForNonPrimeLengths() {
        Solution solution = new Solution();
        assertTrue(solution.primeLength("abcde")); // length 5 (prime)
        assertFalse(solution.primeLength("abcd")); // length 4 (non-prime)
        assertFalse(solution.primeLength("abcdef")); // length 6 (non-prime)
        assertFalse(solution.primeLength("abcdefgh")); // length 8 (non-prime)
    }
}
