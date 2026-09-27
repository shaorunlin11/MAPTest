package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Assert;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CSVRecorditeratorTest {

    @Test
    public void testIterator() throws Exception {
        // Create a CSVRecord with sample data
        String[] values = {"value1", "value2", "value3"};
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("header1", 0);
        mapping.put("header2", 1);
        mapping.put("header3", 2);
        CSVRecord record = new CSVRecord(values, mapping, null, 1, 0);

        // Get the iterator
        Iterator<String> iterator = record.iterator();

        // Verify the iterator contains the correct elements
        List<String> result = new java.util.ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        Assert.assertEquals("Iterator should contain all values", 3, result.size());
        Assert.assertEquals("First element should be value1", "value1", result.get(0));
        Assert.assertEquals("Second element should be value2", "value2", result.get(1));
        Assert.assertEquals("Third element should be value3", "value3", result.get(2));
    }
}
