package humanevaltest.original.task132;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class SolutionisNestedTest {
    @Test
    public void test() {
        // Test logic would go here
    }

@Test
    public void testIsNested() {
        Solution solution = new Solution();
        List<Integer> opening_bracket_index = new ArrayList<>();
        List<Integer> closing_bracket_index = new ArrayList<>();

        // Set up opening and closing bracket indices to trigger target line 9
        opening_bracket_index.add(0);
        opening_bracket_index.add(2);
        closing_bracket_index.add(5);
        closing_bracket_index.add(3);

        // Call the method with a non-null, non-empty string
        boolean result = solution.isNested("[][[[]]");

        // Verify the result
        assertTrue(result);
    }
}
