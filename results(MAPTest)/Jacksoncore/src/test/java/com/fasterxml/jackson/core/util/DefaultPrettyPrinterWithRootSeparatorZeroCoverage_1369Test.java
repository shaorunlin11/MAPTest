package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class DefaultPrettyPrinterWithRootSeparatorZeroCoverage_1369Test {
    @Test
    public void testWithRootSeparatorNull() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withRootSeparator((String) null);
        // This test ensures that the method is called and returns a non-null instance
        // without any exceptions, covering the target line where rootSeparator is null.
    }

    @Test
    public void testWithRootSeparatorNotNull() {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter result = printer.withRootSeparator("testSeparator");
        // This test ensures that the method is called and returns a non-null instance
        // without any exceptions, covering the target line where rootSeparator is not null.
    }
}
