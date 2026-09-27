package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import javax.crypto.Mac;

public class HmacUtilsgetHmacMd5Test {

    @Test
    public void testGetHmacMd5() {
        byte[] key = "testKey".getBytes();
        Mac hmacMd5 = HmacUtils.getHmacMd5(key);
        Assert.assertNotNull(hmacMd5);
    }

    @Test
    public void testGetHmacMd5WithNullKey() {
        try {
            HmacUtils.getHmacMd5(null);
            Assert.fail("Expected InvalidKeyException was not thrown");
        } catch (Exception expected) {
            // Expected exception
        }
    }
}
