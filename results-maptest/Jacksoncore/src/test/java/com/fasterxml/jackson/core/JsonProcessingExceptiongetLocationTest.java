package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonProcessingExceptiongetLocationTest {
    @Test
    public void testGetLocationReturnsNullWhenNotSet() {
        JsonProcessingException exception = new JsonProcessingException("test message");
        Assert.assertNull(exception.getLocation());
    }

    @Test
    public void testGetLocationReturnsSetLocation() {
        JsonLocation location = new JsonLocation(null, 1L, 2, 0);
        JsonProcessingException exception = new JsonProcessingException("test message", location, null);
        Assert.assertEquals(location, exception.getLocation());
    }
}
