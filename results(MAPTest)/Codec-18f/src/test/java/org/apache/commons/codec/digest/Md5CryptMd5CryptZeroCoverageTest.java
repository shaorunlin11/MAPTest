package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class Md5CryptMd5CryptZeroCoverageTest {
    @Test
    public void testMd5CryptWithNullSalt() {
        byte[] keyBytes = "test".getBytes();
        String salt = null;
        String prefix = "$1$";

        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix);
        assertNotNull(result);
        assertTrue(result.startsWith(prefix));
    }

@Test
    public void testMd5CryptWithValidSaltAndPrefix() {
        byte[] keyBytes = "test".getBytes();
        String salt = "$1$abcdefgh";
        String prefix = "$1$";

        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix);
        assertNotNull(result);
        assertTrue(result.startsWith(prefix));
    }
}
