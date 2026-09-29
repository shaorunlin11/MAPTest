package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderCompleteAndCoalesceZeroCoverageTest {
    @Test
    public void testCompleteAndCoalesceWithValidLastBlockLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        int lastBlockLength = 10;
        builder.completeAndCoalesce(lastBlockLength);
    }
}
