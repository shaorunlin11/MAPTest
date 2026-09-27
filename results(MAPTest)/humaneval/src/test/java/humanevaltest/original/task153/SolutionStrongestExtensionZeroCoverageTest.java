package humanevaltest.original.task153;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class SolutionStrongestExtensionZeroCoverageTest {
    @Test
    public void testStrongestExtensionWithNonEmptyExtensions() {
        Solution solution = new Solution();
        List<String> extensions = Arrays.asList("ABC", "aBc", "ABc");
        String result = solution.StrongestExtension("TestClass", extensions);
        // This test ensures that the method is called and the target lines are executed
    }
}
