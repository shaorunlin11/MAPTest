package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.io.IOException;

public class HelpFormatterprintUsage_1619ea3dTest {

    private HelpFormatter formatter;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @After
    public void tearDown() throws IOException {
        printWriter.close();
        stringWriter.close();
    }

    @Test
    public void testPrintUsageWithValidArguments() throws Exception {
        String cmdLineSyntax = "app [options] <arg>";
        int width = 80;

        formatter.printUsage(printWriter, width, cmdLineSyntax);

        String output = stringWriter.toString();
        Assert.assertTrue("Output should contain the syntax prefix", output.contains("usage: "));
        Assert.assertTrue("Output should contain the command line syntax", output.contains(cmdLineSyntax));
    }

    @Test
    public void testPrintUsageWithNoSpaceInCmdLineSyntax() throws Exception {
        String cmdLineSyntax = "app";
        int width = 80;

        formatter.printUsage(printWriter, width, cmdLineSyntax);

        String output = stringWriter.toString();
        Assert.assertTrue("Output should contain the syntax prefix", output.contains("usage: "));
        Assert.assertTrue("Output should contain the command line syntax", output.contains(cmdLineSyntax));
    }

    @Test
    public void testPrintUsageWithCustomSyntaxPrefix() throws Exception {
        // Use a subclass to override the syntax prefix
        HelpFormatter customFormatter = new HelpFormatter() {
            @Override
            public String getSyntaxPrefix() {
                return "command: ";
            }
        };

        String cmdLineSyntax = "app [options] <arg>";
        int width = 80;

        customFormatter.printUsage(printWriter, width, cmdLineSyntax);

        String output = stringWriter.toString();
        Assert.assertTrue("Output should contain the custom syntax prefix", output.contains("command: "));
        Assert.assertTrue("Output should contain the command line syntax", output.contains(cmdLineSyntax));
    }
}
