package org.apache.commons.codec.digest;

import org.junit.Test;

import java.security.MessageDigest;


public class DigestUtilsUpdateDigestZeroCoverageTest {
    @Test
    public void testUpdateDigest() throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] valueToDigest = "test".getBytes();
        DigestUtils.updateDigest(messageDigest, valueToDigest);
    }
}
