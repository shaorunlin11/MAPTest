package humanevaltest.original.task90;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

public class SolutionnextSmallestTest {
    @Test
    public void testNextSmallest() {
        Solution solution = new Solution();

        // Test case 1: List with less than 2 unique elements
        List<Integer> lst1 = Arrays.asList(5, 5, 5);
        Assert.assertFalse(solution.nextSmallest(lst1).isPresent());

        // Test case 2: List with exactly 2 unique elements
        List<Integer> lst2 = Arrays.asList(1, 2);
        Assert.assertEquals(Integer.valueOf(2), solution.nextSmallest(lst2).get());

        // Test case 3: List with more than 2 unique elements
        List<Integer> lst3 = Arrays.asList(3, 1, 4, 2, 5);
        Assert.assertEquals(Integer.valueOf(2), solution.nextSmallest(lst3).get());

        // Test case 4: List with duplicates and multiple unique elements
        List<Integer> lst4 = Arrays.asList(10, 2, 10, 3, 2, 1);
        Assert.assertEquals(Integer.valueOf(2), solution.nextSmallest(lst4).get());

        // Test case 5: Empty list
        List<Integer> lst5 = new ArrayList<>();
        Assert.assertFalse(solution.nextSmallest(lst5).isPresent());

        // Test case 6: List with one element
        List<Integer> lst6 = Arrays.asList(7);
        Assert.assertFalse(solution.nextSmallest(lst6).isPresent());

        // Test case 7: List with multiple duplicates but enough unique elements
        List<Integer> lst7 = Arrays.asList(8, 8, 9, 9, 10);
        Assert.assertEquals(Integer.valueOf(9), solution.nextSmallest(lst7).get());
    }
}
