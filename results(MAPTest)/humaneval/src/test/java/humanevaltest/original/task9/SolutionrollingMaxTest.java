package humanevaltest.original.task9;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SolutionrollingMaxTest {
    @Test
    public void testEmptyList() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.rollingMax(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleElementList() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(5);
        List<Integer> result = solution.rollingMax(input);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).intValue());
    }

    @Test
    public void testIncreasingValues() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        List<Integer> result = solution.rollingMax(input);
        assertEquals(5, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(3, result.get(2).intValue());
        assertEquals(4, result.get(3).intValue());
        assertEquals(5, result.get(4).intValue());
    }

    @Test
    public void testDecreasingValues() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(5);
        input.add(4);
        input.add(3);
        input.add(2);
        input.add(1);
        List<Integer> result = solution.rollingMax(input);
        assertEquals(5, result.size());
        assertEquals(5, result.get(0).intValue());
        assertEquals(5, result.get(1).intValue());
        assertEquals(5, result.get(2).intValue());
        assertEquals(5, result.get(3).intValue());
        assertEquals(5, result.get(4).intValue());
    }

    @Test
    public void testResettingValues() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(3);
        input.add(2);
        input.add(5);
        input.add(4);
        List<Integer> result = solution.rollingMax(input);
        assertEquals(5, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(3, result.get(1).intValue());
        assertEquals(3, result.get(2).intValue());
        assertEquals(5, result.get(3).intValue());
        assertEquals(5, result.get(4).intValue());
    }

    @Test
    public void testDuplicateValues() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(2);
        input.add(2);
        input.add(2);
        List<Integer> result = solution.rollingMax(input);
        assertEquals(4, result.size());
        assertEquals(2, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(2, result.get(2).intValue());
        assertEquals(2, result.get(3).intValue());
    }
}
