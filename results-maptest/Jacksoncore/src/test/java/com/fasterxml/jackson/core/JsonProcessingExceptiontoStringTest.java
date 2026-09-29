package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonProcessingExceptiontoStringTest {

    @Test
    public void testToStringWithMessage() throws Exception {
        JsonProcessingException exception = new JsonProcessingException("Test message");
        String result = exception.toString();
        assertTrue(result.contains("JsonProcessingException: Test message"));
    }

    @Test
    public void testToStringWithLocationAndMessage() throws Exception {
        JsonLocation location = new JsonLocation(null, 1, 2, 0);
        JsonProcessingException exception = new JsonProcessingException("Test message", location);
        String result = exception.toString();
        assertTrue(result.contains("JsonProcessingException: Test message"));
    }

    @Test
    public void testToStringWithRootCause() throws Exception {
        Throwable rootCause = new RuntimeException("Root cause");
        JsonProcessingException exception = new JsonProcessingException("Test message", rootCause);
        String result = exception.toString();
        assertTrue(result.contains("JsonProcessingException: Test message"));
    }

    @Test
    public void testToStringWithLocationAndRootCause() throws Exception {
        JsonLocation location = new JsonLocation(null, 1, 2, 0);
        Throwable rootCause = new RuntimeException("Root cause");
        JsonProcessingException exception = new JsonProcessingException("Test message", location, rootCause);
        String result = exception.toString();
        assertTrue(result.contains("JsonProcessingException: Test message"));
    }
}
