package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextgetEncodingTest {

    @Test
    public void testGetEncoding() throws Exception {
        // Arrange
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;
        IOContext ioContext = new IOContext(bufferRecycler, sourceRef, managedResource);

        // Set the encoding field using reflection to simulate initialization
        java.lang.reflect.Field encodingField = IOContext.class.getDeclaredField("_encoding");
        encodingField.setAccessible(true);
        encodingField.set(ioContext, JsonEncoding.UTF8);

        // Act
        JsonEncoding result = ioContext.getEncoding();

        // Assert
        Assert.assertEquals(JsonEncoding.UTF8, result);
    }
}
