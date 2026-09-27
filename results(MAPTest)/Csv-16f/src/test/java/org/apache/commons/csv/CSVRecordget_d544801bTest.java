package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordget_d544801bTest {
    @Test
    public void testGet() {
        // Create a CSVRecord with some values
        String[] values = {"value1", "value2", "value3"};
        Map<String, Integer> mapping = new HashMap<>();
        CSVRecord record = new CSVRecord(values, mapping, null, 1, 0);

        // Test valid indices
        Assert.assertEquals("value1", record.get(0));
        Assert.assertEquals("value2", record.get(1));
        Assert.assertEquals("value3", record.get(2));

        // Test empty values array
        CSVRecord emptyRecord = new CSVRecord(new String[0], mapping, null, 1, 0);
        try {
            emptyRecord.get(0);
            Assert.fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception
        }

        // Test out-of-bound index
        try {
            record.get(3);
            Assert.fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception
        }
    }
}
