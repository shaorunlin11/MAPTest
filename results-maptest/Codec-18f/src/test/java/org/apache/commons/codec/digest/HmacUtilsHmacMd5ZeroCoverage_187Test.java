package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

public class HmacUtilsHmacMd5ZeroCoverage_187Test {
    @Test
    public void testHmacMd5WithValidKeyAndValue() {
        String key = "testKey";
        String valueToDigest = "testValue";
        byte[] result = HmacUtils.hmacMd5(key, valueToDigest);
        // Ensure the method is called and returns a non-null array
        Assert.assertNotNull(result);
    }
}
