package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class HmacUtilshmacSha256_66db53c5Test {

    @Test
    public void testHmacSha256WithNonNullKeyAndValue() {
        String key = "testKey";
        String valueToDigest = "testValue";
        byte[] result = HmacUtils.hmacSha256(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.length == 0);
    }

    @Test
    public void testHmacSha256WithEmptyKey() {
        String key = "";
        String valueToDigest = "testValue";
        try {
            HmacUtils.hmacSha256(key, valueToDigest);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Empty key", e.getMessage());
        }
    }

    @Test
    public void testHmacSha256WithEmptyValue() {
        String key = "testKey";
        String valueToDigest = "";
        byte[] result = HmacUtils.hmacSha256(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.length == 0);
    }
}
