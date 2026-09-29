package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelpFormatterrtrimTest {

    @Test
    public void testRtrimNullInput() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.rtrim(null);
        assertNull("rtrim should return null for null input", result);
    }

    @Test
    public void testRtrimEmptyString() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.rtrim("");
        assertEquals("rtrim should return empty string for empty input", "", result);
    }

    @Test
    public void testRtrimNoTrailingWhitespace() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.rtrim("hello");
        assertEquals("rtrim should return original string when no trailing whitespace", "hello", result);
    }

    @Test
    public void testRtrimWithTrailingWhitespace() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.rtrim("hello   ");
        assertEquals("rtrim should remove trailing whitespace", "hello", result);
    }

    @Test
    public void testRtrimAllWhitespace() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.rtrim("   ");
        assertEquals("rtrim should return empty string when input is all whitespace", "", result);
    }
}
