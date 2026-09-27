package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptiongetOptTest {
    @Test
    public void testGetOpt() throws Exception {
        // Create an Option instance with a known opt value
        Option option = new Option("a", "description");

        // Verify that getOpt returns the expected value
        assertEquals("a", option.getOpt());
    }
}
