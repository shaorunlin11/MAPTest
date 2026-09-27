package humanevaltest.original.task105;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionbyLengthTest {
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
    public void testByLengthWithValidNumbers() {
        List<Integer> input = Arrays.asList(3, 1, 4, 2, 5);
        List<String> result = solution.byLength(input);
        List<String> expected = Arrays.asList("Five", "Four", "Three", "Two", "One");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testByLengthWithInvalidNumbers() {
        List<Integer> input = Arrays.asList(0, 10, -1, 5, 15);
        List<String> result = solution.byLength(input);
        List<String> expected = Arrays.asList("Five");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testByLengthWithEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<String> result = solution.byLength(input);
        List<String> expected = new ArrayList<>();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testByLengthWithMixedValidAndInvalidNumbers() {
        List<Integer> input = Arrays.asList(9, 0, 8, 10, 7);
        List<String> result = solution.byLength(input);
        List<String> expected = Arrays.asList("Nine", "Eight", "Seven");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testByLengthWithSingleValidNumber() {
        List<Integer> input = Arrays.asList(6);
        List<String> result = solution.byLength(input);
        List<String> expected = Arrays.asList("Six");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testByLengthWithAllInvalidNumbers() {
        List<Integer> input = Arrays.asList(-5, 0, 10, 15);
        List<String> result = solution.byLength(input);
        List<String> expected = new ArrayList<>();
        Assert.assertEquals(expected, result);
    }
}
