package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha256HexZeroCoverageTest {
    @Test
    public void testHmacSha256Hex() {
        byte[] key = "testKey".getBytes();
        byte[] valueToDigest = "testValue".getBytes();
        String result = HmacUtils.hmacSha256Hex(key, valueToDigest);
        // Add assertions if needed to cover target lines
    }
}
