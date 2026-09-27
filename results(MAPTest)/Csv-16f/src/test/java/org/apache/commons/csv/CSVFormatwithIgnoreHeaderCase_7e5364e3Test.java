package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithIgnoreHeaderCase_7e5364e3Test {

    @Test
    public void testWithIgnoreHeaderCase() {
        CSVFormat format = CSVFormat.DEFAULT;
        boolean newIgnoreHeaderCase = true;
        CSVFormat newFormat = format.withIgnoreHeaderCase(newIgnoreHeaderCase);

        assertEquals(newIgnoreHeaderCase, newFormat.getIgnoreHeaderCase());

        // Verify other parameters are unchanged
        assertEquals(CSVFormat.DEFAULT.getDelimiter(), newFormat.getDelimiter());
        assertEquals(CSVFormat.DEFAULT.getQuoteCharacter(), newFormat.getQuoteCharacter());
        assertEquals(CSVFormat.DEFAULT.getQuoteMode(), newFormat.getQuoteMode());
        assertEquals(CSVFormat.DEFAULT.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals(CSVFormat.DEFAULT.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals(CSVFormat.DEFAULT.getIgnoreSurroundingSpaces(), newFormat.getIgnoreSurroundingSpaces());
        assertEquals(CSVFormat.DEFAULT.getIgnoreEmptyLines(), newFormat.getIgnoreEmptyLines());
        assertEquals(CSVFormat.DEFAULT.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals(CSVFormat.DEFAULT.getNullString(), newFormat.getNullString());
        assertArrayEquals(CSVFormat.DEFAULT.getHeaderComments(), newFormat.getHeaderComments());
        assertArrayEquals(CSVFormat.DEFAULT.getHeader(), newFormat.getHeader());
        assertEquals(CSVFormat.DEFAULT.getSkipHeaderRecord(), newFormat.getSkipHeaderRecord());
        assertEquals(CSVFormat.DEFAULT.getAllowMissingColumnNames(), newFormat.getAllowMissingColumnNames());
        assertEquals(CSVFormat.DEFAULT.getTrim(), newFormat.getTrim());
        assertEquals(CSVFormat.DEFAULT.getTrailingDelimiter(), newFormat.getTrailingDelimiter());
        assertEquals(CSVFormat.DEFAULT.getAutoFlush(), newFormat.getAutoFlush());
    }
}
