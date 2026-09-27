package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsdigestAsHex_69a46afbTest {

    @Test
    public void testDigestAsHex() {
        // Create a sample ByteBuffer with known data
        ByteBuffer data = ByteBuffer.wrap("test".getBytes());

        // Create a DigestUtils instance with a MessageDigest (using SHA-256 as an example)
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Assert.fail("Failed to get MessageDigest instance: " + e.getMessage());
        }
        DigestUtils digestUtils = new DigestUtils(messageDigest);

        // Call the method under test
        String result = digestUtils.digestAsHex(data);

        // Verify the result is not null and has the expected length for SHA-256 hex
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Result length should match SHA-256 hex length", 64, result.length());
    }
}
