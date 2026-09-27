package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Assert;

public class CSVFormatIsQuoteCharacterSetZeroCoverageTest {
    @Test
    public void testIsQuoteCharacterSet() {
        // Create a CSVFormat instance with a non-null quoteCharacter
        CSVFormat format = CSVFormat.newFormat(',').withQuote('"');

        // Assert that isQuoteCharacterSet returns true when quoteCharacter is not null
        Assert.assertTrue(format.isQuoteCharacterSet());
    }
}
