package humanevaltest.original.task41;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioncarRaceCollisionTest {
    @Test
    public void testCarRaceCollision() {
        Solution solution = new Solution();
        assertEquals(0, solution.carRaceCollision(0));
        assertEquals(1, solution.carRaceCollision(1));
        assertEquals(4, solution.carRaceCollision(2));
        assertEquals(9, solution.carRaceCollision(3));
        assertEquals(16, solution.carRaceCollision(4));
        assertEquals(25, solution.carRaceCollision(5));
        assertEquals(100, solution.carRaceCollision(10));
        assertEquals(10000, solution.carRaceCollision(100));
    }
}
