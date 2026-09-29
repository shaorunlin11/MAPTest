package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantdecodeBase64Char_cc4932fdTest {
    @Test
    public void testDecodeBase64Char() throws Exception {
        // Create a Base64Variant instance with a valid alphabet
        String base64Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        Base64Variant variant = new Base64Variant("standard", base64Alphabet, true, '=', 76);

        // Test valid ASCII characters
        assertEquals(0, variant.decodeBase64Char('A'));
        assertEquals(26, variant.decodeBase64Char('a'));
        assertEquals(52, variant.decodeBase64Char('0'));
        assertEquals(62, variant.decodeBase64Char('+'));
        assertEquals(63, variant.decodeBase64Char('/'));

        // Test padding character
        assertEquals(Base64Variant.BASE64_VALUE_PADDING, variant.decodeBase64Char('='));

        // Test non-ASCII character
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(0x100));

        // Test control characters
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(0x00)); // NUL
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(0x07)); // BEL
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(0x1F)); // US

        // Test space character (ASCII 32)
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(0x20));
    }
}
