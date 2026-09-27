package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordhasCommentTest {

    @Test
    public void testHasCommentWithNullComment() throws Exception {
        // Arrange
        String[] values = new String[0];
        Map<String, Integer> mapping = new HashMap<>();
        String comment = null;
        long recordNumber = 1;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);

        // Act
        boolean result = record.hasComment();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testHasCommentWithNonNullComment() throws Exception {
        // Arrange
        String[] values = new String[0];
        Map<String, Integer> mapping = new HashMap<>();
        String comment = "This is a comment";
        long recordNumber = 1;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);

        // Act
        boolean result = record.hasComment();

        // Assert
        assertTrue(result);
    }
}
