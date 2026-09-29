package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeNullTest {
    @Test
    public void testIncludeNull() throws Exception {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeNull();
        Assert.assertTrue(result);
    }
}
