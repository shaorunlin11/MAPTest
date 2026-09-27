package humanevaltest.original.task57;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SolutionmonotonicTest {
    @Test
    public void testMonotonic() {
        Solution solution = new Solution();

        // Test empty list
        assertTrue(solution.monotonic(new ArrayList<>()));

        // Test single element
        assertTrue(solution.monotonic(Arrays.asList(5)));

        // Test non-decreasing list
        assertTrue(solution.monotonic(Arrays.asList(1, 2, 3, 4, 5)));

        // Test non-increasing list
        assertTrue(solution.monotonic(Arrays.asList(5, 4, 3, 2, 1)));

        // Test not monotonic
        assertFalse(solution.monotonic(Arrays.asList(1, 3, 2, 4, 5)));

        // Test with duplicates
        assertTrue(solution.monotonic(Arrays.asList(1, 2, 2, 3)));
        assertTrue(solution.monotonic(Arrays.asList(3, 2, 2, 1)));

        // Test with mixed values
        assertFalse(solution.monotonic(Arrays.asList(1, 2, 3, 2, 4)));
    }
}
