package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsdigest_09c82325Test {
    private MessageDigest messageDigest;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-1");
    }

    @After
    public void tearDown() {
        messageDigest = null;
    }

    @Test
    public void testDigest() throws IOException {
        String input = "Hello, world!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        byte[] result = DigestUtils.digest(messageDigest, inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testDigestWithIOException() throws IOException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO error");
            }
        };

        try {
            DigestUtils.digest(messageDigest, inputStream);
            Assert.fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception, test passes
        }
    }
}
