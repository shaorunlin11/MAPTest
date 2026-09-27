package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class HelpFormattersetLongOptPrefixTest {
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
    public void testSetLongOptPrefix() throws Exception {
        // Arrange
        String expectedPrefix = "--custom";

        // Act
        formatter.setLongOptPrefix(expectedPrefix);

        // Assert
        Field field = HelpFormatter.class.getDeclaredField("defaultLongOptPrefix");
        field.setAccessible(true);
        String actualPrefix = (String) field.get(formatter);
        Assert.assertEquals(expectedPrefix, actualPrefix);
    }
}
