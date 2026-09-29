package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MinimalPrettyPrintersetRootValueSeparatorTest {
    private MinimalPrettyPrinter printer;

    @Before
    public void setUp() {
        printer = new MinimalPrettyPrinter();
    }

    @After
    public void tearDown() {
        printer = null;
    }

    @Test
    public void testSetRootValueSeparatorWithNonNullValue() throws Exception {
        String expectedSeparator = "test-sep";
        printer.setRootValueSeparator(expectedSeparator);

        Field field = MinimalPrettyPrinter.class.getDeclaredField("_rootValueSeparator");
        field.setAccessible(true);
        String actualSeparator = (String) field.get(printer);

        assertEquals("Should set the root value separator correctly", expectedSeparator, actualSeparator);
    }

    @Test
    public void testSetRootValueSeparatorWithNullValue() throws Exception {
        printer.setRootValueSeparator(null);

        Field field = MinimalPrettyPrinter.class.getDeclaredField("_rootValueSeparator");
        field.setAccessible(true);
        String actualSeparator = (String) field.get(printer);

        assertNull("Should set the root value separator to null", actualSeparator);
    }
}
