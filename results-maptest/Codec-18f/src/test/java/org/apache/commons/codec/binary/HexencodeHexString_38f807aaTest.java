package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexencodeHexString_38f807aaTest {

    @Test
    public void testEncodeHexStringWithLowercase() {
        byte[] data = {(byte) 0x1a, (byte) 0x2b, (byte) 0x3c};
        String result = Hex.encodeHexString(data, true);
        assertEquals("1a2b3c", result);
    }

    @Test
    public void testEncodeHexStringWithUppercase() {
        byte[] data = {(byte) 0x1a, (byte) 0x2b, (byte) 0x3c};
        String result = Hex.encodeHexString(data, false);
        assertEquals("1A2B3C", result);
    }

    @Test
    public void testEncodeHexStringEmptyArray() {
        byte[] data = {};
        String result = Hex.encodeHexString(data, true);
        assertEquals("", result);
    }

    @Test
    public void testEncodeHexStringSingleByte() {
        byte[] data = {(byte) 0xff};
        String result = Hex.encodeHexString(data, true);
        assertEquals("ff", result);
    }

    @Test
    public void testEncodeHexStringMultipleBytes() {
        byte[] data = {(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04};
        String result = Hex.encodeHexString(data, true);
        assertEquals("01020304", result);
    }

    @Test
    public void testEncodeHexStringWithNullData() {
        byte[] data = null;
        try {
            Hex.encodeHexString(data, true);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
