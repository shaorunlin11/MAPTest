package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.Map;
import java.util.LinkedHashMap;

public class OptionshasShortOptionTest {
    private Options options;

    @Before
    public void setUp() {
        options = new Options();
    }

    @After
    public void tearDown() {
        options = null;
    }

    @Test
    public void testHasShortOption_WithShortOption() {
        // Arrange
        Option option = new Option("a", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasShortOption("a");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasShortOption_WithHyphenatedOption() {
        // Arrange
        Option option = new Option("a", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasShortOption("-a");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasShortOption_WithMultipleHyphens() {
        // Arrange
        Option option = new Option("a", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasShortOption("-a");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasShortOption_WithNoOption() {
        // Arrange
        Option option = new Option("a", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasShortOption("b");

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testHasShortOption_NullInput() {
        // Act
        boolean result = options.hasShortOption(null);

        // Assert
        Assert.assertFalse(result);
    }
}
