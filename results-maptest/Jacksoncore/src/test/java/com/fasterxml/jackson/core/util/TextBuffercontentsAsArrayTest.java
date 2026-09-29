package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;

import java.lang.reflect.Field;


public class TextBuffercontentsAsArrayTest {
    private TextBuffer textBuffer;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        textBuffer = new TextBuffer(bufferRecycler);
    }

    @After
    public void tearDown() {
        textBuffer = null;
        bufferRecycler = null;
    }

    @Test
    public void testContentsAsArray_ReturnsNonNullArray() {
        char[] result = textBuffer.contentsAsArray();
        Assert.assertNotNull("Result array should not be null", result);
    }

    @Test
    public void testContentsAsArray_InitializesResultArrayWhenNull() throws Exception {
        // Clear _resultArray using reflection
        Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
        resultArrayField.setAccessible(true);
        resultArrayField.set(textBuffer, null);

        char[] result = textBuffer.contentsAsArray();
        Assert.assertNotNull("Result array should be initialized when null", result);
    }
}
