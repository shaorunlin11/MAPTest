package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class CommandLinegetArgsTest {

    @Test
    public void testGetArgsReturnsCopyOfArgsList() throws Exception {
        CommandLine commandLine = new CommandLine();
        List<String> args = new ArrayList<>();
        args.add("arg1");
        args.add("arg2");

        // Use reflection to set the private args field
        java.lang.reflect.Field argsField = CommandLine.class.getDeclaredField("args");
        argsField.setAccessible(true);
        argsField.set(commandLine, args);

        String[] result = commandLine.getArgs();

        Assert.assertEquals(2, result.length);
        Assert.assertEquals("arg1", result[0]);
        Assert.assertEquals("arg2", result[1]);

        // Modify the original list to ensure the returned array is a copy
        args.add("arg3");
        Assert.assertEquals(2, result.length);
        Assert.assertEquals("arg1", result[0]);
        Assert.assertEquals("arg2", result[1]);
    }

    @Test
    public void testGetArgsWithEmptyArgsList() throws Exception {
        CommandLine commandLine = new CommandLine();

        // Use reflection to set the private args field
        java.lang.reflect.Field argsField = CommandLine.class.getDeclaredField("args");
        argsField.setAccessible(true);
        argsField.set(commandLine, new ArrayList<String>());

        String[] result = commandLine.getArgs();

        Assert.assertEquals(0, result.length);
    }
}
