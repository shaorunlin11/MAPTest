package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantEncodeBase64ChunkZeroCoverageTest {
    @Test
    public void testEncodeBase64Chunk() {
        // Create a Base64Variant instance with a valid _base64ToAsciiC array
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);

        // Create a buffer with sufficient capacity
        char[] buffer = new char[4];

        // Valid b24 value such that (b24 >> 18) & 0x3F is within the bounds of _base64ToAsciiC
        int b24 = 0x00FF00FF; // This value will generate valid indices

        // Call the method under test
        int result = variant.encodeBase64Chunk(b24, buffer, 0);

        // The method should execute without error and return the correct ptr value
        // This test ensures that the target lines are executed
    }
}
