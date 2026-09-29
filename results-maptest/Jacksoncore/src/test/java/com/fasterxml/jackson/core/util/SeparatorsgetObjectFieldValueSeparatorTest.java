package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeparatorsgetObjectFieldValueSeparatorTest {
    @Test
    public void testGetObjectFieldValueSeparator_DefaultConstructor() throws Exception {
        Separators separators = new Separators();
        char expected = ':';
        char actual = separators.getObjectFieldValueSeparator();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetObjectFieldValueSeparator_ParameterizedConstructor() throws Exception {
        char objectFieldValueSeparator = ';';
        char objectEntrySeparator = '-';
        char arrayValueSeparator = '.';
        Separators separators = new Separators(objectFieldValueSeparator, objectEntrySeparator, arrayValueSeparator);
        char actual = separators.getObjectFieldValueSeparator();
        assertEquals(objectFieldValueSeparator, actual);
    }
}
