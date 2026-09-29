package org.apache.commons.codec.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsgetDigest_149cc759Test {

    @Test
    public void testGetDigest_ValidAlgorithm_ReturnsMessageDigest() {
        MessageDigest md = DigestUtils.getDigest("SHA-1");
        assertNotNull("Should return a non-null MessageDigest for valid algorithm", md);
    }

    @Test
    public void testGetDigest_InvalidAlgorithm_ThrowsIllegalArgumentException() {
        try {
            DigestUtils.getDigest("invalidAlgorithm");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue("Exception should wrap NoSuchAlgorithmException", e.getCause() instanceof NoSuchAlgorithmException);
        }
    }
}
