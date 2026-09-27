package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptiongetValueSeparatorTest {

    @Test
    public void testGetValueSeparator_DefaultValue() throws Exception {
        // Arrange
        Option option = new Option("a", "description");

        // Act
        char result = option.getValueSeparator();

        // Assert
        assertEquals('\0', result);
    }

    @Test
    public void testGetValueSeparator_CustomValue() throws Exception {
        // Arrange
        Option option = new Option("a", "description");
        Field valuesepField = Option.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        valuesepField.set(option, ':');

        // Act
        char result = option.getValueSeparator();

        // Assert
        assertEquals(':', result);
    }
}
