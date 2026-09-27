package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptiongetTypeTest {
    @Test
    public void testGetType_DefaultValue() throws Exception {
        Option option = new Option("a", "description");
        Object type = option.getType();
        assertEquals(String.class, type);
    }

    @Test
    public void testGetType_SetValue() throws Exception {
        Option option = new Option("a", "description");
        // Use reflection to set the type field
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(option, Integer.class);

        Object type = option.getType();
        assertEquals(Integer.class, type);
    }
}
