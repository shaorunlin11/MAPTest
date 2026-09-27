package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordsizeTest {
    @Test
    public void testSizeReturnsValuesLength() throws Exception {
        // Create a CSVRecord with a known values array
        String[] values = {"a", "b", "c"};
        Map<String, Integer> mapping = new HashMap<>();
        CSVRecord record = new CSVRecord(values, mapping, null, 1, 0);

        // Verify the size method returns the correct length
        assertEquals(3, record.size());
    }

    @Test
    public void testSizeReturnsZeroForEmptyValues() throws Exception {
        // Create a CSVRecord with an empty values array
        String[] values = {};
        Map<String, Integer> mapping = new HashMap<>();
        CSVRecord record = new CSVRecord(values, mapping, null, 1, 0);

        // Verify the size method returns 0
        assertEquals(0, record.size());
    }

    @Test
    public void testSizeReturnsCorrectLengthAfterConstructorInitialization() throws Exception {
        // Create a CSVRecord with a non-empty values array
        String[] values = {"x", "y"};
        Map<String, Integer> mapping = new HashMap<>();
        CSVRecord record = new CSVRecord(values, mapping, null, 1, 0);

        // Verify the size method returns the correct length
        assertEquals(2, record.size());
    }
}
