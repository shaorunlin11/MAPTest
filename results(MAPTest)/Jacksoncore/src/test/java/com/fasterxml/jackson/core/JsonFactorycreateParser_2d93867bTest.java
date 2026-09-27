package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;

public class JsonFactorycreateParser_2d93867bTest {

    @Test
    public void testCreateParser() throws Exception {
        JsonFactory factory = new JsonFactory();
        InputStream inputStream = new ByteArrayInputStream("{\"key\":\"value\"}".getBytes());

        JsonParser parser = factory.createParser(inputStream);
        Assert.assertNotNull(parser);
    }

    @Test(expected = JsonParseException.class)
    public void testCreateParserWithInvalidStream() throws Exception {
        JsonFactory factory = new JsonFactory();
        InputStream inputStream = new ByteArrayInputStream("invalid".getBytes());

        JsonParser parser = factory.createParser(inputStream);
        parser.nextToken();
    }
}
