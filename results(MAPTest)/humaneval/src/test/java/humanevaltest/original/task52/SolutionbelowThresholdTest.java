package humanevaltest.original.task52;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SolutionbelowThresholdTest {
    @Test
    public void testBelowThresholdEmptyList() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        int threshold = 5;
        assertTrue(solution.belowThreshold(list, threshold));
    }

    @Test
    public void testBelowThresholdAllElementsBelowThreshold() {
        Solution solution = new Solution();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int threshold = 5;
        assertTrue(solution.belowThreshold(list, threshold));
    }

    @Test
    public void testBelowThresholdOneElementAtThreshold() {
        Solution solution = new Solution();
        List<Integer> list = Arrays.asList(5);
        int threshold = 5;
        assertFalse(solution.belowThreshold(list, threshold));
    }

    @Test
    public void testBelowThresholdOneElementAboveThreshold() {
        Solution solution = new Solution();
        List<Integer> list = Arrays.asList(6);
        int threshold = 5;
        assertFalse(solution.belowThreshold(list, threshold));
    }

    @Test
    public void testBelowThresholdMixedValues() {
        Solution solution = new Solution();
        List<Integer> list = Arrays.asList(1, 3, 5, 7);
        int threshold = 6;
        assertFalse(solution.belowThreshold(list, threshold));
    }
}
