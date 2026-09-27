package humanevaltest.original.task161;

import org.junit.Test;
import static org.junit.Assert.*;

public class Solutionsolve_b45f0839Test {
    @Test
    public void testAllUppercase() {
        Solution solution = new Solution();
        String result = solution.solve("HELLO");
        assertEquals("hello", result);
    }

    @Test
    public void testAllLowercase() {
        Solution solution = new Solution();
        String result = solution.solve("hello");
        assertEquals("HELLO", result);
    }

    @Test
    public void testMixedCase() {
        Solution solution = new Solution();
        String result = solution.solve("Hello World!");
        assertEquals("hELLO wORLD!", result);
    }

    @Test
    public void testNonAlphabeticCharacters() {
        Solution solution = new Solution();
        String result = solution.solve("123!@#");
        assertEquals("#@!321", result);
    }

    @Test
    public void testEmptyString() {
        Solution solution = new Solution();
        String result = solution.solve("");
        assertEquals("", result);
    }

    @Test
    public void testNoTransformation() {
        Solution solution = new Solution();
        String result = solution.solve("123ABC");
        assertEquals("123abc", result);
    }
}
