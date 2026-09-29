package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonFactoryBuilderRootValueSeparatorZeroCoverageTest {
    @Test
    public void testRootValueSeparator() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        String sep = "testSeparator";
        builder.rootValueSeparator(sep);
    }
}
