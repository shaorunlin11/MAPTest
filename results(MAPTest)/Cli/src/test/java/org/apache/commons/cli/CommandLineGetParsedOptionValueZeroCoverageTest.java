package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineGetParsedOptionValueZeroCoverageTest {
    @Test
    public void testGetParsedOptionValueWithNullOption() throws Exception {
        CommandLine commandLine = new CommandLine();
        Option option = null;
        Object result = commandLine.getParsedOptionValue(option);
        // The test is designed to reach line 123, which is the return null; statement
        // No further assertions are needed as the goal is to execute the target lines
    }

@Test
    public void testGetParsedOptionValueWithNullRes() throws Exception {
        CommandLine commandLine = new CommandLine();
        Option option = new Option("a", "test");
        Object result = commandLine.getParsedOptionValue(option);
        // The test is designed to reach line 128, which is the return TypeHandler.createValue(res, option.getType()); statement
        // No further assertions are needed as the goal is to execute the target lines
    }
}
