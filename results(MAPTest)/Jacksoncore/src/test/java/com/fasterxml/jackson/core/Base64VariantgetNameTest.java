package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantgetNameTest {
    @Test
    public void testGetName() throws Exception {
        // Create a Base64Variant instance with a known name
        Base64Variant variant = new Base64Variant("testName", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Verify that the getName method returns the expected value
        assertEquals("testName", variant.getName());
    }
}
