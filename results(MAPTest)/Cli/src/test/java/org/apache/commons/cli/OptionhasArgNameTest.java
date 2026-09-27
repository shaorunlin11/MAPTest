package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionhasArgNameTest {

    @Test
    public void testHasArgNameWithNonNullAndNonEmptyArgName() throws Exception {
        Option option = new Option("t", "test description");
        // Use reflection to set the private argName field
        java.lang.reflect.Field argNameField = Option.class.getDeclaredField("argName");
        argNameField.setAccessible(true);
        argNameField.set(option, "testArg");
        assertTrue(option.hasArgName());
    }

    @Test
    public void testHasArgNameWithNullArgName() throws Exception {
        Option option = new Option("t", "test description");
        // Use reflection to set the private argName field
        java.lang.reflect.Field argNameField = Option.class.getDeclaredField("argName");
        argNameField.setAccessible(true);
        argNameField.set(option, null);
        assertFalse(option.hasArgName());
    }

    @Test
    public void testHasArgNameWithEmptyArgName() throws Exception {
        Option option = new Option("t", "test description");
        // Use reflection to set the private argName field
        java.lang.reflect.Field argNameField = Option.class.getDeclaredField("argName");
        argNameField.setAccessible(true);
        argNameField.set(option, "");
        assertFalse(option.hasArgName());
    }
}
