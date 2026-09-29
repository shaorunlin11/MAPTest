package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithAutoFlushTest {

    @Test
    public void testWithAutoFlush() {
        CSVFormat format = CSVFormat.DEFAULT;
        boolean newAutoFlush = true;
        CSVFormat newFormat = format.withAutoFlush(newAutoFlush);

        assertTrue(newFormat.getAutoFlush());
        assertNotSame(format, newFormat);
    }

    @Test
    public void testWithAutoFlushFalse() {
        CSVFormat format = CSVFormat.DEFAULT;
        boolean newAutoFlush = false;
        CSVFormat newFormat = format.withAutoFlush(newAutoFlush);

        assertFalse(newFormat.getAutoFlush());
        assertNotSame(format, newFormat);
    }

    @Test
    public void testWithAutoFlushPreservesOtherSettings() {
        CSVFormat format = CSVFormat.EXCEL;
        boolean newAutoFlush = true;
        CSVFormat newFormat = format.withAutoFlush(newAutoFlush);

        assertTrue(newFormat.getAutoFlush());
        assertEquals(format.getAllowMissingColumnNames(), newFormat.getAllowMissingColumnNames());
        assertEquals(format.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals(format.getDelimiter(), newFormat.getDelimiter());
        assertEquals(format.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals(format.getIgnoreEmptyLines(), newFormat.getIgnoreEmptyLines());
        assertEquals(format.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals(format.getNullString(), newFormat.getNullString());
        assertArrayEquals(format.getHeaderComments(), newFormat.getHeaderComments());
        assertArrayEquals(format.getHeader(), newFormat.getHeader());
        assertEquals(format.getSkipHeaderRecord(), newFormat.getSkipHeaderRecord());
        assertEquals(format.getIgnoreHeaderCase(), newFormat.getIgnoreHeaderCase());
        assertEquals(format.getTrailingDelimiter(), newFormat.getTrailingDelimiter());
        assertEquals(format.getTrim(), newFormat.getTrim());
        assertNotSame(format, newFormat);
    }
}
