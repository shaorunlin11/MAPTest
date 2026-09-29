package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Properties;

public class DefaultParserparse_4d027441Test {

    private DefaultParser parser;

    @Before
    public void setUp() {
        parser = new DefaultParser();
    }

    @After
    public void tearDown() {
        parser = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testParseWithNonNullArgumentsAndProperties() throws Exception {
        Options options = new Options();
        options.addOption("option", "option", false, "description");
        String[] arguments = {"--option", "value"};
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        Assert.assertNotNull("CommandLine should not be null", result);
    }

    @Test
    public void testParseWithNullArguments() throws Exception {
        Options options = new Options();
        String[] arguments = null;
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        Assert.assertNotNull("CommandLine should not be null", result);
    }

    @Test
    public void testParseWithNullProperties() throws Exception {
        Options options = new Options();
        options.addOption("option", "option", false, "description");
        String[] arguments = {"--option", "value"};
        Properties properties = null;
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        Assert.assertNotNull("CommandLine should not be null", result);
    }

    @Test
    public void testParseWithEmptyArguments() throws Exception {
        Options options = new Options();
        String[] arguments = {};
        Properties properties = new Properties();
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        Assert.assertNotNull("CommandLine should not be null", result);
    }

    @Test
    public void testParseWithStopAtNonOptionTrue() throws Exception {
        Options options = new Options();
        options.addOption("option", "option", false, "description");
        options.addOption("another", "another", false, "description");
        String[] arguments = {"--option", "value", "--another"};
        Properties properties = new Properties();
        boolean stopAtNonOption = true;

        CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);

        Assert.assertNotNull("CommandLine should not be null", result);
    }
}
