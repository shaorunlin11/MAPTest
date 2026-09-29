package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderResetAndGetFirstSegmentZeroCoverageTest {
    @Test
    public void testResetAndGetFirstSegment() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        builder.reset();
        byte[] result = builder.resetAndGetFirstSegment();
    }
}
