package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.LinkedList;
import java.util.List;

import java.lang.reflect.Field;


public class ByteArrayBuildertoByteArrayTest {
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
    public void testToByteArrayWithNoBytes() {
        byte[] result = builder.toByteArray();
        assert result.length == 0;
        assert result == ByteArrayBuilder.NO_BYTES;
    }

    @Test
    public void testToByteArrayWithSingleBlock() {
        byte[] data = {1, 2, 3};
        try {
            builder.write(data, 0, data.length);
        } catch (Exception e) {
            // Should not throw
            assert false;
        }
        byte[] result = builder.toByteArray();
        assert result.length == 3;
        assert result[0] == 1;
        assert result[1] == 2;
        assert result[2] == 3;
    }

    @Test
    public void testToByteArrayWithMultipleBlocks() {
        byte[] data1 = {1, 2, 3};
        byte[] data2 = {4, 5, 6};
        try {
            builder.write(data1, 0, data1.length);
            builder.write(data2, 0, data2.length);
        } catch (Exception e) {
            // Should not throw
            assert false;
        }
        byte[] result = builder.toByteArray();
        assert result.length == 6;
        assert result[0] == 1;
        assert result[1] == 2;
        assert result[2] == 3;
        assert result[3] == 4;
        assert result[4] == 5;
        assert result[5] == 6;
    }

    @Test
    public void testToByteArrayResetsPastBlocks() throws Exception {
        byte[] data1 = {1, 2, 3};
        byte[] data2 = {4, 5, 6};
        try {
            builder.write(data1, 0, data1.length);
            builder.write(data2, 0, data2.length);
        } catch (Exception e) {
            // Should not throw
            assert false;
        }
        byte[] result = builder.toByteArray();
        assert result.length == 6;
        assert result[0] == 1;
        assert result[1] == 2;
        assert result[2] == 3;
        assert result[3] == 4;
        assert result[4] == 5;
        assert result[5] == 6;

        // Check that _pastBlocks is empty after toByteArray()
        Field pastBlocksField = ByteArrayBuilder.class.getDeclaredField("_pastBlocks");
        pastBlocksField.setAccessible(true);
        LinkedList<byte[]> pastBlocks = (LinkedList<byte[]>) pastBlocksField.get(builder);
        assert pastBlocks.isEmpty();
    }
}
