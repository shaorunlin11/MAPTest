package com.fasterxml.jackson.core.util;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.io.SerializedString;

public class MinimalPrettyPrinterWriteEndObjectZeroCoverageTest {
    @Test
    public void testWriteEndObject() throws Exception {
        MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator g = factory.createGenerator(new java.io.StringWriter());
        printer.writeEndObject(g, 0);
    }
}
