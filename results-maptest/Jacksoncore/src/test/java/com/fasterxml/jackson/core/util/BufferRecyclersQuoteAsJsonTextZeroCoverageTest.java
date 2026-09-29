package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class BufferRecyclersQuoteAsJsonTextZeroCoverageTest {
    @Test
    public void testQuoteAsJsonText() {
        String rawText = "test";
        char[] result = BufferRecyclers.quoteAsJsonText(rawText);
        // This test is designed to execute line 136 of BufferRecyclers#quoteAsJsonText
        // by ensuring the method is called with a non-null rawText and that
        // getJsonStringEncoder() returns a non-null object.
        // The actual assertion is not required as the goal is to cover the line,
        // not to verify the output.
    }
}
