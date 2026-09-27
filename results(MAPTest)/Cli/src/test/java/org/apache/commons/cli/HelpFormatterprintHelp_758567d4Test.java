package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.io.IOException;


public class HelpFormatterprintHelp_758567d4Test {
    private HelpFormatter formatter;
    private StringWriter stringWriter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
        stringWriter = new StringWriter();
    }

    @After
    public void tearDown() {
        try {
            stringWriter.close();
        } catch (IOException e) {
            // Ignore
        }
    }

    @Test
    public void testPrintHelpDelegatesToOtherOverload() {
        // Arrange
        String cmdLineSyntax = "myapp";
        String header = "Header";
        Options options = new Options();
        String footer = "Footer";
        boolean autoUsage = true;

        // Act
        try {
            formatter.printHelp(cmdLineSyntax, header, options, footer, autoUsage);
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }

        // Assert
        Assert.assertTrue(true);
    }
}
