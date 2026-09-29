package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.LinkedList;

import java.lang.reflect.Field;


public class ByteArrayBuilderresetTest {
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
    public void testResetResetsPastLenAndCurrBlockPtr() throws Exception {
        // Arrange
        // Use reflection to set private fields
        Field pastLenField = ByteArrayBuilder.class.getDeclaredField("_pastLen");
        pastLenField.setAccessible(true);
        pastLenField.set(builder, 100);

        Field currBlockPtrField = ByteArrayBuilder.class.getDeclaredField("_currBlockPtr");
        currBlockPtrField.setAccessible(true);
        currBlockPtrField.set(builder, 50);

        // Act
        builder.reset();

        // Assert
        Assert.assertEquals(0, pastLenField.get(builder));
        Assert.assertEquals(0, currBlockPtrField.get(builder));
    }

    @Test
    public void testResetClearsPastBlocksIfNotEmpty() throws Exception {
        // Arrange
        // Use reflection to set private field
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        pastBlocksField.setAccessible(true);
        LinkedList<byte[]> pastBlocks = (LinkedList<byte[]>) pastBlocksField.get(builder);
        pastBlocks.add(new byte[10]);
        pastBlocks.add(new byte[20]);

        // Act
        builder.reset();

        // Assert
        Assert.assertTrue(pastBlocks.isEmpty());
    }

    @Test
    public void testResetDoesNotModifyCurrBlock() throws Exception {
        // Arrange
        // Use reflection to set private field
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        byte[] originalBlock = new byte[10];
        currBlockField.set(builder, originalBlock);

        // Act
        builder.reset();

        // Assert
        Assert.assertSame(originalBlock, currBlockField.get(builder));
    }

    @Test
    public void testResetLeavesPastBlocksUnchangedIfEmpty() throws Exception {
        // Arrange
        // Use reflection to set private field
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        pastBlocksField.setAccessible(true);
        LinkedList<byte[]> pastBlocks = (LinkedList<byte[]>) pastBlocksField.get(builder);
        pastBlocks.clear();

        // Act
        builder.reset();

        // Assert
        Assert.assertTrue(pastBlocks.isEmpty());
    }
}
