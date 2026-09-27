package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class HelpFormattersetSyntaxPrefixTest {
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
    public void testSetSyntaxPrefixWithNonNullValue() {
        String expectedPrefix = "custom: ";
        formatter.setSyntaxPrefix(expectedPrefix);
        Assert.assertEquals("The syntax prefix should be set to the provided value", expectedPrefix, formatter.defaultSyntaxPrefix);
    }

    @Test
    public void testSetSyntaxPrefixWithNullValue() {
        String nullPrefix = null;
        formatter.setSyntaxPrefix(nullPrefix);
        Assert.assertNull("The syntax prefix should be set to null", formatter.defaultSyntaxPrefix);
    }
}
