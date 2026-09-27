package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithHeader_31eb0466Test {

    @Test
    public void testWithHeader() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT;
        String[] header = {"col1", "col2", "col3"};

        // Act
        CSVFormat newFormat = format.withHeader(header);

        // Assert
        assertNotSame(format, newFormat);
        assertNotNull(newFormat.getHeader());
        assertEquals(header.length, newFormat.getHeader().length);
        for (int i = 0; i < header.length; i++) {
            assertEquals(header[i], newFormat.getHeader()[i]);
        }

        // Verify other properties are preserved
        assertEquals(format.getDelimiter(), newFormat.getDelimiter());
        assertEquals(format.getQuoteCharacter(), newFormat.getQuoteCharacter());
        assertEquals(format.getQuoteMode(), newFormat.getQuoteMode());
        assertEquals(format.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals(format.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals(format.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals(format.getNullString(), newFormat.getNullString());
        assertArrayEquals(format.getHeaderComments(), newFormat.getHeaderComments());
    }

    @Test
    public void testWithHeaderVarargs() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT;
        String[] header = {"col1", "col2", "col3"};

        // Act
        CSVFormat newFormat = format.withHeader("col1", "col2", "col3");

        // Assert
        assertNotSame(format, newFormat);
        assertNotNull(newFormat.getHeader());
        assertEquals(header.length, newFormat.getHeader().length);
        for (int i = 0; i < header.length; i++) {
            assertEquals(header[i], newFormat.getHeader()[i]);
        }

        // Verify other properties are preserved
        assertEquals(format.getDelimiter(), newFormat.getDelimiter());
        assertEquals(format.getQuoteCharacter(), newFormat.getQuoteCharacter());
        assertEquals(format.getQuoteMode(), newFormat.getQuoteMode());
        assertEquals(format.getCommentMarker(), newFormat.getCommentMarker());
        assertEquals(format.getEscapeCharacter(), newFormat.getEscapeCharacter());
        assertEquals(format.getRecordSeparator(), newFormat.getRecordSeparator());
        assertEquals(format.getNullString(), newFormat.getNullString());
        assertArrayEquals(format.getHeaderComments(), newFormat.getHeaderComments());
    }

    @Test
    public void testWithHeaderNull() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT;

        // Act
        CSVFormat newFormat = format.withHeader((String[]) null);

        // Assert
        assertNotSame(format, newFormat);
        assertNull(newFormat.getHeader());
    }
}
