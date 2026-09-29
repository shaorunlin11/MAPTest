package humanevaltest.original.task26;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
public class SolutionremoveDuplicatesTest {
    @Test
    public void testRemoveDuplicatesEmptyList() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.removeDuplicates(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testRemoveDuplicatesAllUnique() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = solution.removeDuplicates(input);
        assertEquals(input, result);
    }

    @Test
    public void testRemoveDuplicatesAllDuplicates() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(1, 1, 2, 2, 3, 3);
        List<Integer> result = solution.removeDuplicates(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testRemoveDuplicatesMixed() {
        Solution solution = new Solution();
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> result = solution.removeDuplicates(input);
        assertEquals(Arrays.asList(1, 3, 5), result);
    }
}
