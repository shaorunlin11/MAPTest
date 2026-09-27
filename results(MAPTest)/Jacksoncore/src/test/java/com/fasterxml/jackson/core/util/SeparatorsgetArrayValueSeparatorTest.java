package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeparatorsgetArrayValueSeparatorTest {
    @Test
    public void testGetArrayValueSeparator_DefaultConstructor() throws Exception {
        Separators separators = new Separators();
        char result = separators.getArrayValueSeparator();
        assertEquals(',', result);
    }

    @Test
    public void testGetArrayValueSeparator_CustomConstructor() throws Exception {
        Separators separators = new Separators(':', ';', '|');
        char result = separators.getArrayValueSeparator();
        assertEquals('|', result);
    }
}
