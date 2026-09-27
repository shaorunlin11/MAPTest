package humanevaltest.original.task64;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionvowelsCountTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        String s = "aeiouAEIOUy";
        int result = solution.vowelsCount(s);
        assertEquals(11, result);
    }

@Test
    public void testLastCharY() {
        Solution solution = new Solution();
        String s = "bcdfgy";
        int result = solution.vowelsCount(s);
        assertEquals(1, result);
    }

@Test
    public void testLastCharYUpperCase() {
        Solution solution = new Solution();
        String s = "bcdfgY";
        int result = solution.vowelsCount(s);
        assertEquals(1, result);
    }

@Test
    public void testLastCharYWithVowels() {
        Solution solution = new Solution();
        String s = "aeioubcdfgy";
        int result = solution.vowelsCount(s);
        assertEquals(6, result);
    }

@Test
    public void testLastCharYWithVowelsUpperCase() {
        Solution solution = new Solution();
        String s = "AEIOUbcdfgY";
        int result = solution.vowelsCount(s);
        assertEquals(6, result);
    }
}
