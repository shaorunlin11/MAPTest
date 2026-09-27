package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

import java.util.Properties;

public class DefaultParserparse_5d4284d8Test {
    @Test
    public void testParse() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = {};
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, stopAtNonOption);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithNullArguments() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = null;
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, stopAtNonOption);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithEmptyArguments() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = new String[0];
        boolean stopAtNonOption = false;

        CommandLine result = parser.parse(options, arguments, stopAtNonOption);

        Assert.assertNotNull(result);
    }

    @Test
    public void testParseWithStopAtNonOptionTrue() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        String[] arguments = {"--option", "value"};
        boolean stopAtNonOption = true;

        CommandLine result = parser.parse(options, arguments, stopAtNonOption);

        Assert.assertNotNull(result);
    }
}
