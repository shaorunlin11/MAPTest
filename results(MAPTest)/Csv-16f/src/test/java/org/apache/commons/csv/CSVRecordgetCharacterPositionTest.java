package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CSVRecordgetCharacterPositionTest {

    @Test
    public void testGetCharacterPosition() throws Exception {
        // Arrange
        long expectedPosition = 12345L;
        String[] values = {"value1", "value2"};
        Map<String, Integer> mapping = new HashMap<>();
        String comment = "This is a comment";
        long recordNumber = 1L;

        // Create CSVRecord instance using reflection to invoke constructor
        Class<?> csvRecordClass = CSVRecord.class;
        Constructor<?> constructor = csvRecordClass.getDeclaredConstructor(
            String[].class, Map.class, String.class, long.class, long.class);
        constructor.setAccessible(true);
        CSVRecord record = (CSVRecord) constructor.newInstance(
            values, mapping, comment, recordNumber, expectedPosition);

        // Act
        long actualPosition = record.getCharacterPosition();

        // Assert
        assertEquals(expectedPosition, actualPosition);
    }
}
