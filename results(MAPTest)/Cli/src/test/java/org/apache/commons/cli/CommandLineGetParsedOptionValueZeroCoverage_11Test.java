package org.apache.commons.cli;

import org.junit.Test;

public class CommandLineGetParsedOptionValueZeroCoverage_11Test {
    @Test
    public void testGetParsedOptionValueWithChar() throws Exception {
        CommandLine commandLine = new CommandLine();
        char opt = 't';
        Object result = commandLine.getParsedOptionValue(opt);
    }
}
