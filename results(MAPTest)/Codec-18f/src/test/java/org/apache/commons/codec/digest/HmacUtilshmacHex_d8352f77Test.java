package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

public class HmacUtilshmacHex_d8352f77Test {

    private HmacUtils hmacUtils;

    @Before
    public void setUp() {
        // Using a dummy algorithm and key to create HmacUtils instance
        hmacUtils = new HmacUtils("HmacSHA256", "testKey".getBytes());
    }

    @After
    public void tearDown() {
        hmacUtils = null;
    }

    @Test
    public void testHmacHex_WithValidInputStream_ReturnsHexDigest() throws IOException {
        String input = "testData";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        String result = hmacUtils.hmacHex(inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a valid hexadecimal string", result.matches("[0-9a-fA-F]+"));
    }

    @Test(expected = IOException.class)
    public void testHmacHex_WithIOExceptionFromInputStream_ThrowsIOException() throws IOException {
        InputStream faultyStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated stream error");
            }
        };

        hmacUtils.hmacHex(faultyStream);
    }
}
