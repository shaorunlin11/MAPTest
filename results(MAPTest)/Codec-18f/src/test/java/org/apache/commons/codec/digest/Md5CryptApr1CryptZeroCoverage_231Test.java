package org.apache.commons.codec.digest;

import org.junit.Test;

public class Md5CryptApr1CryptZeroCoverage_231Test {
    @Test
    public void testApr1CryptWithNonNullKeyAndSalt() {
        String keyBytes = "testKey";
        String salt = "testSalt";
        Md5Crypt.apr1Crypt(keyBytes, salt);
    }
}
