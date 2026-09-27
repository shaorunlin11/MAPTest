package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonProcessingExceptiongetMessageSuffixTest {

    @Test
    public void testGetMessageSuffixReturnsNullByDefault() throws Exception {
        // Arrange
        JsonProcessingException exception = new JsonProcessingException("test message") {
            // No override of getMessageSuffix
        };

        // Act
        String suffix = exception.getMessageSuffix();

        // Assert
        assertNull("getMessageSuffix should return null by default", suffix);
    }

    @Test
    public void testGetMessageSuffixCanBeOverridden() throws Exception {
        // Arrange
        JsonProcessingException exception = new JsonProcessingException("test message") {
            @Override
            protected String getMessageSuffix() {
                return " - custom suffix";
            }
        };

        // Act
        String suffix = exception.getMessageSuffix();

        // Assert
        assertEquals("getMessageSuffix should return overridden value", " - custom suffix", suffix);
    }
}
