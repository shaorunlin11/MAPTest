package humanevaltest.original.task42;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class SolutionincrListTest {
    @Test
    public void testIncrList() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        List<Integer> result = solution.incrList(input);
        Assert.assertEquals(2, result.get(0).intValue());
        Assert.assertEquals(3, result.get(1).intValue());
        Assert.assertEquals(4, result.get(2).intValue());
    }

    @Test
    public void testIncrListEmpty() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        List<Integer> result = solution.incrList(input);
        Assert.assertTrue(result.isEmpty());
    }
}
