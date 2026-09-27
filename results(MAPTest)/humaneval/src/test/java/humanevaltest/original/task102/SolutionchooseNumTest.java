package humanevaltest.original.task102;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionchooseNumTest {
    @Test
    public void testChooseNum() {
        Solution solution = new Solution();

        // Test case 1: x > y
        assertEquals(-1, solution.chooseNum(5, 3));

        // Test case 2: y is even
        assertEquals(4, solution.chooseNum(2, 4));

        // Test case 3: x == y
        assertEquals(-1, solution.chooseNum(3, 3));

        // Test case 4: y is odd and x < y
        assertEquals(6, solution.chooseNum(3, 6));

        // Test case 5: y is odd and x < y
        assertEquals(8, solution.chooseNum(5, 8));

        // Test case 6: y is even and x < y
        assertEquals(6, solution.chooseNum(4, 6));

        // Test case 7: x < y and y is odd
        assertEquals(10, solution.chooseNum(7, 10));

        // Additional test case to cover target line 17
        assertEquals(4, solution.chooseNum(4, 5));
    }
}
