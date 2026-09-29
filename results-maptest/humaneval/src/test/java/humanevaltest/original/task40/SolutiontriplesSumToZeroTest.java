package humanevaltest.original.task40;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class SolutiontriplesSumToZeroTest {

    @Test
    public void testTriplesSumToZero_WithValidTriple_ReturnsTrue() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(-1);
        input.add(0);
        assertTrue(solution.triplesSumToZero(input));
    }

    @Test
    public void testTriplesSumToZero_WithNoValidTriple_ReturnsFalse() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        assertFalse(solution.triplesSumToZero(input));
    }

    @Test
    public void testTriplesSumToZero_WithLessThanThreeElements_ReturnsFalse() {
        Solution solution = new Solution();
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> oneElementList = new ArrayList<>();
        oneElementList.add(1);
        List<Integer> twoElementList = new ArrayList<>();
        twoElementList.add(1);
        twoElementList.add(2);
        assertFalse(solution.triplesSumToZero(emptyList));
        assertFalse(solution.triplesSumToZero(oneElementList));
        assertFalse(solution.triplesSumToZero(twoElementList));
    }

    @Test
    public void testTriplesSumToZero_WithMultipleValidTriples_ReturnsTrue() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(-1);
        input.add(2);
        input.add(-2);
        input.add(3);
        input.add(-3);
        assertTrue(solution.triplesSumToZero(input));
    }

    @Test
    public void testTriplesSumToZero_WithAllZeros_ReturnsTrue() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(0);
        input.add(0);
        input.add(0);
        assertTrue(solution.triplesSumToZero(input));
    }

    @Test
    public void testTriplesSumToZero_WithTwoZerosAndOneNonZero_ReturnsFalse() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(0);
        input.add(0);
        input.add(1);
        assertFalse(solution.triplesSumToZero(input));
    }
}
