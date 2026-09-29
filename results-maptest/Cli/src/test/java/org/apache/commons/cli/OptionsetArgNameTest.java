package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OptionsetArgNameTest {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test-option");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testSetArgName() throws Exception {
        String expectedArgName = "test-arg";
        option.setArgName(expectedArgName);
        Assert.assertEquals(expectedArgName, getArgName(option));
    }

    @Test
    public void testSetArgNameWithNull() throws Exception {
        String expectedArgName = null;
        option.setArgName(expectedArgName);
        Assert.assertNull(getArgName(option));
    }

    private String getArgName(Option option) throws Exception {
        java.lang.reflect.Field field = Option.class.getDeclaredField("argName");
        field.setAccessible(true);
        return (String) field.get(option);
    }
}
