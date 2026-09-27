package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class UnixCryptCryptZeroCoverage_250Test {
    @Test
    public void testCryptWithNonEmptyString() {
        String original = "test";
        String result = UnixCrypt.crypt(original);
        // Ensure the method executes without throwing an exception
        assertNotNull(result);
    }
}
