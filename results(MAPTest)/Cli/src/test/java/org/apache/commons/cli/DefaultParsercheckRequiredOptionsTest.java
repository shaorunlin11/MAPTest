package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class DefaultParsercheckRequiredOptionsTest {

    @Test
    public void testCheckRequiredOptions_WhenExpectedOptsIsEmpty_DoesNotThrowException() throws Exception {
        DefaultParser parser = new DefaultParser();
        parser.expectedOpts = new ArrayList<>();

        // Method under test
        parser.checkRequiredOptions();
    }

    @Test(expected = MissingOptionException.class)
    public void testCheckRequiredOptions_WhenExpectedOptsIsNotEmpty_ThrowsMissingOptionException() throws Exception {
        DefaultParser parser = new DefaultParser();
        parser.expectedOpts = new ArrayList<>();
        parser.expectedOpts.add("testOption");

        // Method under test
        parser.checkRequiredOptions();
    }
}
