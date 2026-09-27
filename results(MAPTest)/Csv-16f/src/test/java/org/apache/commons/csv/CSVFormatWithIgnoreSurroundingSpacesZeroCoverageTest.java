package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatWithIgnoreSurroundingSpacesZeroCoverageTest {
    @Test
    public void testWithIgnoreSurroundingSpaces() {
        // Create a CSVFormat instance with non-null delimiter and quoteCharacter
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withQuote('\"');

        // Call the method under test
        CSVFormat newFormat = format.withIgnoreSurroundingSpaces(true);

        // Verify that the method returns a non-null instance
        assertNotNull(newFormat);

        // Verify that the ignoreSurroundingSpaces flag is set correctly
        assertTrue(newFormat.getIgnoreSurroundingSpaces());
    }
}
