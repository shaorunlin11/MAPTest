package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatisCommentMarkerSetTest {

    @Test
    public void testIsCommentMarkerSetWithNullCommentMarker() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.isCommentMarkerSet());
    }

    @Test
    public void testIsCommentMarkerSetWithNonNullCommentMarker() {
        CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        assertTrue(format.isCommentMarkerSet());
    }
}
