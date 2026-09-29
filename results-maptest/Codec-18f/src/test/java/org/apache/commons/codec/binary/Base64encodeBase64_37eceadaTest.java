package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class Base64encodeBase64_37eceadaTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncodeBase64_NullInput() {
        byte[] result = Base64.encodeBase64(null, false, false, Integer.MAX_VALUE);
        Assert.assertNull(result);
    }

    @Test
    public void testEncodeBase64_EmptyInput() {
        byte[] result = Base64.encodeBase64(new byte[0], false, false, Integer.MAX_VALUE);
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void testEncodeBase64_NormalEncoding() {
        byte[] input = "Hello, World!".getBytes();
        byte[] result = Base64.encodeBase64(input, false, false, Integer.MAX_VALUE);
        Assert.assertNotNull(result);
        Assert.assertNotSame(input, result);
    }

    @Test
    public void testEncodeBase64_ChunkedEncoding() {
        byte[] input = "Hello, World!".getBytes();
        byte[] result = Base64.encodeBase64(input, true, false, Integer.MAX_VALUE);
        Assert.assertNotNull(result);
        Assert.assertNotSame(input, result);
    }

    @Test
    public void testEncodeBase64_UrlSafeEncoding() {
        byte[] input = "Hello, World!".getBytes();
        byte[] result = Base64.encodeBase64(input, false, true, Integer.MAX_VALUE);
        Assert.assertNotNull(result);
        Assert.assertNotSame(input, result);
    }

    @Test
    public void testEncodeBase64_MaxResultSizeExceeded() {
        byte[] input = new byte[1024 * 1024]; // 1MB
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Input array too big");
        Base64.encodeBase64(input, false, false, 100);
    }
}
