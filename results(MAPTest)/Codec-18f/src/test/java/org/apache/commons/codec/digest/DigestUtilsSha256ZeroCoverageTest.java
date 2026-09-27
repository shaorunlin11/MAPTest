package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsSha256ZeroCoverageTest {
    @Test
    public void testSha256WithNonNullDataAndDigest() throws Exception {
        // Create a non-null InputStream
        InputStream data = new ByteArrayInputStream("test".getBytes());

        // Ensure getSha256Digest() returns a non-null digest object
        MessageDigest digest = DigestUtils.getSha256Digest();
        assertNotNull("getSha256Digest() should return a non-null digest object", digest);

        // Execute the method under test
        byte[] result = DigestUtils.sha256(data);

        // Additional assertions can be added if needed
    }
}
