package humanevaltest.original.task38;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionEncodeCyclicZeroCoverageTest {
    @Test
    public void testEncodeCyclicWithEmptyString() {
        Solution solution = new Solution();
        String result = solution.encodeCyclic("");
        assertEquals("", result);
    }

    @Test
    public void testEncodeCyclicWithStringLengthLessThanThree() {
        Solution solution = new Solution();
        String result1 = solution.encodeCyclic("a");
        assertEquals("a", result1);

        String result2 = solution.encodeCyclic("ab");
        assertEquals("ab", result2);
    }

    @Test
    public void testEncodeCyclicWithStringLengthExactlyThree() {
        Solution solution = new Solution();
        String result = solution.encodeCyclic("abc");
        assertEquals("bca", result);
    }

    @Test
    public void testEncodeCyclicWithStringLengthMultipleOfThree() {
        Solution solution = new Solution();
        String result = solution.encodeCyclic("abcdef");
        assertEquals("bcaefd", result);
    }

    @Test
    public void testEncodeCyclicWithStringLengthNotMultipleOfThree() {
        Solution solution = new Solution();
        String result = solution.encodeCyclic("abcde");
        assertEquals("bcade", result);
    }
}
