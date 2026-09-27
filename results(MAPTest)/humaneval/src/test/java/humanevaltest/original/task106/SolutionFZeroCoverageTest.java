package humanevaltest.original.task106;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class SolutionFZeroCoverageTest {
    @Test
    public void testSolutionFWithNValue() {
        Solution solution = new Solution();
        List<Integer> result = solution.f(5);
        assertEquals(5, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(6, result.get(2).intValue());
        assertEquals(24, result.get(3).intValue());
        assertEquals(15, result.get(4).intValue());
    }
}
