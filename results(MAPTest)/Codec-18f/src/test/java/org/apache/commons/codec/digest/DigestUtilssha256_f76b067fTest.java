package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilssha256_f76b067fTest {

    @Test
    public void testSha256WithNonNullData() throws NoSuchAlgorithmException {
        byte[] data = "Hello, World!".getBytes();
        byte[] result = DigestUtils.sha256(data);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha256WithEmptyData() {
        byte[] data = new byte[0];
        byte[] result = DigestUtils.sha256(data);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Result length should be 32 bytes for empty input", 32, result.length);
    }

    @Test(expected = NullPointerException.class)
    public void testSha256WithNullData() {
        byte[] data = null;
        byte[] result = DigestUtils.sha256(data);
        Assert.assertNull("Result should be null when input is null", result);
    }
}
