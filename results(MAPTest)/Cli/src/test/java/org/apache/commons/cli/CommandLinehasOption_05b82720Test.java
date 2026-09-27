package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

public class CommandLinehasOption_05b82720Test {
    private CommandLine commandLine;

    @Before
    public void setUp() {
        commandLine = new CommandLine();
    }

    @After
    public void tearDown() {
        commandLine = null;
    }

    @Test
    public void testHasOption_WithExistingOption_ReturnsTrue() throws Exception {
        // Arrange
        Option option = new Option("t", "test", false, "test option");
        List<Option> options = new ArrayList<>();
        options.add(option);
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, options);

        // Act
        boolean result = commandLine.hasOption((String) "t");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasOption_WithNonExistingOption_ReturnsFalse() throws Exception {
        // Arrange
        Option option = new Option("t", "test", false, "test option");
        List<Option> options = new ArrayList<>();
        options.add(option);
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, options);

        // Act
        boolean result = commandLine.hasOption((String) "x");

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testHasOption_NullOption_ReturnsFalse() throws Exception {
        // Arrange
        List<Option> options = new ArrayList<>();
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, options);

        // Act
        boolean result = commandLine.hasOption((String) null);

        // Assert
        Assert.assertFalse(result);
    }
}
