package humanevaltest.original.task5;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionintersperseTest {
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
        int delimiter = 0;
        List<Integer> result = solution.intersperse(input, delimiter);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleElementList() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        int delimiter = 0;
        List<Integer> result = solution.intersperse(input, delimiter);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(1, result.get(0).intValue());
    }

    @Test
    public void testMultipleElements() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        int delimiter = 0;
        List<Integer> result = solution.intersperse(input, delimiter);
        Assert.assertEquals(5, result.size());
        Assert.assertEquals(1, result.get(0).intValue());
        Assert.assertEquals(0, result.get(1).intValue());
        Assert.assertEquals(2, result.get(2).intValue());
        Assert.assertEquals(0, result.get(3).intValue());
        Assert.assertEquals(3, result.get(4).intValue());
    }

    @Test
    public void testNegativeDelimiter() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        int delimiter = -1;
        List<Integer> result = solution.intersperse(input, delimiter);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1, result.get(0).intValue());
        Assert.assertEquals(-1, result.get(1).intValue());
        Assert.assertEquals(2, result.get(2).intValue());
    }

    @Test
    public void testZeroDelimiter() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        int delimiter = 0;
        List<Integer> result = solution.intersperse(input, delimiter);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1, result.get(0).intValue());
        Assert.assertEquals(0, result.get(1).intValue());
        Assert.assertEquals(2, result.get(2).intValue());
    }
}
