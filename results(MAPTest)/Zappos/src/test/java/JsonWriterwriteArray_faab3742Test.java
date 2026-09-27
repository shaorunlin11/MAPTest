package com.zappos.json;

import org.junit.Test;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;

public class JsonWriterwriteArray_faab3742Test {
    @Test
    public void testWriteArrayEmpty() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new float[0], writer);
        assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteArraySingleElement() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new float[]{1.0f}, writer);
        assertEquals("[1.0]", writer.toString());
    }

    @Test
    public void testWriteArrayMultipleElements() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new float[]{1.0f, 2.0f, 3.0f}, writer);
        assertEquals("[1.0,2.0,3.0]", writer.toString());
    }

    @Test
    public void testWriteArrayWithCommas() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new float[]{1.0f, 2.0f}, writer);
        assertEquals("[1.0,2.0]", writer.toString());
    }
}
