package humanevaltest.original.task0;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionhasCloseElementsTest {
    @Test
    public void testHasCloseElements_EmptyList_ReturnsFalse() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        double threshold = 1.0;
        assertFalse(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_SingleElementList_ReturnsFalse() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.0);
        double threshold = 1.0;
        assertFalse(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_TwoElementsEqual_ReturnsTrue() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(3.0);
        numbers.add(3.0);
        double threshold = 0.1;
        assertTrue(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_TwoElementsBelowThreshold_ReturnsTrue() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(2.0);
        numbers.add(2.5);
        double threshold = 0.6;
        assertTrue(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_TwoElementsAtThreshold_ReturnsFalse() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        double threshold = 1.0;
        assertFalse(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_TwoElementsAboveThreshold_ReturnsFalse() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        double threshold = 0.5;
        assertFalse(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_MultipleElementsWithOnePair_ReturnsTrue() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(1.5);
        numbers.add(3.0);
        numbers.add(4.0);
        double threshold = 0.6;
        assertTrue(solution.hasCloseElements(numbers, threshold));
    }

    @Test
    public void testHasCloseElements_MultipleElementsNoPairs_ReturnsFalse() {
        Solution solution = new Solution();
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(4.0);
        double threshold = 0.5;
        assertFalse(solution.hasCloseElements(numbers, threshold));
    }
}
