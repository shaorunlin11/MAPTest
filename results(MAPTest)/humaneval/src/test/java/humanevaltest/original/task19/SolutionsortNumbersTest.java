package humanevaltest.original.task19;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionsortNumbersTest {
    private Solution solution = new Solution();

    @Test
    public void testSortNumbersWithValidInput() {
        String input = "three one five";
        String expected = "one three five";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithDuplicates() {
        String input = "two two one";
        String expected = "one two two";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithEmptyInput() {
        String input = "";
        String expected = "";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithNullInput() {
        String input = "";
        String expected = "";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithSingleWord() {
        String input = "seven";
        String expected = "seven";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithMultipleWordsInDifferentOrder() {
        String input = "nine eight seven";
        String expected = "seven eight nine";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithInvalidWord() {
        String input = "ten one";
        String expected = "one";
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    public void testSortNumbersWithMixedValidAndInvalidWords() {
        String input = "zero eleven two";
        String expected = "zero two";
        assertEquals(expected, solution.sortNumbers(input));
    }

@Test
    public void testSortNumbersWithFourInInput() {
        String input = "four three two one";
        String expected = "one two three four";
        assertEquals(expected, solution.sortNumbers(input));
    }

@Test
    public void testSortNumbersWithSixInInput() {
        String input = "six three one";
        String expected = "one three six";
        assertEquals(expected, solution.sortNumbers(input));
    }
}
