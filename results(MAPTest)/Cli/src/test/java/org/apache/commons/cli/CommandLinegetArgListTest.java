package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class CommandLinegetArgListTest {

    @Test
    public void testGetArgListReturnsArgsList() throws Exception {
        // Arrange
        CommandLine commandLine = new CommandLine();
        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("arg1");
        expectedArgs.add("arg2");

        // Set the args field using reflection
        java.lang.reflect.Field argsField = CommandLine.class.getDeclaredField("args");
        argsField.setAccessible(true);
        argsField.set(commandLine, new LinkedList<>(expectedArgs));

        // Act
        List<String> actualArgs = commandLine.getArgList();

        // Assert
        Assert.assertEquals(expectedArgs, actualArgs);
    }

    @Test
    public void testGetArgListReturnsEmptyListWhenNoArgs() throws Exception {
        // Arrange
        CommandLine commandLine = new CommandLine();

        // Act
        List<String> actualArgs = commandLine.getArgList();

        // Assert
        Assert.assertTrue(actualArgs.isEmpty());
    }

    @Test
    public void testGetArgListReturnsModifiableList() throws Exception {
        // Arrange
        CommandLine commandLine = new CommandLine();
        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("arg1");
        expectedArgs.add("arg2");

        // Set the args field using reflection
        java.lang.reflect.Field argsField = CommandLine.class.getDeclaredField("args");
        argsField.setAccessible(true);
        argsField.set(commandLine, new LinkedList<>(expectedArgs));

        // Act
        List<String> actualArgs = commandLine.getArgList();
        actualArgs.add("arg3");

        // Assert
        Assert.assertEquals(3, actualArgs.size());
    }
}
