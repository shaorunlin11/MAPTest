package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.apache.commons.codec.DecoderException;

public class URLCodecdecode_c099f152Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecodeNullInput() throws Exception {
        URLCodec codec = new URLCodec();
        Object result = codec.decode((byte[]) null);
        Assert.assertNull(result);
    }

    @Test
    public void testDecodeByteArrayInput() throws Exception {
        URLCodec codec = new URLCodec();
        byte[] input = { 0x48, 0x65, 0x6C, 0x6C, 0x6F };
        Object result = codec.decode(input);
        Assert.assertTrue(result instanceof byte[]);
        Assert.assertArrayEquals(input, (byte[]) result);
    }

    @Test
    public void testDecodeStringInput() throws Exception {
        URLCodec codec = new URLCodec();
        String input = "Hello";
        Object result = codec.decode(input);
        Assert.assertTrue(result instanceof String);
        Assert.assertEquals(input, result);
    }

    @Test
    public void testDecodeUnsupportedType() throws Exception {
        URLCodec codec = new URLCodec();
        Object input = new Object();
        thrown.expect(DecoderException.class);
        thrown.expectMessage("Objects of type " + input.getClass().getName() + " cannot be URL decoded");
        codec.decode(input);
    }
}
