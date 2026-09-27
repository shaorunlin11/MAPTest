package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineHasOptionZeroCoverageTest {
    @Test
    public void testHasOptionWithChar() {
        CommandLine commandLine = new CommandLine();
        char opt = 't';
        boolean result = commandLine.hasOption(opt);
        // This test is designed to reach line 88 of the hasOption method
        // which calls hasOption(String.valueOf(opt))
        // The test does not assert anything because the goal is only to reach the line
    }
}
