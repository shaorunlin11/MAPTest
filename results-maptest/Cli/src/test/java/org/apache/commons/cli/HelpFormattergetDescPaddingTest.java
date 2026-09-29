package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelpFormattergetDescPaddingTest {
    @Test
    public void testGetDescPaddingReturnsDefaultDescPad() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        int result = formatter.getDescPadding();
        assertEquals("The default description padding should be 3", 3, result);
    }
}
