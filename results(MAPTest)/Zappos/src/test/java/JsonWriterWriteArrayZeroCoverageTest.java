package com.zappos.json;

import org.junit.Test;

import java.io.Writer;


public class JsonWriterWriteArrayZeroCoverageTest {
    @Test
    public void testWriteArray() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Object[] values = new Object[] { "value1", "value2", "value3" };
        Writer writer = new java.io.StringWriter();

        JsonWriter.writeArray(zapposJson, values, writer);
    }
}
