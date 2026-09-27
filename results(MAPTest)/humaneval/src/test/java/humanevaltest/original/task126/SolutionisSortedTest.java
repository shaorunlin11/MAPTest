package humanevaltest.original.task126;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionisSortedTest {
    @Test
    public void testIsSortedEmptyList() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        assertTrue(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedSingleElement() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(5);
        assertTrue(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedTwoElementsSorted() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        assertTrue(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedTwoElementsUnsorted() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(2);
        lst.add(1);
        assertFalse(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedThreeElementsSortedNoDuplicates() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        assertTrue(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedThreeElementsWithDuplicates() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(1);
        lst.add(1);
        assertFalse(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedThreeElementsWithTwoDuplicates() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(1);
        lst.add(2);
        assertTrue(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedMultipleElementsSortedNoThreeDuplicates() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(2);
        lst.add(3);
        lst.add(3);
        lst.add(3);
        assertFalse(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedMultipleElementsUnsorted() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(2);
        lst.add(1);
        assertFalse(solution.isSorted(lst));
    }

    @Test
    public void testIsSortedMultipleElementsWithThreeDuplicates() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(2);
        lst.add(2);
        lst.add(3);
        assertFalse(solution.isSorted(lst));
    }
}
