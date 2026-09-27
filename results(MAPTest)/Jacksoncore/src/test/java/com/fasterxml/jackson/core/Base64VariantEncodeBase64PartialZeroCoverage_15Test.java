package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantEncodeBase64PartialZeroCoverage_15Test {
    @Test
    public void testEncodeBase64PartialLine292() {
        // Create a Base64Variant instance with _usesPadding set to true and _base64ToAsciiC initialized
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Create a StringBuilder
        StringBuilder sb = new StringBuilder();

        // Set bits to a value that will result in a valid index into _base64ToAsciiC
        int bits = 0x3F << 18 | 0x3F << 12 | 0x3F << 6 | 0x3F;

        // Set outputBytes to 2 to reach the target line
        int outputBytes = 2;

        // Call the method under test
        variant.encodeBase64Partial(sb, bits, outputBytes);
    }

@Test
    public void testEncodeBase64PartialLine299() {
        // Create a Base64Variant instance with _usesPadding set to false and _base64ToAsciiC initialized
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '=', 0);

        // Create a StringBuilder
        StringBuilder sb = new StringBuilder();

        // Set bits to a value that will result in a valid index into _base64ToAsciiC
        int bits = 0x3F << 18 | 0x3F << 12 | 0x3F << 6 | 0x3F;

        // Set outputBytes to 2 to reach the target line
        int outputBytes = 2;

        // Call the method under test
        variant.encodeBase64Partial(sb, bits, outputBytes);
    }
}
