package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.LinkedList;

public class ByteArrayBuildergetCurrentSegmentLengthTest {
    private ByteArrayBuilder builder;

    @Before
    public void setUp() {
        builder = new ByteArrayBuilder();
    }

    @Test
    public void testGetCurrentSegmentLengthInitially() {
        // Initially, _currBlockPtr should be 0
        Assert.assertEquals(0, builder.getCurrentSegmentLength());
    }

    @Test
    public void testGetCurrentSegmentLengthAfterWrite() throws Exception {
        // Write some data to the builder
        byte[] data = {1, 2, 3};
        builder.write(data);

        // The current segment length should be equal to the number of bytes written
        Assert.assertEquals(data.length, builder.getCurrentSegmentLength());
    }
}
