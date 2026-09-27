package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class HmacUtilshmacSha256Hex_3975dacaTest {

    @Test
    public void testHmacSha256Hex() {
        String key = "secretKey";
        String valueToDigest = "testValue";
        String result = HmacUtils.hmacSha256Hex(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }
}
