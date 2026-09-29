package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.LinkedList;

public class ByteArrayBuilderfinishCurrentSegmentTest {
    private ByteArrayBuilder builder;

    @Before
    public void setUp() {
        builder = new ByteArrayBuilder();
    }

    @After
    public void tearDown() {
        builder = null;
    }

    @Test
    public void testFinishCurrentSegmentReturnsNonEmptyArrayWhenBlockIsAllocated() throws Exception {
        // Ensure that the current block is allocated
        builder.write(1);

        byte[] result = builder.finishCurrentSegment();
        Assert.assertNotNull("finishCurrentSegment should not return null", result);
        Assert.assertTrue("finishCurrentSegment should return a non-empty array", result.length > 0);
    }

    @Test
    public void testFinishCurrentSegmentReturnsTheCurrentBlock() throws Exception {
        // Write some data to ensure the current block is allocated
        builder.write(1);

        byte[] result = builder.finishCurrentSegment();
        Assert.assertTrue("finishCurrentSegment should return a non-empty array", result.length > 0);
    }
}
