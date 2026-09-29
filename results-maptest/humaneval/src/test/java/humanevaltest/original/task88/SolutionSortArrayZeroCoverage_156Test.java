package humanevaltest.original.task88;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SolutionSortArrayZeroCoverage_156Test {
    @Test
    public void testSortArrayEmptyList() {
        Solution solution = new Solution();
        List<Integer> array = new ArrayList<>();
        List<Integer> result = solution.sortArray(array);
        // Target line 8 is executed when array.size() == 0
        // The test verifies that the method returns the same empty list
        assert result == array;
    }

@Test
    public void testSortArrayNonEmptyList() {
        Solution solution = new Solution();
        List<Integer> array = new ArrayList<>();
        array.add(3);
        array.add(1);
        array.add(2);
        List<Integer> result = solution.sortArray(array);
        // Target lines 12 is executed when array is not empty
        // The test verifies that the method returns a sorted list
        assert result.get(0) == 1;
        assert result.get(result.size() - 1) == 3;
    }

@Test
    public void testSortArrayTargetLines() {
        Solution solution = new Solution();
        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(4);
        array.add(6);
        List<Integer> result = solution.sortArray(array);
        // Target lines 15 is executed when (result.get(0) + result.get(result.size() - 1)) % 2 == 1 is false
        // The test verifies that the method returns a reversed sorted list
        assert result.get(0) == 6;
        assert result.get(result.size() - 1) == 2;
    }
}
