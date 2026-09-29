package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIndenterwithIndentTest {
    @Test
    public void testWithIndentSameValueReturnsThis() {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        DefaultIndenter result = indenter.withIndent("  ");
        assertSame(indenter, result);
    }

    @Test
    public void testWithIndentDifferentValueReturnsNewInstance() {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        DefaultIndenter result = indenter.withIndent("    ");
        assertNotSame(indenter, result);
        assertEquals("    ", result.getIndent());
        assertEquals("\n", result.getEol());
    }

    @Test
    public void testWithIndentNullThrowsNullPointerException() {
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");
        try {
            indenter.withIndent(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
}
