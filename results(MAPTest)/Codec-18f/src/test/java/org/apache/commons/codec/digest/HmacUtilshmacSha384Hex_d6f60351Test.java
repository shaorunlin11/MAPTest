package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilshmacSha384Hex_d6f60351Test {

    @Test
    public void testHmacSha384Hex() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "data".getBytes();

        String result = HmacUtils.hmacSha384Hex(key, valueToDigest);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }
}
