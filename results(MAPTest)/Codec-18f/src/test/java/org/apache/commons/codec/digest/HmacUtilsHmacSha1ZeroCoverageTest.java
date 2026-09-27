package org.apache.commons.codec.digest;

import org.junit.Test;

public class HmacUtilsHmacSha1ZeroCoverageTest {
    @Test
    public void testHmacSha1WithNonNullKeyAndValue() {
        byte[] key = "testKey".getBytes();
        byte[] valueToDigest = "testValue".getBytes();
        HmacUtils.hmacSha1(key, valueToDigest);
    }
}
