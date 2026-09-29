package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatnewFormatTest {

    @Test
    public void testNewFormat() {
        char delimiter = ',';
        CSVFormat format = CSVFormat.newFormat(delimiter);

        assertEquals("Delimiter should be set to the provided value", delimiter, format.getDelimiter());
        assertNull("Quote character should be null", format.getQuoteCharacter());
        assertNull("Quote mode should be null", format.getQuoteMode());
        assertNull("Comment marker should be null", format.getCommentMarker());
        assertNull("Escape character should be null", format.getEscapeCharacter());
        assertFalse("Ignore surrounding spaces should be false", format.getIgnoreSurroundingSpaces());
        assertFalse("Ignore empty lines should be false", format.getIgnoreEmptyLines());
        assertNull("Record separator should be null", format.getRecordSeparator());
        assertNull("Null string should be null", format.getNullString());
        assertNull("Header comments should be null", format.getHeaderComments());
        assertNull("Header should be null", format.getHeader());
        assertFalse("Skip header record should be false", format.getSkipHeaderRecord());
        assertFalse("Allow missing column names should be false", format.getAllowMissingColumnNames());
        assertFalse("Ignore header case should be false", format.getIgnoreHeaderCase());
        assertFalse("Trailing delimiter should be false", format.getTrailingDelimiter());
        assertFalse("Trim should be false", format.getTrim());
        assertFalse("Auto flush should be false", format.getAutoFlush());
    }
}
