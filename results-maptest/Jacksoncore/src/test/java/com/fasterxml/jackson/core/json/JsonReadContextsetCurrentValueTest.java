package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class JsonReadContextsetCurrentValueTest {
    private JsonReadContext context;
    private Object testValue;

    @Before
    public void setUp() throws Exception {
        context = new JsonReadContext(null, null, 0, 0, 0);
        testValue = new Object();
    }

    @Test
    public void testSetCurrentValue() throws Exception {
        // Act
        context.setCurrentValue(testValue);

        // Assert
        Assert.assertEquals("Current value should be set", testValue, context._currentValue);
    }
}
