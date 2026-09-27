package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionisRequiredTest {
    @Test
    public void testIsRequiredReturnsRequiredField() throws Exception {
        // Create an Option instance with required set to true
        Option optionTrue = new Option("t", "test description");
        optionTrue.setRequired(true);

        // Create an Option instance with required set to false
        Option optionFalse = new Option("f", "test description");
        optionFalse.setRequired(false);

        // Test the isRequired method
        assertTrue("isRequired should return true when required is true", optionTrue.isRequired());
        assertFalse("isRequired should return false when required is false", optionFalse.isRequired());
    }
}
