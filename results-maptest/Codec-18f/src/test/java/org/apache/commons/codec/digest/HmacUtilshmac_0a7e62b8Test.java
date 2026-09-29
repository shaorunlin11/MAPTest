package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.nio.ByteBuffer;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;

public class HmacUtilshmac_0a7e62b8Test {
    private HmacUtils hmacUtils;
    private Mac mockMac;

    @Before
    public void setUp() throws NoSuchAlgorithmException, InvalidKeyException {
        // Create a mock Mac instance for testing
        mockMac = Mac.getInstance("HmacSHA256");
        mockMac.init(new SecretKeySpec("testKey".getBytes(), "HmacSHA256"));

        // Initialize HmacUtils with the mock Mac instance
        hmacUtils = new HmacUtils("HmacSHA256", "testKey".getBytes());
    }

    @After
    public void tearDown() {
        hmacUtils = null;
        mockMac = null;
    }

    @Test
    public void testHmacWithByteBuffer() {
        // Prepare input data
        byte[] inputData = "testData".getBytes();
        ByteBuffer valueToDigest = ByteBuffer.wrap(inputData);

        // Execute the method
        byte[] result = hmacUtils.hmac(valueToDigest);

        // Verify the result is not null
        Assert.assertNotNull(result);

        // Verify the length of the result (HMAC-SHA256 produces 32 bytes)
        Assert.assertEquals(32, result.length);
    }
}
