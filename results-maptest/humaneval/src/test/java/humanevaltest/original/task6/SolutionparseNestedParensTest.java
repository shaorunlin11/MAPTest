package humanevaltest.original.task6;
import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class SolutionparseNestedParensTest {
    @Test
    public void test() {
        // Test method implementation would go here
    }

@Test
    public void testParseNestedParens() {
        Solution solution = new Solution();
        String[] testInputs = {"(()())", "((()))", "()()", "((())())"};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(2);
        expectedResults.add(3);
        expectedResults.add(1);
        expectedResults.add(3);

        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            List<Integer> result = solution.parseNestedParens(input);
            Assert.assertEquals(expectedResults.get(i), result.get(0));
        }
    }
}
