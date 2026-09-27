package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilshmacSha384_86c84d78Test {

    @Test
    public void testHmacSha384() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "data".getBytes();
        byte[] result = HmacUtils.hmacSha384(key, valueToDigest);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result length should be at least 1", result.length > 0);
    }
}
