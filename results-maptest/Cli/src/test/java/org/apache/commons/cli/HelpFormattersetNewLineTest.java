package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class HelpFormattersetNewLineTest {
    private HelpFormatter formatter;
    private Field defaultNewLineField;

    @Before
    public void setUp() throws Exception {
        formatter = new HelpFormatter();
        defaultNewLineField = HelpFormatter.class.getDeclaredField("defaultNewLine");
        defaultNewLineField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        defaultNewLineField.setAccessible(false);
    }

    @Test
    public void testSetNewLine() throws Exception {
        // Arrange
        String expectedNewLine = "\r\n";

        // Act
        formatter.setNewLine(expectedNewLine);

        // Assert
        String actualNewLine = (String) defaultNewLineField.get(formatter);
        Assert.assertEquals("The newline should be set correctly", expectedNewLine, actualNewLine);
    }

    @Test
    public void testSetNewLineWithNull() throws Exception {
        // Arrange
        String expectedNewLine = null;

        // Act
        formatter.setNewLine(expectedNewLine);

        // Assert
        String actualNewLine = (String) defaultNewLineField.get(formatter);
        Assert.assertNull("The newline should be set to null", actualNewLine);
    }

    @Test
    public void testSetNewLineWithEmptyString() throws Exception {
        // Arrange
        String expectedNewLine = "";

        // Act
        formatter.setNewLine(expectedNewLine);

        // Assert
        String actualNewLine = (String) defaultNewLineField.get(formatter);
        Assert.assertEquals("The newline should be set to empty string", expectedNewLine, actualNewLine);
    }
}
