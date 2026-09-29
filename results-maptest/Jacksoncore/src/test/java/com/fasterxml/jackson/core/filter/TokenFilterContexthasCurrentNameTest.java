package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class TokenFilterContexthasCurrentNameTest {
    private TokenFilterContext context;

    @Before
    public void setUp() throws Exception {
        context = new TokenFilterContext(0, null, null, false);
    }

    @After
    public void tearDown() throws Exception {
        context = null;
    }

    @Test
    public void testHasCurrentNameWhenNull() {
        Assert.assertFalse(context.hasCurrentName());
    }

    @Test
    public void testHasCurrentNameWhenNotNull() throws Exception {
        // Use reflection to set _currentName
        java.lang.reflect.Field currentNameField = TokenFilterContext.class.getDeclaredField("_currentName");
        currentNameField.setAccessible(true);
        currentNameField.set(context, "testName");

        Assert.assertTrue(context.hasCurrentName());
    }
}
