package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantequalsTest {
    @Test
    public void testEqualsWithSameInstance() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertTrue(variant.equals(variant));
    }

    @Test
    public void testEqualsWithDifferentInstance() {
        Base64Variant variant1 = new Base64Variant("test1", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        Base64Variant variant2 = new Base64Variant("test2", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertFalse(variant1.equals(variant2));
    }

    @Test
    public void testEqualsWithNull() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        assertFalse(variant.equals(null));
    }
}
