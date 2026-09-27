package com.zappos.json;

import org.junit.Test;
import java.io.StringWriter;
import java.io.Writer;
import java.io.IOException;
import com.zappos.json.util.JsonUtils;

public class JsonWriterwriteStringTest {
    @Test
    public void testWriteString() throws IOException {
        ZapposJson zapposJson = new ZapposJson();
        String value = "test string";
        Writer writer = new StringWriter();

        JsonWriter.writeString(zapposJson, value, writer);

        String result = writer.toString();
        assert result.equals("\"test string\"") : "Expected quoted string, but got: " + result;
    }
}
