package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsDigestZeroCoverage_170Test {
    @Test
    public void testDigestWithNonNullDataAndMessageDigest() throws Exception {
        // Arrange
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        InputStream data = new ByteArrayInputStream("test".getBytes());

        // Act
        DigestUtils digestUtils = new DigestUtils(messageDigest);
        byte[] result = digestUtils.digest(data);

        // Assert
        assertNotNull(result);
    }
}
