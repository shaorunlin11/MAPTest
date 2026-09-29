package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class UTF8JsonGeneratorgetOutputTargetTest {
    private UTF8JsonGenerator generator;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws IOException {
        outputStream = new ByteArrayOutputStream();
        BufferRecycler bufferRecycler = new BufferRecycler();
        IOContext ioContext = new IOContext(bufferRecycler, null, false);
        generator = new UTF8JsonGenerator(ioContext, 0, null, outputStream);
    }

    @After
    public void tearDown() {
        generator = null;
        outputStream = null;
    }

    @Test
    public void testGetOutputTargetReturnsOutputStream() {
        Object result = generator.getOutputTarget();
        assert result == outputStream;
    }
}
