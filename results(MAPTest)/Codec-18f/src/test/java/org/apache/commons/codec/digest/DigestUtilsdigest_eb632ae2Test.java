package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsdigest_eb632ae2Test {

    @Test
    public void testDigest() {
        // Arrange
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            DigestUtils digestUtils = new DigestUtils(messageDigest);
            byte[] data = "Hello, world!".getBytes();

            // Act
            byte[] result = digestUtils.digest(data);

            // Assert
            Assert.assertNotNull(result);
            Assert.assertTrue(result.length > 0);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception if the algorithm is not available
            Assert.fail("Could not get MessageDigest instance: " + e.getMessage());
        }
    }
}
