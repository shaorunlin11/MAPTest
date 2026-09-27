package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import java.lang.reflect.Field;

public class OptionsaddOption_fc62b4ffTest {
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
    public void testAddOption() throws Exception {
        // Arrange
        String opt = "t";
        String description = "test option";

        // Act
        Options result = options.addOption(opt, description);

        // Assert
        Assert.assertEquals(options, result);

        // Check that the shortOpts map contains the option
        Field shortOptsField = Options.class.getDeclaredField("shortOpts");
        shortOptsField.setAccessible(true);
        Map<String, Option> shortOpts = (Map<String, Option>) shortOptsField.get(options);
        Assert.assertTrue(shortOpts.containsKey(opt));

        // Check that the option has the correct description
        Option option = shortOpts.get(opt);
        Assert.assertEquals(description, option.getDescription());
    }
}
