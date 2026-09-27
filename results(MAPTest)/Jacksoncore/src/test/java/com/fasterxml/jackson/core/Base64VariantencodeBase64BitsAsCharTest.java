package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantencodeBase64BitsAsCharTest {
    @Test
    public void testEncodeBase64BitsAsChar() throws Exception {
        // Create a Base64Variant instance with a known alphabet
        String base64Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        Base64Variant variant = new Base64Variant("test", base64Alphabet, true, '=', 76);

        // Test values 0-63
        for (int i = 0; i < 64; i++) {
            char result = variant.encodeBase64BitsAsChar(i);
            assertEquals(base64Alphabet.charAt(i), result);
        }

        // Test out-of-bound value (should return undefined, but we can check if it's within the array)
        try {
            variant.encodeBase64BitsAsChar(64);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }
}
