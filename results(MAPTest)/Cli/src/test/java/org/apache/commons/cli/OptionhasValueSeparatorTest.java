package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionhasValueSeparatorTest {

    @Test
    public void testHasValueSeparatorWithDefaultValue() throws Exception {
        Option option = new Option("a", "description");
        assertFalse(option.hasValueSeparator());
    }

    @Test
    public void testHasValueSeparatorWithValueSeparatorSet() throws Exception {
        Option option = new Option("a", "description");
        // Use reflection to set valuesep field
        java.lang.reflect.Field valuesepField = Option.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        valuesepField.setChar(option, ':');
        assertTrue(option.hasValueSeparator());
    }

    @Test
    public void testHasValueSeparatorWithNegativeValueSeparator() throws Exception {
        Option option = new Option("a", "description");
        // Use reflection to set valuesep field
        java.lang.reflect.Field valuesepField = Option.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        valuesepField.setChar(option, '\0');
        assertFalse(option.hasValueSeparator());
    }

    @Test
    public void testHasValueSeparatorWithZeroValueSeparator() throws Exception {
        Option option = new Option("a", "description");
        // Use reflection to set valuesep field
        java.lang.reflect.Field valuesepField = Option.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        valuesepField.setChar(option, '\0');
        assertFalse(option.hasValueSeparator());
    }
}
