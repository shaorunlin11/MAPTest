package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class CSVFormatgetHeaderCommentsTest {

    @Test
    public void testGetHeaderCommentsReturnsNullWhenHeaderCommentsIsNull() {
        CSVFormat format = CSVFormat.DEFAULT.withHeaderComments(null);
        String[] result = format.getHeaderComments();
        assertNull(result);
    }

    @Test
    public void testGetHeaderCommentsReturnsClonedArrayWhenHeaderCommentsIsNotNull() {
        String[] headerComments = {"Comment 1", "Comment 2"};
        CSVFormat format = CSVFormat.DEFAULT.withHeaderComments(headerComments);
        String[] result = format.getHeaderComments();
        assertNotNull(result);
        assertNotSame(headerComments, result);
        assertEquals(Arrays.toString(headerComments), Arrays.toString(result));
    }
}
