package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordtoMapTest {
    private CSVRecord csvRecord;

    @Before
    public void setUp() {
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("key1", 0);
        mapping.put("key2", 1);
        csvRecord = new CSVRecord(values, mapping, null, 1, 0);
    }

    @After
    public void tearDown() {
        csvRecord = null;
    }

    @Test
    public void testToMap() {
        Map<String, String> result = csvRecord.toMap();
        Assert.assertNotNull("The result map should not be null", result);
        Assert.assertEquals("The map should contain the correct number of entries", 2, result.size());
        Assert.assertEquals("The first entry should have the correct value", "value1", result.get("key1"));
        Assert.assertEquals("The second entry should have the correct value", "value2", result.get("key2"));
    }
}
