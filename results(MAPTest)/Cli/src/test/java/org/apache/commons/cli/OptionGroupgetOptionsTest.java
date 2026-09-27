package org.apache.commons.cli;

import org.junit.Test;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptionGroupgetOptionsTest {

    @Test
    public void testGetOptionsReturnsCollectionOfOptions() throws Exception {
        // Arrange
        OptionGroup optionGroup = new OptionGroup();
        Map<String, Option> optionMap = new LinkedHashMap<>();
        optionMap.put("a", new Option("a", "option a"));
        optionMap.put("b", new Option("b", "option b"));

        // Use reflection to set the private optionMap field
        Field optionMapField = OptionGroup.class.getDeclaredField("optionMap");
        optionMapField.setAccessible(true);
        optionMapField.set(optionGroup, optionMap);

        // Act
        Collection<Option> options = optionGroup.getOptions();

        // Assert
        assertNotNull(options);
        assertEquals(2, options.size());
        assertTrue(options.contains(new Option("a", "option a")));
        assertTrue(options.contains(new Option("b", "option b")));
    }

    @Test
    public void testGetOptionsReturnsEmptyCollectionWhenNoOptionsAreAdded() throws Exception {
        // Arrange
        OptionGroup optionGroup = new OptionGroup();

        // Act
        Collection<Option> options = optionGroup.getOptions();

        // Assert
        assertNotNull(options);
        assertEquals(0, options.size());
    }

    @Test
    public void testGetOptionsReturnsViewOfOptionMap() throws Exception {
        // Arrange
        OptionGroup optionGroup = new OptionGroup();
        Map<String, Option> optionMap = new LinkedHashMap<>();
        optionMap.put("a", new Option("a", "option a"));
        Field optionMapField = OptionGroup.class.getDeclaredField("optionMap");
        optionMapField.setAccessible(true);
        optionMapField.set(optionGroup, optionMap);

        // Act
        Collection<Option> options = optionGroup.getOptions();
        optionMap.put("b", new Option("b", "option b"));
        Collection<Option> updatedOptions = optionGroup.getOptions();

        // Assert
        assertNotNull(options);
        assertEquals(2, updatedOptions.size());
        assertTrue(updatedOptions.contains(new Option("a", "option a")));
        assertTrue(updatedOptions.contains(new Option("b", "option b")));
    }
}
