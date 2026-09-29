package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonProcessingExceptiongetOriginalMessageTest {

    @Test
    public void testGetOriginalMessage() throws Exception {
        // Create a JsonProcessingException with a message and no cause
        JsonProcessingException exception = new JsonProcessingException("Test message");

        // Verify that getOriginalMessage returns the same message as getMessage
        assertEquals("Test message", exception.getOriginalMessage());
    }

    @Test
    public void testGetOriginalMessageWithNullMessage() throws Exception {
        // Create a JsonProcessingException with null message and no cause
        JsonProcessingException exception = new JsonProcessingException((String) null);

        // Verify that getOriginalMessage returns null
        assertNull(exception.getOriginalMessage());
    }

    @Test
    public void testGetOriginalMessageWithCause() throws Exception {
        // Create a cause
        Throwable cause = new RuntimeException("Cause message");

        // Create a JsonProcessingException with a message and cause
        JsonProcessingException exception = new JsonProcessingException("Test message", cause);

        // Verify that getOriginalMessage returns the same message as getMessage
        assertEquals("Test message", exception.getOriginalMessage());
    }

    @Test
    public void testGetOriginalMessageWithLocation() throws Exception {
        // Create a location using valid constructor
        JsonLocation location = new JsonLocation((Object) null, 0, 0, 0, 0);

        // Create a JsonProcessingException with a message and location
        JsonProcessingException exception = new JsonProcessingException("Test message", location);

        // Verify that getOriginalMessage returns the same message as getMessage
        assertEquals("Test message", exception.getOriginalMessage());
    }
}
