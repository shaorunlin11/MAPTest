package humanevaltest.original.task130;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SolutiontriTest {
    @Test
    public void testTri_n0() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(0);
        Assert.assertEquals(Arrays.asList(1), result);
    }

    @Test
    public void testTri_n1() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(1);
        Assert.assertEquals(Arrays.asList(1, 3), result);
    }

    @Test
    public void testTri_n2() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(2);
        Assert.assertEquals(Arrays.asList(1, 3, 2), result);
    }

    @Test
    public void testTri_n3() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(3);
        Assert.assertEquals(Arrays.asList(1, 3, 2, 8), result);
    }

    @Test
    public void testTri_n4() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(4);
        Assert.assertEquals(Arrays.asList(1, 3, 2, 8, 3), result);
    }

    @Test
    public void testTri_n5() {
        Solution solution = new Solution();
        List<Integer> result = solution.tri(5);
        Assert.assertEquals(Arrays.asList(1, 3, 2, 8, 3, 15), result);
    }
}
