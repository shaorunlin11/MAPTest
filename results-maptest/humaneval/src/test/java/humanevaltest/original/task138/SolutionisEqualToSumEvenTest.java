package humanevaltest.original.task138;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionisEqualToSumEvenTest {
    @Test
    public void testIsEqualToSumEven() {
        Solution solution = new Solution();

        // Test case: n = 8 (even and >= 8)
        assertTrue(solution.isEqualToSumEven(8));

        // Test case: n = 7 (odd)
        assertFalse(solution.isEqualToSumEven(7));

        // Test case: n = 9 (odd)
        assertFalse(solution.isEqualToSumEven(9));

        // Test case: n = 10 (even and >= 8)
        assertTrue(solution.isEqualToSumEven(10));

        // Test case: n = 6 (even but < 8)
        assertFalse(solution.isEqualToSumEven(6));

        // Test case: n = 0 (even but < 8)
        assertFalse(solution.isEqualToSumEven(0));

        // Test case: n = 1 (odd)
        assertFalse(solution.isEqualToSumEven(1));

        // Test case: n = -2 (even but < 8)
        assertFalse(solution.isEqualToSumEven(-2));
    }
}
