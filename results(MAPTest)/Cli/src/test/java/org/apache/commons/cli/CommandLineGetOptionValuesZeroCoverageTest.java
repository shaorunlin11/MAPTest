package org.apache.commons.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class CommandLineGetOptionValuesZeroCoverageTest {
    @Test
    public void testGetOptionValues() {
        // Arrange
        CommandLine commandLine = new CommandLine();
        List<Option> options = new ArrayList<>();
        Option testOption = new Option("t", "test", false, "Test option");
        testOption.getValuesList().add("value1");
        testOption.getValuesList().add("value2");
        options.add(testOption);

        // Use the addOption method to populate the options
        for (Option option : options) {
            commandLine.addOption(option);
        }

        // Act
        String[] result = commandLine.getOptionValues(testOption);

        // Assert
        assertArrayEquals(new String[]{"value1", "value2"}, result);
    }
}
