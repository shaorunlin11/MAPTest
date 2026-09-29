package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class OptionGroupsetRequiredTest {
    private OptionGroup optionGroup;
    private Field requiredField;

    @Before
    public void setUp() throws Exception {
        optionGroup = new OptionGroup();
        requiredField = OptionGroup.class.getDeclaredField("required");
        requiredField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        requiredField.setAccessible(false);
    }

    @Test
    public void testSetRequiredTrue() throws Exception {
        boolean value = true;
        optionGroup.setRequired(value);
        boolean result = (boolean) requiredField.get(optionGroup);
        assertTrue("Expected required to be set to true", result);
    }

    @Test
    public void testSetRequiredFalse() throws Exception {
        boolean value = false;
        optionGroup.setRequired(value);
        boolean result = (boolean) requiredField.get(optionGroup);
        assertFalse("Expected required to be set to false", result);
    }
}
