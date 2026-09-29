package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;

public class JsonFactorycreateJsonParser_a160083eTest {
    @Test
    public void testCreateJsonParserWithByteArray() throws IOException, JsonParseException {
        JsonFactory factory = new JsonFactory();
        byte[] data = "{'key':'value'}".getBytes();
        JsonParser parser = factory.createParser(data);
        Assert.assertNotNull(parser);
    }
}
