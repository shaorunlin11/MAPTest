package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilshmacSha512_d009f996Test {

    @Test
    public void testHmacSha512() throws NoSuchAlgorithmException, InvalidKeyException {
        String key = "secretKey";
        String valueToDigest = "testValue";

        byte[] result = HmacUtils.hmacSha512(key, valueToDigest);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result length should be greater than 0", result.length > 0);
    }
}
