package com.zappos.json;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.junit.Assert.*;
public class JsonWriterwriteBooleanTest {
    @Test
    public void testWriteBooleanTrue() throws IOException {
        StringWriter writer = new StringWriter();
        JsonWriter.writeBoolean(new ZapposJson(), true, writer);
        assertEquals("true", writer.toString());
    }

    @Test
    public void testWriteBooleanFalse() throws IOException {
        StringWriter writer = new StringWriter();
        JsonWriter.writeBoolean(new ZapposJson(), false, writer);
        assertEquals("false", writer.toString());
    }
}
