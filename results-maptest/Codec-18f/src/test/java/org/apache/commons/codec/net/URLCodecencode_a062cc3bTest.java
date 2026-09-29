package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.apache.commons.codec.EncoderException;

public class URLCodecencode_a062cc3bTest {
    private URLCodec codec;

    @Before
    public void setUp() {
        codec = new URLCodec();
    }

    @After
    public void tearDown() {
        codec = null;
    }

    @Test
    public void testEncodeNullInputReturnsNull() throws EncoderException {
        Object result = codec.encode((String) null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncodeByteArrayDelegatesToByteEncoding() throws EncoderException {
        byte[] input = {0x48, 0x65, 0x6C, 0x6C, 0x6F};
        Object result = codec.encode(input);
        Assert.assertTrue(result instanceof byte[]);
    }

    @Test
    public void testEncodeStringDelegatesToStringEncoding() throws EncoderException {
        String input = "Hello World";
        Object result = codec.encode(input);
        Assert.assertTrue(result instanceof String);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeUnsupportedTypeThrowsEncoderException() throws EncoderException {
        Object input = new Object();
        codec.encode(input);
    }

@Test
    public void testEncodeNullObjectReturnsNull() throws EncoderException {
        Object result = codec.encode((Object) null);
        Assert.assertNull(result);
    }
}
