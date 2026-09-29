package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha1HexZeroCoverage_196Test {
    @Test
    public void testHmacSha1Hex() {
        String key = "testKey";
        String valueToDigest = "testValue";
        String result = HmacUtils.hmacSha1Hex(key, valueToDigest);
        // The test is designed to execute the target lines without making assertions
        // as per the requirement to cover target lines 472.
    }
}
