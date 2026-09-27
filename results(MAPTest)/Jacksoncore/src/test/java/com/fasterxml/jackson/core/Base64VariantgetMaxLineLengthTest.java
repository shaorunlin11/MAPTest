package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantgetMaxLineLengthTest {
    @Test
    public void testGetMaxLineLength() throws Exception {
        // Create a Base64Variant instance with known max line length
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);

        // Verify that getMaxLineLength returns the expected value
        assertEquals(76, variant.getMaxLineLength());
    }
}
