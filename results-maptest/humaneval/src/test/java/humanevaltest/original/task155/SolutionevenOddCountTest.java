package humanevaltest.original.task155;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class SolutionevenOddCountTest {
    @Test
    public void testEvenOddCountPositiveNumber() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(1234);
        assertEquals(Arrays.asList(2, 2), result);
    }

    @Test
    public void testEvenOddCountNegativeNumber() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(-567);
        assertEquals(Arrays.asList(1, 2), result);
    }

    @Test
    public void testEvenOddCountZero() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(0);
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    public void testEvenOddCountSingleDigitEven() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(8);
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    public void testEvenOddCountSingleDigitOdd() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(9);
        assertEquals(Arrays.asList(0, 1), result);
    }

    @Test
    public void testEvenOddCountMultipleZeros() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(0);
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    public void testEvenOddCountMixedDigits() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(24680);
        assertEquals(Arrays.asList(5, 0), result);
    }

    @Test
    public void testEvenOddCountAllOddDigits() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddCount(13579);
        assertEquals(Arrays.asList(0, 5), result);
    }
}
