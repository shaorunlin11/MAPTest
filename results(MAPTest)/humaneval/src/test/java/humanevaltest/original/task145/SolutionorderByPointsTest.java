package humanevaltest.original.task145;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionorderByPointsTest {
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
    public void testOrderByPoints_EmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.orderByPoints(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testOrderByPoints_SingleElement() {
        List<Integer> input = Arrays.asList(5);
        List<Integer> result = solution.orderByPoints(input);
        Assert.assertEquals(input, result);
    }

    @Test
    public void testOrderByPoints_PositiveNumbers() {
        List<Integer> input = Arrays.asList(123, 45, 6);
        List<Integer> result = solution.orderByPoints(input);
        Assert.assertEquals(Arrays.asList(123, 6, 45), result);
    }



    @Test
    public void testOrderByPoints_OriginalListUnmodified() {
        List<Integer> input = new ArrayList<>(Arrays.asList(123, 45, 6));
        List<Integer> result = solution.orderByPoints(input);
        Assert.assertNotSame(input, result);
        Assert.assertEquals(Arrays.asList(123, 6, 45), result);
    }

@Test
    public void testOrderByPoints_TargetLine14() {
        List<Integer> input = Arrays.asList(-123, 45, 6);
        List<Integer> result = solution.orderByPoints(input);
        Assert.assertEquals(Arrays.asList(-123, 6, 45), result);
    }
}
