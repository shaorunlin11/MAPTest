package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextwithEncodingTest {
    @Test
    public void testWithEncoding() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;

        IOContext context = new IOContext(bufferRecycler, sourceRef, managedResource);

        JsonEncoding enc = JsonEncoding.UTF8;
        IOContext result = context.withEncoding(enc);

        assertEquals(context, result);
        assertEquals(enc, context._encoding);
    }
}
