package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantDecodeZeroCoverageTest {
    @Test
    public void testDecode() {
        // Create a Base64Variant instance with a valid configuration
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Call the decode method with a valid input string
        variant.decode("SGVsbG8gV29ybGQ=");
    }
}
