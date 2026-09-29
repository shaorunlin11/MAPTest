package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

public class HmacUtilsHmacSha1HexZeroCoverageTest {
    @Test
    public void testHmacSha1Hex() {
        byte[] key = "testKey".getBytes();
        byte[] valueToDigest = "testValue".getBytes();
        String result = HmacUtils.hmacSha1Hex(key, valueToDigest);
        // Add assertion to verify the result is not null
        Assert.assertNotNull(result);
    }
}
