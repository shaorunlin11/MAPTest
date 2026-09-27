package org.apache.commons.codec.binary;

import org.junit.Test;

import org.apache.commons.codec.EncoderException;


public class BinaryCodecEncodeZeroCoverage_51Test {
    @Test
    public void testEncodeWithNonByteArrayInput() {
        BinaryCodec codec = new BinaryCodec();
        Object raw = "not a byte array";
        try {
            codec.encode(raw);
        } catch (EncoderException e) {
            // Expected exception for non-byte array input
        }
    }
}
