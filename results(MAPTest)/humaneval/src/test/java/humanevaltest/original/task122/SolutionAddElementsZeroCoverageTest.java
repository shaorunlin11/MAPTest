package humanevaltest.original.task122;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SolutionAddElementsZeroCoverageTest {
    @Test
    public void testAddElementsWithValidInput() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(200);
        arr.add(30);
        int k = 2;
        int result = solution.addElements(arr, k);
        // Expected sum: 10 + 30 = 40 (since 200 has 3 digits and is filtered out)
        // This test ensures that target line 8 is executed
    }
}
