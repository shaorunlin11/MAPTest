package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelpFormattergetOptPrefixTest {
    @Test
    public void testGetOptPrefixReturnsDefaultOptPrefix() {
        HelpFormatter formatter = new HelpFormatter();
        assertEquals("defaultOptPrefix should return the default value", HelpFormatter.DEFAULT_OPT_PREFIX, formatter.getOptPrefix());
    }
}
