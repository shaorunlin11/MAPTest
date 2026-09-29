package humanevaltest.original.task15;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionstringSequenceTest {

    @Test
    public void testStringSequenceWithNZero() {
        Solution solution = new Solution();
        String result = solution.stringSequence(0);
        assertEquals("0", result);
    }

    @Test
    public void testStringSequenceWithNOne() {
        Solution solution = new Solution();
        String result = solution.stringSequence(1);
        assertEquals("0 1", result);
    }

    @Test
    public void testStringSequenceWithNTwo() {
        Solution solution = new Solution();
        String result = solution.stringSequence(2);
        assertEquals("0 1 2", result);
    }

    @Test
    public void testStringSequenceWithNegativeN() {
        Solution solution = new Solution();
        String result = solution.stringSequence(-5);
        assertEquals("-5", result);
    }

    @Test
    public void testStringSequenceWithNThree() {
        Solution solution = new Solution();
        String result = solution.stringSequence(3);
        assertEquals("0 1 2 3", result);
    }
}
