package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsgetSha1DigestTest {

    @Test
    public void testGetSha1Digest() throws NoSuchAlgorithmException {
        MessageDigest result = DigestUtils.getSha1Digest();
        assertNotNull("The SHA-1 digest should not be null", result);
    }
}
