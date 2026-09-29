package humanevaltest.original.task109;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SolutionmoveOneBallTest {
    @Test
    public void testEmptyList() {
        Solution solution = new Solution();
        assertTrue(solution.moveOneBall(new ArrayList<>()));
    }

    @Test
    public void testSingleElementList() {
        Solution solution = new Solution();
        assertTrue(solution.moveOneBall(Arrays.asList(5)));
    }

    @Test
    public void testAlreadySortedList() {
        Solution solution = new Solution();
        assertTrue(solution.moveOneBall(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testRotatedSortedList() {
        Solution solution = new Solution();
        assertTrue(solution.moveOneBall(Arrays.asList(3, 4, 5, 1, 2)));
    }

    @Test
    public void testNotRotatableList() {
        Solution solution = new Solution();
        assertFalse(solution.moveOneBall(Arrays.asList(3, 5, 4, 1, 2)));
    }

    @Test
    public void testDuplicateMinimumValue() {
        Solution solution = new Solution();
        assertFalse(solution.moveOneBall(Arrays.asList(1, 3, 1, 2)));
    }

    @Test
    public void testMultipleRotations() {
        Solution solution = new Solution();
        assertTrue(solution.moveOneBall(Arrays.asList(4, 5, 1, 2, 3)));
    }

    @Test
    public void testLargeInput() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            arr.add(i);
        }
        assertTrue(solution.moveOneBall(arr));
    }

    @Test
    public void testLargeRotatedInput() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        for (int i = 500; i <= 1000; i++) {
            arr.add(i);
        }
        for (int i = 1; i < 500; i++) {
            arr.add(i);
        }
        assertTrue(solution.moveOneBall(arr));
    }
}
