package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesappendQuotedTest {

    @Test
    public void testAppendQuotedWithNoEscapes() {
        StringBuilder sb = new StringBuilder();
        String content = "HelloWorld";
        CharTypes.appendQuoted(sb, content);
        assertEquals("HelloWorld", sb.toString());
    }

    @Test
    public void testAppendQuotedWithEscapeCharacter() {
        StringBuilder sb = new StringBuilder();
        String content = "Hello\nWorld";
        CharTypes.appendQuoted(sb, content);
        assertEquals("Hello\\nWorld", sb.toString());
    }

    @Test
    public void testAppendQuotedWithUnicodeEscape() {
        StringBuilder sb = new StringBuilder();
        String content = "Hello\u00A9World";
        CharTypes.appendQuoted(sb, content);
        assertEquals("Hello©World", sb.toString());
    }

    @Test
    public void testAppendQuotedWithMultipleEscapes() {
        StringBuilder sb = new StringBuilder();
        String content = "Hello\n\tWorld";
        CharTypes.appendQuoted(sb, content);
        assertEquals("Hello\\n\\tWorld", sb.toString());
    }

    @Test
    public void testAppendQuotedWithEmptyString() {
        StringBuilder sb = new StringBuilder();
        String content = "";
        CharTypes.appendQuoted(sb, content);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendQuotedWithNullContent() {
        StringBuilder sb = new StringBuilder();
        try {
            CharTypes.appendQuoted(sb, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testAppendQuotedWithNullStringBuilder() {
        try {
            CharTypes.appendQuoted(null, "test");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

@Test
    public void testAppendQuotedWithHexEncodingBranch() {
        // Use a control character that should trigger the hex encoding branch
        // For example, '\u0001' is a control character that typically has a negative escape code
        StringBuilder sb = new StringBuilder();
        String content = "\u0001";

        CharTypes.appendQuoted(sb, content);

        // The expected output should be the Unicode escape sequence for '\u0001'
        // Which is "\\u0001"
        assertEquals("\\u0001", sb.toString());
    }
}
