package com.fasterxml.jackson.core;

import org.junit.Test;

public class Base64VariantEncodeBase64ChunkZeroCoverage_13Test {
    @Test
    public void testEncodeBase64Chunk() {
        // Create a Base64Variant instance with a valid _base64ToAsciiC array
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);

        // Create a StringBuilder to capture the output
        StringBuilder sb = new StringBuilder();

        // Call the method with a sample b24 value
        variant.encodeBase64Chunk(sb, 0x123456);
    }
}
