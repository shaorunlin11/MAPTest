package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantusesPaddingTest {
    @Test
    public void testUsesPaddingReturnsCorrectValue() throws Exception {
        // Create a Base64Variant with padding
        Base64Variant variantWithPadding = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertTrue("usesPadding should return true when padding is enabled", variantWithPadding.usesPadding());

        // Create a Base64Variant without padding
        Base64Variant variantWithoutPadding = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '\0', 76);
        assertFalse("usesPadding should return false when padding is disabled", variantWithoutPadding.usesPadding());
    }
}
