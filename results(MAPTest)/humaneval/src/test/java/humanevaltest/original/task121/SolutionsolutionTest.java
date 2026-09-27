package humanevaltest.original.task121;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class SolutionsolutionTest {
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
        List<Integer> lst = new ArrayList<>();
        int result = solution.solution(lst);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSingleOddElement() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        int result = solution.solution(lst);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSingleEvenElement() {
        List<Integer> lst = new ArrayList<>();
        lst.add(4);
        int result = solution.solution(lst);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testMultipleElementsWithOddAtEvenIndices() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1); // index 0 (even)
        lst.add(2); // index 1 (odd)
        lst.add(3); // index 2 (even)
        lst.add(4); // index 3 (odd)
        lst.add(5); // index 4 (even)
        int result = solution.solution(lst);
        Assert.assertEquals(9, result); // 1 + 3 + 5 = 9
    }

    @Test
    public void testMultipleElementsWithEvenAtEvenIndices() {
        List<Integer> lst = new ArrayList<>();
        lst.add(2); // index 0 (even)
        lst.add(3); // index 1 (odd)
        lst.add(4); // index 2 (even)
        lst.add(5); // index 3 (odd)
        lst.add(6); // index 4 (even)
        int result = solution.solution(lst);
        Assert.assertEquals(0, result); // no odd numbers at even indices
    }

    @Test
    public void testOddLengthList() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1); // index 0 (even)
        lst.add(2); // index 1 (odd)
        lst.add(3); // index 2 (even)
        lst.add(4); // index 3 (odd)
        lst.add(5); // index 4 (even)
        lst.add(6); // index 5 (odd)
        lst.add(7); // index 6 (even)
        int result = solution.solution(lst);
        Assert.assertEquals(1 + 3 + 5 + 7, result); // 1 + 3 + 5 + 7 = 16
    }
}
