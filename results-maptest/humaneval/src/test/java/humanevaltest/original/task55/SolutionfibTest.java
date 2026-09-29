package humanevaltest.original.task55;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionfibTest {
    @Test
    public void testFibBaseCases() {
        Solution solution = new Solution();
        assertEquals("fib(0) should return 0", 0, solution.fib(0));
        assertEquals("fib(1) should return 1", 1, solution.fib(1));
    }

    @Test
    public void testFibRecursiveCase() {
        Solution solution = new Solution();
        assertEquals("fib(2) should return 1", 1, solution.fib(2));
        assertEquals("fib(3) should return 2", 2, solution.fib(3));
        assertEquals("fib(4) should return 3", 3, solution.fib(4));
        assertEquals("fib(5) should return 5", 5, solution.fib(5));
    }
}
