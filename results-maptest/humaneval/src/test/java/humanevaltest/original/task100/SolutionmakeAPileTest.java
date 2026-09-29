package humanevaltest.original.task100;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class SolutionmakeAPileTest {
    @Test
    public void testMakeAPileWithNZero() {
        Solution solution = new Solution();
        List<Integer> result = solution.makeAPile(0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMakeAPileWithNOne() {
        Solution solution = new Solution();
        List<Integer> result = solution.makeAPile(1);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).intValue());
    }

    @Test
    public void testMakeAPileWithNTwo() {
        Solution solution = new Solution();
        List<Integer> result = solution.makeAPile(2);
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).intValue());
        assertEquals(4, result.get(1).intValue());
    }

    @Test
    public void testMakeAPileWithNThree() {
        Solution solution = new Solution();
        List<Integer> result = solution.makeAPile(3);
        assertEquals(3, result.size());
        assertEquals(3, result.get(0).intValue());
        assertEquals(5, result.get(1).intValue());
        assertEquals(7, result.get(2).intValue());
    }

    @Test
    public void testMakeAPileWithNFive() {
        Solution solution = new Solution();
        List<Integer> result = solution.makeAPile(5);
        assertEquals(5, result.size());
        assertEquals(5, result.get(0).intValue());
        assertEquals(7, result.get(1).intValue());
        assertEquals(9, result.get(2).intValue());
        assertEquals(11, result.get(3).intValue());
        assertEquals(13, result.get(4).intValue());
    }
}
