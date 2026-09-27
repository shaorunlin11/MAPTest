package org.apache.commons.cli;

import org.junit.Test;

public class HelpFormatterPrintHelpZeroCoverageTest {
    @Test
    public void testPrintHelp() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        formatter.printHelp("cmdLineSyntax", options);
    }
}
