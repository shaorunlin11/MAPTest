package org.apache.commons.codec.binary;

import org.junit.Test;

public class BinaryCodecFromAsciiZeroCoverageTest {
    @Test
    public void testFromAsciiNull() {
        char[] ascii = null;
        byte[] result = BinaryCodec.fromAscii(ascii);
        // Verify that the method returns the empty byte array when ascii is null
        assert result.length == 0;
    }

    @Test
    public void testFromAsciiEmpty() {
        char[] ascii = new char[0];
        byte[] result = BinaryCodec.fromAscii(ascii);
        // Verify that the method returns the empty byte array when ascii is empty
        assert result.length == 0;
    }

@Test
    public void testFromAsciiNonEmpty() {
        char[] ascii = {'0', '1', '0', '1', '0', '1', '0', '1'};
        byte[] result = BinaryCodec.fromAscii(ascii);
        // Verify that the method processes non-empty ascii correctly
        assert result.length == 1;
        assert result[0] == (byte) 0x55;
    }
}
