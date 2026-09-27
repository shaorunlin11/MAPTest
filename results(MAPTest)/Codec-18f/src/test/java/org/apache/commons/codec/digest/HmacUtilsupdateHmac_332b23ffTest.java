package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;

public class HmacUtilsupdateHmac_332b23ffTest {

    private Mac mac;

    @Before
    public void setUp() throws NoSuchAlgorithmException, InvalidKeyException {
        mac = Mac.getInstance("HmacSHA256");
        byte[] key = "testKey".getBytes();
        mac.init(new SecretKeySpec(key, "HmacSHA256"));
    }

    @After
    public void tearDown() {
        mac = null;
    }

    @Test
    public void testUpdateHmacWithInputStream() throws IOException {
        String input = "Hello, World!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Mac result = HmacUtils.updateHmac(mac, inputStream);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals(mac));
    }
}
