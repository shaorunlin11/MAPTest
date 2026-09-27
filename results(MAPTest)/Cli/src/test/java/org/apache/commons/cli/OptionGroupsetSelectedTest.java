package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class OptionGroupsetSelectedTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSetSelectedWithNullOption() throws Exception {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setSelected(null);
        assertEquals("selected should be null", null, getSelected(optionGroup));
    }

    @Test
    public void testSetSelectedWithNewOption() throws Exception {
        OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("a", "test");
        optionGroup.setSelected(option);
        assertEquals("selected should be the key of the option", "a", getSelected(optionGroup));
    }

    @Test
    public void testSetSelectedWithSameOptionAgain() throws Exception {
        OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("a", "test");
        optionGroup.setSelected(option);
        optionGroup.setSelected(option);
        assertEquals("selected should remain the key of the option", "a", getSelected(optionGroup));
    }

    @Test
    public void testSetSelectedWithDifferentOption() throws Exception {
        OptionGroup optionGroup = new OptionGroup();
        Option option1 = new Option("a", "test1");
        Option option2 = new Option("b", "test2");

        optionGroup.setSelected(option1);
        thrown.expect(AlreadySelectedException.class);
        optionGroup.setSelected(option2);
    }

    private String getSelected(OptionGroup optionGroup) throws Exception {
        java.lang.reflect.Field field = OptionGroup.class.getDeclaredField("selected");
        field.setAccessible(true);
        return (String) field.get(optionGroup);
    }
}
