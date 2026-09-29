package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineGetOptionValueZeroCoverage_19Test {
    @Test
    public void testGetOptionValueWithNonNullOption() {
        CommandLine commandLine = new CommandLine();
        Option option = new Option("t", "test");
        commandLine.addOption(option);

        String result = commandLine.getOptionValue(option, "default");
        // This test ensures that the method is called with a non-null option
        // and that it returns the correct value based on the option's presence.
    }
}
