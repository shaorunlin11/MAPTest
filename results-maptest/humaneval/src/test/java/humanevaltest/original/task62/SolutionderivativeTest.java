package humanevaltest.original.task62;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class SolutionderivativeTest {
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
        Assert.assertEquals(expected, solution.derivative(input));
    }

    @Test
    public void testSingleElementList() {
        List<Integer> input = new ArrayList<>();
        input.add(5);
        List<Integer> expected = new ArrayList<>();
        Assert.assertEquals(expected, solution.derivative(input));
    }

    @Test
    public void testMultipleElements() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<Integer> expected = new ArrayList<>();
        expected.add(2); // 1 * 2
        expected.add(6); // 2 * 3
        expected.add(12); // 3 * 4
        Assert.assertEquals(expected, solution.derivative(input));
    }

    @Test
    public void testZeroElements() {
        List<Integer> input = new ArrayList<>();
        input.add(0);
        input.add(0);
        input.add(0);
        List<Integer> expected = new ArrayList<>();
        expected.add(0); // 1 * 0
        expected.add(0); // 2 * 0
        Assert.assertEquals(expected, solution.derivative(input));
    }
}
