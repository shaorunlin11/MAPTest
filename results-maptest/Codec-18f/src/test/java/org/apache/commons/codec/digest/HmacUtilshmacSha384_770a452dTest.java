package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilshmacSha384_770a452dTest {

    @Test
    public void testHmacSha384() throws NoSuchAlgorithmException, InvalidKeyException {
        String key = "secret";
        String valueToDigest = "data";
        byte[] result = HmacUtils.hmacSha384(key, valueToDigest);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.length > 0);
    }

    @Test
    public void testHmacSha384WithNullKey() {
        String key = null;
        String valueToDigest = "data";
        try {
            HmacUtils.hmacSha384(key, valueToDigest);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testHmacSha384WithNullValue() {
        String key = "secret";
        String valueToDigest = null;
        try {
            HmacUtils.hmacSha384(key, valueToDigest);
            // No exception expected, continue
        } catch (NullPointerException e) {
            Assert.fail("Unexpected NullPointerException");
        }
    }
}
