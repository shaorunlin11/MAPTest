package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantdecodeBase64ByteTest {
    @Test
    public void testDecodeBase64ByteNegative() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) -1));
    }

    @Test
    public void testDecodeBase64ByteValid() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        // Test with a valid Base64 character (e.g., 'A' which is at index 0)
        assertEquals(0, variant.decodeBase64Byte((byte) 'A'));
    }

    @Test
    public void testDecodeBase64BytePadding() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertEquals(Base64Variant.BASE64_VALUE_PADDING, variant.decodeBase64Byte((byte) '='));
    }

    @Test
    public void testDecodeBase64ByteNonBase64Char() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Byte((byte) ' '));
    }
}
