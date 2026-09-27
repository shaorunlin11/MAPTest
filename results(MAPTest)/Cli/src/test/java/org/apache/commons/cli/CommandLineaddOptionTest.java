package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

import java.lang.reflect.Field;

public class CommandLineaddOptionTest {
    @Test
    public void testAddOption() throws Exception {
        // Arrange
        CommandLine commandLine = new CommandLine();
        Option option = new Option("t", "test", false, "test option");

        // Act
        commandLine.addOption(option);

        // Assert
        List<Option> options = new ArrayList<>();
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        options = (List<Option>) optionsField.get(commandLine);
        Assert.assertTrue(options.contains(option));
    }
}
