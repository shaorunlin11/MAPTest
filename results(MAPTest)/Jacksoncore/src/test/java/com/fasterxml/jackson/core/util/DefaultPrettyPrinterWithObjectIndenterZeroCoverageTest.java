package com.fasterxml.jackson.core.util;

import org.junit.Test;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class DefaultPrettyPrinterWithObjectIndenterZeroCoverageTest {
    @Test
    public void testWithObjectIndenterWithNullArgument() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withObjectIndenter(null);

        // Verify that the object indenter is set to NopIndenter.instance when null is passed
        assert result._objectIndenter == DefaultPrettyPrinter.NopIndenter.instance;
    }

@Test
    public void testWithObjectIndenterWithSameIndenter() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withObjectIndenter(printer._objectIndenter);

        // Verify that the same instance is returned when the indenter is the same
        assert result == printer;
    }
}
