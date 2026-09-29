package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsaddOption_7ea311a4Test {

    @Test
    public void testAddOption() {
        Options options = new Options();
        Options result = options.addOption("t", true, "test option");

        assertNotNull(result);
        assertTrue(result instanceof Options);
        assertSame(options, result);
    }
}
