package humanevaltest.original.task108;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutioncountNumsTest {
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
    public void testCountNums_EmptyList_ReturnsZero() {
        List<Integer> arr = new ArrayList<>();
        int result = solution.countNums(arr);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testCountNums_PositiveNumbers_SumOfDigitsPositive() {
        List<Integer> arr = Arrays.asList(123, 456, 789);
        int result = solution.countNums(arr);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testCountNums_NegativeNumbers_SignAdjusted() {
        List<Integer> arr = Arrays.asList(-123, -456, -789);
        int result = solution.countNums(arr);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testCountNums_Zero_ReturnsZero() {
        List<Integer> arr = Arrays.asList(0);
        int result = solution.countNums(arr);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testCountNums_MixedNumbers() {
        List<Integer> arr = Arrays.asList(123, -456, 0, 789);
        int result = solution.countNums(arr);
        Assert.assertEquals(3, result);
    }


    @Test
    public void testCountNums_LeadingZerosInStringRepresentation() {
        List<Integer> arr = Arrays.asList(123, -456, 789);
        int result = solution.countNums(arr);
        Assert.assertEquals(3, result);
    }
}
