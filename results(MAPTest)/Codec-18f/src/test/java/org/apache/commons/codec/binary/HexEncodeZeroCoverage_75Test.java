package org.apache.commons.codec.binary;

import org.junit.Test;

import org.apache.commons.codec.EncoderException;


public class HexEncodeZeroCoverage_75Test {
    @Test
    public void testEncodeWithValidString() throws EncoderException {
        Hex hex = new Hex();
        String input = "test";
        Object result = hex.encode(input);
        // This test ensures that the encode method is called with a String
        // and that the getCharset() method returns a valid charset.
        // The actual encoding is not verified, but the method path is covered.
    }
}
