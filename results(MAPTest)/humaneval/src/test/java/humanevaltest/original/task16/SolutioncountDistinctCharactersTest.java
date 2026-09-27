package humanevaltest.original.task16;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutioncountDistinctCharactersTest {
    @Test
    public void testCountDistinctCharacters() {
        Solution solution = new Solution();

        // Test empty string
        assertEquals(0, solution.countDistinctCharacters(""));

        // Test null input
        try {
            solution.countDistinctCharacters(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }

        // Test single character
        assertEquals(1, solution.countDistinctCharacters("a"));

        // Test mixed case
        assertEquals(3, solution.countDistinctCharacters("AbC"));

        // Test repeated characters
        assertEquals(3, solution.countDistinctCharacters("aabbc"));

        // Test all unique characters
        assertEquals(5, solution.countDistinctCharacters("abcde"));

        // Test special characters
        assertEquals(4, solution.countDistinctCharacters("!@#$"));

        // Test alphanumeric
        assertEquals(6, solution.countDistinctCharacters("A1b2C3"));
    }
}
