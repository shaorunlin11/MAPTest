package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelpFormatterPrintHelpZeroCoverage_62Test {
    @Test
    public void testPrintHelpWithNonNullParameters() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        String cmdLineSyntax = "testCommand";
        String header = "Header";
        String footer = "Footer";
        int width = 80;

        // Ensure the parameters meet the requirements
        assertNotNull("options must be non-null", options);
        assertTrue("width must be a positive integer", width > 0);
        assertNotNull("cmdLineSyntax must be non-null", cmdLineSyntax);

        // Call the method under test
        formatter.printHelp(width, cmdLineSyntax, header, options, footer);
    }
}
