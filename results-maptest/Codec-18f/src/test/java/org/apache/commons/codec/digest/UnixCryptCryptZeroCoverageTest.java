package org.apache.commons.codec.digest;

import org.junit.Test;

public class UnixCryptCryptZeroCoverageTest {
    @Test
    public void testCryptWithNullSalt() {
        byte[] original = new byte[0];
        String salt = null;
        UnixCrypt.crypt(original, salt);
    }

@Test
    public void testCryptWithNonNullSalt() {
        byte[] original = new byte[0];
        String salt = "ab";
        UnixCrypt.crypt(original, salt);
    }

@Test
    public void testCryptWithTargetLines204() {
        byte[] original = new byte[] { 0 };
        String salt = "ab";
        UnixCrypt.crypt(original, salt);
    }
}
