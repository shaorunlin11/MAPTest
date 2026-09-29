package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantEncodeBase64ChunkZeroCoverage_17Test {
    @Test
    public void testEncodeBase64Chunk() {
        // Create a Base64Variant instance with a custom base64 alphabet
        // We need to ensure _base64ToAsciiB is initialized with valid entries
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Prepare the buffer and ptr
        byte[] buffer = new byte[4];
        int ptr = 0;

        // Set b24 such that each 6-bit segment is within the valid range of the base64 alphabet
        // For example, using values that correspond to 'A', 'B', 'C', 'D'
        int b24 = ((0) << 18) | ((1) << 12) | ((2) << 6) | (3);

        // Call the method under test
        variant.encodeBase64Chunk(b24, buffer, ptr);
    }
}
