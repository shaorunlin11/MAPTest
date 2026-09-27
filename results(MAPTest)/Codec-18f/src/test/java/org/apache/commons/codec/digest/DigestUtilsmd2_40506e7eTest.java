package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.StringUtils;

public class DigestUtilsmd2_40506e7eTest {

    @Test
    public void testMd2WithNonNullString() throws NoSuchAlgorithmException {
        String input = "Hello, World!";
        byte[] result = DigestUtils.md2(input);
        Assert.assertNotNull("Result should not be null", result);
    }

    @Test
    public void testMd2WithEmptyString() {
        String input = "";
        byte[] result = DigestUtils.md2(input);
        Assert.assertNotNull("Result should not be null", result);
    }

    @Test
    public void testMd2WithNullString() {
        String input = null;
        try {
            DigestUtils.md2(input);
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
