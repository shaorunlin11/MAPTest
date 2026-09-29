package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.PrintWriter;
import java.io.StringWriter;

public class HelpFormatterprintHelp_28bc8a3dTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPrintHelp_withValidParameters() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "Header text";
        String footer = "Footer text";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertTrue("Output should contain usage line", output.contains("usage: myapp [options]"));
        Assert.assertTrue("Output should contain header", output.contains("Header text"));
        Assert.assertTrue("Output should contain footer", output.contains("Footer text"));
    }

    @Test
    public void testPrintHelp_withNullCmdLineSyntax_throwsIllegalArgumentException() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("cmdLineSyntax not provided");

        formatter.printHelp(pw, 80, null, "header", options, 2, 3, "footer", true);
    }

    @Test
    public void testPrintHelp_withEmptyCmdLineSyntax_throwsIllegalArgumentException() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("cmdLineSyntax not provided");

        formatter.printHelp(pw, 80, "", "header", options, 2, 3, "footer", true);
    }

    @Test
    public void testPrintHelp_withAutoUsageTrue_callsPrintUsageWithOptions() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "Header text";
        String footer = "Footer text";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertTrue("Output should contain usage line", output.contains("usage: myapp [options]"));
    }

    @Test
    public void testPrintHelp_withAutoUsageFalse_callsPrintUsageWithoutOptions() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "Header text";
        String footer = "Footer text";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, false);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertTrue("Output should contain usage line", output.contains("usage: myapp [options]"));
    }

    @Test
    public void testPrintHelp_withHeaderAndFooter_printsThem() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "This is a header";
        String footer = "This is a footer";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertTrue("Output should contain header", output.contains("This is a header"));
        Assert.assertTrue("Output should contain footer", output.contains("This is a footer"));
    }

    @Test
    public void testPrintHelp_withNullHeader_doesNotPrintHeader() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String footer = "Footer text";

        formatter.printHelp(pw, 80, cmdLineSyntax, null, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertFalse("Output should not contain header", output.contains("Header text"));
    }

    @Test
    public void testPrintHelp_withEmptyHeader_doesNotPrintHeader() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "";
        String footer = "Footer text";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertFalse("Output should not contain header", output.contains("Header text"));
    }

    @Test
    public void testPrintHelp_withNullFooter_doesNotPrintFooter() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "Header text";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, null, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertFalse("Output should not contain footer", output.contains("Footer text"));
    }

    @Test
    public void testPrintHelp_withEmptyFooter_doesNotPrintFooter() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        // Add a sample option to avoid null references
        options.addOption("v", "verbose", false, "Enable verbose mode");
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);

        String cmdLineSyntax = "myapp [options]";
        String header = "Header text";
        String footer = "";

        formatter.printHelp(pw, 80, cmdLineSyntax, header, options, 2, 3, footer, true);

        String output = stringWriter.toString();
        Assert.assertNotNull("Output should not be null", output);
        Assert.assertFalse("Output should not be empty", output.isEmpty());
        Assert.assertFalse("Output should not contain footer", output.contains("Footer text"));
    }
}
