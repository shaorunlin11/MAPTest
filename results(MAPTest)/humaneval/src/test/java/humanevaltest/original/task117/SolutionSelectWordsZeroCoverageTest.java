package humanevaltest.original.task117;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class SolutionSelectWordsZeroCoverageTest {
    @Test
    public void testSelectWordsWithValidInput() {
        Solution solution = new Solution();
        List<String> result = solution.selectWords("hello world", 3);
        // This test ensures that line 8 (the if statement) is executed
        // by providing input that should trigger the condition.
    }
}
