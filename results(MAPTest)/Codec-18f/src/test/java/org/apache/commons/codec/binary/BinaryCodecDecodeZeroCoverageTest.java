package org.apache.commons.codec.binary;

import org.junit.Test;

import org.apache.commons.codec.DecoderException;


public class BinaryCodecDecodeZeroCoverageTest {
    @Test
    public void testDecodeWithCharArray() throws DecoderException {
        BinaryCodec codec = new BinaryCodec();
        char[] ascii = {'0', '1', '0', '1'};
        byte[] result = (byte[]) codec.decode(ascii);
        // Add assertions if needed to verify the decoded value
    }
}
