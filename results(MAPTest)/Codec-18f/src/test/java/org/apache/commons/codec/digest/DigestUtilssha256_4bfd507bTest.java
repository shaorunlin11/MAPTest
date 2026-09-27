package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilssha256_4bfd507bTest {

    @Test
    public void testSha256WithNonNullString() {
        byte[] result = DigestUtils.sha256("Hello, World!");
        assertNotNull("Result should not be null", result);
        assertEquals("Length of SHA-256 hash should be 32 bytes", 32, result.length);
    }

    @Test
    public void testSha256WithEmptyString() {
        byte[] result = DigestUtils.sha256("");
        assertNotNull("Result should not be null", result);
        assertEquals("Length of SHA-256 hash should be 32 bytes", 32, result.length);
    }

    @Test
    public void testSha256WithNullString() {
        try {
            DigestUtils.sha256((String) null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
