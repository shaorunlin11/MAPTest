package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsmd5_d00958b0Test {

    @Test
    public void testMd5WithNonNullString() {
        byte[] result = DigestUtils.md5("test");
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testMd5WithEmptyString() {
        byte[] result = DigestUtils.md5("");
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    public void testMd5WithNullString() {
        try {
            DigestUtils.md5((String) null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
