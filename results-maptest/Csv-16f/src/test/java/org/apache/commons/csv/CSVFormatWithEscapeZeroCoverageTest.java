package org.apache.commons.csv;

import org.junit.Test;

public class CSVFormatWithEscapeZeroCoverageTest {
    @Test
    public void testWithEscapeLineBreak() {
        // Test case to cover target line 1581: the check for line break in withEscape method
        // Using a line break character as escape character should throw IllegalArgumentException
        // This test ensures that the condition isLineBreak(escape) returns true and triggers the exception
        final Character lineBreak = '\n'; // Line break character
        try {
            CSVFormat.DEFAULT.withEscape(lineBreak);
            // If no exception is thrown, the test fails
            throw new AssertionError("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }
}
