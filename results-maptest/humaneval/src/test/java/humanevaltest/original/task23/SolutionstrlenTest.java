package humanevaltest.original.task23;

import org.junit.Test;
import org.junit.Assert;

public class SolutionstrlenTest {
    @Test
    public void test_strlen_withEmptyString() {
        Solution solution = new Solution();
        int result = solution.strlen("");
        Assert.assertEquals(0, result);
    }

    @Test
    public void test_strlen_withNonEmptyString() {
        Solution solution = new Solution();
        int result = solution.strlen("hello");
        Assert.assertEquals(5, result);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void test_strlen_withNullInput() {
        Solution solution = new Solution();
        solution.strlen(null);
    }
}
