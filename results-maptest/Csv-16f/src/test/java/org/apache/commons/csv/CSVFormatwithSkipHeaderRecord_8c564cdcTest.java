package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithSkipHeaderRecord_8c564cdcTest {

    @Test
    public void testWithSkipHeaderRecord() {
        CSVFormat format = CSVFormat.DEFAULT;
        boolean newSkipHeaderRecord = true;
        CSVFormat newFormat = format.withSkipHeaderRecord(newSkipHeaderRecord);

        // Verify that the new instance has the correct skipHeaderRecord value
        assertTrue("New format should have skipHeaderRecord set to true", newFormat.getSkipHeaderRecord());

        // Verify that other properties are preserved using public API methods
        assertEquals("Delimiter should be unchanged", CSVFormat.DEFAULT.getDelimiter(), newFormat.getDelimiter());
        assertEquals("Quote character should be unchanged", CSVFormat.DEFAULT.getQuoteCharacter(), newFormat.getQuoteCharacter());
        assertEquals("Quote mode should be unchanged", CSVFormat.DEFAULT.getQuoteMode(), newFormat.getQuoteMode());
        assertEquals("Comment marker should be unchanged", CSVFormat.DEFAULT.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals("Escape character should be unchanged", CSVFormat.DEFAULT.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals("Record separator should be unchanged", CSVFormat.DEFAULT.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals("Null string should be unchanged", CSVFormat.DEFAULT.getNullString(), newFormat.getNullString());
        assertArrayEquals("Header comments should be unchanged", CSVFormat.DEFAULT.getHeaderComments(), newFormat.getHeaderComments());
        assertArrayEquals("Header should be unchanged", CSVFormat.DEFAULT.getHeader(), newFormat.getHeader());
    }
}
