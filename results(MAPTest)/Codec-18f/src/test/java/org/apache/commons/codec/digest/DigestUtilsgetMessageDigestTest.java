package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;

public class DigestUtilsgetMessageDigestTest {
    @Test
    public void testGetMessageDigest() throws Exception {
        // Create a DigestUtils instance with a MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        DigestUtils digestUtils = new DigestUtils(messageDigest);

        // Get the MessageDigest from the instance
        MessageDigest result = digestUtils.getMessageDigest();

        // Assert that the returned MessageDigest is the same as the one provided
        Assert.assertEquals(messageDigest, result);
    }

    @Test
    public void testGetMessageDigestWithNull() {
        // Create a DigestUtils instance with default constructor (messageDigest is null)
        DigestUtils digestUtils = new DigestUtils();

        // Get the MessageDigest from the instance
        MessageDigest result = digestUtils.getMessageDigest();

        // Assert that the returned MessageDigest is null
        Assert.assertNull(result);
    }
}
