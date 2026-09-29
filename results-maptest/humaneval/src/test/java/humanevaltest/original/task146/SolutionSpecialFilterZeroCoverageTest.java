package humanevaltest.original.task146;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SolutionSpecialFilterZeroCoverageTest {
    @Test
    public void testSpecialFilterTargetLine8() {
        Solution solution = new Solution();
        List<Integer> nums = new ArrayList<>();
        nums.add(123); // This should trigger the target line 8
        solution.specialFilter(nums);
    }
}
