package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.apache.commons.codec.EncoderException;

public class QCodecencode_a062cc3bTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode_nullInput_returnsNull() throws Exception {
        QCodec qCodec = new QCodec();
        Object result = qCodec.encode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_stringInput_delegatesToStringEncode() throws Exception {
        QCodec qCodec = new QCodec();
        String input = "test";
        Object result = qCodec.encode(input);
        Assert.assertTrue(result instanceof String);
        Assert.assertEquals("=?UTF-8?Q?test?=", result);
    }

    @Test
    public void testEncode_nonStringInput_throwsEncoderException() throws Exception {
        QCodec qCodec = new QCodec();
        thrown.expect(EncoderException.class);
        thrown.expectMessage("Objects of type java.lang.Integer cannot be encoded using Q codec");
        qCodec.encode(123);
    }
}
