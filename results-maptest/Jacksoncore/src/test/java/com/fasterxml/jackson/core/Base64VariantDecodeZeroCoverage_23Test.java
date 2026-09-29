package com.fasterxml.jackson.core;

import org.junit.Test;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

public class Base64VariantDecodeZeroCoverage_23Test {
    @Test
    public void testDecodeWithPaddingAndPaddingChar() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        String str = "SGVsbG8gd29ybGQ=";
        ByteArrayBuilder builder = new ByteArrayBuilder();

        variant.decode(str, builder);
    }

@Test(expected = IllegalArgumentException.class)
    public void testDecodeWithInvalidCharacter() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        String str = "SGVsbG8gd29ybGQ"; // Missing padding character
        ByteArrayBuilder builder = new ByteArrayBuilder();

        variant.decode(str, builder);
    }

@Test(expected = IllegalArgumentException.class)
    public void testDecodeWithInvalidBase64Char() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        String str = "SGVsbG8gd29ybGQ="; // Valid string
        ByteArrayBuilder builder = new ByteArrayBuilder();

        // Modify the string to include an invalid character
        String invalidStr = "SGVsbG8gd29ybGQ=Z";
        variant.decode(invalidStr, builder);
    }
}
