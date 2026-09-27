package humanevaltest.original.task83;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionstartsOneEndsTest {
    @Test
    public void testStartsOneEnds_N1() {
        Solution solution = new Solution();
        assertEquals(1, solution.startsOneEnds(1));
    }

    @Test
    public void testStartsOneEnds_N2() {
        Solution solution = new Solution();
        assertEquals(18, solution.startsOneEnds(2));
    }

    @Test
    public void testStartsOneEnds_N3() {
        Solution solution = new Solution();
        assertEquals(180, solution.startsOneEnds(3));
    }

    @Test
    public void testStartsOneEnds_N4() {
        Solution solution = new Solution();
        assertEquals(1800, solution.startsOneEnds(4));
    }

    @Test
    public void testStartsOneEnds_N5() {
        Solution solution = new Solution();
        assertEquals(18000, solution.startsOneEnds(5));
    }
}
