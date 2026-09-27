package humanevaltest.original.task97;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionmultiplyTest {
    @Test
    public void testMultiplyPositiveNumbers() {
        Solution solution = new Solution();
        assertEquals(15, solution.multiply(23, 45));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        Solution solution = new Solution();
        assertEquals(15, solution.multiply(-23, -45));
    }

    @Test
    public void testMultiplyMixedSigns() {
        Solution solution = new Solution();
        assertEquals(15, solution.multiply(-23, 45));
    }

    @Test
    public void testMultiplyWithZero() {
        Solution solution = new Solution();
        assertEquals(0, solution.multiply(10, 20));
    }

    @Test
    public void testMultiplyWithSingleDigitNumbers() {
        Solution solution = new Solution();
        assertEquals(6, solution.multiply(2, 3));
    }

    @Test
    public void testMultiplyWithLargeNumbers() {
        Solution solution = new Solution();
        assertEquals(0, solution.multiply(12345, 67890));
    }

    @Test
    public void testMultiplyWithNegativeAndPositiveLastDigits() {
        Solution solution = new Solution();
        assertEquals(15, solution.multiply(-23, 45));
    }
}
