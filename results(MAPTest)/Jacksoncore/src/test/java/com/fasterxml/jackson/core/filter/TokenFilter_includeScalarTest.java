package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilter_includeScalarTest {
    @Test
    public void testIncludeScalarReturnsTrue() {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter._includeScalar();
        Assert.assertTrue("Expected _includeScalar to return true", result);
    }
}
