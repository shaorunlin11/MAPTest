package com.fasterxml.jackson.core.io;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonToken;


public class JsonEOFExceptionGetTokenBeingDecodedZeroCoverageTest {
    @Test
    public void testGetTokenBeingDecoded() {
        JsonToken token = JsonToken.VALUE_STRING;
        JsonEOFException exception = new JsonEOFException(null, token, "test message");
        assert exception.getTokenBeingDecoded() == token;
    }
}
