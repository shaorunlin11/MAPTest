package com.zappos.json.util;
import org.junit.Test;
import static org.junit.Assert.*;
public class StringsrandomTest {
    @Test
    public void testRandomWithValidInput() {
        char[] chars = {'a', 'b', 'c'};
        String result = Strings.random(5, chars);
        assertEquals(5, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(c == 'a' || c == 'b' || c == 'c');
        }
    }

    @Test
    public void testRandomWithZeroLength() {
        char[] chars = {'x', 'y', 'z'};
        String result = Strings.random(0, chars);
        assertEquals(0, result.length());
    }

    @Test
    public void testRandomWithEmptyCharsArray() {
        char[] chars = {};
        try {
            Strings.random(3, chars);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
