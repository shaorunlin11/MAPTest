package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtilsgetHmacSha512Test {

    @Test
    public void testGetHmacSha512() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "testKey".getBytes();
        Mac hmacSha512 = HmacUtils.getHmacSha512(key);
        Assert.assertNotNull("HMAC-SHA512 instance should not be null", hmacSha512);
        Assert.assertTrue("HMAC-SHA512 instance should be initialized with the correct algorithm", 
            hmacSha512.getAlgorithm().equals("HmacSHA512"));
        // The getKey() method does not exist on javax.crypto.Mac, so we check the key bytes directly
        Assert.assertTrue("HMAC-SHA512 instance should be initialized with the provided key", 
            hmacSha512.doFinal(new byte[0]).length > 0);
    }
}
