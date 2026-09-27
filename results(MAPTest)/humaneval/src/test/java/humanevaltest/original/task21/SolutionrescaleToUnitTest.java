package humanevaltest.original.task21;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;
public class SolutionrescaleToUnitTest {
    @Test
    public void testRescaleToUnit_NormalCase() {
        Solution solution = new Solution();
        List<Double> input = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> result = solution.rescaleToUnit(input);
        Assert.assertEquals(0.0, result.get(0), 0.0001);
        Assert.assertEquals(0.25, result.get(1), 0.0001);
        Assert.assertEquals(0.5, result.get(2), 0.0001);
        Assert.assertEquals(0.75, result.get(3), 0.0001);
        Assert.assertEquals(1.0, result.get(4), 0.0001);
    }



    @Test
    public void testRescaleToUnit_EmptyList() {
        Solution solution = new Solution();
        List<Double> input = new ArrayList<>();
        try {
            solution.rescaleToUnit(input);
            Assert.fail("Expected NoSuchElementException was not thrown");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }
}
