package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.codec.DecoderException;

public class QuotedPrintableCodecdecode_19c55be3Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecode() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        byte[] input = "Hello=20World".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.decode(input);
        Assert.assertEquals("Hello World", new String(result, Charset.forName("UTF-8")));
    }

    @Test
    public void testDecodeWithCustomCharset() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec(Charset.forName("UTF-8"));
        byte[] input = "Hello=20World".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.decode(input);
        Assert.assertEquals("Hello World", new String(result, Charset.forName("UTF-8")));
    }

    @Test
    public void testDecodeWithStrictMode() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec(true);
        byte[] input = "Hello=20World".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.decode(input);
        Assert.assertEquals("Hello World", new String(result, Charset.forName("UTF-8")));
    }

    @Test
    public void testDecodeWithInvalidInput() throws Exception {
        thrown.expect(DecoderException.class);
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        byte[] input = "Hello=ZWorld".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.decode(input);
        Assert.assertNotEquals("Hello World", new String(result, Charset.forName("UTF-8")));
    }
}
