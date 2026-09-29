package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.EncoderException;

public class BCodecencode_23425c21Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode_NullValue_ReturnsNull() throws EncoderException {
        BCodec codec = new BCodec();
        String result = codec.encode(null, Charset.forName("UTF-8"));
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_NonNullValue_DelegatesToEncodeText() throws EncoderException {
        BCodec codec = new BCodec();
        String value = "test";
        Charset charset = Charset.forName("UTF-8");
        String result = codec.encode(value, charset);
        // This test only verifies that the method doesn't throw an exception
        // and returns a non-null value, assuming encodeText is correctly implemented
        Assert.assertNotNull(result);
    }
}
