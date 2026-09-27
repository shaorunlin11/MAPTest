package humanevaltest.original.task18;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionhowManyTimesTest {
    @Test
    public void testExample() {
        // This is a placeholder test method to satisfy JUnit requirements
        // Replace with actual test logic when available
        assertTrue(true);
    }

@Test
    public void testHowManyTimes() {
        Solution solution = new Solution();

        // Test case 1: substring appears once
        assertEquals(1, solution.howManyTimes("abc", "a"));

        // Test case 2: substring appears multiple times
        assertEquals(2, solution.howManyTimes("ababa", "aba"));

        // Test case 3: substring does not appear
        assertEquals(0, solution.howManyTimes("abc", "d"));

        // Test case 4: substring is same as string
        assertEquals(1, solution.howManyTimes("abc", "abc"));

        // Test case 5: substring is longer than string
        assertEquals(0, solution.howManyTimes("abc", "abcd"));

        // Test case 6: substring appears at the end
        assertEquals(1, solution.howManyTimes("abcde", "cde"));

        // Test case 7: substring appears in the middle
        assertEquals(1, solution.howManyTimes("abcde", "bcd"));

        // Test case 8: substring appears multiple times with overlapping
        assertEquals(4, solution.howManyTimes("aaaaa", "aa"));
    }
}
