package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.ByteBuffer;

public class HexencodeHex_ff8dc219Test {

    @Test
    public void testEncodeHex_withLowerCaseTrue() {
        ByteBuffer data = ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03});
        char[] result = Hex.encodeHex(data, true);
        assertEquals("010203", new String(result));
    }

    @Test
    public void testEncodeHex_withLowerCaseFalse() {
        ByteBuffer data = ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03});
        char[] result = Hex.encodeHex(data, false);
        assertEquals("010203", new String(result));
    }

    @Test
    public void testEncodeHex_withEmptyByteBuffer() {
        ByteBuffer data = ByteBuffer.allocate(0);
        char[] result = Hex.encodeHex(data, true);
        assertEquals("", new String(result));
    }

    @Test
    public void testEncodeHex_withSingleByte() {
        ByteBuffer data = ByteBuffer.wrap(new byte[]{(byte) 0xff});
        char[] result = Hex.encodeHex(data, true);
        assertEquals("ff", new String(result));
    }

    @Test
    public void testEncodeHex_withMultipleBytes() {
        ByteBuffer data = ByteBuffer.wrap(new byte[]{(byte) 0x1a, (byte) 0x2b, (byte) 0x3c});
        char[] result = Hex.encodeHex(data, true);
        assertEquals("1a2b3c", new String(result));
    }
}
