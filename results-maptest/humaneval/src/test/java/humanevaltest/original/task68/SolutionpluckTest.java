package humanevaltest.original.task68;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionpluckTest {
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
    public void testEmptyList() {
        List<Integer> arr = new ArrayList<>();
        List<Integer> result = solution.pluck(arr);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testNoEvenNumbers() {
        List<Integer> arr = Arrays.asList(1, 3, 5, 7);
        List<Integer> result = solution.pluck(arr);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleEvenNumber() {
        List<Integer> arr = Arrays.asList(2, 4, 6);
        List<Integer> result = solution.pluck(arr);
        Assert.assertEquals(Arrays.asList(2, 0), result);
    }

    @Test
    public void testMultipleEvenNumbers() {
        List<Integer> arr = Arrays.asList(4, 2, 6, 8, 10);
        List<Integer> result = solution.pluck(arr);
        Assert.assertEquals(Arrays.asList(2, 1), result);
    }

    @Test
    public void testEvenNumbersWithSmallestAtEnd() {
        List<Integer> arr = Arrays.asList(10, 8, 6, 4, 2);
        List<Integer> result = solution.pluck(arr);
        Assert.assertEquals(Arrays.asList(2, 4), result);
    }

    @Test
    public void testEvenNumbersWithSmallestInMiddle() {
        List<Integer> arr = Arrays.asList(10, 2, 8, 4, 6);
        List<Integer> result = solution.pluck(arr);
        Assert.assertEquals(Arrays.asList(2, 1), result);
    }

    @Test
    public void testEvenNumbersWithMultipleInstancesOfSmallest() {
        List<Integer> arr = Arrays.asList(2, 4, 2, 6, 8);
        List<Integer> result = solution.pluck(arr);
        Assert.assertEquals(Arrays.asList(2, 0), result);
    }
}
