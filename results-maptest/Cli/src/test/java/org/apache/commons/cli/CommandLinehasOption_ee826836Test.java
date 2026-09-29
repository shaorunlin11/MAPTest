package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class CommandLinehasOption_ee826836Test {
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
    public void testHasOption_ReturnsTrueWhenOptionIsPresent() {
        // Arrange
        Option option = new Option("a", "option-a", false, "description");
        List<Option> options = new ArrayList<>();
        options.add(option);

        // Use reflection to set the private 'options' field
        try {
            java.lang.reflect.Field field = CommandLine.class.getDeclaredField("options");
            field.setAccessible(true);
            field.set(commandLine, options);
        } catch (Exception e) {
            Assert.fail("Failed to set 'options' field: " + e.getMessage());
        }

        // Act
        boolean result = commandLine.hasOption(option);

        // Assert
        Assert.assertTrue("Expected hasOption to return true when option is present", result);
    }

    @Test
    public void testHasOption_ReturnsFalseWhenOptionIsNotPresent() {
        // Arrange
        Option option = new Option("b", "option-b", false, "description");
        List<Option> options = new ArrayList<>();

        // Use reflection to set the private 'options' field
        try {
            java.lang.reflect.Field field = CommandLine.class.getDeclaredField("options");
            field.setAccessible(true);
            field.set(commandLine, options);
        } catch (Exception e) {
            Assert.fail("Failed to set 'options' field: " + e.getMessage());
        }

        // Act
        boolean result = commandLine.hasOption(option);

        // Assert
        Assert.assertFalse("Expected hasOption to return false when option is not present", result);
    }
}
