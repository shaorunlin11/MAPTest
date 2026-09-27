package humanevaltest.original.task85;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Solutionadd_8a470de0Test {
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
    public void testAddEmptyList() {
        List<Integer> lst = new ArrayList<>();
        int result = solution.add(lst);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddSingleElementList() {
        List<Integer> lst = new ArrayList<>();
        lst.add(5);
        int result = solution.add(lst);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddEvenNumbersAtOddIndices() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1); // index 0
        lst.add(4); // index 1 (even)
        lst.add(3); // index 2
        lst.add(8); // index 3 (even)
        lst.add(5); // index 4
        lst.add(10); // index 5 (even)
        int result = solution.add(lst);
        Assert.assertEquals(4 + 8 + 10, result);
    }

    @Test
    public void testAddOddNumbersAtOddIndices() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1); // index 0
        lst.add(3); // index 1 (odd)
        lst.add(3); // index 2
        lst.add(5); // index 3 (odd)
        lst.add(5); // index 4
        lst.add(7); // index 5 (odd)
        int result = solution.add(lst);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddMixedNumbersAtOddIndices() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1); // index 0
        lst.add(2); // index 1 (even)
        lst.add(3); // index 2
        lst.add(5); // index 3 (odd)
        lst.add(5); // index 4
        lst.add(6); // index 5 (even)
        int result = solution.add(lst);
        Assert.assertEquals(2 + 6, result);
    }
}
