package humanevaltest.original.task53;

import org.junit.Test;
import static org.junit.Assert.*;

public class Solutionadd_6bfd19c7Test {
    @Test
    public void testAdd() {
        Solution solution = new Solution();
        assertEquals(5, solution.add(2, 3));
        assertEquals(0, solution.add(0, 0));
        assertEquals(-5, solution.add(-2, -3));
        assertEquals(2147483647, solution.add(2147483647, 0));
        assertEquals(-2147483648, solution.add(-2147483648, 0));
    }
}
