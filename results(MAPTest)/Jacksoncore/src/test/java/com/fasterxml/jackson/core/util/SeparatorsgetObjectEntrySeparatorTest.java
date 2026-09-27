package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeparatorsgetObjectEntrySeparatorTest {
    @Test
    public void testGetObjectEntrySeparator_DefaultConstructor() {
        Separators separators = new Separators();
        char result = separators.getObjectEntrySeparator();
        assertEquals(',', result);
    }

    @Test
    public void testGetObjectEntrySeparator_CustomConstructor() {
        Separators separators = new Separators(':', ';', '|');
        char result = separators.getObjectEntrySeparator();
        assertEquals(';', result);
    }

    @Test
    public void testGetObjectEntrySeparator_MultipleCalls() {
        Separators separators = new Separators(':', ';', '|');
        char result1 = separators.getObjectEntrySeparator();
        char result2 = separators.getObjectEntrySeparator();
        assertEquals(';', result1);
        assertEquals(';', result2);
    }
}
