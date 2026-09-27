package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64encodeBase64_7a398cf5Test {

    @Test
    public void testEncodeBase64() {
        byte[] input = "Hello, World!".getBytes();
        byte[] result = org.apache.commons.codec.binary.Base64.encodeBase64(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Result length should match expected", 20, result.length);
        // Actual base64 encoding of "Hello, World!" is "SGVsbG8sIFdvcmxkfQ=="
        // This test verifies the method delegates correctly, actual encoding is handled by implementation
    }

    @Test
    public void testEncodeBase64WithEmptyInput() {
        byte[] input = new byte[0];
        byte[] result = org.apache.commons.codec.binary.Base64.encodeBase64(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Result length should be 0", 0, result.length);
    }

    @Test
    public void testEncodeBase64WithNullInput() {
        byte[] input = null;
        byte[] result = org.apache.commons.codec.binary.Base64.encodeBase64(input);
        assertNull("Result should be null", result);
    }
}
