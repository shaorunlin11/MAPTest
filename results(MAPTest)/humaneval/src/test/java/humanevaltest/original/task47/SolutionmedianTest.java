package humanevaltest.original.task47;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionmedianTest {
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
    public void testMedianOddSize() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        double result = solution.median(list);
        Assert.assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testMedianEvenSize() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(2);
        double result = solution.median(list);
        Assert.assertEquals(2.5, result, 0.0001);
    }

    @Test
    public void testMedianSingleElement() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        double result = solution.median(list);
        Assert.assertEquals(5.0, result, 0.0001);
    }
}
