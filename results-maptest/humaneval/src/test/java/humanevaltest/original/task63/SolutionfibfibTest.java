package humanevaltest.original.task63;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionfibfibTest {
    @Test
    public void testFibfibBaseCases() {
        Solution solution = new Solution();
        assertEquals("n=0 should return 0", 0, solution.fibfib(0));
        assertEquals("n=1 should return 0", 0, solution.fibfib(1));
        assertEquals("n=2 should return 1", 1, solution.fibfib(2));
    }

    @Test
    public void testFibfibRecursiveCase() {
        Solution solution = new Solution();
        assertEquals("n=3 should return 1", 1, solution.fibfib(3));
        assertEquals("n=4 should return 2", 2, solution.fibfib(4));
        assertEquals("n=5 should return 4", 4, solution.fibfib(5));
        assertEquals("n=6 should return 7", 7, solution.fibfib(6));
    }
}
