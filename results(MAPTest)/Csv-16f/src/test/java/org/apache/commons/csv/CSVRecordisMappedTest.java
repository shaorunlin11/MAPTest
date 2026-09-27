package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordisMappedTest {

    @Test
    public void testIsMappedWithNonNullMappingAndExistingKey() {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 0);
        CSVRecord record = new CSVRecord(new String[] {"value"}, mapping, null, 0, 0);
        Assert.assertTrue(record.isMapped("name"));
    }

    @Test
    public void testIsMappedWithNonNullMappingAndNonExistingKey() {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 0);
        CSVRecord record = new CSVRecord(new String[] {"value"}, mapping, null, 0, 0);
        Assert.assertFalse(record.isMapped("nonExistingKey"));
    }

    @Test
    public void testIsMappedWithNullMapping() {
        CSVRecord record = new CSVRecord(new String[] {"value"}, null, null, 0, 0);
        Assert.assertFalse(record.isMapped("name"));
    }
}
