package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeRawValueTest {
    @Test
    public void testIncludeRawValue() throws Exception {
        // Create an instance of TokenFilter
        TokenFilter tokenFilter = new TokenFilter();

        // Call the method under test
        boolean result = tokenFilter.includeRawValue();

        // Since the method delegates to _includeScalar(), which is protected,
        // we cannot directly verify its implementation without subclassing.
        // However, we can assert that the method returns a boolean value.
        Assert.assertTrue("Method should return a boolean value", result);
    }
}
