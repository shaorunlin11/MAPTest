package humanevaltest.original.task35;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

public class SolutionmaxElementTest {
    @Test
    public void testMaxElementWithNonEmptyList() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        Assert.assertEquals(3, solution.maxElement(list));
    }

    @Test
    public void testMaxElementWithSingleElementList() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        Assert.assertEquals(5, solution.maxElement(list));
    }

    @Test
    public void testMaxElementWithMultipleElements() {
        Solution solution = new Solution();
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(15);
        Assert.assertEquals(20, solution.maxElement(list));
    }
}
