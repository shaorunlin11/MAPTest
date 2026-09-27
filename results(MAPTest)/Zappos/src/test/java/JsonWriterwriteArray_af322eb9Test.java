package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriterwriteArray_af322eb9Test {

    @Test
    public void testWriteArrayWithEmptyArray() throws IOException {
        Writer writer = new StringWriter();
        long[] values = new long[0];
        JsonWriter.writeArray(values, writer);
        assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteArrayWithSingleElement() throws IOException {
        Writer writer = new StringWriter();
        long[] values = {123};
        JsonWriter.writeArray(values, writer);
        assertEquals("[123]", writer.toString());
    }

    @Test
    public void testWriteArrayWithMultipleElements() throws IOException {
        Writer writer = new StringWriter();
        long[] values = {123, 456, 789};
        JsonWriter.writeArray(values, writer);
        assertEquals("[123,456,789]", writer.toString());
    }

    @Test
    public void testWriteArrayWithTwoElements() throws IOException {
        Writer writer = new StringWriter();
        long[] values = {123, 456};
        JsonWriter.writeArray(values, writer);
        assertEquals("[123,456]", writer.toString());
    }
}
