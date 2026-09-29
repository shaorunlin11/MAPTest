package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class Base64VariantencodeBase64BitsAsByteTest {
    @Test
    public void testEncodeBase64BitsAsByte() throws Exception {
        // Create a Base64Variant instance with a known alphabet
        String base64Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        Base64Variant variant = new Base64Variant("test", base64Alphabet, true, '=', 76);

        // Test values 0-63
        for (int i = 0; i < 64; i++) {
            byte result = variant.encodeBase64BitsAsByte(i);
            Assert.assertEquals((byte) base64Alphabet.charAt(i), result);
        }
    }
}
