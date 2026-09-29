package org.apache.commons.codec.digest;

import org.junit.Test;

import org.apache.commons.codec.Charsets;


public class Md5CryptApr1CryptZeroCoverageTest {
    @Test
    public void testApr1Crypt() {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String result = Md5Crypt.apr1Crypt(keyBytes);
    }
}
