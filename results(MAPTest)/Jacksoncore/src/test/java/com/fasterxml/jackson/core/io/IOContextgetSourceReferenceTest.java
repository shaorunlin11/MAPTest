package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextgetSourceReferenceTest {
    @Test
    public void testGetSourceReference() throws Exception {
        // Create a BufferRecycler instance
        BufferRecycler bufferRecycler = new BufferRecycler();

        // Create an Object to use as the source reference
        Object sourceRef = new Object();

        // Create an IOContext instance
        IOContext ioContext = new IOContext(bufferRecycler, sourceRef, false);

        // Call the method under test
        Object result = ioContext.getSourceReference();

        // Verify the result matches the expected value
        assertEquals("getSourceReference should return the same object passed in", sourceRef, result);
    }

    @Test
    public void testGetSourceReferenceWithNull() throws Exception {
        // Create a BufferRecycler instance
        BufferRecycler bufferRecycler = new BufferRecycler();

        // Create an IOContext instance with null source reference
        IOContext ioContext = new IOContext(bufferRecycler, null, false);

        // Call the method under test
        Object result = ioContext.getSourceReference();

        // Verify the result is null
        assertNull("getSourceReference should return null when source reference is null", result);
    }
}
