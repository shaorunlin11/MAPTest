package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithTrailingDelimiter_0c353391Test {

    @Test
    public void testWithTrailingDelimiter() {
        CSVFormat format = CSVFormat.DEFAULT;
        boolean newTrailingDelimiter = true;

        CSVFormat newFormat = format.withTrailingDelimiter(newTrailingDelimiter);

        // Verify that the new instance has the correct trailingDelimiter value
        assertTrue("New format should have trailingDelimiter set to true", newFormat.getTrailingDelimiter());

        // Verify that other configuration values remain unchanged
        assertFalse("Original format's trailingDelimiter should remain false", format.getTrailingDelimiter());
        assertEquals("Delimiter should remain unchanged", CSVFormat.DEFAULT.getDelimiter(), newFormat.getDelimiter());
        assertEquals("Quote character should remain unchanged", CSVFormat.DEFAULT.getQuoteCharacter(), newFormat.getQuoteCharacter());
        assertEquals("Quote mode should remain unchanged", CSVFormat.DEFAULT.getQuoteMode(), newFormat.getQuoteMode());
        assertEquals("Comment marker should remain unchanged", CSVFormat.DEFAULT.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals("Escape character should remain unchanged", CSVFormat.DEFAULT.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals("Ignore surrounding spaces should remain unchanged", CSVFormat.DEFAULT.getIgnoreSurroundingSpaces(), newFormat.getIgnoreSurroundingSpaces());
        assertEquals("Ignore empty lines should remain unchanged", CSVFormat.DEFAULT.getIgnoreEmptyLines(), newFormat.getIgnoreEmptyLines());
        assertEquals("Record separator should remain unchanged", CSVFormat.DEFAULT.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals("Null string should remain unchanged", CSVFormat.DEFAULT.getNullString(), newFormat.getNullString());
        assertArrayEquals("Header comments should remain unchanged", CSVFormat.DEFAULT.getHeaderComments(), newFormat.getHeaderComments());
        assertArrayEquals("Header should remain unchanged", CSVFormat.DEFAULT.getHeader(), newFormat.getHeader());
        assertEquals("Skip header record should remain unchanged", CSVFormat.DEFAULT.getSkipHeaderRecord(), newFormat.getSkipHeaderRecord());
        assertEquals("Allow missing column names should remain unchanged", CSVFormat.DEFAULT.getAllowMissingColumnNames(), newFormat.getAllowMissingColumnNames());
        assertEquals("Ignore header case should remain unchanged", CSVFormat.DEFAULT.getIgnoreHeaderCase(), newFormat.getIgnoreHeaderCase());
        assertEquals("Trim should remain unchanged", CSVFormat.DEFAULT.getTrim(), newFormat.getTrim());
        assertEquals("Auto flush should remain unchanged", CSVFormat.DEFAULT.getAutoFlush(), newFormat.getAutoFlush());
    }
}
