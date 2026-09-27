package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.codec.digest.HmacAlgorithms;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilsgetInitializedMac_938b3be3Test {

    @Test
    public void testGetInitializedMac() throws NoSuchAlgorithmException, InvalidKeyException {
        // Arrange
        HmacAlgorithms algorithm = HmacAlgorithms.HMAC_SHA_256;
        byte[] key = "testKey".getBytes();

        // Act
        Mac result = HmacUtils.getInitializedMac(algorithm, key);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof Mac);
    }
}
