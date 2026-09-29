package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.nio.ByteBuffer;

public class HmacUtilshmacHex_f15475a4Test {

    @Test
    public void testHmacHex_ByteBuffer() throws Exception {
        // Arrange
        HmacUtils hmacUtils = new HmacUtils("HmacSHA256", "testKey".getBytes());

        ByteBuffer valueToDigest = ByteBuffer.wrap("testData".getBytes());

        // Act
        String result = hmacUtils.hmacHex(valueToDigest);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result.matches("[0-9a-f]{64}"));
    }
}
