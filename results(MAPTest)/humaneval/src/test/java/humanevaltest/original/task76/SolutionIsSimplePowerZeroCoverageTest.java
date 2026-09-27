package humanevaltest.original.task76;

import org.junit.Test;

public class SolutionIsSimplePowerZeroCoverageTest {
    @Test
    public void testIsSimplePowerWithNEqualsOne() {
        humanevaltest.original.task76.Solution solution = new humanevaltest.original.task76.Solution();
        // Target line 8 is executed when n == 1
        // The method returns x == 1 when n == 1
        // This test case ensures that the target line is executed
        boolean result = solution.isSimplePower(1, 1);
        assert result : "Expected true for x=1, n=1";
    }

@Test
    public void testIsSimplePowerWithNEqualsOneAndXNotOne() {
        humanevaltest.original.task76.Solution solution = new humanevaltest.original.task76.Solution();
        // Target line 8 is executed when n == 1
        // The method returns x == 1 when n == 1
        // This test case ensures that the target line is executed
        boolean result = solution.isSimplePower(2, 1);
        assert !result : "Expected false for x=2, n=1";
    }

@Test
    public void testIsSimplePowerWithPowerLessThanX() {
        humanevaltest.original.task76.Solution solution = new humanevaltest.original.task76.Solution();
        // Target line 12 is executed when power < x
        // This test case ensures that the target line is executed
        boolean result = solution.isSimplePower(8, 2);
        assert result : "Expected true for x=8, n=2";
    }
}
