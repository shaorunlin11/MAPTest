package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtilsgetInitializedMac_8734b981Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetInitializedMac_NullKey() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Null key");
        HmacUtils.getInitializedMac("HmacSHA256", null);
    }

    @Test
    public void testGetInitializedMac_ValidKeyAndAlgorithm() throws Exception {
        byte[] key = "testKey".getBytes();
        Mac mac = HmacUtils.getInitializedMac("HmacSHA256", key);
        Assert.assertNotNull(mac);
    }

    @Test
    public void testGetInitializedMac_InvalidAlgorithm() {
        thrown.expect(IllegalArgumentException.class);
        HmacUtils.getInitializedMac("InvalidAlgorithm", "testKey".getBytes());
    }

    @Test
    public void testGetInitializedMac_InvalidKeyForAlgorithm() {
        thrown.expect(IllegalArgumentException.class);
        HmacUtils.getInitializedMac("HmacSHA256", new byte[0]);
    }
}
