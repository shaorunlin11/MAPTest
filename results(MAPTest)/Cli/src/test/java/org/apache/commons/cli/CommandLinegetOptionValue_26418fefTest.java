package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class CommandLinegetOptionValue_26418fefTest {
    private CommandLine commandLine;

    @Before
    public void setUp() {
        commandLine = new CommandLine();
    }

    @After
    public void tearDown() {
        commandLine = null;
    }

    @Test
    public void testGetOptionValue_NullOption_ReturnsNull() {
        // Arrange
        Option option = null;

        // Act
        String result = commandLine.getOptionValue(option);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetOptionValue_OptionWithNoValues_ReturnsNull() {
        // Arrange
        Option option = new Option("a", "option a");

        // Act
        String result = commandLine.getOptionValue(option);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetOptionValue_OptionWithValue_ReturnsFirstValue() {
        // Arrange
        Option option = new Option("a", "option a");
        List<Option> options = new ArrayList<>();
        options.add(option);
        commandLine = new CommandLine() {
            @Override
            public String[] getOptionValues(Option option) {
                return new String[]{"value1", "value2"};
            }
        };

        // Act
        String result = commandLine.getOptionValue(option);

        // Assert
        Assert.assertEquals("value1", result);
    }
}
