package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.io.UnsupportedEncodingException;

public class QuotedPrintableCodecdecode_5af0b36bTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecode_NullInput_ReturnsNull() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.decode(null, "UTF-8");
        Assert.assertNull(result);
    }

    @Test
    public void testDecode_ValidInput_DecodesWithSpecifiedCharset() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String input = "Hello=20World";
        String expected = "Hello World";
        String result = codec.decode(input, "UTF-8");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testDecode_InvalidCharset_ThrowsUnsupportedEncodingException() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        thrown.expect(UnsupportedEncodingException.class);
        codec.decode("test", "invalidCharset");
    }

    @Test
    public void testDecode_NullCharset_ThrowsNullPointerException() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        thrown.expect(NullPointerException.class);
        codec.decode("test", (String) null);
    }
}
