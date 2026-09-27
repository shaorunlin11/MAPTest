package humanevaltest.original.task120;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SolutionmaximumTest {
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
    public void testMaximumWithKZero() {
        List<Integer> arr = new ArrayList<>(Arrays.asList(3, 1, 4, 2));
        int k = 0;
        List<Integer> result = solution.maximum(arr, k);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testMaximumWithKEqualToListSize() {
        List<Integer> arr = new ArrayList<>(Arrays.asList(3, 1, 4, 2));
        int k = 4;
        List<Integer> result = solution.maximum(arr, k);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    public void testMaximumWithKLessThanListSize() {
        List<Integer> arr = new ArrayList<>(Arrays.asList(3, 1, 4, 2));
        int k = 2;
        List<Integer> result = solution.maximum(arr, k);
        Assert.assertEquals(Arrays.asList(3, 4), result);
    }

}
