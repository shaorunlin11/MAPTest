package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class HelpFormattersetOptPrefixTest {
    private HelpFormatter helpFormatter;
    private Field defaultOptPrefixField;

    @Before
    public void setUp() throws Exception {
        helpFormatter = new HelpFormatter();
        defaultOptPrefixField = HelpFormatter.class.getDeclaredField("defaultOptPrefix");
        defaultOptPrefixField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        defaultOptPrefixField.setAccessible(false);
    }

    @Test
    public void testSetOptPrefixWithNonNullValue() throws Exception {
        String expectedPrefix = "custom-";
        helpFormatter.setOptPrefix(expectedPrefix);
        String actualPrefix = (String) defaultOptPrefixField.get(helpFormatter);
        Assert.assertEquals(expectedPrefix, actualPrefix);
    }

    @Test
    public void testSetOptPrefixWithNullValue() throws Exception {
        helpFormatter.setOptPrefix(null);
        String actualPrefix = (String) defaultOptPrefixField.get(helpFormatter);
        Assert.assertNull(actualPrefix);
    }
}
