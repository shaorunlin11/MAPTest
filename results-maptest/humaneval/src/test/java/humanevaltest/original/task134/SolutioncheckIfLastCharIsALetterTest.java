package humanevaltest.original.task134;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioncheckIfLastCharIsALetterTest {
    private final Solution solution = new Solution();

    @Test
    public void testEmptyString() {
        assertFalse(solution.checkIfLastCharIsALetter(""));
    }

    @Test
    public void testSingleWordWithSingleLetter() {
        assertTrue(solution.checkIfLastCharIsALetter("a"));
    }

    @Test
    public void testSingleWordWithMultipleLetters() {
        assertFalse(solution.checkIfLastCharIsALetter("ab"));
    }

    @Test
    public void testMultipleWordsWithLastCharacterLetter() {
        assertTrue(solution.checkIfLastCharIsALetter("hello a"));
    }

    @Test
    public void testMultipleWordsWithLastCharacterNotLetter() {
        assertFalse(solution.checkIfLastCharIsALetter("hello 2"));
    }

    @Test
    public void testEndsWithSpace() {
        assertFalse(solution.checkIfLastCharIsALetter("hello "));
    }

    @Test
    public void testLastWordIsEmpty() {
        assertFalse(solution.checkIfLastCharIsALetter("  "));
    }

    @Test
    public void testLastWordWithMultipleCharacters() {
        assertFalse(solution.checkIfLastCharIsALetter("hello world"));
    }

    @Test
    public void testLastWordWithOneLetterButNotAlphabetical() {
        assertFalse(solution.checkIfLastCharIsALetter("hello 5"));
    }
}
