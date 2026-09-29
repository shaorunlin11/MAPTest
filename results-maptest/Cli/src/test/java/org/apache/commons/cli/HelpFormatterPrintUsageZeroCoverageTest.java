package org.apache.commons.cli;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class HelpFormatterPrintUsageZeroCoverageTest {
    @Test
    public void testPrintUsageTargetLine583() {
        // Arrange
        HelpFormatter formatter = new HelpFormatter();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        int width = 80;
        String app = "testApp";
        Options options = new Options();

        // Ensure getSyntaxPrefix() returns a non-null string
        formatter.setSyntaxPrefix("customPrefix");

        // Add an option to the options
        Option option = new Option("t", "test", false, "test option");
        options.addOption(option);

        // Act
        formatter.printUsage(pw, width, app, options);

        // Assert
        String result = stringWriter.toString();
        assertTrue("Result should contain the app name", result.contains(app));
        assertTrue("Result should contain the option", result.contains("-t"));
        assertTrue("Result should contain the syntax prefix", result.contains("customPrefix"));
    }

@Test
    public void testPrintUsageTargetLine600() {
        // Arrange
        HelpFormatter formatter = new HelpFormatter();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        int width = 80;
        String app = "testApp";
        Options options = new Options();

        // Ensure getSyntaxPrefix() returns a non-null string
        formatter.setSyntaxPrefix("customPrefix");

        // Add an option to the options
        Option option = new Option("t", "test", false, "test option");
        options.addOption(option);

        // Set up the required object state
        // options.getOptionGroup(option) returns a non-null value
        OptionGroup group = new OptionGroup();
        group.addOption(option);
        options.addOptionGroup(group);

        // getOptionComparator() returns null
        // This is the default state, so no need to set explicitly

        // Act
        formatter.printUsage(pw, width, app, options);

        // Assert
        String result = stringWriter.toString();
        assertTrue("Result should contain the app name", result.contains(app));
        assertTrue("Result should contain the option", result.contains("-t"));
        assertTrue("Result should contain the syntax prefix", result.contains("customPrefix"));
    }
}
