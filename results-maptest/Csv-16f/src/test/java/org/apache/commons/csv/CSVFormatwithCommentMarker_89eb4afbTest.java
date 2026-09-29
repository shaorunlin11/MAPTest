package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithCommentMarker_89eb4afbTest {

    @Test
    public void testWithCommentMarker() {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        assertEquals('#', format.getCommentMarker().charValue());
    }

    @Test
    public void testWithCommentMarkerThrowsExceptionForLineBreak() {
        try {
            CSVFormat.DEFAULT.withCommentMarker('\n');
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testWithCommentMarkerPreservesOtherProperties() {
        CSVFormat format = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withQuote('\"')
                .withEscape('\\')
                .withCommentMarker('#');

        assertEquals(';', format.getDelimiter());
        assertEquals('\"', format.getQuoteCharacter().charValue());
        assertEquals('\\', format.getEscapeCharacter().charValue());
        assertEquals('#', format.getCommentMarker().charValue());
    }

    @Test
    public void testWithCommentMarkerWithNullValue() {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker(null);
        assertNull(format.getCommentMarker());
    }
}
