package com.zappos.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.junit.Test;
import org.junit.Assert;

public class JsonWriterwriteMapTest {
    @Test
    public void testWriteMap() throws IOException {
        ZapposJson zapposJson = new ZapposJson();
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 123);
        map.put("key3", true);

        StringWriter writer = new StringWriter();
        JsonWriter.writeMap(zapposJson, map, writer);

        String result = writer.toString();
        Assert.assertTrue(result.contains("\"key1\":\"value1\""));
        Assert.assertTrue(result.contains("\"key2\":123"));
        Assert.assertTrue(result.contains("\"key3\":true"));
        Assert.assertTrue(result.startsWith("{"));
        Assert.assertTrue(result.endsWith("}"));
    }
}
