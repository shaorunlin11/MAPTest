package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;


public class RequestPayloadtoStringTest {

    @Test
    public void testToStringWithBytes() throws Exception {
        byte[] bytes = "Hello, World!".getBytes("UTF-8");
        RequestPayload payload = new RequestPayload(bytes, "UTF-8");
        assertEquals("Hello, World!", payload.toString());
    }

    @Test
    public void testToStringWithText() throws Exception {
        CharSequence text = "Hello, World!";
        RequestPayload payload = new RequestPayload(text);
        assertEquals("Hello, World!", payload.toString());
    }

    @Test
    public void testToStringWithBytesAndInvalidCharset() throws Exception {
        byte[] bytes = "Hello, World!".getBytes("UTF-8");
        RequestPayload payload = new RequestPayload(bytes, "invalidCharset");
        try {
            payload.toString();
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertTrue(e.getCause() instanceof IOException);
        }
    }

    @Test
    public void testToStringWithNullBytes() throws Exception {
        RequestPayload payload = new RequestPayload("Hello, World!");
        assertEquals("Hello, World!", payload.toString());
    }
}
