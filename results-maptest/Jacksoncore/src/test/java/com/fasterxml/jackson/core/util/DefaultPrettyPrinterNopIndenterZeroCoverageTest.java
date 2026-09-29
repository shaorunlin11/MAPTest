package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class DefaultPrettyPrinterNopIndenterZeroCoverageTest {
    @Test
    public void testNopIndenterStaticInitializer() {
        // Trigger the static initializer for NopIndenter by accessing the instance field
        DefaultPrettyPrinter.NopIndenter instance = DefaultPrettyPrinter.NopIndenter.instance;
    }
}
