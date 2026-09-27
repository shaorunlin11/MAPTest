package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class HelpFormattersetWidthTest {
    private HelpFormatter formatter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testSetWidth() throws Exception {
        int expectedWidth = 80;
        formatter.setWidth(expectedWidth);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = HelpFormatter.class.getDeclaredField("defaultWidth");
        field.setAccessible(true);
        int actualWidth = (int) field.get(formatter);

        Assert.assertEquals("setWidth should set defaultWidth to the provided value", expectedWidth, actualWidth);
    }
}
