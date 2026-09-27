package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HmacUtilshmacSha256_e6dc418eTest {

    @Test
    public void testHmacSha256WithInputStream() throws IOException {
        byte[] key = "testKey".getBytes();
        String input = "testValue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        byte[] result = HmacUtils.hmacSha256(key, inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test(expected = IOException.class)
    public void testHmacSha256WithIOException() throws IOException {
        byte[] key = "testKey".getBytes();
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated error");
            }
        };

        HmacUtils.hmacSha256(key, inputStream);
    }
}
