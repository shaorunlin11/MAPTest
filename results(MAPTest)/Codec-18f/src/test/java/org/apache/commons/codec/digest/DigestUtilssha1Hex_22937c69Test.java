package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilssha1Hex_22937c69Test {

    @Test
    public void testSha1HexWithNonNullData() throws NoSuchAlgorithmException {
        byte[] data = "Hello, World!".getBytes();
        String result = DigestUtils.sha1Hex(data);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test
    public void testSha1HexWithEmptyData() {
        byte[] data = new byte[0];
        String result = DigestUtils.sha1Hex(data);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testSha1HexWithNullData() {
        byte[] data = null;
        String result = DigestUtils.sha1Hex(data);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }
}
