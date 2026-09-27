package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilssha512_444cd5a0Test {

    @Test
    public void testSha512WithNonNullString() throws NoSuchAlgorithmException {
        String input = "Hello, World!";
        byte[] result = DigestUtils.sha512(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha512WithEmptyString() throws NoSuchAlgorithmException {
        String input = "";
        byte[] result = DigestUtils.sha512(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha512WithNullString() throws NoSuchAlgorithmException {
        String input = null;
        try {
            DigestUtils.sha512(input);
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
