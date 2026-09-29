package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MinimalPrettyPrintersetSeparatorsTest {
    @Test
    public void testSetSeparators() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        Separators separators = new Separators();

        MinimalPrettyPrinter result = printer.setSeparators(separators);

        assertEquals(printer, result);

        // Verify that the internal _separators field was set
        Field separatorsField = MinimalPrettyPrinter.class.getDeclaredField("_separators");
        separatorsField.setAccessible(true);
        assertEquals(separators, separatorsField.get(printer));
    }
}
