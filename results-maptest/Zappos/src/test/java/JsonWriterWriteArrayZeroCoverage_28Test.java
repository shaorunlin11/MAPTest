package com.zappos.json;

import org.junit.Test;
import java.io.IOException;
import java.io.Writer;

public class JsonWriterWriteArrayZeroCoverage_28Test {
    @Test
    public void testWriteArray() throws IOException {
        Writer writer = new java.io.StringWriter();
        char[] values = {'a', 'b', 'c'};
        JsonWriter.writeArray(values, writer);
    }
}
