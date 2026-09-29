package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class MissingArgumentExceptiongetOptionTest {
    @Test
    public void testGetOption() throws Exception {
        // Create an Option instance
        Option option = new Option("a", "test", false, "test option");

        // Create a MissingArgumentException instance with the option
        MissingArgumentException exception = new MissingArgumentException(option);

        // Call the getOption method
        Option result = exception.getOption();

        // Assert that the returned option matches the expected one
        Assert.assertEquals("Expected the same option instance", option, result);
    }
}
