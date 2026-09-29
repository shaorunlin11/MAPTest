package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIndentergetEolTest {

    @Test
    public void testGetEol() throws Exception {
        // Create an instance with a known EOL value
        String customEol = "\n";
        DefaultIndenter indenter = new DefaultIndenter("  ", customEol);

        // Verify that getEol returns the expected value
        assertEquals(customEol, indenter.getEol());
    }

    @Test
    public void testGetEolUsingSystemLinefeedInstance() throws Exception {
        // Use the pre-initialized system line feed instance
        DefaultIndenter systemIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;

        // Verify that getEol returns the system line feed value
        assertEquals(DefaultIndenter.SYS_LF, systemIndenter.getEol());
    }
}
