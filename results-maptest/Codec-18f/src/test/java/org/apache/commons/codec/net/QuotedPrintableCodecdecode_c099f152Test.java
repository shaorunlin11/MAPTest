package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.codec.DecoderException;

public class QuotedPrintableCodecdecode_c099f152Test {

    @Test
    public void testDecodeNullInput() throws DecoderException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        Object result = codec.decode((byte[]) null);
        Assert.assertNull(result);
    }

    @Test
    public void testDecodeByteArrayInput() throws DecoderException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        byte[] input = "test".getBytes();
        Object result = codec.decode(input);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof byte[]);
    }

    @Test
    public void testDecodeStringInput() throws DecoderException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String input = "test";
        Object result = codec.decode(input);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof String);
    }

    @Test(expected = DecoderException.class)
    public void testDecodeUnsupportedType() throws DecoderException {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        Integer input = 123;
        codec.decode(input);
    }
}
