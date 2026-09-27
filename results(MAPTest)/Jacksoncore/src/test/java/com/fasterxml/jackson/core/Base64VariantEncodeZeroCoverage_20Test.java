package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class Base64VariantEncodeZeroCoverage_20Test {
    @Test
    public void testEncodeWithZeroLengthInput() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        byte[] input = new byte[0];
        String result = variant.encode(input, false);
        // Expected behavior: returns empty string
        Assert.assertEquals("", result);
    }

@Test
    public void testEncodeWithAddQuotesAndNonEmptyInput() {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        byte[] input = new byte[100]; // Use a larger input to trigger line breaks
        String result = variant.encode(input, true);
        // Expected behavior: returns a quoted Base64 string
        Assert.assertTrue(result.startsWith("\""));
        Assert.assertTrue(result.endsWith("\""));
        // Verify that the target line 381 is executed (the append of '\n' and 'n')
        // This is achieved by ensuring the input length is such that the loop runs and the line break is triggered
        Assert.assertTrue(result.contains("\\n"));
    }
}
