package org.apache.commons.csv;
import org.junit.Test;
import static org.junit.Assert.*;
public class CSVFormatequalsTest {
    @Test
    public void testEqualsSameObject() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.equals(format));
    }

    @Test
    public void testEqualsNull() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        CSVFormat format = CSVFormat.DEFAULT;
        Object other = new Object();
        assertFalse(format.equals(other));
    }

    @Test
    public void testEqualsDifferentDelimiter() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withDelimiter('|');
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameDelimiter() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentQuoteMode() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL);
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameQuoteMode() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }


    @Test
    public void testEqualsSameQuoteCharacter() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentCommentMarker() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withCommentMarker('#');
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameCommentMarker() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentEscapeCharacter() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withEscape('\\');
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameEscapeCharacter() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentNullString() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withNullString("null");
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameNullString() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentHeader() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withHeader("header1", "header2");
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameHeader() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentIgnoreSurroundingSpaces() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true);
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameIgnoreSurroundingSpaces() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }


    @Test
    public void testEqualsSameIgnoreEmptyLines() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

    @Test
    public void testEqualsDifferentSkipHeaderRecord() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
        assertFalse(format1.equals(format2));
    }

    @Test
    public void testEqualsSameSkipHeaderRecord() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }


    @Test
    public void testEqualsSameRecordSeparator() {
        CSVFormat format1 = CSVFormat.DEFAULT;
        CSVFormat format2 = CSVFormat.DEFAULT;
        assertTrue(format1.equals(format2));
    }

@Test
    public void testEqualsQuoteCharacterNullOtherNotNull() {
        CSVFormat format1 = CSVFormat.DEFAULT.withQuote('\"');
        CSVFormat format2 = CSVFormat.DEFAULT.withQuote((char) 0);
        assertFalse(format1.equals(format2));
    }

@Test
    public void testEqualsCommentMarkerNotEqual() {
        CSVFormat format1 = CSVFormat.DEFAULT.withCommentMarker('A');
        CSVFormat format2 = CSVFormat.DEFAULT.withCommentMarker('B');
        assertFalse(format1.equals(format2));
    }

@Test
    public void testEqualsTargetLine726() {
        // Target line 726 is part of the check for commentMarker and quoteCharacter
        // We need to ensure that the test covers the branch where both are not null and equal
        CSVFormat format1 = CSVFormat.DEFAULT.withCommentMarker('#').withQuote('\"');
        CSVFormat format2 = CSVFormat.DEFAULT.withCommentMarker('#').withQuote('\"');
        assertTrue(format1.equals(format2));
    }

@Test
    public void testEqualsTargetLine733() {
        // Target line 733 is the check for escapeCharacter
        // We need to ensure that the test covers the branch where escapeCharacter is not null and equal
        CSVFormat format1 = CSVFormat.DEFAULT.withEscape('\\');
        CSVFormat format2 = CSVFormat.DEFAULT.withEscape('\\');
        assertTrue(format1.equals(format2));
    }
}
