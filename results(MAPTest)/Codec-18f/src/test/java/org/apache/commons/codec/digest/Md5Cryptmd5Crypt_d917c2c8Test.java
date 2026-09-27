package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

public class Md5Cryptmd5Crypt_d917c2c8Test {

    @Test
    public void testMd5CryptWithKeyBytesAndSalt() {
        byte[] keyBytes = "test".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$salt";
        String result = Md5Crypt.md5Crypt(keyBytes, salt);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with MD5_PREFIX", result.startsWith(Md5Crypt.MD5_PREFIX));
    }
}
