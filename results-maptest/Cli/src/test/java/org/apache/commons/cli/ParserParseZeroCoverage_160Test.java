package org.apache.commons.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

public class ParserParseZeroCoverage_160Test {
    @Test
    public void testParseTargetLines154() throws Exception {
        Parser parser = new GnuParser();
        Options options = new Options();
        String[] arguments = {"-a", "value"};
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        List<Option> helpOptions = new ArrayList<>();
        Option helpOption = new Option("a", "help", false, "help option");
        helpOptions.add(helpOption);
        options.addOption(helpOption);

        List<OptionGroup> optionGroups = new ArrayList<>();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("b", "option", false, "option"));
        optionGroups.add(group);
        options.addOptionGroup(group);

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        assertEquals("Expected a CommandLine instance", CommandLine.class, result.getClass());
    }

@Test
    public void testParseTargetLines166() throws Exception {
        Parser parser = new GnuParser();
        Options options = new Options();
        String[] arguments = null;
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        List<Option> helpOptions = new ArrayList<>();
        Option helpOption = new Option("a", "help", false, "help option");
        helpOptions.add(helpOption);
        options.addOption(helpOption);

        List<OptionGroup> optionGroups = new ArrayList<>();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("b", "option", false, "option"));
        optionGroups.add(group);
        options.addOptionGroup(group);

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        assertEquals("Expected a CommandLine instance", CommandLine.class, result.getClass());
    }

@Test
    public void testParseTargetLines181() throws Exception {
        Parser parser = new GnuParser();
        Options options = new Options();
        String[] arguments = {"--", "arg1", "arg2"};
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        // Ensure tokenList contains "--"
        // This is achieved by passing "--" as the first argument

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        assertEquals("Expected a CommandLine instance", CommandLine.class, result.getClass());
    }

@Test
    public void testParseTargetLines187() throws Exception {
        Parser parser = new GnuParser();
        Options options = new Options();
        String[] arguments = {"-"};
        Properties properties = new Properties();
        boolean stopAtNonOption = true;

        List<Option> helpOptions = new ArrayList<>();
        Option helpOption = new Option("a", "help", false, "help option");
        helpOptions.add(helpOption);
        options.addOption(helpOption);

        List<OptionGroup> optionGroups = new ArrayList<>();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("b", "option", false, "option"));
        optionGroups.add(group);
        options.addOptionGroup(group);

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        assertEquals("Expected a CommandLine instance", CommandLine.class, result.getClass());
    }

@Test
    public void testParseTargetLines189() throws Exception {
        Parser parser = new GnuParser();
        Options options = new Options();
        String[] arguments = {"-a", "value"};
        Properties properties = new Properties();
        boolean stopAtNonOption = true;

        List<Option> helpOptions = new ArrayList<>();
        Option helpOption = new Option("a", "help", false, "help option");
        helpOptions.add(helpOption);
        options.addOption(helpOption);

        List<OptionGroup> optionGroups = new ArrayList<>();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("b", "option", false, "option"));
        optionGroups.add(group);
        options.addOptionGroup(group);

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        assertEquals("Expected a CommandLine instance", CommandLine.class, result.getClass());
    }
}
