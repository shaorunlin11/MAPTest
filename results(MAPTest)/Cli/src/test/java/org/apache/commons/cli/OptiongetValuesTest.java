package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class OptiongetValuesTest {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testGetValuesReturnsNullWhenNoValues() {
        // Arrange
        // No values added to the option

        // Act
        String[] result = option.getValues();

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetValuesReturnsValuesAsArray() {
        // Arrange
        List<String> expectedValues = new ArrayList<>();
        expectedValues.add("value1");
        expectedValues.add("value2");

        // Use reflection to set values since there's no public setValues method
        try {
            java.lang.reflect.Field valuesField = Option.class.getDeclaredField("values");
            valuesField.setAccessible(true);
            valuesField.set(option, new ArrayList<>(expectedValues));
        } catch (Exception e) {
            Assert.fail("Failed to set values using reflection: " + e.getMessage());
        }

        // Act
        String[] result = option.getValues();

        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedValues.size(), result.length);
        for (int i = 0; i < expectedValues.size(); i++) {
            Assert.assertEquals(expectedValues.get(i), result[i]);
        }
    }
}
