package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypescopyHexCharsTest {
    @Test
    public void testCopyHexCharsReturnsClone() {
        char[] result = CharTypes.copyHexChars();
        char[] original = "0123456789ABCDEF".toCharArray();
        assertNotSame("Should return a clone, not the original array", original, result);
    }

    @Test
    public void testCopyHexCharsContainsCorrectCharacters() {
        char[] result = CharTypes.copyHexChars();
        char[] expected = "0123456789ABCDEF".toCharArray();
        assertArrayEquals("Should contain the correct hexadecimal characters", expected, result);
    }

    @Test
    public void testCopyHexCharsHasCorrectLength() {
        char[] result = CharTypes.copyHexChars();
        assertEquals("Should have exactly 16 characters", 16, result.length);
    }
}
