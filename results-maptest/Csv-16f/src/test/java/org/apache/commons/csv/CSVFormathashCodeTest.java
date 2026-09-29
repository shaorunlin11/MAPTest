package org.apache.commons.csv;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;
public class CSVFormathashCodeTest {
    @Test
    public void testHashCode() {
        // Create two CSVFormat instances with the same configuration
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithQuoteModeNull() {
        // Create CSVFormat instances with quoteMode null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(null);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(null);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithQuoteModeNotNull() {
        // Create CSVFormat instances with quoteMode not null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithQuoteCharacterNull() {
        // Create CSVFormat instances with quoteCharacter null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote(null)
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote(null)
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithQuoteCharacterNotNull() {
        // Create CSVFormat instances with quoteCharacter not null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithCommentMarkerNull() {
        // Create CSVFormat instances with commentMarker null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withCommentMarker(null);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withCommentMarker(null);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithCommentMarkerNotNull() {
        // Create CSVFormat instances with commentMarker not null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withCommentMarker('#');

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withCommentMarker('#');

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithEscapeCharacterNull() {
        // Create CSVFormat instances with escapeCharacter null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape(null)
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape(null)
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithEscapeCharacterNotNull() {
        // Create CSVFormat instances with escapeCharacter not null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithNullStringNull() {
        // Create CSVFormat instances with nullString null
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString(null)
                .withQuoteMode(null);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString(null)
                .withQuoteMode(null);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreSurroundingSpacesTrue() {
        // Create CSVFormat instances with ignoreSurroundingSpaces true
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreSurroundingSpaces(true);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreSurroundingSpaces(true);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreSurroundingSpacesFalse() {
        // Create CSVFormat instances with ignoreSurroundingSpaces false
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreSurroundingSpaces(false);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreSurroundingSpaces(false);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreHeaderCaseTrue() {
        // Create CSVFormat instances with ignoreHeaderCase true
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreHeaderCase(true);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreHeaderCase(true);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreHeaderCaseFalse() {
        // Create CSVFormat instances with ignoreHeaderCase false
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreHeaderCase(false);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreHeaderCase(false);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreEmptyLinesTrue() {
        // Create CSVFormat instances with ignoreEmptyLines true
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreEmptyLines(true);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreEmptyLines(true);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithIgnoreEmptyLinesFalse() {
        // Create CSVFormat instances with ignoreEmptyLines false
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreEmptyLines(false);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withIgnoreEmptyLines(false);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithSkipHeaderRecordTrue() {
        // Create CSVFormat instances with skipHeaderRecord true
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withSkipHeaderRecord(true);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withSkipHeaderRecord(true);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }

@Test
    public void testHashCodeWithSkipHeaderRecordFalse() {
        // Create CSVFormat instances with skipHeaderRecord false
        CSVFormat format1 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withSkipHeaderRecord(false);

        CSVFormat format2 = CSVFormat.DEFAULT
                .withDelimiter(',')
                .withQuote('"')
                .withEscape('\\')
                .withRecordSeparator("\r\n")
                .withNullString("\\N")
                .withQuoteMode(QuoteMode.ALL_NON_NULL)
                .withSkipHeaderRecord(false);

        // Verify that hash codes are equal for identical configurations
        assertEquals("Hash codes should be equal for identical configurations", format1.hashCode(), format2.hashCode());
    }
}
