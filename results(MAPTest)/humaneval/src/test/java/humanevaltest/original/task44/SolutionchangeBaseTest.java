package humanevaltest.original.task44;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionchangeBaseTest {
    @Test
    public void testChangeBaseWithZero() {
        Solution solution = new Solution();
        assertEquals("", solution.changeBase(0, 2));
    }

    @Test
    public void testChangeBaseWithPositiveNumberAndBaseTwo() {
        Solution solution = new Solution();
        assertEquals("1010", solution.changeBase(10, 2));
    }

    @Test
    public void testChangeBaseWithPositiveNumberAndBaseEight() {
        Solution solution = new Solution();
        assertEquals("12", solution.changeBase(10, 8));
    }

    @Test
    public void testChangeBaseWithPositiveNumberAndBaseSixteen() {
        Solution solution = new Solution();
        assertEquals("01", solution.changeBase(10, 16));
    }

    @Test
    public void testChangeBaseWithPositiveNumberAndBaseThree() {
        Solution solution = new Solution();
        assertEquals("101", solution.changeBase(10, 3));
    }

    @Test
    public void testChangeBaseWithSingleDigitNumber() {
        Solution solution = new Solution();
        assertEquals("5", solution.changeBase(5, 10));
    }

    @Test
    public void testChangeBaseWithMultipleDigits() {
        Solution solution = new Solution();
        assertEquals("1101", solution.changeBase(13, 2));
    }
}
