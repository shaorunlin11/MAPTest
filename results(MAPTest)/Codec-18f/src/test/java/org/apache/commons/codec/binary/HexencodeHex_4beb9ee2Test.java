package org.apache.commons.codec.binary;

import org.junit.Test;
import java.nio.ByteBuffer;
import static org.junit.Assert.*;

public class HexencodeHex_4beb9ee2Test {

    @Test
    public void testEncodeHex_BufferAndDigits() {
        // Arrange
        ByteBuffer data = ByteBuffer.wrap(new byte[]{0x1A, 0x2B, 0x3C});
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        // Act
        char[] result = Hex.encodeHex(data, toDigits);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Result length should be 6", 6, result.length);
        assertEquals("First character should be '1'", '1', result[0]);
        assertEquals("Second character should be 'a'", 'a', result[1]);
        assertEquals("Third character should be '2'", '2', result[2]);
        assertEquals("Fourth character should be 'b'", 'b', result[3]);
        assertEquals("Fifth character should be '3'", '3', result[4]);
        assertEquals("Sixth character should be 'c'", 'c', result[5]);
    }

    @Test
    public void testEncodeHex_BufferAndDigits_NullData() {
        // Arrange
        ByteBuffer data = null;
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        // Act & Assert
        try {
            Hex.encodeHex(data, toDigits);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testEncodeHex_BufferAndDigits_NullToDigits() {
        // Arrange
        ByteBuffer data = ByteBuffer.wrap(new byte[]{0x1A, 0x2B, 0x3C});
        char[] toDigits = null;

        // Act & Assert
        try {
            Hex.encodeHex(data, toDigits);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
