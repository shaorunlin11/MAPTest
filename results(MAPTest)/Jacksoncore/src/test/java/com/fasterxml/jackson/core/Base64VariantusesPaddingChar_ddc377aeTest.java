package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantusesPaddingChar_ddc377aeTest {

    @Test
    public void testUsesPaddingChar() throws Exception {
        // Create a Base64Variant with a specific padding character
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);

        // Test when the character matches the padding character
        assertTrue(variant.usesPaddingChar('='));

        // Test when the character does not match the padding character
        assertFalse(variant.usesPaddingChar('A'));
        assertFalse(variant.usesPaddingChar('a'));
        assertFalse(variant.usesPaddingChar('0'));
        assertFalse(variant.usesPaddingChar('+'));
    }
}
