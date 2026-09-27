package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextisResourceManagedTest {
    @Test
    public void testIsResourceManagedWithTrue() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = true;

        IOContext context = new IOContext(bufferRecycler, sourceRef, managedResource);
        assertTrue(context.isResourceManaged());
    }

    @Test
    public void testIsResourceManagedWithFalse() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;

        IOContext context = new IOContext(bufferRecycler, sourceRef, managedResource);
        assertFalse(context.isResourceManaged());
    }
}
