package org.apache.commons.codec.binary;

import org.junit.Test;

public class BinaryCodecToAsciiStringZeroCoverageTest {
    @Test
    public void testToAsciiString() {
        byte[] raw = new byte[] { 0, 1, 2, 3 };
        String result = BinaryCodec.toAsciiString(raw);
        // This test is designed to cover line 299 of BinaryCodec#toAsciiString
        // by ensuring the method is called with a non-null input.
    }
}
