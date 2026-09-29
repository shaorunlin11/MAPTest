package humanevaltest.original.task133;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class SolutionSumSquaresZeroCoverageTest {
    @Test
    public void testSumSquares() {
        Solution solution = new Solution();
        List<Double> lst = Arrays.asList(1.2, 2.5, 3.7);
        int result = solution.sumSquares(lst);
        // This test ensures that the target line (line 8) is executed.
        // The line in question is: return lst.stream().map(p -> (int) Math.ceil(p)).map(p -> p * p).reduce(Integer::sum).get();
        // By providing a non-null and non-empty list, we ensure that the stream is processed,
        // and the target line is reached.
    }
}
