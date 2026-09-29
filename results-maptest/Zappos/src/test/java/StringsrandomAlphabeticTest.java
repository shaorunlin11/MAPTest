package com.zappos.json.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.lang.NegativeArraySizeException;

public class StringsrandomAlphabeticTest {
    @Test
    public void testRandomAlphabeticWithPositiveLength() {
        String result = Strings.randomAlphabetic(5);
        assertEquals(5, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(Character.isLetter(c));
        }
    }

    @Test
    public void testRandomAlphabeticWithZeroLength() {
        String result = Strings.randomAlphabetic(0);
        assertEquals(0, result.length());
    }

    @Test
    public void testRandomAlphabeticWithNegativeLength() {
        try {
            Strings.randomAlphabetic(-1);
            fail("Expected NegativeArraySizeException was not thrown");
        } catch (NegativeArraySizeException e) {
            // Expected exception
        }
    }

    @Test
    public void testRandomAlphabeticGeneratedCharactersAreLetters() {
        String result = Strings.randomAlphabetic(10);
        for (char c : result.toCharArray()) {
            assertTrue(Character.isLetter(c));
        }
    }
}
