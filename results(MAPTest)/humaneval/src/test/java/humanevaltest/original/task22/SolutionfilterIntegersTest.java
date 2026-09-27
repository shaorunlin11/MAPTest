package humanevaltest.original.task22;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class SolutionfilterIntegersTest {
    @Test
    public void testFilterIntegersEmptyList() {
        Solution solution = new Solution();
        List<Object> input = new ArrayList<>();
        List<Integer> result = solution.filterIntegers(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFilterIntegersOnlyNonIntegers() {
        Solution solution = new Solution();
        List<Object> input = new ArrayList<>();
        input.add("string");
        input.add(3.14);
        input.add(new Object());
        List<Integer> result = solution.filterIntegers(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFilterIntegersMixedTypes() {
        Solution solution = new Solution();
        List<Object> input = new ArrayList<>();
        input.add(1);
        input.add("string");
        input.add(2);
        input.add(3.14);
        input.add(4);
        List<Integer> result = solution.filterIntegers(input);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1, result.get(0).intValue());
        Assert.assertEquals(2, result.get(1).intValue());
        Assert.assertEquals(4, result.get(2).intValue());
    }

    @Test
    public void testFilterIntegersWithNulls() {
        Solution solution = new Solution();
        List<Object> input = new ArrayList<>();
        input.add(null);
        input.add(5);
        input.add(null);
        input.add(6);
        List<Integer> result = solution.filterIntegers(input);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(5, result.get(0).intValue());
        Assert.assertEquals(6, result.get(1).intValue());
    }
}
