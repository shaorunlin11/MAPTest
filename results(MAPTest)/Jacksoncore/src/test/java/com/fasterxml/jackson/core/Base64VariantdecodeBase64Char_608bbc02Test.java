package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantdecodeBase64Char_608bbc02Test {

    @Test
    public void testDecodeBase64Char_WithinAsciiRange() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertEquals(0, variant.decodeBase64Char('A'));
        assertEquals(26, variant.decodeBase64Char('a'));
        assertEquals(52, variant.decodeBase64Char('0'));
        assertEquals(62, variant.decodeBase64Char('+'));
        assertEquals(63, variant.decodeBase64Char('/'));
    }

    @Test
    public void testDecodeBase64Char_OutsideAsciiRange() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\u0080'));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\u00FF'));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\u1000'));
    }

    @Test
    public void testDecodeBase64Char_PaddingCharacter() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertEquals(Base64Variant.BASE64_VALUE_PADDING, variant.decodeBase64Char('='));
    }

    @Test
    public void testDecodeBase64Char_InvalidCharacter() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char(' '));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\t'));
        assertEquals(Base64Variant.BASE64_VALUE_INVALID, variant.decodeBase64Char('\n'));
    }
}
