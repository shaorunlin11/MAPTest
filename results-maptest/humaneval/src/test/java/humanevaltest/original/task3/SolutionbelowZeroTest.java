package humanevaltest.original.task3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SolutionbelowZeroTest {
    @Test
    public void testBelowZeroEmptyList() {
        Solution solution = new Solution();
        List<Integer> operations = new ArrayList<>();
        assertFalse(solution.belowZero(operations));
    }

    @Test
    public void testBelowZeroFirstOperationNegative() {
        Solution solution = new Solution();
        List<Integer> operations = Arrays.asList(-1, 2, 3);
        assertTrue(solution.belowZero(operations));
    }

    @Test
    public void testBelowZeroMultipleOperations() {
        Solution solution = new Solution();
        List<Integer> operations = Arrays.asList(1, -2, 3, -4);
        assertTrue(solution.belowZero(operations));
    }

    @Test
    public void testBelowZeroAllPositive() {
        Solution solution = new Solution();
        List<Integer> operations = Arrays.asList(1, 2, 3, 4);
        assertFalse(solution.belowZero(operations));
    }

    @Test
    public void testBelowZeroBalanceZeroAtEnd() {
        Solution solution = new Solution();
        List<Integer> operations = Arrays.asList(1, -1, 2, -2);
        assertFalse(solution.belowZero(operations));
    }

    @Test
    public void testBelowZeroNegativeAfterPositive() {
        Solution solution = new Solution();
        List<Integer> operations = Arrays.asList(5, -3, -3);
        assertTrue(solution.belowZero(operations));
    }
}
