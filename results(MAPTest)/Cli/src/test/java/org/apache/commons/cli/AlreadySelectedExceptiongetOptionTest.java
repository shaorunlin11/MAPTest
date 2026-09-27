package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlreadySelectedExceptiongetOptionTest {
    @Test
    public void testGetOption() throws Exception {
        // Create a mock OptionGroup and Option
        OptionGroup group = new OptionGroup();
        Option option = new Option("a", "test");

        // Create an AlreadySelectedException with the group and option
        AlreadySelectedException exception = new AlreadySelectedException(group, option);

        // Verify that getOption returns the expected option
        assertEquals(option, exception.getOption());
    }
}
