package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexencodeHex_98cfb0ffTest {

    @Test
    public void testEncodeHex() {
        byte[] data = {0x01, 0x02, (byte) 0xFF};
        char[] result = Hex.encodeHex(data);

        // Expected hexadecimal representation: "0102ff"
        char[] expected = {'0', '1', '0', '2', 'f', 'f'};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeHexEmptyArray() {
        byte[] data = {};
        char[] result = Hex.encodeHex(data);
        assertTrue(result.length == 0);
    }

    @Test
    public void testEncodeHexNullInput() {
        byte[] data = null;
        try {
            Hex.encodeHex(data);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
