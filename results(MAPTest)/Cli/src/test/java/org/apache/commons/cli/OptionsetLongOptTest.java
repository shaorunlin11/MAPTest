package org.apache.commons.cli;

import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class OptionsetLongOptTest {

    @Test
    public void testSetLongOpt() throws Exception {
        // Arrange
        Option option = new Option("a", "description");

        // Act
        option.setLongOpt("longOption");

        // Assert
        Field longOptField = Option.class.getDeclaredField("longOpt");
        longOptField.setAccessible(true);
        assertEquals("longOption", longOptField.get(option));
    }

    @Test
    public void testSetLongOptWithNull() throws Exception {
        // Arrange
        Option option = new Option("a", "description");

        // Act
        option.setLongOpt(null);

        // Assert
        Field longOptField = Option.class.getDeclaredField("longOpt");
        longOptField.setAccessible(true);
        assertEquals(null, longOptField.get(option));
    }
}
