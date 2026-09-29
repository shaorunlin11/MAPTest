package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommandLinegetOptionObject_880e7ba9Test {

    @Test
    public void testGetOptionObject_withChar() {
        CommandLine commandLine = new CommandLine();
        Object result = commandLine.getOptionObject('a');
        assertNull("The method delegates to a deprecated method which may return null", result);
    }
}
