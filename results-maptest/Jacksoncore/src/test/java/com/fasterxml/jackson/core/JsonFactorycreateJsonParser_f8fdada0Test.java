package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;

public class JsonFactorycreateJsonParser_f8fdada0Test {

    @Test
    public void testCreateJsonParser() throws IOException, JsonParseException {
        JsonFactory factory = new JsonFactory();
        Reader reader = new StringReader("{}");
        JsonParser parser = factory.createParser(reader);
        // Verify that the method delegates correctly
        Assert.assertNotNull("Expected non-null JsonParser", parser);
    }
}
