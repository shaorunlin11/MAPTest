package humanevaltest.original.task77;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioniscubeTest {
    @Test
    public void testIscube() {
        Solution solution = new Solution();

        // Test with perfect cubes
        assertTrue(solution.iscube(8));
        assertTrue(solution.iscube(27));
        assertTrue(solution.iscube(64));
        assertTrue(solution.iscube(1));
        assertTrue(solution.iscube(0));

        // Test with non-cubes
        assertFalse(solution.iscube(9));
        assertFalse(solution.iscube(10));
        assertFalse(solution.iscube(15));

        // Test with negative values
        assertTrue(solution.iscube(-8));
        assertTrue(solution.iscube(-27));
        assertTrue(solution.iscube(-64));

        // Test edge cases
        assertTrue(solution.iscube(1));
        assertTrue(solution.iscube(0));
    }
}
