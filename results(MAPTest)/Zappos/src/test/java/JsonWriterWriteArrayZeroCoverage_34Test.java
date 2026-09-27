package com.zappos.json;

import org.junit.Test;

import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

public class JsonWriterWriteArrayZeroCoverage_34Test {
    @Test
    public void testWriteArrayWithNonNullValuesAndWriter() throws Exception {
        double[] values = {1.0, 2.0, 3.0};
        Writer writer = new StringWriter();

        JsonWriter.writeArray(values, writer);
    }
}
