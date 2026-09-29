package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class OptiongetValue_42dd55d7Test {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test", false, "test description");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testGetValue_returnsValueWhenNotNull() {
        // Arrange
        String expectedValue = "testValue";
        List<String> values = new ArrayList<>();
        values.add(expectedValue);
        // Use reflection to set the values field
        try {
            java.lang.reflect.Field valuesField = Option.class.getDeclaredField("values");
            valuesField.setAccessible(true);
            valuesField.set(option, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        String result = option.getValue("defaultValue");

        // Assert
        Assert.assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValue_returnsDefaultValueWhenValueIsNull() {
        // Arrange
        String defaultValue = "defaultValue";

        // Act
        String result = option.getValue(defaultValue);

        // Assert
        Assert.assertEquals(defaultValue, result);
    }
}
