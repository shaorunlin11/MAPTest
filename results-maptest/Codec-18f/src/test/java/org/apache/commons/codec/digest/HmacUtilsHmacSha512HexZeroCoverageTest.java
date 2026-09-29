package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha512HexZeroCoverageTest {
    @Test
    public void testHmacSha512Hex() {
        byte[] key = "testKey".getBytes();
        byte[] valueToDigest = "testValue".getBytes();
        String result = HmacUtils.hmacSha512Hex(key, valueToDigest);
        // Add assertions if needed to cover target lines 775
    }
}
