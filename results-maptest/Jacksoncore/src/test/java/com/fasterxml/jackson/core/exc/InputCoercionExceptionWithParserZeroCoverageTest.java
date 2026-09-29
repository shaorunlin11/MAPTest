package com.fasterxml.jackson.core.exc;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class InputCoercionExceptionWithParserZeroCoverageTest {
    @Test
    public void testWithParser() {
        JsonParser p = null; // This line is just to satisfy the compiler, actual test will use a valid instance
        InputCoercionException exception = new InputCoercionException(p, "test message", JsonToken.VALUE_STRING, String.class);
        exception.withParser(p);
    }
}
