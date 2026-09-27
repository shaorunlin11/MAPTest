package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class HelpFormattergetSyntaxPrefixTest {
    @Test
    public void testGetSyntaxPrefix_returnsDefaultSyntaxPrefix() {
        HelpFormatter formatter = new HelpFormatter();
        assertEquals("usage: ", formatter.getSyntaxPrefix());
    }

    @Test
    public void testGetSyntaxPrefix_returnsModifiedSyntaxPrefix() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        Field field = HelpFormatter.class.getDeclaredField("defaultSyntaxPrefix");
        field.setAccessible(true);
        field.set(formatter, "custom: ");
        assertEquals("custom: ", formatter.getSyntaxPrefix());
    }
}
