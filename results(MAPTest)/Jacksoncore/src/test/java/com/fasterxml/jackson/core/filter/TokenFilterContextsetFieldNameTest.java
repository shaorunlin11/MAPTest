package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterContextsetFieldNameTest {
    @Test
    public void testSetFieldName() throws Exception {
        // Create a mock TokenFilter
        TokenFilter mockFilter = new TokenFilter() {
            public boolean include(String fieldName) {
                return false;
            }
        };

        // Create a TokenFilterContext instance
        TokenFilterContext context = new TokenFilterContext(0, null, mockFilter, false);

        // Call setFieldName
        TokenFilter result = context.setFieldName("testName");

        // Verify that _currentName was set
        Assert.assertEquals("testName", context._currentName);

        // Verify that _needToHandleName was set to true
        Assert.assertTrue(context._needToHandleName);

        // Verify that the returned filter is the same instance
        Assert.assertEquals(mockFilter, result);
    }
}
