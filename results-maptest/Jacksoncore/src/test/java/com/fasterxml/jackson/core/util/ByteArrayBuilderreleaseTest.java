package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.LinkedList;

import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class ByteArrayBuilderreleaseTest {
    private ByteArrayBuilder builder;
    private BufferRecycler mockBufferRecycler;
    private byte[] mockCurrBlock;

    @Before
    public void setUp() throws Exception {
        mockBufferRecycler = new BufferRecycler();
        mockCurrBlock = new byte[10];
        builder = new ByteArrayBuilder(mockBufferRecycler, 10);
        // Set _currBlock directly using reflection to simulate construction
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        currBlockField.set(builder, mockCurrBlock);
    }

    @After
    public void tearDown() throws Exception {
        builder = null;
        mockBufferRecycler = null;
        mockCurrBlock = null;
    }

    @Test
    public void testReleaseWithNonNullBufferRecyclerAndCurrBlock() throws Exception {
        // Act
        builder.release();

        // Assert
        // Check that reset was called (indirectly verified by state changes)
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        assertNull(currBlockField.get(builder));

        // Verify that the buffer was released
        // Note: Since we don't have a real mock for BufferRecycler, we can't verify the actual release
        // but we can confirm that the method attempted to release the buffer
    }

    @Test
    public void testReleaseWithNullBufferRecycler() throws Exception {
        // Arrange
        builder = new ByteArrayBuilder(null, 10);
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        currBlockField.set(builder, new byte[10]);

        // Act
        builder.release();

        // Assert
        Field currBlockAfterReleaseField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockAfterReleaseField.setAccessible(true);
        assertNotNull(currBlockAfterReleaseField.get(builder));
    }

    @Test
    public void testReleaseWithNullCurrBlock() throws Exception {
        // Arrange
        builder = new ByteArrayBuilder(mockBufferRecycler, 10);
        Field currBlockField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockField.setAccessible(true);
        currBlockField.set(builder, null);

        // Act
        builder.release();

        // Assert
        Field currBlockAfterReleaseField = ByteArrayBuilder.class.getDeclaredField("_currBlock");
        currBlockAfterReleaseField.setAccessible(true);
        assertNull(currBlockAfterReleaseField.get(builder));
    }
}
