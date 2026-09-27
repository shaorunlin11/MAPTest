package com.fasterxml.jackson.core.json;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;
public class UTF8JsonGeneratorwriteNullTest {
    private UTF8JsonGenerator generator;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
        IOContext ioContext = new IOContext(new BufferRecycler(), null, false);
        ObjectCodec codec = null;
        generator = new UTF8JsonGenerator(ioContext, 0, codec, outputStream);
    }

    @After
    public void tearDown() throws Exception {
        if (generator != null) {
            generator.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }

    @Test
    public void testWriteNull() throws IOException {
        generator.writeNull();
        generator.close();
        Assert.assertTrue(outputStream.toByteArray().length > 0);
    }
}
