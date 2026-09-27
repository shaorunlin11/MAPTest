package humanevaltest.original.task157;

import org.junit.Test;

public class SolutionRightAngleTriangleZeroCoverageTest {
    @Test
    public void testRightAngleTriangleWithValidInputs() {
        humanevaltest.original.task157.Solution solution = new humanevaltest.original.task157.Solution();
        // Test case where a^2 = b^2 + c^2
        solution.rightAngleTriangle(5, 3, 4);
        // Test case where b^2 = a^2 + c^2
        solution.rightAngleTriangle(5, 4, 3);
        // Test case where c^2 = a^2 + b^2
        solution.rightAngleTriangle(5, 3, 4);
    }
}
