package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

public class OptiongetValuesListTest {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test", false, "test description");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testGetValuesListReturnsEmptyListWhenNoValuesSet() {
        List<String> values = option.getValuesList();
        Assert.assertNotNull("The returned list should not be null", values);
        Assert.assertTrue("The returned list should be empty", values.isEmpty());
    }

    @Test
    public void testGetValuesListReturnsExpectedValuesAfterAddingValues() throws Exception {
        List<String> expectedValues = new ArrayList<>();
        expectedValues.add("value1");
        expectedValues.add("value2");

        // Use reflection to set the values list
        java.lang.reflect.Field valuesField = Option.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        valuesField.set(option, new ArrayList<>(expectedValues));

        List<String> actualValues = option.getValuesList();
        Assert.assertEquals("The returned list should match the expected values", expectedValues, actualValues);
    }
}
