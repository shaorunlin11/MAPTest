package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsetArgsTest {
    @Test
    public void testSetArgs() throws Exception {
        Option option = new Option("a", "description");
        assertEquals(Option.UNINITIALIZED, option.getArgs());

        option.setArgs(2);
        assertEquals(2, option.getArgs());

        option.setArgs(Option.UNLIMITED_VALUES);
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());

        option.setArgs(0);
        assertEquals(0, option.getArgs());
    }
}
