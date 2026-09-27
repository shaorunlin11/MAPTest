package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexencodeHex_d8d49470Test {

    @Test
    public void testEncodeHex() {
        byte[] data = {0x12, (byte) 0x34, 0x56, (byte) 0x78};
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] result = Hex.encodeHex(data, toDigits);

        assertEquals("12345678", new String(result));
    }

    @Test
    public void testEncodeHexWithUpperCaseDigits() {
        byte[] data = {0x12, (byte) 0x34, 0x56, (byte) 0x78};
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        char[] result = Hex.encodeHex(data, toDigits);

        assertEquals("12345678", new String(result));
    }

    @Test
    public void testEncodeHexEmptyArray() {
        byte[] data = {};
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] result = Hex.encodeHex(data, toDigits);

        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeHexSingleByte() {
        byte[] data = {0x0A};
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] result = Hex.encodeHex(data, toDigits);

        assertEquals("0a", new String(result));
    }

    @Test
    public void testEncodeHexNegativeByte() {
        byte[] data = {(byte) 0xFF};
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] result = Hex.encodeHex(data, toDigits);

        assertEquals("ff", new String(result));
    }
}
