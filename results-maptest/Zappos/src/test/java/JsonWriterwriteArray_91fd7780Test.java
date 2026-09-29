package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriterwriteArray_91fd7780Test {

    @Test
    public void testWriteArrayEmpty() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new int[0], writer);
        assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteArraySingleElement() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new int[]{42}, writer);
        assertEquals("[42]", writer.toString());
    }

    @Test
    public void testWriteArrayMultipleElements() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new int[]{1, 2, 3}, writer);
        assertEquals("[1,2,3]", writer.toString());
    }

    @Test
    public void testWriteArrayWithCommas() throws IOException {
        Writer writer = new StringWriter();
        JsonWriter.writeArray(new int[]{10, 20, 30}, writer);
        assertEquals("[10,20,30]", writer.toString());
    }
}
