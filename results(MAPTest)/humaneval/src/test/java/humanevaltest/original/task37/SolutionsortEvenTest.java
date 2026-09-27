package humanevaltest.original.task37;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SolutionsortEvenTest {
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
    public void testEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.sortEven(input);
        Assert.assertEquals(input, result);
    }

    @Test
    public void testSingleElementList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5));
        List<Integer> result = solution.sortEven(input);
        Assert.assertEquals(input, result);
    }



    @Test
    public void testAllEvenIndices() {
        List<Integer> input = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        List<Integer> result = solution.sortEven(input);
        List<Integer> expected = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testNullInput() {
        List<Integer> input = null;
        try {
            solution.sortEven(input);
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
