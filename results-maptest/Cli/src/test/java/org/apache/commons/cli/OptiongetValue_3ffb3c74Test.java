package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class OptiongetValue_3ffb3c74Test {
    @Test
    public void testGetValue_returnsNullWhenNoValues() throws Exception {
        // Arrange
        Option option = new Option("t", "test option");

        // Act
        String result = option.getValue();

        // Assert
        assertNull("Should return null when no values are present", result);
    }

    @Test
    public void testGetValue_returnsFirstValueWhenValuesExist() throws Exception {
        // Arrange
        Option option = new Option("t", "test option");
        List<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");

        // Use reflection to set the values field
        java.lang.reflect.Field valuesField = Option.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        valuesField.set(option, values);

        // Act
        String result = option.getValue();

        // Assert
        assertEquals("Should return the first value from the values list", "value1", result);
    }
}
