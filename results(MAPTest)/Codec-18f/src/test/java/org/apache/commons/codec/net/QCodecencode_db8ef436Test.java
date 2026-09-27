package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.util.BitSet;

public class QCodecencode_db8ef436Test {
    private QCodec qCodec;

    @Before
    public void setUp() {
        qCodec = new QCodec(Charset.forName("UTF-8"));
    }

    @After
    public void tearDown() {
        qCodec = null;
    }

    @Test
    public void testEncode_NullInput_ReturnsNull() throws Exception {
        String result = qCodec.encode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_NonNullInput_DelegatesToParameterizedEncode() throws Exception {
        String input = "test";
        String expected = qCodec.encode(input, qCodec.getCharset());
        String result = qCodec.encode(input);
        Assert.assertEquals(expected, result);
    }
}
