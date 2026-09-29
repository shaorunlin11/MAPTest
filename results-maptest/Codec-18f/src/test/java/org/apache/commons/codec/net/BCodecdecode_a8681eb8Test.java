package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.apache.commons.codec.DecoderException;

public class BCodecdecode_a8681eb8Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecodeNullInput() throws DecoderException {
        BCodec codec = new BCodec();
        Object result = codec.decode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testDecodeStringInput() throws DecoderException {
        BCodec codec = new BCodec();
        String input = "=?UTF-8?B?dGVzdA==?=";
        Object result = codec.decode(input);
        Assert.assertEquals("test", result);
    }

    @Test
    public void testDecodeNonStringInput() throws DecoderException {
        BCodec codec = new BCodec();
        Integer input = 123;
        thrown.expect(DecoderException.class);
        thrown.expectMessage("Objects of type java.lang.Integer cannot be decoded using BCodec");
        codec.decode(input);
    }
}
