package humanevaltest.original.task78;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionhexKeyTest {
    @Test
    public void testHexKeyWithNullInput() {
        Solution solution = new Solution();
        try {
            solution.hexKey(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

@Test
    public void testHexKeyWithValidInput() {
        Solution solution = new Solution();
        String num = "2357BD";
        int result = solution.hexKey(num);
        assertEquals(6, result);
    }

@Test
    public void testHexKeyWithMixedInput() {
        Solution solution = new Solution();
        String num = "A2B3C5D7E";
        int result = solution.hexKey(num);
        assertEquals(6, result);
    }
}
