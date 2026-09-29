package com.fasterxml.jackson.core.io;

import org.junit.Test;

public class JsonStringEncoderQuoteAsStringZeroCoverageTest {
    @Test
    public void testQuoteAsStringTargetLine86() {
        JsonStringEncoder encoder = new JsonStringEncoder();
        String input = "test\\n";
        char[] result = encoder.quoteAsString(input);
        // Additional assertions can be added here if needed
    }
}
