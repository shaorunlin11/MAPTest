package org.apache.commons.codec.digest;

import org.junit.Test;

import java.security.MessageDigest;


public class DigestUtilsUpdateDigestZeroCoverage_163Test {
    @Test
    public void testUpdateDigestWithValidString() throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        String valueToDigest = "test";

        DigestUtils.updateDigest(messageDigest, valueToDigest);
    }
}
