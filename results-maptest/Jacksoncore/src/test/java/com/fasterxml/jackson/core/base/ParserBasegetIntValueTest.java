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

public class ParserBasegetIntValueTest {
    private ParserBase parser;
    private JsonParser jsonParser;

    @Before
    public void setUp() throws Exception {
        JsonFactory factory = new JsonFactory();
        StringReader reader = new StringReader("{\"test\": 123}");
        jsonParser = factory.createParser(reader);
        parser = (ParserBase) jsonParser;
    }

    @After
    public void tearDown() throws Exception {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @Test
    public void testGetIntValue_WhenNumTypesValidIsNRUnknown() throws IOException {
        // Arrange: Advance to the number token
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.START_OBJECT);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.FIELD_NAME);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.VALUE_NUMBER_INT);

        // Set _numTypesValid to NR_UNKNOWN
        parser._numTypesValid = ParserBase.NR_UNKNOWN;

        // Act
        int result = parser.getIntValue();

        // Assert: Verify that _parseIntValue is called and returns a valid int
        Assert.assertEquals(123, result);
    }

    @Test
    public void testGetIntValue_WhenNumTypesValidHasNoNRInt() throws IOException {
        // Arrange: Advance to the number token
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.START_OBJECT);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.FIELD_NAME);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.VALUE_NUMBER_INT);

        // Set _numTypesValid to a value that doesn't include NR_INT
        parser._numTypesValid = ParserBase.NR_UNKNOWN;

        // Act
        int result = parser.getIntValue();

        // Assert: Verify that convertNumberToInt is called and returns a valid int
        Assert.assertEquals(123, result);
    }

    @Test
    public void testGetIntValue_WhenNumTypesValidHasNRInt() throws IOException {
        // Arrange: Advance to the number token
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.START_OBJECT);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.FIELD_NAME);
        Assert.assertTrue(jsonParser.nextToken() == JsonToken.VALUE_NUMBER_INT);

        // Set _numTypesValid to include NR_INT
        parser._numTypesValid = ParserBase.NR_INT;

        // Manually set _numberInt to 123 to ensure it's returned directly
        parser._numberInt = 123;

        // Act
        int result = parser.getIntValue();

        // Assert: Verify that _numberInt is returned directly
        Assert.assertEquals(123, result);
    }
}
