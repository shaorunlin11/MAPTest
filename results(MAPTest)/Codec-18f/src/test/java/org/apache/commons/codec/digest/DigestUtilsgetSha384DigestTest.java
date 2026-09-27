package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsgetSha384DigestTest {

    @Test
    public void testGetSha384Digest() {
        MessageDigest result = DigestUtils.getSha384Digest();
        assertNotNull("getSha384Digest should return a non-null MessageDigest instance", result);
    }
}
