package humanevaltest.original.task112;
import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.Arrays;
public class SolutionreverseDeleteTest {
    @Test
    public void testExample() {
        // Example test case - replace with actual test logic
        Assert.assertTrue(true);
    }

@Test
    public void testReverseDeleteWithNonEmptyStrings() {
        Solution solution = new Solution();
        List<Object> result = solution.reverseDelete("abc", "a");
        Assert.assertEquals(Arrays.asList("bc", false), result);
    }
}
