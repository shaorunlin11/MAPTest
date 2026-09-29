package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.*;

import java.lang.reflect.Field;

public class ByteArrayBuildersetCurrentSegmentLengthTest {
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
    public void testSetCurrentSegmentLength() throws Exception {
        int[] testValues = {0, 1, 500, 1000, Integer.MAX_VALUE};

        for (int len : testValues) {
            builder.setCurrentSegmentLength(len);
            Field field = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
            field.setAccessible(true);
            int result = (Integer) field.get(builder);
            Assert.assertEquals("Failed to set current segment length to " + len, len, result);
        }
    }
}
