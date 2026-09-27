package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelpFormattergetArgNameTest {
    @Test
    public void testGetArgName() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        assertEquals("arg", formatter.getArgName());
    }
}
