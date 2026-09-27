package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionCloneZeroCoverageTest {
    @Test
    public void testClone() throws Exception {
        // Create an Option instance
        Option option = new Option("t", "test", true, "test description");
        option.setArgName("arg");
        option.setDescription("description");
        option.setRequired(true);
        option.setOptionalArg(true);
        option.setArgs(2);
        option.setType(String.class);
        option.setValueSeparator(',');
        option.addValueForProcessing("value1");
        option.addValueForProcessing("value2");

        // Call clone method
        Object cloned = option.clone();

        // Verify that the clone is not null
        assertNotNull(cloned);

        // Verify that the clone is an instance of Option
        assertTrue(cloned instanceof Option);

        // Verify that the values are copied correctly
        Option clonedOption = (Option) cloned;
        assertEquals(option.getValuesList(), clonedOption.getValuesList());
    }
}
