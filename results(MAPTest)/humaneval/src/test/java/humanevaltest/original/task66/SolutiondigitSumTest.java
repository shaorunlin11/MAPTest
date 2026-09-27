package humanevaltest.original.task66;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiondigitSumTest {
    @Test
    public void testDigitSumWithEmptyString() {
        Solution solution = new Solution();
        assertEquals(0, solution.digitSum(""));
    }

    @Test
    public void testDigitSumWithNoUppercaseCharacters() {
        Solution solution = new Solution();
        assertEquals(0, solution.digitSum("abcdefg"));
    }

    @Test
    public void testDigitSumWithUppercaseCharacters() {
        Solution solution = new Solution();
        assertEquals('A' + 'B' + 'C', solution.digitSum("ABC"));
    }

    @Test
    public void testDigitSumWithMixedCharacters() {
        Solution solution = new Solution();
        assertEquals('B' + 'D', solution.digitSum("aBcD"));
    }

    @Test
    public void testDigitSumWithNullInput() {
        Solution solution = new Solution();
        try {
            solution.digitSum(null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
