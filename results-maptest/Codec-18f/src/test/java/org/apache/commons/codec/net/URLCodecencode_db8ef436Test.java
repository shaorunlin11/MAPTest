package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;

public class URLCodecencode_db8ef436Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode_nullInput_returnsNull() throws Exception {
        URLCodec codec = new URLCodec();
        String result = codec.encode((String) null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_validInput_encodesSuccessfully() throws Exception {
        URLCodec codec = new URLCodec();
        String input = "Hello World!";
        String result = codec.encode(input);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(input, result);
    }

    @Test
    public void testEncode_unsupportedCharset_throwsEncoderException() throws Exception {
        thrown.expect(org.apache.commons.codec.EncoderException.class);
        thrown.expectMessage("invalidCharset");

        URLCodec codec = new URLCodec("invalidCharset");
        codec.encode("test");
    }
}
