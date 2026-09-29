package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha512HexZeroCoverage_214Test {
    @Test
    public void testHmacSha512Hex() {
        String key = "testKey";
        String valueToDigest = "testValue";
        String result = HmacUtils.hmacSha512Hex(key, valueToDigest);
        // The test is designed to execute the target lines without making assertions
        // as per the requirement to cover target lines 814.
    }
}
