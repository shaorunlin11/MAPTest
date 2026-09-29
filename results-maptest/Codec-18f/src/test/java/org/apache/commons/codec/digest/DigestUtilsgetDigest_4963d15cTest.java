package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsgetDigest_4963d15cTest {

    @Test
    public void testGetDigest_ValidAlgorithm_ReturnsMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest defaultDigest = MessageDigest.getInstance("SHA-256");
        MessageDigest result = DigestUtils.getDigest("SHA-256", defaultDigest);
        Assert.assertNotNull(result);
        Assert.assertEquals("SHA-256", result.getAlgorithm());
    }

    @Test
    public void testGetDigest_InvalidAlgorithm_ReturnsDefaultMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest defaultDigest = MessageDigest.getInstance("SHA-256");
        MessageDigest result = DigestUtils.getDigest("INVALID_ALGORITHM", defaultDigest);
        Assert.assertSame(defaultDigest, result);
    }
}
