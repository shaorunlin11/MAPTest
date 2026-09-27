package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

public class Md5Cryptapr1Crypt_9864e172Test {

    @Test
    public void testApr1Crypt_SaltIsNull() {
        byte[] keyBytes = "test".getBytes(StandardCharsets.UTF_8);
        String salt = null;
        String result = Md5Crypt.apr1Crypt(keyBytes, salt);
        assertNotNull(result);
    }

    @Test
    public void testApr1Crypt_SaltDoesNotStartWithApr1Prefix() {
        byte[] keyBytes = "test".getBytes(StandardCharsets.UTF_8);
        String salt = "salt";
        String result = Md5Crypt.apr1Crypt(keyBytes, salt);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }

    @Test
    public void testApr1Crypt_SaltAlreadyStartsWithApr1Prefix() {
        byte[] keyBytes = "test".getBytes(StandardCharsets.UTF_8);
        String salt = Md5Crypt.APR1_PREFIX + "salt";
        String result = Md5Crypt.apr1Crypt(keyBytes, salt);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }
}
