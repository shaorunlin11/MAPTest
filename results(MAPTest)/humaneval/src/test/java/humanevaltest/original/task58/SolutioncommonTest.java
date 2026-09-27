package humanevaltest.original.task58;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

public class SolutioncommonTest {
    @Test
    public void testCommonWithDuplicates() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 3, 3, 4));
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(Arrays.asList(2, 3), result);
    }

    @Test
    public void testCommonWithNoCommonElements() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testCommonWithEmptyLists() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testCommonWithOneEmptyList() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> l2 = new ArrayList<>();
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testCommonWithSameElements() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testCommonWithSortedResult() {
        Solution solution = new Solution();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(3, 2, 1));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 1, 3));
        List<Integer> result = solution.common(l1, l2);
        Assert.assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
