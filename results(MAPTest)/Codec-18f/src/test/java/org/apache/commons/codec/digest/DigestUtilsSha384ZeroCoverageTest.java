package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import static org.junit.Assert.assertNotNull;

public class DigestUtilsSha384ZeroCoverageTest {
    @Test
    public void testSha384WithNonNullDataAndDigest() throws Exception {
        // Arrange
        String inputData = "test data";
        InputStream data = new ByteArrayInputStream(inputData.getBytes());

        // Act
        byte[] result = DigestUtils.sha384(data);

        // Assert
        assertNotNull("Result should not be null", result);
    }
}
