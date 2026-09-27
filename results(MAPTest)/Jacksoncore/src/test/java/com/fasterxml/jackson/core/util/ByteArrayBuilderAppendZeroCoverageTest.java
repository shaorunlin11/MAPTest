package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderAppendZeroCoverageTest {
    @Test
    public void testAppendWhenCurrBlockPtrExceedsCurrBlockLength() throws Exception {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        // Use public method to set current segment length to force the condition
        builder.setCurrentSegmentLength(1);

        // Call append with any int value
        builder.append(0);
    }
}
