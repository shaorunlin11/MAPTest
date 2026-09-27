package humanevaltest.original.task1;
import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
public class SolutionseparateParenGroupsTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        String input = "(a(b)c)";
        List<String> result = solution.separateParenGroups(input);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("(())", result.get(0));
    }
}
