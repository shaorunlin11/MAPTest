package org.apache.commons.codec.digest;

import org.junit.Test;

public class UnixCryptCryptZeroCoverage_251Test {
    @Test
    public void testCryptWithNonNullOriginalAndSalt() {
        String original = "test";
        String salt = "ab";
        UnixCrypt.crypt(original, salt);
    }
}
