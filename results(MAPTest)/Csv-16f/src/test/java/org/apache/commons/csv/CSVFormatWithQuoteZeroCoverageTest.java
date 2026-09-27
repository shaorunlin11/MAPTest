package org.apache.commons.csv;

import org.junit.Test;

public class CSVFormatWithQuoteZeroCoverageTest {
    @Test
    public void testWithQuoteLineBreak() {
        // Test case to cover target line 1883: check that isLineBreak(quoteChar) returns true
        // and quoteChar is not null, which should trigger the IllegalArgumentException
        Character quoteChar = '\n'; // Line break character
        CSVFormat format = CSVFormat.DEFAULT;
        try {
            format.withQuote(quoteChar);
            // If no exception is thrown, the test fails
            throw new AssertionError("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }
}
