package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordisConsistentTest {

    @Test
    public void testIsConsistentWithNullMapping() {
        String[] values = {"a", "b", "c"};
        Map<String, Integer> mapping = null;
        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);
        Assert.assertTrue(record.isConsistent());
    }

    @Test
    public void testIsConsistentWithMatchingSize() {
        String[] values = {"a", "b", "c"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("col1", 0);
        mapping.put("col2", 1);
        mapping.put("col3", 2);
        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);
        Assert.assertTrue(record.isConsistent());
    }

    @Test
    public void testIsConsistentWithMismatchedSize() {
        String[] values = {"a", "b"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("col1", 0);
        mapping.put("col2", 1);
        mapping.put("col3", 2);
        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);
        Assert.assertFalse(record.isConsistent());
    }
}
