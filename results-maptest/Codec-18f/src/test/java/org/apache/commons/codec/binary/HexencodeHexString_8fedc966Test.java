package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HexencodeHexString_8fedc966Test {

    @Test
    public void testEncodeHexStringWithLowercase() {
        ByteBuffer data = ByteBuffer.allocate(2);
        data.put((byte) 0x1A);
        data.put((byte) 0x2B);
        data.flip();

        String result = Hex.encodeHexString(data, true);
        assertEquals("1a2b", result);
    }

    @Test
    public void testEncodeHexStringWithUppercase() {
        ByteBuffer data = ByteBuffer.allocate(2);
        data.put((byte) 0x1A);
        data.put((byte) 0x2B);
        data.flip();

        String result = Hex.encodeHexString(data, false);
        assertEquals("1A2B", result);
    }

    @Test
    public void testEncodeHexStringEmptyBuffer() {
        ByteBuffer data = ByteBuffer.allocate(0);
        String result = Hex.encodeHexString(data, true);
        assertEquals("", result);
    }

    @Test
    public void testEncodeHexStringSingleByte() {
        ByteBuffer data = ByteBuffer.allocate(1);
        data.put((byte) 0xFF);
        data.flip();

        String result = Hex.encodeHexString(data, true);
        assertEquals("ff", result);
    }

    @Test
    public void testEncodeHexStringMultipleBytes() {
        ByteBuffer data = ByteBuffer.allocate(3);
        data.put((byte) 0x01);
        data.put((byte) 0x02);
        data.put((byte) 0x03);
        data.flip();

        String result = Hex.encodeHexString(data, true);
        assertEquals("010203", result);
    }
}
