package org.apache.commons.codec.net;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.DecoderException;


public class QuotedPrintableCodecDecodeZeroCoverageTest {
    @Test
    public void testDecode() throws DecoderException, UnsupportedCharsetException, IllegalCharsetNameException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec("UTF-8");
        String input = "Hello=20World";
        String expected = "Hello World";
        assertEquals(expected, codec.decode(input));
    }
}
