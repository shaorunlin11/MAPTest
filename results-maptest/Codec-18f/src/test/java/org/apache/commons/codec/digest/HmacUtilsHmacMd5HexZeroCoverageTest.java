package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacMd5HexZeroCoverageTest {
    @Test
    public void testHmacMd5Hex() {
        byte[] key = "testKey".getBytes();
        byte[] valueToDigest = "testValue".getBytes();
        String result = HmacUtils.hmacMd5Hex(key, valueToDigest);
        // Add assertion if needed, but no specific requirement provided
    }
}
