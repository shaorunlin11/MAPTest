package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.IndexOutOfBoundsException;

public class OptiongetValue_4bedcf13Test {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test option");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testGetValueWithNoValues() {
        Assert.assertNull(option.getValue(0));
    }

    @Test
    public void testGetValueWithValidIndex() {
        List<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");

        try {
            Field valuesField = Option.class.getDeclaredField("values");
            valuesField.setAccessible(true);
            valuesField.set(option, values);
        } catch (Exception e) {
            Assert.fail("Failed to set values field: " + e.getMessage());
        }

        Assert.assertEquals("value1", option.getValue(0));
        Assert.assertEquals("value2", option.getValue(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetValueWithInvalidIndex() {
        List<String> values = new ArrayList<>();
        values.add("value1");

        try {
            Field valuesField = Option.class.getDeclaredField("values");
            valuesField.setAccessible(true);
            valuesField.set(option, values);
        } catch (Exception e) {
            Assert.fail("Failed to set values field: " + e.getMessage());
        }

        option.getValue(2);
    }
}
