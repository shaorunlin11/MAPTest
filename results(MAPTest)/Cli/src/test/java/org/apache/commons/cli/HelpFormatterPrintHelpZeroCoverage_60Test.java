package org.apache.commons.cli;

import org.junit.Test;

public class HelpFormatterPrintHelpZeroCoverage_60Test {
    @Test
    public void testPrintHelpWithNonNullParameters() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        String cmdLineSyntax = "cmd";
        String header = "header";
        String footer = "footer";

        formatter.printHelp(cmdLineSyntax, header, options, footer);
    }
}
