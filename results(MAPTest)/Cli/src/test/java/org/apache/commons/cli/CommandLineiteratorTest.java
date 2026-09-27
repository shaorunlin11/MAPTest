package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CommandLineiteratorTest {
    @Test
    public void testIteratorReturnsNonNullIterator() {
        CommandLine commandLine = new CommandLine();
        Iterator<Option> iterator = commandLine.iterator();
        assertNotNull("Iterator should not be null", iterator);
    }

    @Test
    public void testIteratorOverOptionsList() {
        CommandLine commandLine = new CommandLine();
        List<Option> options = new ArrayList<>();
        options.add(new Option("a", "option a"));
        options.add(new Option("b", "option b"));

        // Use reflection to set the options list
        try {
            java.lang.reflect.Field optionsField = CommandLine.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            optionsField.set(commandLine, options);
        } catch (Exception e) {
            fail("Failed to set options field: " + e.getMessage());
        }

        Iterator<Option> iterator = commandLine.iterator();
        assertTrue("Iterator should have next element", iterator.hasNext());
        assertEquals("First element should be option a", "option a", iterator.next().getDescription());
        assertTrue("Iterator should have next element", iterator.hasNext());
        assertEquals("Second element should be option b", "option b", iterator.next().getDescription());
        assertFalse("Iterator should not have next element", iterator.hasNext());
    }
}
