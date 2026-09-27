package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionhasArgTest {
    @Test
    public void testHasArgWithUninitialized() throws Exception {
        Option option = new Option("a", "description");
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgWithZeroArgs() throws Exception {
        Option option = new Option("a", false, "description");
        assertFalse(option.hasArg());
    }

    @Test
    public void testHasArgWithOneArg() throws Exception {
        Option option = new Option("a", true, "description");
        assertTrue(option.hasArg());
    }

    @Test
    public void testHasArgWithUnlimitedValues() throws Exception {
        Option option = new Option("a", "longOpt", true, "description");
        // Use the constructor that allows setting numberOfArgs
        // This is not directly possible, so we use a workaround with reflection
        // to set the numberOfArgs field, as there's no public setter
        java.lang.reflect.Field field = Option.class.getDeclaredField("numberOfArgs");
        field.setAccessible(true);
        field.set(option, Option.UNLIMITED_VALUES);
        assertTrue(option.hasArg());
    }

    @Test
    public void testHasArgWithMultipleArgs() throws Exception {
        Option option = new Option("a", "longOpt", true, "description");
        // Use the constructor that allows setting numberOfArgs
        // This is not directly possible, so we use a workaround with reflection
        // to set the numberOfArgs field, as there's no public setter
        java.lang.reflect.Field field = Option.class.getDeclaredField("numberOfArgs");
        field.setAccessible(true);
        field.set(option, 3);
        assertTrue(option.hasArg());
    }
}
