package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

public class HmacUtilshmacMd5Hex_cc3b4090Test {

    @Test
    public void testHmacMd5Hex() {
        String key = "secretKey";
        String valueToDigest = "testValue";
        String result = HmacUtils.hmacMd5Hex(key, valueToDigest);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }
}
