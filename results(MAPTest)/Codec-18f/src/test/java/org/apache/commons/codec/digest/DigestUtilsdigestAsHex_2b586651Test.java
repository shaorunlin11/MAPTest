package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsdigestAsHex_2b586651Test {

    @Test
    public void testDigestAsHex() throws NoSuchAlgorithmException {
        // Create a MessageDigest instance for SHA-1
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        // Create a DigestUtils instance with the MessageDigest
        DigestUtils digestUtils = new DigestUtils(messageDigest);

        // Test input string
        String data = "Hello, World!";

        // Call the method under test
        String result = digestUtils.digestAsHex(data);

        // Expected output (SHA-1 hash of "Hello, World!" in hex)
        String expected = "0a0a9f2a6772942557ab5355d76af442f8f65e01";

        // Assert the result matches the expected value
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals(expected, result);
    }
}
