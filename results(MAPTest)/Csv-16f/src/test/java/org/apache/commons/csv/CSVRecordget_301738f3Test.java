package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.HashMap;

public class CSVRecordget_301738f3Test {
    private CSVRecord csvRecord;

    @Before
    public void setUp() {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 0);
        mapping.put("age", 1);
        String[] values = {"John", "30"};
        csvRecord = new CSVRecord(values, mapping, null, 1, 0);
    }

    public enum TestEnum { NAME, AGE }

    @Test
    public void testGetWithEnum() {
        Assert.assertEquals("John", csvRecord.get("name"));
        Assert.assertEquals("30", csvRecord.get("age"));
    }
}
