package humanevaltest.original.task92;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionanyIntTest {
    @Test
    public void testAllIntegersSatisfyCondition() {
        Solution solution = new Solution();
        assertTrue(solution.anyInt(1, 2, 3));
        assertTrue(solution.anyInt(5, 5, 10));
        assertTrue(solution.anyInt(0, 0, 0));
    }


    @Test
    public void testNonIntegerInputs() {
        Solution solution = new Solution();
        assertFalse(solution.anyInt("1", 2, 3));
        assertFalse(solution.anyInt(1, "2", 3));
        assertFalse(solution.anyInt(1, 2, "3"));
        assertFalse(solution.anyInt(1.5, 2, 3));
        assertFalse(solution.anyInt(1, 2.5, 3));
        assertFalse(solution.anyInt(1, 2, 3.5));
        assertFalse(solution.anyInt(null, 2, 3));
        assertFalse(solution.anyInt(1, null, 3));
        assertFalse(solution.anyInt(1, 2, null));
    }

    @Test
    public void testMixedIntegerAndNonIntegerInputs() {
        Solution solution = new Solution();
        assertFalse(solution.anyInt(1, "2", 3));
        assertFalse(solution.anyInt("1", 2, 3));
        assertFalse(solution.anyInt(1, 2, "3"));
        assertFalse(solution.anyInt(1.0, 2, 3));
        assertFalse(solution.anyInt(1, 2.0, 3));
        assertFalse(solution.anyInt(1, 2, 3.0));
    }
}
