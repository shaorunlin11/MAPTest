package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.StringUtils;

public class DigestUtilssha384_382d20d8Test {

    @Test
    public void testSha384WithNonNullString() throws NoSuchAlgorithmException {
        String input = "Hello, World!";
        byte[] result = DigestUtils.sha384(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha384WithEmptyString() {
        String input = "";
        byte[] result = DigestUtils.sha384(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha384WithNullString() {
        String input = null;
        try {
            DigestUtils.sha384(input);
            Assert.fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
