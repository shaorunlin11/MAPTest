package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineGetOptionValueZeroCoverage_20Test {
    @Test
    public void testGetOptionValueWithNonNullOptAndResolvedOption() {
        CommandLine commandLine = new CommandLine();
        Option option = new Option("a", "test");
        commandLine.addOption(option);

        String result = commandLine.getOptionValue("a", "default");
        // This test ensures that the target lines are executed, but no assertions are needed as per the requirements.
    }
}
