package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsgetMd5DigestTest {

    @Test
    public void testGetMd5Digest() throws NoSuchAlgorithmException {
        MessageDigest md5Digest = DigestUtils.getMd5Digest();
        assertNotNull("MD5 digest should not be null", md5Digest);
    }
}
