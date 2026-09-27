package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIndenterwithLinefeedTest {
    @Test
    public void testWithLinefeedSameEolReturnsThis() {
        String eol = "\n";
        DefaultIndenter indenter = new DefaultIndenter("  ", eol);
        DefaultIndenter result = indenter.withLinefeed(eol);
        assertSame(indenter, result);
    }

    @Test
    public void testWithLinefeedDifferentEolReturnsNewInstance() {
        String originalEol = "\n";
        String newEol = "\r\n";
        DefaultIndenter indenter = new DefaultIndenter("  ", originalEol);
        DefaultIndenter result = indenter.withLinefeed(newEol);
        assertNotSame(indenter, result);
        assertEquals(newEol, result.getEol());
    }

    @Test
    public void testWithLinefeedNullLfThrowsNullPointerException() {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        try {
            indenter.withLinefeed(null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
