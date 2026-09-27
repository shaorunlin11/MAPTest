package org.apache.commons.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OptionsAddOptionGroupZeroCoverageTest {
    @Test
    public void testAddOptionGroupWithRequiredGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true); // Explicitly set required flag

        // Set up the group with options
        List<Option> optionsList = new ArrayList<>();
        Option option1 = new Option("a", "option1");
        Option option2 = new Option("b", "option2");
        optionsList.add(option1);
        optionsList.add(option2);
        group.addOption(option1);
        group.addOption(option2);

        // Execute the method under test
        Options result = options.addOptionGroup(group);

        // Verify that the requiredOpts list contains the group
        assertEquals(1, result.getRequiredOptions().size());
        assertEquals(group, result.getRequiredOptions().iterator().next());

        // Verify that the options in the group have their required flag set to false
        for (Option option : group.getOptions()) {
            assertEquals(false, option.isRequired());
        }

        // Verify that the optionGroups map contains the group for each option
        for (Option option : group.getOptions()) {
            assertEquals(group, result.getOptionGroup(option));
        }
    }
}
