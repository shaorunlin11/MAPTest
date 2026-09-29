package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilshmacSha256_8646796bTest {

    @Test
    public void testHmacSha256() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "data".getBytes();

        byte[] result = HmacUtils.hmacSha256(key, valueToDigest);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.length > 0);
    }
}
