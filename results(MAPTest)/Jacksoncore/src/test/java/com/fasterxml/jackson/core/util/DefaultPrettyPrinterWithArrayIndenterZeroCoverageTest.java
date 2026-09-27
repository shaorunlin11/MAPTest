package com.fasterxml.jackson.core.util;

import org.junit.Test;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class DefaultPrettyPrinterWithArrayIndenterZeroCoverageTest {
    @Test
    public void testWithArrayIndenterWithNullParameter() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withArrayIndenter(null);

        // Verify that the returned instance is not the same as the original
        assert result != printer;

        // Verify that the array indenter is set to NopIndenter.instance
        assert result._arrayIndenter == DefaultPrettyPrinter.NopIndenter.instance;
    }

@Test
    public void testWithArrayIndenterWithSameInstance() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withArrayIndenter(printer._arrayIndenter);

        // Verify that the returned instance is the same as the original
        assert result == printer;
    }
}
