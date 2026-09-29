package org.apache.commons.codec.digest;

import org.junit.Test;

import org.apache.commons.codec.Charsets;


public class Sha2CryptSha512CryptZeroCoverageTest {
    @Test
    public void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String salt = null;
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);
        // The test is designed to reach line 540 of the method, which is the line where salt is set to SHA512_PREFIX + B64.getRandomSalt(8)
        // This test ensures that the code path is executed with salt being null, triggering the assignment in the method.
    }
}
