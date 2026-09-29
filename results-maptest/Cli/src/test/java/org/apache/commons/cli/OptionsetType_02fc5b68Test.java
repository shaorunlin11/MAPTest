package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionsetType_02fc5b68Test {

    @Test
    public void testSetType() throws Exception {
        // Arrange
        Option option = new Option("t", "test option");

        // Act
        option.setType(Integer.class);

        // Assert
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        assertEquals(Integer.class, typeField.get(option));
    }

    @Test
    public void testSetTypeWithNull() throws Exception {
        // Arrange
        Option option = new Option("t", "test option");

        // Act
        option.setType(null);

        // Assert
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        assertNull(typeField.get(option));
    }

    @Test
    public void testSetTypeMultipleTimes() throws Exception {
        // Arrange
        Option option = new Option("t", "test option");

        // Act
        option.setType(String.class);
        option.setType(Boolean.class);

        // Assert
        Field typeField = Option.class.getDeclaredField("type");
        typeField.setAccessible(true);
        assertEquals(Boolean.class, typeField.get(option));
    }
}
