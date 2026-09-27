package humanevaltest.original.task43;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionpairsSumToZeroTest {

    @Test
    public void testEmptyList() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        assertFalse(solution.pairsSumToZero(list));
    }

    @Test
    public void testSingleElementList() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        assertFalse(solution.pairsSumToZero(list));
    }

    @Test
    public void testPairSumToZero() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-1);
        assertTrue(solution.pairsSumToZero(list));
    }

    @Test
    public void testMultiplePairs() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(-2);
        list.add(3);
        list.add(-3);
        assertTrue(solution.pairsSumToZero(list));
    }

    @Test
    public void testNoPairsSumToZero() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertFalse(solution.pairsSumToZero(list));
    }

    @Test
    public void testNegativeAndPositiveDuplicates() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-1);
        list.add(1);
        list.add(-1);
        assertTrue(solution.pairsSumToZero(list));
    }

    @Test
    public void testZeroWithAnotherZero() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        assertTrue(solution.pairsSumToZero(list));
    }

    @Test
    public void testZeroWithOtherNumbers() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        assertFalse(solution.pairsSumToZero(list));
    }
}
