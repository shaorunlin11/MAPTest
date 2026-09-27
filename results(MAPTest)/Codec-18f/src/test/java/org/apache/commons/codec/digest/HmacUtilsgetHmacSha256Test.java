package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtilsgetHmacSha256Test {

    @Test
    public void testGetHmacSha256WithValidKey() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "secret".getBytes();
        Mac result = HmacUtils.getHmacSha256(key);
        assertNotNull("Result should not be null", result);
        // Verify that the Mac instance is initialized with HMAC-SHA256
        assertEquals("HmacSHA256", result.getAlgorithm());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHmacSha256WithNullKey() {
        HmacUtils.getHmacSha256(null);
    }
}
