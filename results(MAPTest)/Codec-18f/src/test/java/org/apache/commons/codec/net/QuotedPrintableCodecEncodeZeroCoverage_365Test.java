package org.apache.commons.codec.net;

import org.junit.Test;

import org.apache.commons.codec.EncoderException;


public class QuotedPrintableCodecEncodeZeroCoverage_365Test {
    @Test
    public void testEncode() throws EncoderException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.encode("test");
    }
}
