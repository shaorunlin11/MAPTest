package org.apache.commons.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class OptionGroupToStringZeroCoverageTest {
    @Test
    public void testToString() {
        // Create an OptionGroup instance
        OptionGroup optionGroup = new OptionGroup();

        // Create some Option instances
        Option option1 = new Option(null, "option1", false, "Description for option1");
        Option option2 = new Option("b", "option2", false, "Description for option2");

        // Add options to the OptionGroup
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);

        // Verify that getOptions() returns a non-null collection and is not empty
        Collection<Option> options = optionGroup.getOptions();
        assertEquals(2, options.size());

        // Call the toString method
        String result = optionGroup.toString();

        // Expected output: [--option1 Description for option1, -b Description for option2]
        assertEquals("[--option1 Description for option1, -b Description for option2]", result);
    }
}
