package com.fasterxml.jackson.core.io;
import org.junit.Test;
import static org.junit.Assert.*;
public class CharTypescharToHexTest {
    @Test
    public void testCharToHexWithValidInput() {
        // Test with a valid ASCII character (e.g., 'A' which is 65)
        assertEquals(10, CharTypes.charToHex('A'));

        // Test with a valid ASCII character (e.g., '0' which is 48)
        assertEquals(0, CharTypes.charToHex('0'));

        // Test with a valid ASCII character (e.g., '9' which is 57)
        assertEquals(9, CharTypes.charToHex('9'));

        // Test with a valid ASCII character (e.g., 'F' which is 70)
        assertEquals(15, CharTypes.charToHex('F'));
    }

@Test
    public void testCharToHexWithInvalidInput() {
        // Test with a character greater than 127
        assertEquals(-1, CharTypes.charToHex(128));
    }
}
