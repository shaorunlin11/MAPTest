package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantusesPaddingChar_1a78ab0dTest {

    @Test
    public void testUsesPaddingCharWithPaddingChar() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertTrue(variant.usesPaddingChar('='));
    }

    @Test
    public void testUsesPaddingCharWithoutPaddingChar() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '\0', 76);
        assertFalse(variant.usesPaddingChar('A'));
    }

    @Test
    public void testUsesPaddingCharWithDifferentChar() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '-', 76);
        assertFalse(variant.usesPaddingChar('='));
    }

    @Test
    public void testUsesPaddingCharWithZero() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '\0', 76);
        assertTrue(variant.usesPaddingChar(0));
    }

    @Test
    public void testUsesPaddingCharWithNonPaddingChar() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '+', 76);
        assertFalse(variant.usesPaddingChar('-'));
    }
}
