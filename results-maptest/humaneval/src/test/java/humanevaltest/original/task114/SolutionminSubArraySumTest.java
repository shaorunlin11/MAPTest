package humanevaltest.original.task114;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SolutionminSubArraySumTest {

    @Test
    public void testMinSubArraySum_EmptyList() {
        Solution solution = new Solution();
        List<Integer> nums = new ArrayList<>();
        assertEquals(Integer.MAX_VALUE, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_AllPositiveNumbers() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(1, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_AllNegativeNumbers() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(-5, -3, -2, -4, -1);
        assertEquals(-15, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_MixedNumbers() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        assertEquals(-5, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_SingleElement() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(5);
        assertEquals(5, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_TwoElements() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(-1, 2);
        assertEquals(-1, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_AllZeroes() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(0, 0, 0);
        assertEquals(0, solution.minSubArraySum(nums));
    }

    @Test
    public void testMinSubArraySum_MixedWithPositiveAndNegative() {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(3, -2, 5, -1, 2);
        assertEquals(-2, solution.minSubArraySum(nums));
    }
}
