package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

public class HmacUtilshmacSha1_efa87426Test {

    private static final byte[] TEST_KEY = "testKey".getBytes();
    private static final byte[] TEST_DATA = "testData".getBytes();

    @Test
    public void testHmacSha1WithInputStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(TEST_DATA);
        byte[] result = HmacUtils.hmacSha1(TEST_KEY, inputStream);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test(expected = IOException.class)
    public void testHmacSha1WithIOException() throws IOException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO exception");
            }
        };
        HmacUtils.hmacSha1(TEST_KEY, inputStream);
    }
}
