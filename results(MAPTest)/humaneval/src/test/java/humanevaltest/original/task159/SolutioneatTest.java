package humanevaltest.original.task159;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

public class SolutioneatTest {
    @Test
    public void testEatWithNeedLessThanOrEqualToRemaining() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, 3, 10);
        assertEquals(Arrays.asList(8, 7), result);
    }

    @Test
    public void testEatWithNeedGreaterThanRemaining() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, 10, 3);
        assertEquals(Arrays.asList(8, 0), result);
    }

    @Test
    public void testEatWithZeroNeed() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, 0, 10);
        assertEquals(Arrays.asList(5, 10), result);
    }

    @Test
    public void testEatWithZeroRemaining() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, 3, 0);
        assertEquals(Arrays.asList(5, 0), result);
    }

    @Test
    public void testEatWithNegativeNeed() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, -2, 10);
        assertEquals(Arrays.asList(3, 12), result);
    }

    @Test
    public void testEatWithNegativeRemaining() {
        Solution solution = new Solution();
        List<Integer> result = solution.eat(5, 3, -2);
        assertEquals(Arrays.asList(3, 0), result);
    }
}
