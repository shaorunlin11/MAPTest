package humanevaltest.original.task28;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionconcatenateTest {
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
    public void testConcatenateEmptyList() {
        List<String> input = new ArrayList<>();
        String result = solution.concatenate(input);
        Assert.assertEquals("", result);
    }

    @Test
    public void testConcatenateNonEmptyList() {
        List<String> input = new ArrayList<>();
        input.add("a");
        input.add("b");
        input.add("c");
        String result = solution.concatenate(input);
        Assert.assertEquals("abc", result);
    }
}
