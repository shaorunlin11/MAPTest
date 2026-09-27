package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexencodeHex_656ddd9aTest {

    @Test
    public void testEncodeHex_lowercase() {
        byte[] data = {0x01, 0x02, 0x03};
        char[] result = Hex.encodeHex(data, true);
        assertNotNull(result);
        assertArrayEquals(new char[]{'0', '1', '0', '2', '0', '3'}, result);
    }

    @Test
    public void testEncodeHex_uppercase() {
        byte[] data = {0x01, 0x02, 0x03};
        char[] result = Hex.encodeHex(data, false);
        assertNotNull(result);
        assertArrayEquals(new char[]{'0', '1', '0', '2', '0', '3'}, result);
    }

    @Test
    public void testEncodeHex_emptyData() {
        byte[] data = {};
        char[] result = Hex.encodeHex(data, true);
        assertNotNull(result);
        assertArrayEquals(new char[]{}, result);
    }

    @Test
    public void testEncodeHex_nullData() {
        byte[] data = null;
        try {
            Hex.encodeHex(data, true);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
