package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordisSetTest {
    private CSVRecord csvRecord;

    @Before
    public void setUp() {
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name1", 0);
        mapping.put("name2", 1);
        csvRecord = new CSVRecord(values, mapping, null, 1, 1);
    }

    @After
    public void tearDown() {
        csvRecord = null;
    }

    @Test
    public void testIsSet_WithValidName_ReturnsTrue() {
        Assert.assertTrue(csvRecord.isSet("name1"));
        Assert.assertTrue(csvRecord.isSet("name2"));
    }

    @Test
    public void testIsSet_WithInvalidName_ReturnsFalse() {
        Assert.assertFalse(csvRecord.isSet("invalidName"));
    }

    @Test
    public void testIsSet_WithNameBeyondValuesLength_ReturnsFalse() {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name3", 2);
        CSVRecord record = new CSVRecord(new String[]{"value1", "value2"}, mapping, null, 1, 1);
        Assert.assertFalse(record.isSet("name3"));
    }
}
