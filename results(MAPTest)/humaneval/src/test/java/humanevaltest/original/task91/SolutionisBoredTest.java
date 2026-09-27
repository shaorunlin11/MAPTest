package humanevaltest.original.task91;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionisBoredTest {
    @Test
    public void test() {
        // Test method placeholder - add actual test logic here
    }

@Test
    public void testIsBored() {
        Solution solution = new Solution();
        String input = "I am happy. I am sad. I am confused.";
        int result = solution.isBored(input);
        assertEquals(3, result);
    }
}
