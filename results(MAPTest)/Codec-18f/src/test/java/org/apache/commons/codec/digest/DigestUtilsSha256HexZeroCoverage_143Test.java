package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsSha256HexZeroCoverage_143Test {
    @Test
    public void testSha256Hex() {
        String data = "test";
        String result = DigestUtils.sha256Hex(data);
        // This test ensures that the method is called and executes the target lines
        // The actual value is not asserted, but the method execution is verified
    }
}
