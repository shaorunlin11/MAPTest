package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordgetCommentTest {

    @Test
    public void testGetCommentReturnsSetComment() throws Exception {
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        String comment = "This is a comment";
        long recordNumber = 1;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);
        assertEquals("The comment should be returned as set", comment, record.getComment());
    }

    @Test
    public void testGetCommentReturnsNullWhenNotSet() throws Exception {
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        String comment = null;
        long recordNumber = 1;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);
        assertNull("The comment should be null when not set", record.getComment());
    }
}
