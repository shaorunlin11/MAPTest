package com.zappos.json.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringsrandomNumberTest {
    @Test
    public void testRandomNumberWithPositiveN() {
        String result = Strings.randomNumber(5);
        assertNotNull(result);
        assertEquals(5, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(c >= '0' && c <= '9');
        }
    }

    @Test
    public void testRandomNumberWithZeroN() {
        String result = Strings.randomNumber(0);
        assertNotNull(result);
        assertEquals(0, result.length());
    }

    @Test
    public void testRandomNumberWithLargeN() {
        String result = Strings.randomNumber(100);
        assertNotNull(result);
        assertEquals(100, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(c >= '0' && c <= '9');
        }
    }
}
