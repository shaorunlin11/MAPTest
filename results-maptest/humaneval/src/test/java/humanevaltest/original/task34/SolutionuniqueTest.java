package humanevaltest.original.task34;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SolutionuniqueTest {
    @Test
    public void testUniqueEmptyList() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.unique(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUniqueAllDuplicates() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(2, 2, 2, 2);
        List<Integer> result = solution.unique(input);
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(2), result.get(0));
    }

    @Test
    public void testUniqueMixedElements() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(3, 1, 2, 3, 2, 1);
        List<Integer> result = solution.unique(input);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
    }

    @Test
    public void testUniqueSingleElement() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(5);
        List<Integer> result = solution.unique(input);
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(5), result.get(0));
    }

    @Test
    public void testUniqueMultipleDuplicates() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(4, 4, 5, 5, 6, 6);
        List<Integer> result = solution.unique(input);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(4), result.get(0));
        assertEquals(Integer.valueOf(5), result.get(1));
        assertEquals(Integer.valueOf(6), result.get(2));
    }
}
