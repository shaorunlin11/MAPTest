package org.apache.commons.cli;

import org.junit.Test;

public class HelpFormatterPrintHelpZeroCoverage_63Test {
    @Test
    public void testPrintHelpWithValidParameters() {
        HelpFormatter formatter = new HelpFormatter();
        String cmdLineSyntax = "cmd";
        String header = "Header";
        Options options = new Options();
        String footer = "Footer";
        boolean autoUsage = true;

        formatter.printHelp(80, cmdLineSyntax, header, options, footer, autoUsage);
    }
}
