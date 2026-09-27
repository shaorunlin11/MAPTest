package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class DefaultPrettyPrinterwithSpacesInObjectEntriesTest {

    @Test
    public void testWithSpacesInObjectEntries() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter newPrinter = printer.withSpacesInObjectEntries();

        // Verify that the new instance has spaces in object entries enabled
        Field field = DefaultPrettyPrinter.class.getDeclaredField("_spacesInObjectEntries");
        field.setAccessible(true);
        Boolean result = (Boolean) field.get(newPrinter);
        assertTrue("withSpacesInObjectEntries should set _spacesInObjectEntries to true", result);
    }
}
