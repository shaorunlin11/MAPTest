package humanevaltest.original.task104;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SolutionUniqueDigitsZeroCoverageTest {
    @Test
    public void testUniqueDigits() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(135);
        input.add(246);
        input.add(789);
        input.add(111);
        input.add(123);
        List<Integer> result = solution.uniqueDigits(input);
        // Expected: [135, 123] since 135 has all odd digits, 123 has all odd digits, others have even digits
    }
}
