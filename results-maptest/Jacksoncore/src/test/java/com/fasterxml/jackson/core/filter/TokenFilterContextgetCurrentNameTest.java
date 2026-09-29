package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class TokenFilterContextgetCurrentNameTest {
    @Test
    public void testGetCurrentName() throws Exception {
        // Create a TokenFilterContext instance
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);

        // Set the _currentName field using reflection
        Field currentNameField = TokenFilterContext.class.getDeclaredField("_currentName");
        currentNameField.setAccessible(true);
        currentNameField.set(context, "testName");

        // Call the method under test
        String result = context.getCurrentName();

        // Assert the result
        Assert.assertEquals("testName", result);
    }
}
