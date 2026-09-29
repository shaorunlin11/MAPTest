package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HmacUtilsupdateHmac_979503d4Test {

    private Mac mac;

    @Before
    public void setUp() throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec("key".getBytes(), "HmacSHA256");
        hmacSha256.init(key);
        this.mac = hmacSha256;
    }

    @After
    public void tearDown() {
        this.mac = null;
    }

    @Test
    public void testUpdateHmacWithNonNullMacAndValueToDigest() throws Exception {
        byte[] valueToDigest = "test".getBytes();
        assertNotNull("Mac should be initialized", mac);
        Mac updatedMac = HmacUtils.updateHmac(mac, valueToDigest);
        assertNotNull("Updated Mac should not be null", updatedMac);
        assertEquals("Mac should have the same digest length as the original", mac.getMacLength(), updatedMac.getMacLength());
    }
}
