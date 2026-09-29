package humanevaltest.original.task32;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;
public class SolutionfindZeroTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<Double> xs = Arrays.asList(1.0, -3.0, 2.0); // Polynomial: x^2 - 3x + 2
        double result = solution.findZero(xs);
        Assert.assertTrue("Result should be between 0 and 1", result > 0 && result < 1);
    }
}
