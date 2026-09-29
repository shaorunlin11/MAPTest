package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class HexencodeHexString_40a425a9Test {
    @Test
    public void testEncodeHexString() {
        // Test with a simple byte buffer
        ByteBuffer data = ByteBuffer.allocate(3);
        data.put((byte) 0x48);
        data.put((byte) 0x65);
        data.put((byte) 0x6C);
        data.flip();

        char[] resultChars = Hex.encodeHex(data);
        String result = new String(resultChars);
        assertEquals("48656c", result);
    }

    @Test
    public void testEncodeHexStringWithZeroBytes() {
        ByteBuffer data = ByteBuffer.allocate(2);
        data.put((byte) 0x00);
        data.put((byte) 0x00);
        data.flip();

        char[] resultChars = Hex.encodeHex(data);
        String result = new String(resultChars);
        assertEquals("0000", result);
    }

    @Test
    public void testEncodeHexStringWithMaxValueBytes() {
        ByteBuffer data = ByteBuffer.allocate(2);
        data.put((byte) 0xFF);
        data.put((byte) 0xFF);
        data.flip();

        char[] resultChars = Hex.encodeHex(data);
        String result = new String(resultChars);
        assertEquals("ffff", result);
    }

    @Test
    public void testEncodeHexStringWithMixedValues() {
        ByteBuffer data = ByteBuffer.allocate(4);
        data.put((byte) 0x1A);
        data.put((byte) 0x2B);
        data.put((byte) 0x3C);
        data.put((byte) 0x4D);
        data.flip();

        char[] resultChars = Hex.encodeHex(data);
        String result = new String(resultChars);
        assertEquals("1a2b3c4d", result);
    }
}
