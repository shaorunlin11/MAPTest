package humanevaltest.original.task11;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionstringXorTest {
    @Test
    public void testStringXorWithEqualStrings() {
        Solution solution = new Solution();
        String result = solution.stringXor("abc", "abc");
        assertEquals("000", result);
    }

    @Test
    public void testStringXorWithDifferentStrings() {
        Solution solution = new Solution();
        String result = solution.stringXor("abc", "def");
        assertEquals("111", result);
    }

    @Test
    public void testStringXorWithEmptyStrings() {
        Solution solution = new Solution();
        String result = solution.stringXor("", "");
        assertEquals("", result);
    }

    @Test
    public void testStringXorWithMixedCharacters() {
        Solution solution = new Solution();
        String result = solution.stringXor("aBc", "AbC");
        assertEquals("111", result);
    }

    @Test
    public void testStringXorWithOneCharacter() {
        Solution solution = new Solution();
        String result = solution.stringXor("x", "y");
        assertEquals("1", result);
    }

    @Test
    public void testStringXorWithSameCharacters() {
        Solution solution = new Solution();
        String result = solution.stringXor("123", "123");
        assertEquals("000", result);
    }
}
