package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsmd5_99366b2cTest {

    @Test
    public void testMd5WithNonNullData() throws Exception {
        byte[] data = "Hello, World!".getBytes();
        byte[] result = DigestUtils.md5(data);
        assertNotNull("Result should not be null", result);
        assertEquals("Result length should be 16 bytes for MD5", 16, result.length);
    }

    @Test
    public void testMd5WithNullData() {
        byte[] data = null;
        try {
            byte[] result = DigestUtils.md5(data);
            assertNull("Result should be null when input is null", result);
        } catch (NullPointerException e) {
            // Expected exception when passing null to md5 method
            assertTrue("Expected NullPointerException when passing null to md5", true);
        }
    }
}
