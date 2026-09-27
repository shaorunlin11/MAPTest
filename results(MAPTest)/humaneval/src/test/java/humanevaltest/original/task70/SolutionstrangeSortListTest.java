package humanevaltest.original.task70;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SolutionstrangeSortListTest {
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
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSingleElementList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5));
        List<Integer> expected = new ArrayList<>(Arrays.asList(5));
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testTwoElementsList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 1));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMultipleElementsList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 1, 3, 2));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 4, 2, 3));
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testDuplicateValuesList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 1, 1));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMixedValuesList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 4));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 8, 3, 5, 4));
        List<Integer> result = solution.strangeSortList(input);
        Assert.assertEquals(expected, result);
    }
}
