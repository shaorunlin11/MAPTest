package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import java.io.UnsupportedEncodingException;

public class URLCodecencode_6ecb30d2Test {
    private URLCodec urlCodec;

    @Before
    public void setUp() {
        urlCodec = new URLCodec();
    }

    @After
    public void tearDown() {
        urlCodec = null;
    }

    @Test
    public void testEncode_NullInput_ReturnsNull() throws UnsupportedEncodingException {
        String result = urlCodec.encode(null, "UTF-8");
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_ValidInput_EncodesCorrectly() throws UnsupportedEncodingException {
        String input = "Hello World!";
        String expected = "Hello+World%21";
        String result = urlCodec.encode(input, "UTF-8");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testEncode_UnsupportedCharset_ThrowsException() throws UnsupportedEncodingException {
        try {
            urlCodec.encode("test", "invalidCharset");
            Assert.fail("Expected UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e) {
            // Expected exception
        }
    }
}
