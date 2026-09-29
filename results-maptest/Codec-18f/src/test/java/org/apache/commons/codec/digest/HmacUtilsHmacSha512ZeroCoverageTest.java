package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class HmacUtilsHmacSha512ZeroCoverageTest {
    @Test
    public void testHmacSha512WithNonNullKeyAndValueToDigest() throws IOException {
        byte[] key = "testKey".getBytes();
        String valueToDigest = "testValue";
        InputStream inputStream = new ByteArrayInputStream(valueToDigest.getBytes());

        byte[] result = HmacUtils.hmacSha512(key, inputStream);

        assertNotNull("Result should not be null", result);
    }
}
