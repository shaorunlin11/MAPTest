package humanevaltest.original.task79;

import org.junit.Test;

public class SolutionDecimalToBinaryZeroCoverageTest {
    @Test
    public void testDecimalToBinary() {
        Solution solution = new Solution();
        String result = solution.decimalToBinary(10);
        // Target line 8 is executed as part of the method call
        // The assertion is added to ensure the method behaves as expected
        assert result.equals("db1010db");
    }
}
