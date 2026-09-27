package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonProcessingExceptiongetMessageTest {

    @Test
    public void testGetMessageWithNullSuperMessage() throws Exception {
        // Create a JsonProcessingException with null message
        JsonProcessingException exception = new JsonProcessingException((String) null) {
            @Override
            public JsonLocation getLocation() {
                return null;
            }

            @Override
            protected String getMessageSuffix() {
                return null;
            }
        };

        String message = exception.getMessage();
        Assert.assertEquals("N/A", message);
    }

    @Test
    public void testGetMessageWithSuffixOnly() throws Exception {
        // Create a JsonProcessingException with a message and suffix
        JsonProcessingException exception = new JsonProcessingException("Test message") {
            @Override
            public JsonLocation getLocation() {
                return null;
            }

            @Override
            protected String getMessageSuffix() {
                return " - Additional info";
            }
        };

        String message = exception.getMessage();
        Assert.assertEquals("Test message - Additional info", message);
    }

    @Test
    public void testGetMessageWithLocationOnly() throws Exception {
        // Create a JsonProcessingException with a message and location
        JsonProcessingException exception = new JsonProcessingException("Test message") {
            @Override
            public JsonLocation getLocation() {
                return new JsonLocation(null, 0, 0, 0, 0) {
                    @Override
                    public String toString() {
                        return "Line 10, Column 5";
                    }
                };
            }

            @Override
            protected String getMessageSuffix() {
                return null;
            }
        };

        String message = exception.getMessage();
        Assert.assertEquals("Test message\n at Line 10, Column 5", message);
    }

    @Test
    public void testGetMessageWithBothSuffixAndLocation() throws Exception {
        // Create a JsonProcessingException with message, suffix, and location
        JsonProcessingException exception = new JsonProcessingException("Test message") {
            @Override
            public JsonLocation getLocation() {
                return new JsonLocation(null, 0, 0, 0, 0) {
                    @Override
                    public String toString() {
                        return "Line 10, Column 5";
                    }
                };
            }

            @Override
            protected String getMessageSuffix() {
                return " - Additional info";
            }
        };

        String message = exception.getMessage();
        Assert.assertEquals("Test message - Additional info\n at Line 10, Column 5", message);
    }

    @Test
    public void testGetMessageWithoutSuffixOrLocation() throws Exception {
        // Create a JsonProcessingException with message only
        JsonProcessingException exception = new JsonProcessingException("Test message") {
            @Override
            public JsonLocation getLocation() {
                return null;
            }

            @Override
            protected String getMessageSuffix() {
                return null;
            }
        };

        String message = exception.getMessage();
        Assert.assertEquals("Test message", message);
    }
}
