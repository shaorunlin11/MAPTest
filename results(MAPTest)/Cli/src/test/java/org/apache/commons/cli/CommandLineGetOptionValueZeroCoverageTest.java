package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineGetOptionValueZeroCoverageTest {
    @Test
    public void testGetOptionValue() {
        CommandLine commandLine = new CommandLine();
        // Ensure that the method can be called with a non-null option
        // This test does not require any specific setup as per the requirements
        commandLine.getOptionValue("test");
    }
}
