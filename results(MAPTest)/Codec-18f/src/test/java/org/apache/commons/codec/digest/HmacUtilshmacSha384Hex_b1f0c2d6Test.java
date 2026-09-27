package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class HmacUtilshmacSha384Hex_b1f0c2d6Test {

    @Test
    public void testHmacSha384Hex() {
        String key = "secretKey";
        String valueToDigest = "helloWorld";
        String result = HmacUtils.hmacSha384Hex(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }

    @Test
    public void testHmacSha384HexWithEmptyKey() {
        String key = "";
        String valueToDigest = "test";
        try {
            HmacUtils.hmacSha384Hex(key, valueToDigest);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Empty key", e.getMessage());
        }
    }

    @Test
    public void testHmacSha384HexWithEmptyValue() {
        String key = "key";
        String valueToDigest = "";
        String result = HmacUtils.hmacSha384Hex(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }

    @Test
    public void testHmacSha384HexWithNullKey() {
        String key = null;
        String valueToDigest = "test";
        try {
            HmacUtils.hmacSha384Hex(key, valueToDigest);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Null key", e.getMessage());
        }
    }

    @Test
    public void testHmacSha384HexWithNullValue() {
        String key = "key";
        String valueToDigest = null;
        String result = HmacUtils.hmacSha384Hex(key, valueToDigest);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }
}
