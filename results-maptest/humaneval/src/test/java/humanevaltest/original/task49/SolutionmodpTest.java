package humanevaltest.original.task49;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionmodpTest {
    @Test
    public void testModp() {
        Solution solution = new Solution();

        // Test case where n = 0 and p > 1
        assertEquals(1, solution.modp(0, 5));

        // Test case where p = 1
        assertEquals(0, solution.modp(3, 1));

        // Test case where n = 3 and p = 7 (2^3 % 7 = 8 % 7 = 1)
        assertEquals(1, solution.modp(3, 7));

        // Test case where n = 5 and p = 3 (2^5 % 3 = 32 % 3 = 2)
        assertEquals(2, solution.modp(5, 3));

        // Test case where n = 10 and p = 1000 (2^10 % 1000 = 1024 % 1000 = 24)
        assertEquals(24, solution.modp(10, 1000));

        // Test case where n = 0 and p = 1 (1 % 1 = 0)
        assertEquals(1, solution.modp(0, 1));

        // Test case where n = 0 and p = 0 (undefined behavior, but method will return 1 % 0 which causes ArithmeticException)
        // This test is omitted as it would cause an exception and is not handled in the method
    }
}
