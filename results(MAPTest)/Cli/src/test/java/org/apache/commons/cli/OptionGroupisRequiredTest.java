package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class OptionGroupisRequiredTest {
    private OptionGroup optionGroup;

    @Before
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @After
    public void tearDown() {
        optionGroup = null;
    }

    @Test
    public void testIsRequiredReturnsDefaultValue() {
        // Arrange: No action needed, default value is false
        // Act
        boolean result = optionGroup.isRequired();
        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testIsRequiredReturnsSetValues() throws Exception {
        // Arrange
        Field requiredField = OptionGroup.class.getDeclaredField("required");
        requiredField.setAccessible(true);

        // Act 1: Set to true
        requiredField.set(optionGroup, true);
        boolean result1 = optionGroup.isRequired();

        // Act 2: Set to false
        requiredField.set(optionGroup, false);
        boolean result2 = optionGroup.isRequired();

        // Assert
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }
}
