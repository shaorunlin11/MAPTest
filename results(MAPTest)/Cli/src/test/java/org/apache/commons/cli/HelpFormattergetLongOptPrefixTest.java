package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelpFormattergetLongOptPrefixTest {
    @Test
    public void testGetLongOptPrefix() {
        HelpFormatter formatter = new HelpFormatter();
        assertEquals("--", formatter.getLongOptPrefix());
    }
}
