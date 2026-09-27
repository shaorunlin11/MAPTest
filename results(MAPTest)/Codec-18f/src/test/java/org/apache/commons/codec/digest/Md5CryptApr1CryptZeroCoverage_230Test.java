package org.apache.commons.codec.digest;

import org.junit.Test;

public class Md5CryptApr1CryptZeroCoverage_230Test {
    @Test
    public void testApr1CryptWithNonNullKeyBytes() {
        String keyBytes = "testKey";
        String result = Md5Crypt.apr1Crypt(keyBytes);
        // This test ensures that the method is called with a non-null keyBytes parameter
        // and executes the target lines 105 through the selected target plan.
    }
}
