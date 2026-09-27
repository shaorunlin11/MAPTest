package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.io.*;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.io.IOContext;

public class OutputDecoratordecorate_5501d8efTest {

    @Test
    public void testDecorateMethod() throws Exception {
        // Since OutputDecorator is abstract, we need to create an anonymous subclass
        OutputDecorator decorator = new OutputDecorator() {
            @Override
            public Writer decorate(IOContext ctxt, Writer w) throws IOException {
                // Simple implementation that returns the same writer
                return w;
            }

            @Override
            public OutputStream decorate(IOContext ctxt, OutputStream out) throws IOException {
                // Dummy implementation for required abstract method
                return null;
            }
        };

        // Create a mock IOContext (since it's a concrete class)
        // Note: The actual constructor requires BufferRecycler, Object, and boolean
        // For testing purposes, we'll use a dummy BufferRecycler instance
        BufferRecycler bufferRecycler = new BufferRecycler();
        IOContext context = new IOContext(bufferRecycler, new Object(), false);

        // Create a sample writer
        StringWriter originalWriter = new StringWriter();

        // Call the decorate method
        Writer decoratedWriter = decorator.decorate(context, originalWriter);

        // Verify that the returned writer is not null
        Assert.assertNotNull("Decorated writer should not be null", decoratedWriter);

        // Verify that the decorated writer is the same as the original
        Assert.assertEquals("Decorated writer should be the same as the original", originalWriter, decoratedWriter);
    }
}
