package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base32DecodeZeroCoverageTest {
    @Test
    public void testDecodeWithEofTrue() {
        // Create a context with context.eof set to true
        Base32.Context context = new Base32.Context();
        context.eof = true;

        // Create a Base32 instance
        Base32 base32 = new Base32();

        // Call the decode method with parameters that would reach line 340
        byte[] in = new byte[0];
        base32.decode(in, 0, 0, context);
    }

@Test
    public void testDecodeWithEofFalseAndInAvailNegative() {
        // Create a context with context.eof set to false
        Base32.Context context = new Base32.Context();
        context.eof = false;

        // Create a Base32 instance
        Base32 base32 = new Base32();

        // Call the decode method with parameters that would reach line 342
        byte[] in = new byte[0];
        base32.decode(in, 0, -1, context);
    }
}
