package com.fasterxml.jackson.core.base;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.IOException;
import java.io.StringReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.base.ParserBase;
public class ParserBasegetLongValueTest {
    private ParserBase parser;
    private JsonParser jsonParser;

    @Before
    public void setUp() throws Exception {
        JsonFactory factory = new JsonFactory();
        String json = "{\"test\": 123456789012345}";
        jsonParser = factory.createParser(new StringReader(json));
        parser = (ParserBase) jsonParser;
    }

    @After
    public void tearDown() throws Exception {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @Test
    public void testGetLongValue() throws IOException {
        // Advance to the field
        Assert.assertEquals(JsonToken.START_OBJECT, jsonParser.nextToken());
        Assert.assertEquals(JsonToken.FIELD_NAME, jsonParser.nextToken());
        Assert.assertEquals("test", jsonParser.getCurrentName());
        Assert.assertEquals(JsonToken.VALUE_NUMBER_INT, jsonParser.nextToken());

        // Verify getLongValue returns the correct value
        long value = parser.getLongValue();
        Assert.assertEquals(123456789012345L, value);
    }

    @Test
    public void testGetLongValueWithNRUnknown() throws IOException {
        // Advance to the field
        Assert.assertEquals(JsonToken.START_OBJECT, jsonParser.nextToken());
        Assert.assertEquals(JsonToken.FIELD_NAME, jsonParser.nextToken());
        Assert.assertEquals("test", jsonParser.getCurrentName());
        Assert.assertEquals(JsonToken.VALUE_NUMBER_INT, jsonParser.nextToken());

        // Simulate _numTypesValid being NR_UNKNOWN
        parser._numTypesValid = ParserBase.NR_UNKNOWN;

        // Verify getLongValue triggers parsing and returns the correct value
        long value = parser.getLongValue();
        Assert.assertEquals(123456789012345L, value);
    }
}
