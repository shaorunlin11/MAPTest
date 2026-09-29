package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriterwriteArray_56c181f7Test {

    @Test
    public void testWriteArrayWithEmptyArray() throws IOException {
        Writer writer = new StringWriter();
        short[] values = new short[0];
        JsonWriter.writeArray(values, writer);
        assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteArrayWithSingleElement() throws IOException {
        Writer writer = new StringWriter();
        short[] values = { 42 };
        JsonWriter.writeArray(values, writer);
        assertEquals("[42]", writer.toString());
    }

    @Test
    public void testWriteArrayWithMultipleElements() throws IOException {
        Writer writer = new StringWriter();
        short[] values = { 1, 2, 3 };
        JsonWriter.writeArray(values, writer);
        assertEquals("[1,2,3]", writer.toString());
    }

    @Test
    public void testWriteArrayWithNullValues() throws IOException {
        Writer writer = new StringWriter();
        short[] values = { Short.MIN_VALUE, 0, Short.MAX_VALUE };
        JsonWriter.writeArray(values, writer);
        assertEquals("[" + Short.MIN_VALUE + ",0," + Short.MAX_VALUE + "]", writer.toString());
    }
}
