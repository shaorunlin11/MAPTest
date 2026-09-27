package humanevaltest.original.task30;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutiongetPositiveTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @After
    public void tearDown() {
        solution = null;
    }

    @Test
    public void testGetPositiveEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.getPositive(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPositiveAllNegative() {
        List<Integer> input = Arrays.asList(-5, -3, -1);
        List<Integer> result = solution.getPositive(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPositiveMixedValues() {
        List<Integer> input = Arrays.asList(-2, 0, 3, -1, 5);
        List<Integer> result = solution.getPositive(input);
        Assert.assertEquals(Arrays.asList(3, 5), result);
    }

    @Test
    public void testGetPositiveSinglePositive() {
        List<Integer> input = Arrays.asList(4, -2, 0, -5);
        List<Integer> result = solution.getPositive(input);
        Assert.assertEquals(Collections.singletonList(4), result);
    }
}
