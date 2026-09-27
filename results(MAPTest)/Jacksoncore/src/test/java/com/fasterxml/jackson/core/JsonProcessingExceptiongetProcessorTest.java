package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonProcessingExceptiongetProcessorTest {
    @Test
    public void testGetProcessorReturnsNull() throws Exception {
        JsonProcessingException exception = new JsonProcessingException("test message");
        Object processor = exception.getProcessor();
        assertNull("getProcessor should return null", processor);
    }
}
