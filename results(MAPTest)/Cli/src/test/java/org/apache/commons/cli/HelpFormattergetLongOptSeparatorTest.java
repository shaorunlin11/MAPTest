package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelpFormattergetLongOptSeparatorTest {
    @Test
    public void testGetLongOptSeparator_returnsDefaultSeparator() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.getLongOptSeparator();
        assertEquals(" ", result);
    }
}
