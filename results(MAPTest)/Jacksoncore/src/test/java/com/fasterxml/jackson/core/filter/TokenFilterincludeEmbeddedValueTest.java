package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeEmbeddedValueTest {
    @Test
    public void testIncludeEmbeddedValue() throws Exception {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeEmbeddedValue(null);
        Assert.assertTrue(result);
    }
}
