package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsgetSha512DigestTest {

    @Test
    public void testGetSha512Digest() throws NoSuchAlgorithmException {
        MessageDigest result = DigestUtils.getSha512Digest();
        assertNotNull("The returned MessageDigest should not be null", result);
    }
}
