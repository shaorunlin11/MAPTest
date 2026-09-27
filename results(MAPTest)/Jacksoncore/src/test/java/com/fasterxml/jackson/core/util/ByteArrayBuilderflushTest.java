package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.OutputStream;

public class ByteArrayBuilderflushTest {
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
    public void testFlushDoesNotModifyState() throws Exception {
        // Arrange
        int initialPastLen = getPastLen(builder);
        byte[] initialCurrBlock = getCurrBlock(builder);
        int initialCurrBlockPtr = getCurrBlockPtr(builder);

        // Act
        builder.flush();

        // Assert
        Assert.assertEquals("Past length should not change", initialPastLen, getPastLen(builder));
        Assert.assertSame("Current block should not change", initialCurrBlock, getCurrBlock(builder));
        Assert.assertEquals("Current block pointer should not change", initialCurrBlockPtr, getCurrBlockPtr(builder));
    }

    private int getPastLen(ByteArrayBuilder builder) throws Exception {
        java.lang.reflect.Field field = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        field.setAccessible(true);
        return (Integer) field.get(builder);
    }

    private byte[] getCurrBlock(ByteArrayBuilder builder) throws Exception {
        java.lang.reflect.Field field = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        field.setAccessible(true);
        return (byte[]) field.get(builder);
    }

    private int getCurrBlockPtr(ByteArrayBuilder builder) throws Exception {
        java.lang.reflect.Field field = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
        field.setAccessible(true);
        return (Integer) field.get(builder);
    }
}
