package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class OptionhasLongOptTest {
    @Test
    public void testHasLongOpt_NullLongOpt_ReturnsFalse() throws Exception {
        // Arrange
        Option option = new Option("a", "description");

        // Act
        boolean result = option.hasLongOpt();

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testHasLongOpt_NonNullLongOpt_ReturnsTrue() throws Exception {
        // Arrange
        Option option = new Option("a", "longOpt", false, "description");

        // Act
        boolean result = option.hasLongOpt();

        // Assert
        Assert.assertTrue(result);
    }
}
