package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsgetSha256DigestTest {

    @Test
    public void testGetSha256Digest() throws NoSuchAlgorithmException {
        MessageDigest result = DigestUtils.getSha256Digest();
        assertNotNull("The SHA-256 digest should not be null", result);
    }
}
