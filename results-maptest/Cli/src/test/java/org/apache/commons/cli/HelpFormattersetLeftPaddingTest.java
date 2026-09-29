package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class HelpFormattersetLeftPaddingTest {
    private HelpFormatter helpFormatter;

    @Before
    public void setUp() {
        helpFormatter = new HelpFormatter();
    }

    @After
    public void tearDown() {
        helpFormatter = null;
    }

    @Test
    public void testSetLeftPadding() throws Exception {
        int expectedPadding = 5;
        helpFormatter.setLeftPadding(expectedPadding);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = HelpFormatter.class.getDeclaredField("defaultLeftPad");
        field.setAccessible(true);
        int actualPadding = (int) field.get(helpFormatter);

        Assert.assertEquals("The left padding should be set correctly", expectedPadding, actualPadding);
    }
}
