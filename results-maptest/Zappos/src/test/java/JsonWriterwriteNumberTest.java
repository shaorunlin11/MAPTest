package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import org.junit.Assert;

public class JsonWriterwriteNumberTest {
    @Test
    public void testWriteNumber() throws IOException {
        ZapposJson zapposJson = new ZapposJson();
        Number value = 123;
        Writer writer = new StringWriter();

        JsonWriter.writeNumber(zapposJson, value, writer);

        Assert.assertEquals("123", writer.toString());
    }
}
