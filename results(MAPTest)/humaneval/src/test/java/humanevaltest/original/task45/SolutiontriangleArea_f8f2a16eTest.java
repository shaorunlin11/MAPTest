package humanevaltest.original.task45;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutiontriangleArea_f8f2a16eTest {
    @Test
    public void testTriangleArea() {
        Solution solution = new Solution();
        assertEquals(6.0, solution.triangleArea(4.0, 3.0), 0.0001);
        assertEquals(0.0, solution.triangleArea(0.0, 5.0), 0.0001);
        assertEquals(0.0, solution.triangleArea(5.0, 0.0), 0.0001);
        assertEquals(2.5, solution.triangleArea(2.5, 2.0), 0.0001);
        assertEquals(10.0, solution.triangleArea(5.0, 4.0), 0.0001);
    }
}
