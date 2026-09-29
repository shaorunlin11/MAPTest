package org.apache.commons.cli;

import org.junit.Test;

import java.io.PrintWriter;


public class HelpFormatterPrintWrappedZeroCoverageTest {
    @Test
    public void testPrintWrapped() {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        int width = 20;
        int nextLineTabStop = 0;
        String text = "This is a test string that should be wrapped.";

        formatter.printWrapped(pw, width, nextLineTabStop, text);
    }
}
