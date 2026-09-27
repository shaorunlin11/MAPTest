package org.apache.commons.cli;

import org.junit.Test;
import java.util.Map;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptionGroupaddOptionTest {
    @Test
    public void testAddOption() throws Exception {
        // Arrange
        OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("a", "description");

        // Act
        OptionGroup result = optionGroup.addOption(option);

        // Assert
        assertEquals(optionGroup, result);
        Map<String, Option> optionMap = getOptionMap(optionGroup);
        assertNotNull(optionMap);
        assertTrue(optionMap.containsKey("a"));
        assertEquals(option, optionMap.get("a"));
    }

    private Map<String, Option> getOptionMap(OptionGroup optionGroup) throws Exception {
        Field field = OptionGroup.class.getDeclaredField("optionMap");
        field.setAccessible(true);
        return (Map<String, Option>) field.get(optionGroup);
    }
}
