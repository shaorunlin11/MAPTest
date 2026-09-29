package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class HmacUtilsHmacSha512HexZeroCoverage_213Test {
    @Test
    public void testHmacSha512Hex() throws IOException {
        // Arrange
        byte[] key = "testKey".getBytes();
        String valueToDigest = "testValue";
        InputStream inputStream = new ByteArrayInputStream(valueToDigest.getBytes());

        // Act
        String result = HmacUtils.hmacSha512Hex(key, inputStream);

        // Assert
        assertEquals("99997ffdee76da2f016fe4ee9256c3361c7dc9f1588be5cabeca9e541f8224db00b10260f4885eaaf29edab66574237058d43f5644b47e0fc13e66b89dbcde68", result);
    }
}
