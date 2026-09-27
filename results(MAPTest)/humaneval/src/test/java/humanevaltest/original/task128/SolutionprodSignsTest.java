package humanevaltest.original.task128;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;

public class SolutionprodSignsTest {
    @Test
    public void testEmptyList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        Assert.assertFalse(solution.prodSigns(arr).isPresent());
    }

    @Test
    public void testContainsZero() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        Assert.assertEquals(Integer.valueOf(0), solution.prodSigns(arr).get());
    }

    @Test
    public void testEvenNumberOfNegatives() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(-2);
        arr.add(-3);
        arr.add(4);
        Assert.assertEquals(Integer.valueOf(9), solution.prodSigns(arr).get());
    }

    @Test
    public void testOddNumberOfNegatives() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(-2);
        arr.add(3);
        arr.add(4);
        Assert.assertEquals(Integer.valueOf(-9), solution.prodSigns(arr).get());
    }

    @Test
    public void testMixedSigns() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(-1);
        arr.add(2);
        arr.add(-3);
        arr.add(4);
        Assert.assertEquals(Integer.valueOf(10), solution.prodSigns(arr).get());
    }

    @Test
    public void testSingleNegative() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(-5);
        Assert.assertEquals(Integer.valueOf(-5), solution.prodSigns(arr).get());
    }

    @Test
    public void testSinglePositive() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(7);
        Assert.assertEquals(Integer.valueOf(7), solution.prodSigns(arr).get());
    }
}
