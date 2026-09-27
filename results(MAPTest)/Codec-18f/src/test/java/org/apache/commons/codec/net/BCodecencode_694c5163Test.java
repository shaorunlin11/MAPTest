package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.apache.commons.codec.EncoderException;

public class BCodecencode_694c5163Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncodeNullInput() throws EncoderException {
        BCodec codec = new BCodec();
        Object result = codec.encode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncodeStringInput() throws EncoderException {
        BCodec codec = new BCodec();
        String input = "test";
        Object result = codec.encode(input);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof String);
    }

    @Test
    public void testEncodeNonStringInput() throws EncoderException {
        BCodec codec = new BCodec();
        Integer input = 123;
        thrown.expect(EncoderException.class);
        thrown.expectMessage("Objects of type java.lang.Integer cannot be encoded using BCodec");
        codec.encode(input);
    }
}
