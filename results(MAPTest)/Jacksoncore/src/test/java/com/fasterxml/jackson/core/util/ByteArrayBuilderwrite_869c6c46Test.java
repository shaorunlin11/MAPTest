package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.OutputStream;
import java.util.LinkedList;

public class ByteArrayBuilderwrite_869c6c46Test {
    private ByteArrayBuilder builder;
    private byte[] testBytes;

    @Before
    public void setUp() {
        builder = new ByteArrayBuilder();
        testBytes = new byte[]{1, 2, 3, 4, 5};
    }

    @After
    public void tearDown() {
        builder = null;
        testBytes = null;
    }

    @Test
    public void testWriteByteArrayDelegatesToWriteWithOffsetAndLength() throws Exception {
        // Arrange
        byte[] expectedBytes = testBytes;

        // Act
        builder.write(testBytes);

        // Assert
        // Verify that the write(byte[], int, int) method was called with the correct parameters
        // by checking the resulting byte array
        byte[] result = builder.toByteArray();
        Assert.assertArrayEquals("Bytes should have been written to the builder", expectedBytes, result);
    }
}
