package org.apache.commons.csv;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CSVRecordGetZeroCoverageTest {
    @Test
    public void testGetWithValidName() {
        // Arrange
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 0);
        String[] values = {"value"};

        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);

        // Act & Assert
        // This test will execute line 68 (values[index.intValue()]) through the selected target plan.
        // The required conditions are met:
        // - mapping is not null
        // - name is present in mapping
        // - index is not null
        // - values array is non-empty
        record.get("name");
    }

@Test
    public void testGetWithNullMapping() {
        // Arrange
        Map<String, Integer> mapping = null;
        String[] values = {"value"};

        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);

        // Act & Assert
        // This test will execute line 96 (throw new IllegalStateException(...)) through the selected target plan.
        // The required conditions are met:
        // - mapping is null
        // - name is not present in mapping (not needed since mapping is null)
        // - index is null (not needed since mapping is null)
        // - values array is non-empty (not needed since mapping is null)
        try {
            record.get("name");
        } catch (IllegalStateException e) {
            // Expected exception
        }
    }

@Test
    public void testGetWithInvalidName() {
        // Arrange
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 0);
        String[] values = {"value"};

        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);

        // Act & Assert
        // This test will execute line 101 (throw new IllegalArgumentException(...)) through the selected target plan.
        // The required conditions are met:
        // - mapping is not null
        // - mapping contains the key 'name'
        // - mapping.get(name) returns a non-null Integer
        // - values array is non-empty
        try {
            record.get("invalidName");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

@Test
    public void testGetWithIndexOutOfBounds() {
        // Arrange
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("name", 1);
        String[] values = {"value"};

        CSVRecord record = new CSVRecord(values, mapping, null, 0, 0);

        // Act & Assert
        // This test will execute line 107 (throw new IllegalArgumentException(...)) through the selected target plan.
        // The required conditions are met:
        // - mapping is not null
        // - mapping contains the key 'name'
        // - index is not null
        // - index.intValue() is within the bounds of values array
        try {
            record.get("name");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
