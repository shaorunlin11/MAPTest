package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtilshmacHex_437ab8f9Test {

    @Test
    public void testHmacHex() throws NoSuchAlgorithmException, InvalidKeyException {
        // Arrange
        String algorithm = "HmacSHA256";
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "test".getBytes();

        // Create a Mac instance manually to avoid relying on HmacUtils constructor
        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        mac.init(keySpec);

        // Create HmacUtils instance using the algorithm and key
        HmacUtils hmacUtils = new HmacUtils(algorithm, key);

        // Act
        String result = hmacUtils.hmacHex(valueToDigest);

        // Assert
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a hexadecimal string", result.matches("[0-9a-fA-F]+"));
    }
}
