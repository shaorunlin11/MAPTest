package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class HmacUtilshmacHex_29f98ef6Test {

    @Test
    public void testHmacHex() throws Exception {
        // Arrange
        HmacUtils hmacUtils = new HmacUtils("HmacSHA256", "secretKey".getBytes());

        // Act
        String result = hmacUtils.hmacHex("testValue");

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
