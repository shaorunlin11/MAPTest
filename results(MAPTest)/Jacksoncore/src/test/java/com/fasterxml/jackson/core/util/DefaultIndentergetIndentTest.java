package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIndentergetIndentTest {
    @Test
    public void testGetIndent() throws Exception {
        // Create an instance with a single space as indent
        DefaultIndenter indenter = new DefaultIndenter(" ", "\n");

        // Verify that getIndent returns the correct string
        assertEquals(" ", indenter.getIndent());
    }

    @Test
    public void testGetIndentWithMultipleChars() throws Exception {
        // Create an instance with two spaces as indent
        DefaultIndenter indenter = new DefaultIndenter("  ", "\n");

        // Verify that getIndent returns the correct string
        assertEquals("  ", indenter.getIndent());
    }

    @Test
    public void testGetIndentWithEmptyString() throws Exception {
        // Create an instance with empty string as indent
        DefaultIndenter indenter = new DefaultIndenter("", "\n");

        // Verify that getIndent returns an empty string
        assertEquals("", indenter.getIndent());
    }

    @Test
    public void testGetIndentWithDifferentEol() throws Exception {
        // Create an instance with a different eol
        DefaultIndenter indenter = new DefaultIndenter("  ", "\r\n");

        // Verify that getIndent returns the correct string
        assertEquals("  ", indenter.getIndent());
    }
}
