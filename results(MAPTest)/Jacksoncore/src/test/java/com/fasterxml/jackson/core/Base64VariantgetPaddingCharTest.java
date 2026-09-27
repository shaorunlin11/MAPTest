package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantgetPaddingCharTest {
    @Test
    public void testGetPaddingChar_WithPadding() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertEquals('=', variant.getPaddingChar());
    }

    @Test
    public void testGetPaddingChar_WithoutPadding() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '\0', 76);
        assertEquals('\0', variant.getPaddingChar());
    }

    @Test
    public void testGetPaddingChar_DefaultValue() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '\0', 76);
        assertEquals('\0', variant.getPaddingChar());
    }
}
