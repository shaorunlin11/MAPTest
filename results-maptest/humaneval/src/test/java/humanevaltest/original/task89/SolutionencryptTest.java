package humanevaltest.original.task89;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionencryptTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        String input = "abc";
        String result = solution.encrypt(input);
        assertEquals("efg", result);
    }

@Test
    public void testWithLetterAndNonLetter() {
        Solution solution = new Solution();
        String input = "a1b2c3";
        String result = solution.encrypt(input);
        assertEquals("e1f2g3", result);
    }
}
