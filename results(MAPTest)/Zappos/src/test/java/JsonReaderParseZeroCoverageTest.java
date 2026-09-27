package com.zappos.json;

import org.junit.Test;

import java.io.StringReader;


public class JsonReaderParseZeroCoverageTest {
    @Test
    public void testParse() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        String json = "{\"key\": \"value\"}";
        JsonReader reader = new JsonReader(zapposJson, new StringReader(json));
        reader.parse();
    }
}
