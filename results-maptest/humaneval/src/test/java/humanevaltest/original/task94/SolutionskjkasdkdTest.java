package humanevaltest.original.task94;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionskjkasdkdTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @After
    public void tearDown() {
        solution = null;
    }

    @Test
    public void testEmptyListReturnsZero() {
        List<Integer> input = new ArrayList<>();
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testNoPrimesInListReturnsZero() {
        List<Integer> input = Arrays.asList(4, 6, 8, 10);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSinglePrimeInListReturnsSumOfDigits() {
        List<Integer> input = Arrays.asList(7);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(7, result);
    }

    @Test
    public void testMultiplePrimesSelectsLargestAndReturnsSumOfDigits() {
        List<Integer> input = Arrays.asList(2, 3, 5, 7, 11);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(2, result); // 11 -> 1 + 1 = 2
    }

    @Test
    public void testPrimeWithMultipleDigitsReturnsSumOfDigits() {
        List<Integer> input = Arrays.asList(13, 17, 19);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(10, result); // 19 -> 1 + 9 = 10
    }

    @Test
    public void testListWithNonPrimeAndPrimeReturnsSumOfLargestPrimeDigits() {
        List<Integer> input = Arrays.asList(1, 4, 6, 13, 15);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(4, result); // 13 -> 1 + 3 = 4
    }

    @Test
    public void testListWithOneReturnsZero() {
        List<Integer> input = Arrays.asList(1);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testListWithZeroReturnsZero() {
        List<Integer> input = Arrays.asList(0);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testListWithNegativeNumbersReturnsZero() {
        List<Integer> input = Arrays.asList(-2, -3, -5);
        int result = solution.skjkasdkd(input);
        Assert.assertEquals(0, result);
    }
}
