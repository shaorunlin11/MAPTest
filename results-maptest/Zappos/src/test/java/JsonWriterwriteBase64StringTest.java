package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.DatatypeConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriterwriteBase64StringTest {

    @Test
    public void testWriteBase64String() throws IOException {
        ZapposJson zapposJson = new ZapposJson();
        byte[] value = "test".getBytes();
        Writer writer = new StringWriter();

        JsonWriter.writeBase64String(zapposJson, value, writer);

        String result = writer.toString();
        String expected = "\"" + DatatypeConverter.printBase64Binary(value) + "\"";
        assertEquals(expected, result);
    }

    @Test(expected = IOException.class)
    public void testWriteBase64StringThrowsIOException() throws IOException {
        ZapposJson zapposJson = new ZapposJson();
        byte[] value = "test".getBytes();
        Writer writer = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("Simulated error");
            }

            @Override
            public void flush() throws IOException {
                throw new IOException("Simulated error");
            }

            @Override
            public void close() throws IOException {
                throw new IOException("Simulated error");
            }
        };

        JsonWriter.writeBase64String(zapposJson, value, writer);
    }
}
