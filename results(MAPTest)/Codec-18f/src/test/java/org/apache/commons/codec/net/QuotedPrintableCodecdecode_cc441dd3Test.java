package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.StringUtils;

public class QuotedPrintableCodecdecode_cc441dd3Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecode_NullInput_ReturnsNull() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.decode(null, Charset.defaultCharset());
        Assert.assertNull(result);
    }

    @Test
    public void testDecode_ValidInput_DecodesUsingProvidedCharset() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String input = "Hello=20World";
        String expected = "Hello World";
        byte[] bytes = StringUtils.getBytesUsAscii(input);
        String result = codec.decode(input, Charset.defaultCharset());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testDecode_InvalidCharset_ThrowsDecoderException() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String input = "Hello=20World";
        thrown.expect(UnsupportedCharsetException.class);
        codec.decode(input, Charset.forName("invalid"));
    }
}
