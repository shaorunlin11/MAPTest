package humanevaltest.original.task115;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionmaxFillTest {
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
    public void testMaxFillWithSingleRow() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(3, 4, 5));
        int capacity = 5;
        int expected = 3;
        int actual = solution.maxFill(grid, capacity);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMaxFillWithMultipleRows() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(2, 3, 5));
        grid.add(Arrays.asList(1, 4));
        int capacity = 4;
        int expected = 5;
        int actual = solution.maxFill(grid, capacity);
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void testMaxFillWithAllZeros() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(0, 0, 0));
        grid.add(Arrays.asList(0, 0));
        int capacity = 1;
        int expected = 0;
        int actual = solution.maxFill(grid, capacity);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMaxFillWithLargeCapacity() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(10, 20, 30));
        grid.add(Arrays.asList(5, 5, 5, 5));
        int capacity = 100;
        int expected = 2;
        int actual = solution.maxFill(grid, capacity);
        Assert.assertEquals(expected, actual);
    }
}
