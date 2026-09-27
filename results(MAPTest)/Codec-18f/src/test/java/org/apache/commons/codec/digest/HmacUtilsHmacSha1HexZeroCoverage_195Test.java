package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class HmacUtilsHmacSha1HexZeroCoverage_195Test {
    @Test
    public void testHmacSha1Hex() throws IOException {
        // Arrange
        String key = "testKey";
        String valueToDigest = "testValue";
        InputStream inputStream = new ByteArrayInputStream(valueToDigest.getBytes());

        // Act
        String result = HmacUtils.hmacSha1Hex(key.getBytes(), inputStream);

        // Assert
        assertEquals("c512cffdf35e74543a9abcbf288c4e98680b044a", result);
    }
}
