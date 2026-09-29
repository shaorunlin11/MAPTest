package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.lang.reflect.Field;


public class CommandLineBuilderTest {

    @Test
    public void testBuilderAddsOptionToCommandLine() {
        // Arrange
        CommandLine.Builder builder = new CommandLine.Builder();
        Option option = new Option("a", "test-option");

        // Act
        builder.addOption(option);

        // Assert
        List<Option> options = new ArrayList<>();
        try {
            Field optionsField = CommandLine.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            options = (List<Option>) optionsField.get(builder.build());
        } catch (Exception e) {
            fail("Failed to access private field: " + e.getMessage());
        }
        assertTrue("Expected option to be added to CommandLine", options.contains(option));
    }

    @Test
    public void testBuilderAddsArgumentToCommandLine() {
        // Arrange
        CommandLine.Builder builder = new CommandLine.Builder();
        String argument = "test-arg";

        // Act
        builder.addArg(argument);

        // Assert
        List<String> args = new ArrayList<>();
        try {
            Field argsField = CommandLine.class.getDeclaredField("args");
            argsField.setAccessible(true);
            args = (List<String>) argsField.get(builder.build());
        } catch (Exception e) {
            fail("Failed to access private field: " + e.getMessage());
        }
        assertTrue("Expected argument to be added to CommandLine", args.contains(argument));
    }

    @Test
    public void testBuilderReturnsConstructedCommandLine() {
        // Arrange
        CommandLine.Builder builder = new CommandLine.Builder();

        // Act
        CommandLine commandLine = builder.build();

        // Assert
        assertNotNull("Expected build() to return a non-null CommandLine", commandLine);
    }

    @Test
    public void testMethodChainingWithAddOptionAndAddArg() {
        // Arrange
        CommandLine.Builder builder = new CommandLine.Builder();
        Option option = new Option("b", "test-option");
        String argument = "test-arg";

        // Act
        CommandLine commandLine = builder.addOption(option).addArg(argument).build();

        // Assert
        List<Option> options = new ArrayList<>();
        List<String> args = new ArrayList<>();
        try {
            Field optionsField = CommandLine.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            options = (List<Option>) optionsField.get(commandLine);

            Field argsField = CommandLine.class.getDeclaredField("args");
            argsField.setAccessible(true);
            args = (List<String>) argsField.get(commandLine);
        } catch (Exception e) {
            fail("Failed to access private fields: " + e.getMessage());
        }
        assertTrue("Expected option to be added to CommandLine", options.contains(option));
        assertTrue("Expected argument to be added to CommandLine", args.contains(argument));
    }
}
