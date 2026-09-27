package com.zappos.json.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringsformatTest {
    @Test
    public void testFormatWithNullArgs() {
        String pattern = "Hello@";
        String result = Strings.format(pattern, (Object[]) null);
        assertEquals("Hello@", result);
    }

    @Test
    public void testFormatWithNullArgument() {
        String pattern = "Hello@";
        String result = Strings.format(pattern, (Object) null);
        assertEquals("Hello" + com.zappos.json.JsonWriter.CONST_NULL, result);
    }

    @Test
    public void testFormatWithMultiplePlaceholders() {
        String pattern = "Hello@, @!";
        String result = Strings.format(pattern, "World", "Java");
        assertEquals("HelloWorld, Java!", result);
    }

    @Test
    public void testFormatWithNoPlaceholders() {
        String pattern = "No placeholders here";
        String result = Strings.format(pattern, "arg1", "arg2");
        assertEquals("No placeholders here", result);
    }

    @Test
    public void testFormatWithMultiplePlaceholdersAndNulls() {
        String pattern = "@, @, @";
        String result = Strings.format(pattern, (Object) null, "value", (Object) null);
        assertEquals(com.zappos.json.JsonWriter.CONST_NULL + ", value, " + com.zappos.json.JsonWriter.CONST_NULL, result);
    }
}
