package org.apache.commons.codec.binary;

import org.junit.Test;

public class BinaryCodecToAsciiCharsZeroCoverageTest {
    @Test
    public void testToAsciiCharsWithNullInput() {
        // Test case where raw is null
        char[] result = BinaryCodec.toAsciiChars(null);
        // Since the method returns EMPTY_CHAR_ARRAY when raw is null, we expect it to be empty
        // This test ensures that the code path for null input is executed
    }

    @Test
    public void testToAsciiCharsWithNonNullInput() {
        // Test case where raw is not null
        byte[] raw = { (byte) 0 };
        char[] result = BinaryCodec.toAsciiChars(raw);
        // This test ensures that the code path for non-null input is executed
    }

@Test
    public void testToAsciiCharsWithNonEmptyInput() {
        // Test case where raw is not empty and BITS.length > 0
        byte[] raw = { (byte) 255 };
        char[] result = BinaryCodec.toAsciiChars(raw);
        // This test ensures that the code path for non-empty input is executed
        // and covers target lines 280
    }
}
