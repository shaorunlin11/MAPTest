package humanevaltest.original.task136;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;
public class SolutionlargestSmallestIntegersTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<Integer> lst = Arrays.asList(-5, 0, 3, -2, 7);
        List<Optional<Integer>> result = solution.largestSmallestIntegers(lst);
        Assert.assertEquals(Optional.of(-2), result.get(0));
        Assert.assertEquals(Optional.of(3), result.get(1));
    }

@Test
    public void testWithEmptySmallest() {
        Solution solution = new Solution();
        List<Integer> lst = Arrays.asList(0, 1, 2, 3);
        List<Optional<Integer>> result = solution.largestSmallestIntegers(lst);
        Assert.assertEquals(Optional.empty(), result.get(0));
        Assert.assertEquals(Optional.of(1), result.get(1));
    }

@Test
    public void testWithOnlyNegativeNumbers() {
        Solution solution = new Solution();
        List<Integer> lst = Arrays.asList(-5, -3, -2);
        List<Optional<Integer>> result = solution.largestSmallestIntegers(lst);
        Assert.assertEquals(Optional.of(-2), result.get(0));
        Assert.assertEquals(Optional.empty(), result.get(1));
    }

@Test
    public void testWithOnlyPositiveNumbers() {
        Solution solution = new Solution();
        List<Integer> lst = Arrays.asList(5, 3, 2);
        List<Optional<Integer>> result = solution.largestSmallestIntegers(lst);
        Assert.assertEquals(Optional.empty(), result.get(0));
        Assert.assertEquals(Optional.of(2), result.get(1));
    }
}
