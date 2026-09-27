package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

public class CommandLinegetOptionValues_2f04f46fTest {
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
    public void testGetOptionValuesWithValidOption() throws Exception {
        // Arrange
        Option option = new Option("a", "test");
        List<Option> options = new ArrayList<>();
        options.add(option);
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, options);

        // Act
        String[] result = commandLine.getOptionValues("a");

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetOptionValuesWithInvalidOption() throws Exception {
        // Arrange
        List<Option> options = new ArrayList<>();
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, options);

        // Act
        String[] result = commandLine.getOptionValues("b");

        // Assert
        Assert.assertNull(result);
    }
}
