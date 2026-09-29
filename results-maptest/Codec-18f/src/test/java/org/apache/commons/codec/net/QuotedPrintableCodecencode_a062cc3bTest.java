package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.BinaryDecoder;

public class QuotedPrintableCodecencode_a062cc3bTest {
    private QuotedPrintableCodec codec;

    @Before
    public void setUp() {
        codec = new QuotedPrintableCodec();
    }

    @After
    public void tearDown() {
        codec = null;
    }

    @Test
    public void testEncodeNullInput() throws EncoderException {
        Object result = codec.encode((String) null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncodeByteArrayInput() throws EncoderException {
        byte[] input = "test".getBytes();
        Object result = codec.encode(input);
        Assert.assertTrue(result instanceof byte[]);
    }

    @Test
    public void testEncodeStringInput() throws EncoderException {
        String input = "test";
        Object result = codec.encode(input);
        Assert.assertTrue(result instanceof String);
    }

    @Test
    public void testEncodeUnsupportedType() throws EncoderException {
        Object input = new Object();
        try {
            codec.encode(input);
            Assert.fail("Expected EncoderException was not thrown");
        } catch (EncoderException e) {
            Assert.assertTrue(e.getMessage().contains(input.getClass().getName()));
        }
    }
}
