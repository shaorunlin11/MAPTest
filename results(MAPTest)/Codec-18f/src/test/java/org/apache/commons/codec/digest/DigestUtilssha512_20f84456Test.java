package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilssha512_20f84456Test {
    private static final String TEST_STRING = "Hello, World!";
    private static final byte[] TEST_BYTES = TEST_STRING.getBytes();

    @Test
    public void testSha512WithInputStream() throws IOException, NoSuchAlgorithmException {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream(TEST_BYTES);

        // Act
        byte[] result = DigestUtils.sha512(inputStream);

        // Assert
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testSha512WithEmptyInputStream() throws IOException, NoSuchAlgorithmException {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);

        // Act
        byte[] result = DigestUtils.sha512(inputStream);

        // Assert
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Result should have 64 bytes", 64, result.length);
    }

    @Test
    public void testSha512WithIOException() throws IOException, NoSuchAlgorithmException {
        // Arrange
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO error");
            }
        };

        // Act & Assert
        try {
            DigestUtils.sha512(inputStream);
            Assert.fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }
}
