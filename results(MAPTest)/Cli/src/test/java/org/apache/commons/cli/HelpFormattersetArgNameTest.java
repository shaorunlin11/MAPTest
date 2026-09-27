package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class HelpFormattersetArgNameTest {
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
    public void testSetArgName() throws Exception {
        // Arrange
        String expectedArgName = "testArg";

        // Act
        formatter.setArgName(expectedArgName);

        // Assert
        Field field = HelpFormatter.class.getDeclaredField("defaultArgName");
        field.setAccessible(true);
        String actualArgName = (String) field.get(formatter);
        Assert.assertEquals(expectedArgName, actualArgName);
    }
}
