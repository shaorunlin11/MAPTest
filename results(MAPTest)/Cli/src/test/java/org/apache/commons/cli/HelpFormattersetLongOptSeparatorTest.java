package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class HelpFormattersetLongOptSeparatorTest {
    private HelpFormatter formatter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testSetLongOptSeparator() throws Exception {
        // Arrange
        String expectedSeparator = "=>";

        // Act
        formatter.setLongOptSeparator(expectedSeparator);

        // Assert
        Field longOptSeparatorField = HelpFormatter.class.getDeclaredField("longOptSeparator");
        longOptSeparatorField.setAccessible(true);
        String actualSeparator = (String) longOptSeparatorField.get(formatter);
        Assert.assertEquals("The longOptSeparator should be set correctly", expectedSeparator, actualSeparator);
    }

    @Test
    public void testSetLongOptSeparatorWithNull() throws Exception {
        // Arrange
        String expectedSeparator = null;

        // Act
        formatter.setLongOptSeparator(expectedSeparator);

        // Assert
        Field longOptSeparatorField = HelpFormatter.class.getDeclaredField("longOptSeparator");
        longOptSeparatorField.setAccessible(true);
        String actualSeparator = (String) longOptSeparatorField.get(formatter);
        Assert.assertEquals("The longOptSeparator should be set to null", expectedSeparator, actualSeparator);
    }
}
