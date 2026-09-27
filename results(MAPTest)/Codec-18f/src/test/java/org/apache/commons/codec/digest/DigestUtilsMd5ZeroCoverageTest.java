package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;

public class DigestUtilsMd5ZeroCoverageTest {
    @Test
    public void testMd5WithNonNullInputStream() throws IOException {
        String testData = "test data";
        byte[] expectedMd5 = DigestUtils.md5(testData.getBytes());

        InputStream inputStream = new ByteArrayInputStream(testData.getBytes());
        byte[] actualMd5 = DigestUtils.md5(inputStream);

        assertArrayEquals(expectedMd5, actualMd5);
    }
}
