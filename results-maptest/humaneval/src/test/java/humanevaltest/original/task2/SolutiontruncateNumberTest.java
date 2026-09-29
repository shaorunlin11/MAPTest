package humanevaltest.original.task2;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiontruncateNumberTest {
    @Test
    public void testTruncateNumber() {
        Solution solution = new Solution();

        // Test with integer value
        assertEquals(0.0, solution.truncateNumber(5.0), 1e-10);

        // Test with positive non-integer value
        assertEquals(0.14, solution.truncateNumber(3.14), 1e-10);

        // Test with negative non-integer value
        assertEquals(-0.14, solution.truncateNumber(-3.14), 1e-10);

        // Test with zero
        assertEquals(0.0, solution.truncateNumber(0.0), 1e-10);

        // Test with large positive value
        assertEquals(0.0, solution.truncateNumber(123456789.0), 1e-10);

        // Test with very small positive value
        assertEquals(0.0000001, solution.truncateNumber(1.0000001), 1e-10);

        // Test with negative value with fractional part
        assertEquals(-0.9999999, solution.truncateNumber(-1.9999999), 1e-10);

        // Test with Double.MAX_VALUE
        assertEquals(0.0, solution.truncateNumber(Double.MAX_VALUE), 1e-10);

        // Test with Double.MIN_VALUE
        assertEquals(0.0, solution.truncateNumber(Double.MIN_VALUE), 1e-10);
    }
}
