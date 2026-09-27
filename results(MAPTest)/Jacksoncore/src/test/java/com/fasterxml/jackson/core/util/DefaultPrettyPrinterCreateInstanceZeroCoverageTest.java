package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class DefaultPrettyPrinterCreateInstanceZeroCoverageTest {
    @Test
    public void testCreateInstance() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.createInstance();
        // Ensure the returned instance is of the correct type
        assert result instanceof DefaultPrettyPrinter;
    }
}
