package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextsetEncodingTest {
    @Test
    public void testSetEncoding() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;
        IOContext context = new IOContext(bufferRecycler, sourceRef, managedResource);

        JsonEncoding enc = JsonEncoding.UTF8;
        context.setEncoding(enc);

        Field encodingField = IOContext.class.getDeclaredField("_encoding");
        encodingField.setAccessible(true);
        assertEquals(enc, encodingField.get(context));
    }
}
