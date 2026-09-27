package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class OptionGroupgetSelectedTest {
    private OptionGroup optionGroup;

    @Before
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @After
    public void tearDown() {
        optionGroup = null;
    }

    @Test
    public void testGetSelectedReturnsNullWhenNotSet() {
        Assert.assertNull(optionGroup.getSelected());
    }

    @Test
    public void testGetSelectedReturnsSetStringValue() throws Exception {
        String expected = "testValue";
        Field selectedField = OptionGroup.class.getDeclaredField("selected");
        selectedField.setAccessible(true);
        selectedField.set(optionGroup, expected);

        Assert.assertEquals(expected, optionGroup.getSelected());
    }
}
