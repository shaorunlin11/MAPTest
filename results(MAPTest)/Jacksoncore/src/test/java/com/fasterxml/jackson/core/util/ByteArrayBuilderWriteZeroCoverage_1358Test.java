package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderWriteZeroCoverage_1358Test {
    @Test
    public void testWriteWithValidInput() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.write(0);
    }
}
