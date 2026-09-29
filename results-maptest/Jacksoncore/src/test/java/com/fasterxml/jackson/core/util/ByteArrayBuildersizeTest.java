package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.LinkedList;

import java.lang.reflect.Field;


public class ByteArrayBuildersizeTest {
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
    public void testSizeReturnsSumOfPastLenAndCurrBlockPtr() throws Exception {
        // Initialize fields directly using reflection to simulate state
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        pastLenField.setInt(builder, 100);

        Field currBlockPtrField = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
        currBlockPtrField.setAccessible(true);
        currBlockPtrField.setInt(builder, 50);

        int result = builder.size();
        Assert.assertEquals(150, result);
    }
}
