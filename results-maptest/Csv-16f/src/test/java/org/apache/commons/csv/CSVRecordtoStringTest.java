package org.apache.commons.csv;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CSVRecordtoStringTest {

    @Test
    public void testToString() throws Exception {
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("header1", 0);
        mapping.put("header2", 1);
        String comment = "This is a comment";
        long recordNumber = 123;
        long characterPosition = 456;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);

        String expected = "CSVRecord [comment=" + comment + ", mapping=" + mapping +
                ", recordNumber=" + recordNumber + ", values=" + Arrays.toString(values) + "]";
        assertEquals(expected, record.toString());
    }

    @Test
    public void testToStringWithEmptyValues() throws Exception {
        String[] values = {};
        Map<String, Integer> mapping = new HashMap<>();
        String comment = null;
        long recordNumber = 0;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);

        String expected = "CSVRecord [comment=" + comment + ", mapping=" + mapping +
                ", recordNumber=" + recordNumber + ", values=" + Arrays.toString(values) + "]";
        assertEquals(expected, record.toString());
    }

    @Test
    public void testToStringWithNullComment() throws Exception {
        String[] values = {"value1"};
        Map<String, Integer> mapping = new HashMap<>();
        String comment = null;
        long recordNumber = 1;
        long characterPosition = 0;

        CSVRecord record = new CSVRecord(values, mapping, comment, recordNumber, characterPosition);

        String expected = "CSVRecord [comment=" + comment + ", mapping=" + mapping +
                ", recordNumber=" + recordNumber + ", values=" + Arrays.toString(values) + "]";
        assertEquals(expected, record.toString());
    }
}
