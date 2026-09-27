package com.fasterxml.jackson.core.base;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.base.ParserBase;

public class ParserBasegetFloatValueTest {
    private ParserBase parser;
    private JsonFactory jsonFactory;

    @Before
    public void setUp() throws Exception {
        jsonFactory = new JsonFactory();
        Reader reader = new StringReader("{\"value\": 3.1415926535}");
        parser = (ParserBase) jsonFactory.createParser(reader);
        parser.nextToken(); // Move to the start of the object
        parser.nextToken(); // Move to the "value" field
        parser.nextToken(); // Move to the value token
    }

    @After
    public void tearDown() throws Exception {
        if (parser != null) {
            parser.close();
        }
    }

    @Test
    public void testGetFloatValue() throws IOException {
        float floatValue = parser.getFloatValue();
        Assert.assertEquals(3.1415926535f, floatValue, 0.000001f);
    }
}
