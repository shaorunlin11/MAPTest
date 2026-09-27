package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilssha_3167dc83Test {

    @Test
    public void testShaWithNonNullString() {
        String input = "test";
        byte[] result = DigestUtils.sha(input);
        assertNotNull("Result should not be null", result);
    }

    @Test
    public void testShaWithEmptyString() {
        String input = "";
        byte[] result = DigestUtils.sha(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Empty string should produce fixed-length byte array", 20, result.length);
    }

    @Test
    public void testShaWithNullString() {
        String input = null;
        try {
            DigestUtils.sha(input);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
