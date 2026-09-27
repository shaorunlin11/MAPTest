package humanevaltest.original.task142;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class SolutionSumSquaresZeroCoverage_51Test {
    @Test
    public void testSumSquaresWithNonEmptyList() {
        Solution solution = new Solution();
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(5);
        lst.add(6);
        lst.add(7);
        lst.add(8);
        lst.add(9);
        solution.sumSquares(lst);
    }
}
