package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

public class DefaultParserhandleConcatenatedOptionsTest {
    private DefaultParser parser;
    private Options options;
    private CommandLine commandLine;

    @Before
    public void setUp() throws Exception {
        parser = new DefaultParser();
        options = new Options();
        commandLine = new CommandLine();

        // Set up reflection to access protected fields
        Field cmdField = DefaultParser.class.getDeclaredField("cmd");
        cmdField.setAccessible(true);
        cmdField.set(parser, commandLine);

        Field optionsField = DefaultParser.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(parser, options);

        Field stopAtNonOptionField = DefaultParser.class.getDeclaredField("stopAtNonOption");
        stopAtNonOptionField.setAccessible(true);
        stopAtNonOptionField.set(parser, false);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up if needed
    }

    @Test
    public void testHandleConcatenatedOptionsWithKnownOptions() throws Exception {
        // Create test options
        Option optionA = new Option("a", "option-a");
        Option optionB = new Option("b", "option-b");
        Option optionC = new Option("c", "option-c");

        options.addOption(optionA);
        options.addOption(optionB);
        options.addOption(optionC);

        // Test token "-abc"
        parser.handleConcatenatedOptions("-abc");

        // Verify that each option was handled
        Assert.assertTrue(commandLine.hasOption("a"));
        Assert.assertTrue(commandLine.hasOption("b"));
        Assert.assertTrue(commandLine.hasOption("c"));
    }

    @Test
    public void testHandleConcatenatedOptionsWithPartialMatching() throws Exception {
        // Create test options
        Option optionA = new Option("a", "option-a");
        Option optionB = new Option("b", "option-b");
        Option optionC = new Option("c", "option-c");

        options.addOption(optionA);
        options.addOption(optionB);
        options.addOption(optionC);

        // Enable partial matching
        Field allowPartialMatchingField = DefaultParser.class.getDeclaredField("allowPartialMatching");
        allowPartialMatchingField.setAccessible(true);
        allowPartialMatchingField.set(parser, true);

        // Test token "-ab"
        parser.handleConcatenatedOptions("-ab");

        // Verify that each option was handled
        Assert.assertTrue(commandLine.hasOption("a"));
        Assert.assertTrue(commandLine.hasOption("b"));
    }

    @Test
    public void testHandleConcatenatedOptionsWithValueAddition() throws Exception {
        // Create test options
        Option optionA = new Option("a", "option-a");
        optionA.setArgs(1);

        options.addOption(optionA);

        // Test token "-a123"
        parser.handleConcatenatedOptions("-a123");

        // Verify that the value was added
        Assert.assertEquals("123", commandLine.getOptionValue("a"));
    }

    @Test
    public void testHandleConcatenatedOptionsWithUnknownToken() throws Exception {
        // Create test options
        Option optionA = new Option("a", "option-a");
        options.addOption(optionA);

        // Test token "-bx"
        try {
            parser.handleConcatenatedOptions("-bx");
        } catch (ParseException e) {
            // Expected exception for unknown options
        }

        // Verify that the unknown token was handled
        Assert.assertFalse(commandLine.hasOption("b"));
        Assert.assertFalse(commandLine.hasOption("x"));
    }

    @Test
    public void testHandleConcatenatedOptionsWithStopAtNonOption() throws Exception {
        // Create test options
        Option optionA = new Option("a", "option-a");
        options.addOption(optionA);

        // Enable stopAtNonOption
        Field stopAtNonOptionField = DefaultParser.class.getDeclaredField("stopAtNonOption");
        stopAtNonOptionField.setAccessible(true);
        stopAtNonOptionField.set(parser, true);

        // Test token "-abx"
        parser.handleConcatenatedOptions("-abx");

        // Verify that parsing stopped at the first unknown option
        Assert.assertTrue(commandLine.hasOption("a"));
        Assert.assertFalse(commandLine.hasOption("b"));
        Assert.assertFalse(commandLine.hasOption("x"));
    }
}
