package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithCommentMarker_07762449Test {
    @Test
    public void testWithCommentMarker() {
        CSVFormat format = CSVFormat.DEFAULT;
        char newCommentMarker = '#';

        CSVFormat newFormat = format.withCommentMarker(newCommentMarker);

        assertEquals(newCommentMarker, newFormat.getCommentMarker().charValue());
        assertNotSame(format, newFormat);
    }

    @Test
    public void testWithCommentMarkerWithCharacter() {
        CSVFormat format = CSVFormat.DEFAULT;
        Character newCommentMarker = '#';

        CSVFormat newFormat = format.withCommentMarker(newCommentMarker);

        assertEquals(newCommentMarker, newFormat.getCommentMarker());
        assertNotSame(format, newFormat);
    }
}
