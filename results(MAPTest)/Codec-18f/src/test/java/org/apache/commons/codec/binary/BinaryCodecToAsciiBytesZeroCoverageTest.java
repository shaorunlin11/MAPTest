package org.apache.commons.codec.binary;

import org.junit.Test;

public class BinaryCodecToAsciiBytesZeroCoverageTest {
    @Test
    public void testToAsciiBytesWithNullInput() {
        byte[] result = BinaryCodec.toAsciiBytes(null);
        // Verify that the method returns the empty byte array when input is null
        assert result.length == 0;
    }

    @Test
    public void testToAsciiBytesWithNonNullInput() {
        byte[] raw = { (byte) 0 };
        byte[] result = BinaryCodec.toAsciiBytes(raw);
        // Verify that the method returns an array of length 8 when input is not null
        assert result.length == 8;
    }

@Test
    public void testToAsciiBytesWithNonEmptyInputAndBitsLengthGreaterThanZero() {
        byte[] raw = { (byte) 255 };
        byte[] result = BinaryCodec.toAsciiBytes(raw);
        // Verify that the method returns an array of length 8 when input is not null
        assert result.length == 8;
        // Verify that the first byte's bits are all set to '1'
        for (int i = 0; i < 8; i++) {
            assert result[i] == '1';
        }
    }
}
