package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilssha384Hex_9c7b0969Test {

    @Test
    public void testSha384HexWithNonNullData() throws NoSuchAlgorithmException {
        byte[] data = "Hello, World!".getBytes();
        String result = DigestUtils.sha384Hex(data);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a valid hex string", result.matches("[a-fA-F0-9]+"));
    }

    @Test
    public void testSha384HexWithEmptyData() {
        byte[] data = new byte[0];
        String result = DigestUtils.sha384Hex(data);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a valid hex string", result.matches("[a-fA-F0-9]+"));
    }

    @Test
    public void testSha384HexWithNullData() {
        byte[] data = null;
        try {
            String result = DigestUtils.sha384Hex(data);
            Assert.fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
