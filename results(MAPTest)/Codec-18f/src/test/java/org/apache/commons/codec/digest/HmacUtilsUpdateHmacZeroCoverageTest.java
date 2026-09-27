package org.apache.commons.codec.digest;

import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class HmacUtilsUpdateHmacZeroCoverageTest {
    @Test
    public void testUpdateHmac() throws Exception {
        // Create a valid Mac instance with a valid HMAC algorithm
        Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec("testKey".getBytes(), "HmacSHA256");
        mac.init(key);

        // Call the method under test
        HmacUtils.updateHmac(mac, "testValue");
    }
}
