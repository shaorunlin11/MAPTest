package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha1ZeroCoverage_193Test {
    @Test
    public void testHmacSha1WithNonNullKeyAndValue() {
        String key = "testKey";
        String valueToDigest = "testValue";
        HmacUtils.hmacSha1(key, valueToDigest);
    }
}
