package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;

import java.nio.charset.Charset;
import java.util.Base64;

public class BCodecdoDecodingTest {

    @Test
    public void testDoDecoding_NullInput_ReturnsNull() {
        BCodec codec = new BCodec();
        byte[] result = codec.doDecoding(null);
        Assert.assertNull(result);
    }

    @Test
    public void testDoDecoding_ValidInput_DecodesBase64() throws Exception {
        BCodec codec = new BCodec();
        String original = "Hello, World!";
        byte[] encoded = Base64.getEncoder().encode(original.getBytes(Charset.forName("UTF-8")));
        byte[] result = codec.doDecoding(encoded);
        Assert.assertEquals(original, new String(result, Charset.forName("UTF-8")));
    }
}
