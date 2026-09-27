package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptiongetLongOptTest {

    @Test
    public void testGetLongOpt_ReturnsSetLongOpt() {
        // Arrange
        Option option = new Option("a", "testLongOpt", false, "description");

        // Act
        String result = option.getLongOpt();

        // Assert
        assertEquals("testLongOpt", result);
    }

    @Test
    public void testGetLongOpt_ReturnsNullWhenNotSet() {
        // Arrange
        Option option = new Option("a", "description");

        // Act
        String result = option.getLongOpt();

        // Assert
        assertNull(result);
    }
}
