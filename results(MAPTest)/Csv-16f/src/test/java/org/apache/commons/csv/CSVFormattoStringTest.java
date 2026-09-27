package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormattoStringTest {

    @Test
    public void testToString() {
        // Create a CSVFormat instance with isEscapeCharacterSet() returning true
        CSVFormat format = CSVFormat.DEFAULT
            .withDelimiter(',')
            .withQuote('"')
            .withEscape('\\')
            .withRecordSeparator("\n")
            .withNullString("NULL")
            .withHeader("Header1", "Header2");

        // Verify that the toString() method includes the escape character section
        String result = format.toString();
        assertTrue("Result should contain 'Escape=<\\>'", result.contains("Escape=<\\>"));
    }

@Test
    public void testToStringCommentMarkerSet() {
        // Create a CSVFormat instance with isCommentMarkerSet() returning true
        CSVFormat format = CSVFormat.DEFAULT
            .withDelimiter(',')
            .withCommentMarker('#')
            .withEscape((char) 0) // Ensure escape character is not set
            .withQuote((char) 0) // Ensure quote character is not set
            .withRecordSeparator("\n")
            .withNullString("NULL")
            .withHeader("Header1", "Header2");

        // Verify that the toString() method includes the comment start section
        String result = format.toString();
        assertTrue("Result should contain 'CommentStart=<#>'", result.contains("CommentStart=<#>"));
    }
}
