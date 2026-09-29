package org.apache.commons.cli;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class HelpFormatterPrintHelpZeroCoverage_64Test {
    @Test
    public void testPrintHelpTargetLine515() {
        HelpFormatter formatter = new HelpFormatter();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        String cmdLineSyntax = "cmd";
        String header = "header";
        Options options = new Options();
        int leftPad = 2;
        int descPad = 3;
        String footer = "footer";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, leftPad, descPad, footer);
    }
}
