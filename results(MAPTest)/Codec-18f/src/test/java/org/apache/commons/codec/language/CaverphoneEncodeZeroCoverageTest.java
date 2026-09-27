package org.apache.commons.codec.language;

import org.junit.Test;

import org.apache.commons.codec.EncoderException;


public class CaverphoneEncodeZeroCoverageTest {
    @Test
    public void testEncodeWithNonStringObject() {
        Caverphone caverphone = new Caverphone();
        Object obj = new Object();
        try {
            caverphone.encode(obj);
        } catch (EncoderException e) {
            // Expected exception for non-String input
        }
    }
}
