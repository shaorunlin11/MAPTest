package org.apache.commons.cli;

import org.junit.Test;

public class HelpFormatterPrintHelpZeroCoverage_59Test {
    @Test
    public void testPrintHelp() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(80);
        String cmdLineSyntax = "myapp [options]";
        Options options = new Options();
        options.addOption("v", "verbose", false, "Enable verbose output");
        formatter.printHelp(cmdLineSyntax, options, true);
    }
}
