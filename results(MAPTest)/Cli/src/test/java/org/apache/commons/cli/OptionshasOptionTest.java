package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;

public class OptionshasOptionTest {
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
    public void testHasOptionWithShortOption() {
        // Arrange
        Option option = new Option("a", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasOption("a");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasOptionWithLongOption() {
        // Arrange
        Option option = new Option("test", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasOption("test");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasOptionWithLeadingHyphens() {
        // Arrange
        Option option = new Option("b", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasOption("--b");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasOptionWithMultipleLeadingHyphens() {
        // Arrange
        Option option = new Option("c", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasOption("-c");

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testHasOptionWhenOptionDoesNotExist() {
        // Arrange
        Option option = new Option("d", "test");
        options.addOption(option);

        // Act
        boolean result = options.hasOption("e");

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testHasOptionWithNullInput() {
        // Act
        boolean result = options.hasOption(null);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testHasOptionWithEmptyString() {
        // Act
        boolean result = options.hasOption("");

        // Assert
        Assert.assertFalse(result);
    }
}
