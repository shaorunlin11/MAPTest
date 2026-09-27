package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderAppendTwoBytesZeroCoverageTest {
    @Test
    public void testAppendTwoBytes() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.appendTwoBytes(0x1234);
    }
}
