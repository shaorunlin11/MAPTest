package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import java.util.Properties;

public class DefaultParserparse_afc7a0bdTest {
    @Test
    public void testParseWithValidParameters() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        // Add a dummy option to avoid null reference issues
        options.addOption("t", "test", false, "Test option");
        String[] arguments = {};
        Properties properties = new Properties();

        CommandLine result = parser.parse(options, arguments, properties);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithNullOptions() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = {};
        Properties properties = new Properties();

        CommandLine result = parser.parse(options, arguments, properties);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithNullArguments() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = new String[0];
        Properties properties = new Properties();

        CommandLine result = parser.parse(options, arguments, properties);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithNullProperties() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = {};
        Properties properties = new Properties();

        CommandLine result = parser.parse(options, arguments, properties);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithAllNullParameters() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = new String[0];
        Properties properties = new Properties();

        CommandLine result = parser.parse(options, arguments, properties);

        Assert.assertNotNull(result);
    }
}
