package org.apache.commons.codec.language;

import org.junit.Test;

import org.apache.commons.codec.EncoderException;


public class NysiisEncodeZeroCoverageTest {
    @Test
    public void testEncodeWithNonStringObject() {
        Nysiis nysiis = new Nysiis();
        Object nonStringObject = new Object();
        try {
            nysiis.encode(nonStringObject);
        } catch (EncoderException e) {
            // Expected exception for non-String input
        }
    }
}
