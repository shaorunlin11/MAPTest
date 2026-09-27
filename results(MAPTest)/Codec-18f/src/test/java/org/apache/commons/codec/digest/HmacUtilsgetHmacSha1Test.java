package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class HmacUtilsgetHmacSha1Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetHmacSha1() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "testKey".getBytes();
        Mac hmacSha1 = HmacUtils.getHmacSha1(key);
        Assert.assertNotNull(hmacSha1);
    }

    @Test
    public void testGetHmacSha1WithNullKey() {
        thrown.expect(IllegalArgumentException.class);
        byte[] key = null;
        HmacUtils.getHmacSha1(key);
    }
}
