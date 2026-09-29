package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.codec.binary.Base64;

public class BCodecdoEncodingTest {
    @Test
    public void testDoEncoding_NullInput_ReturnsNull() {
        BCodec codec = new BCodec();
        byte[] result = codec.doEncoding(null);
        Assert.assertNull(result);
    }

    @Test
    public void testDoEncoding_ValidInput_EncodesWithBase64() {
        BCodec codec = new BCodec();
        byte[] input = "test".getBytes();
        byte[] expected = Base64.encodeBase64(input);
        byte[] result = codec.doEncoding(input);
        Assert.assertArrayEquals(expected, result);
    }
}
