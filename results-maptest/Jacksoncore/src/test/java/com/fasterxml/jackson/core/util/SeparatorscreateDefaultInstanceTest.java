package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class SeparatorscreateDefaultInstanceTest {
    @Test
    public void testCreateDefaultInstance_returnsNonNullInstance() {
        Separators separators = Separators.createDefaultInstance();
        assertNotNull("createDefaultInstance should return a non-null instance", separators);
    }

    @Test
    public void testCreateDefaultInstance_returnsDefaultValues() throws Exception {
        Separators separators = Separators.createDefaultInstance();

        Field objectFieldValueSeparatorField = Separators.class.getDeclaredField("objectFieldValueSeparator");
        objectFieldValueSeparatorField.setAccessible(true);
        Character objectFieldValueSeparator = (Character) objectFieldValueSeparatorField.get(separators);

        Field objectEntrySeparatorField = Separators.class.getDeclaredField("objectEntrySeparator");
        objectEntrySeparatorField.setAccessible(true);
        Character objectEntrySeparator = (Character) objectEntrySeparatorField.get(separators);

        Field arrayValueSeparatorField = Separators.class.getDeclaredField("arrayValueSeparator");
        arrayValueSeparatorField.setAccessible(true);
        Character arrayValueSeparator = (Character) arrayValueSeparatorField.get(separators);

        assertEquals("Default object field value separator should be ':', but was " + objectFieldValueSeparator, ':', objectFieldValueSeparator.charValue());
        assertEquals("Default object entry separator should be ',', but was " + objectEntrySeparator, ',', objectEntrySeparator.charValue());
        assertEquals("Default array value separator should be ',', but was " + arrayValueSeparator, ',', arrayValueSeparator.charValue());
    }
}
