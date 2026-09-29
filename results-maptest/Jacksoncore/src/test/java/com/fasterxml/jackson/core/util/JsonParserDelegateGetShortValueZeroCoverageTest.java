package com.fasterxml.jackson.core.util;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

public class JsonParserDelegateGetShortValueZeroCoverageTest {
    @Test
    public void testGetShortValue() throws Exception {
        // Create a real instance of JsonParser
        JsonParser delegate = new JsonFactory().createParser("123");

        // Create the delegate under test
        JsonParserDelegate parserDelegate = new JsonParserDelegate(delegate);

        // Advance to the number token before calling getShortValue
        if (delegate.nextToken() != JsonToken.VALUE_NUMBER_INT) {
            throw new IOException("Expected number token");
        }

        // Call the method to cover line 166
        short value = parserDelegate.getShortValue();

        // Assert the result (optional, but can be added for verification)
        assert value == 123;
    }
}
