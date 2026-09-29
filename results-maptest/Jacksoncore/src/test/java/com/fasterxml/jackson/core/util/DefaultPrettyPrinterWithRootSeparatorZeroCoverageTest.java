package com.fasterxml.jackson.core.util;

import org.junit.Test;

import com.fasterxml.jackson.core.io.SerializedString;


public class DefaultPrettyPrinterWithRootSeparatorZeroCoverageTest {
    @Test
    public void testWithRootSeparator() {
        // Create an instance with a non-null root separator
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter(new SerializedString("test"));

        // Call the method with a root separator that is equal to the existing one
        printer.withRootSeparator(new SerializedString("test"));
    }

@Test
    public void testWithRootSeparatorDifferentValue() {
        // Create an instance with a non-null root separator
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter(new SerializedString("test"));

        // Call the method with a root separator that is different from the existing one
        DefaultPrettyPrinter newPrinter = printer.withRootSeparator(new SerializedString("another"));

        // Verify that a new instance is created
        assert newPrinter != printer;
    }
}
