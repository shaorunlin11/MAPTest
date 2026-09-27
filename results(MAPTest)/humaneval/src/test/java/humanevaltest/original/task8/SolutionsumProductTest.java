package humanevaltest.original.task8;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.Arrays;

import java.util.ArrayList;


public class SolutionsumProductTest {
    @Test
    public void testSumProduct() {
        Solution solution = new Solution();

        // Test case 1: Empty list
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> result1 = solution.sumProduct(emptyList);
        Assert.assertEquals(Arrays.asList(0, 1), result1);

        // Test case 2: List with one element
        List<Integer> singleElementList = Arrays.asList(5);
        List<Integer> result2 = solution.sumProduct(singleElementList);
        Assert.assertEquals(Arrays.asList(5, 5), result2);

        // Test case 3: List with multiple elements
        List<Integer> multipleElementsList = Arrays.asList(2, 3, 4);
        List<Integer> result3 = solution.sumProduct(multipleElementsList);
        Assert.assertEquals(Arrays.asList(9, 24), result3);

        // Test case 4: List with zero
        List<Integer> zeroList = Arrays.asList(0, 1, 2);
        List<Integer> result4 = solution.sumProduct(zeroList);
        Assert.assertEquals(Arrays.asList(3, 0), result4);

        // Test case 5: List with negative numbers
        List<Integer> negativeList = Arrays.asList(-1, -2, -3);
        List<Integer> result5 = solution.sumProduct(negativeList);
        Assert.assertEquals(Arrays.asList(-6, -6), result5);
    }
}
