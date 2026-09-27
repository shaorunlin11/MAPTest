package humanevaltest.original.task131;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiondigitsTest {
    @Test
    public void testDigitsWithAllEvenDigits() {
        Solution solution = new Solution();
        assertEquals(0, solution.digits(2468));
    }

    @Test
    public void testDigitsWithAllOddDigits() {
        Solution solution = new Solution();
        assertEquals(1 * 3 * 5 * 7 * 9, solution.digits(13579));
    }

    @Test
    public void testDigitsWithMixedDigits() {
        Solution solution = new Solution();
        assertEquals(1 * 3 * 5, solution.digits(12345));
    }

    @Test
    public void testDigitsWithZero() {
        Solution solution = new Solution();
        assertEquals(0, solution.digits(0));
    }

    @Test
    public void testDigitsWithNegativeNumber() {
        Solution solution = new Solution();
        assertEquals(1 * 3 * 5, solution.digits(-12345));
    }

    @Test
    public void testDigitsWithSingleOddDigit() {
        Solution solution = new Solution();
        assertEquals(7, solution.digits(7));
    }

    @Test
    public void testDigitsWithSingleEvenDigit() {
        Solution solution = new Solution();
        assertEquals(0, solution.digits(2));
    }

    @Test
    public void testDigitsWithNoOddDigits() {
        Solution solution = new Solution();
        assertEquals(0, solution.digits(222222));
    }
}
