package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.EncoderException;

public class BCodecencode_4cae3ab0Test {
    private BCodec bCodec;

    @Before
    public void setUp() {
        bCodec = new BCodec();
    }

    @After
    public void tearDown() {
        bCodec = null;
    }

    @Test
    public void testEncode_NullInput_ReturnsNull() throws EncoderException {
        String result = bCodec.encode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_NonNullInput_DelegatesToParameterizedEncode() throws EncoderException {
        // Since we can't directly verify the delegation, we check that the method is called
        // and that it returns a non-null value for a non-null input
        String input = "test";
        String result = bCodec.encode(input);
        Assert.assertNotNull(result);
    }
}
