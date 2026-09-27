package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SeparatorswithObjectEntrySeparatorTest {
    @Test
    public void testWithObjectEntrySeparatorSameValueReturnsThis() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withObjectEntrySeparator(',');
        assertSame(separators, result);
    }

    @Test
    public void testWithObjectEntrySeparatorDifferentValueReturnsNewInstance() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withObjectEntrySeparator(':');
        assertNotSame(separators, result);

        // Use reflection to access private fields
        try {
            Field objectFieldValueSeparatorField = Separators.class.getDeclaredField("objectFieldValueSeparator");
            objectFieldValueSeparatorField.setAccessible(true);
            assertEquals(':', objectFieldValueSeparatorField.get(result));

            Field objectEntrySeparatorField = Separators.class.getDeclaredField("objectEntrySeparator");
            objectEntrySeparatorField.setAccessible(true);
            assertEquals(':', objectEntrySeparatorField.get(result));

            Field arrayValueSeparatorField = Separators.class.getDeclaredField("arrayValueSeparator");
            arrayValueSeparatorField.setAccessible(true);
            assertEquals(',', arrayValueSeparatorField.get(result));
        } catch (Exception e) {
            fail("Failed to access private fields: " + e.getMessage());
        }
    }
}
