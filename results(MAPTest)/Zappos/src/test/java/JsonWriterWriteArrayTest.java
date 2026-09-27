package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriterWriteArrayTest {

    @Test
    public void testWriteArrayEmpty() throws IOException {
        Writer writer = new StringWriter();
        boolean[] values = new boolean[0];
        JsonWriter.writeArray(values, writer);
        assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteArraySingleElement() throws IOException {
        Writer writer = new StringWriter();
        boolean[] values = {true};
        JsonWriter.writeArray(values, writer);
        assertEquals("[true]", writer.toString());
    }

    @Test
    public void testWriteArrayMultipleElements() throws IOException {
        Writer writer = new StringWriter();
        boolean[] values = {true, false, true};
        JsonWriter.writeArray(values, writer);
        assertEquals("[true,false,true]", writer.toString());
    }

    @Test
    public void testWriteArrayWithNullValues() throws IOException {
        Writer writer = new StringWriter();
        boolean[] values = {false, true, false};
        JsonWriter.writeArray(values, writer);
        assertEquals("[false,true,false]", writer.toString());
    }
}
