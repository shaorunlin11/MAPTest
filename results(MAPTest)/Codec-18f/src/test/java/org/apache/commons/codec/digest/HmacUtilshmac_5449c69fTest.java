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

public class HmacUtilshmac_5449c69fTest {
    private HmacUtils hmacUtils;
    private Mac mockMac;

    @Before
    public void setUp() throws NoSuchAlgorithmException, InvalidKeyException {
        // Create a mock Mac instance for testing
        Mac mockMac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec("testKey".getBytes(), "HmacSHA256");
        mockMac.init(keySpec);
        this.mockMac = mockMac;

        // Initialize HmacUtils with the mock Mac
        this.hmacUtils = new HmacUtils("HmacSHA256", "testKey".getBytes());
    }

    @After
    public void tearDown() {
        this.hmacUtils = null;
        this.mockMac = null;
    }

    @Test
    public void testHmacWithInputStream() throws IOException {
        // Prepare input data
        String input = "testData";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        // Execute the method
        byte[] result = hmacUtils.hmac(inputStream);

        // Verify that the result is not null
        Assert.assertNotNull(result);
        Assert.assertTrue(result.length > 0);
    }
}
