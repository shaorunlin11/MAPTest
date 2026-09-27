package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsetRequiredTest {
    @Test
    public void testSetRequired() throws Exception {
        // Create an Option instance
        Option option = new Option("t", "test option");

        // Verify initial state
        assertFalse(option.isRequired());

        // Call setRequired with true
        option.setRequired(true);

        // Verify state after setting to true
        assertTrue(option.isRequired());

        // Call setRequired with false
        option.setRequired(false);

        // Verify state after setting to false
        assertFalse(option.isRequired());
    }
}
