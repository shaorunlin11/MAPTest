package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SeparatorswithObjectFieldValueSeparatorTest {
    @Test
    public void testWithObjectFieldValueSeparatorSameValueReturnsThis() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withObjectFieldValueSeparator(':');
        assertSame("Should return this when separator is the same", separators, result);
    }

    @Test
    public void testWithObjectFieldValueSeparatorDifferentValueCreatesNewInstance() {
        Separators separators = new Separators(':', ',', ',');
        Separators result = separators.withObjectFieldValueSeparator(';');
        assertNotSame("Should return a new instance when separator is different", separators, result);

        // Use reflection to access private fields
        try {
            Field field1 = Separators.class.getDeclaredField("objectFieldValueSeparator");
            field1.setAccessible(true);
            assertEquals("Should have the new object field value separator", ';', field1.getChar(result));

            Field field2 = Separators.class.getDeclaredField("objectEntrySeparator");
            field2.setAccessible(true);
            assertEquals("Should preserve object entry separator", ',', field2.getChar(result));

            Field field3 = Separators.class.getDeclaredField("arrayValueSeparator");
            field3.setAccessible(true);
            assertEquals("Should preserve array value separator", ',', field3.getChar(result));
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }
}
