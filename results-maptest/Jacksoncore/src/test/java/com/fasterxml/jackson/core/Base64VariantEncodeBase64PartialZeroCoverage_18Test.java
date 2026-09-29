package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantEncodeBase64PartialZeroCoverage_18Test {
    @Test
    public void testEncodeBase64PartialTargetLines335() {
        // Create a Base64Variant instance with _base64ToAsciiB initialized
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Set up input parameters
        int bits = 0x123456; // Example value that will generate valid indices
        int outputBytes = 2; // To trigger the code path in target lines 335
        byte[] buffer = new byte[4];
        int outPtr = 0;

        // Call the method under test
        variant.encodeBase64Partial(bits, outputBytes, buffer, outPtr);
    }

@Test
    public void testEncodeBase64PartialTargetLines342() {
        // Create a Base64Variant instance with _usesPadding set to false
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", false, '=', 0);

        // Set up input parameters
        int bits = 0x123456; // Example value that will generate valid indices
        int outputBytes = 2; // To trigger the code path in target lines 342
        byte[] buffer = new byte[4];
        int outPtr = 0;

        // Call the method under test
        variant.encodeBase64Partial(bits, outputBytes, buffer, outPtr);
    }
}
