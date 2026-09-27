package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SeparatorswithArrayValueSeparatorTest {

    @Test
    public void testWithArrayValueSeparatorSameValueReturnsThis() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withArrayValueSeparator(',');
        assertSame("Should return the same instance when separator is unchanged", separators, result);
    }

    @Test
    public void testWithArrayValueSeparatorDifferentValueReturnsNewInstance() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withArrayValueSeparator(';');
        assertNotSame("Should return a new instance when separator is changed", separators, result);

        // Use reflection to access private fields
        try {
            Field objectFieldValueSeparatorField = Separators.class.getDeclaredField("objectFieldValueSeparator");
            objectFieldValueSeparatorField.setAccessible(true);
            assertEquals("Object field value separator should remain unchanged", ':', objectFieldValueSeparatorField.get(result));

            Field objectEntrySeparatorField = Separators.class.getDeclaredField("objectEntrySeparator");
            objectEntrySeparatorField.setAccessible(true);
            assertEquals("Object entry separator should remain unchanged", ',', objectEntrySeparatorField.get(result));

            Field arrayValueSeparatorField = Separators.class.getDeclaredField("arrayValueSeparator");
            arrayValueSeparatorField.setAccessible(true);
            assertEquals("Array value separator should be updated", ';', arrayValueSeparatorField.get(result));
        } catch (Exception e) {
            fail("Failed to access private fields: " + e.getMessage());
        }
    }
}
