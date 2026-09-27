package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.Assert.assertNotNull;

public class HmacUtilsgetHmacSha384Test {

    @Test
    public void testGetHmacSha384() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "testKey".getBytes();
        Mac hmacSha384 = HmacUtils.getHmacSha384(key);
        assertNotNull("HMAC-SHA384 instance should not be null", hmacSha384);
    }
}
