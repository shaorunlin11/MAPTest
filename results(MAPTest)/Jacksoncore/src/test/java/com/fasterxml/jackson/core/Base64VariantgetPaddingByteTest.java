package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantgetPaddingByteTest {
    @Test
    public void testGetPaddingByte() throws Exception {
        // Create a Base64Variant with a specific padding character
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 72);
        assertEquals((byte) '=', variant.getPaddingByte());

        // Create a Base64Variant with no padding
        Base64Variant noPaddingVariant = new Base64Variant("noPadding", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, Base64Variant.PADDING_CHAR_NONE, 72);
        assertEquals((byte) 0, noPaddingVariant.getPaddingByte());
    }
}
