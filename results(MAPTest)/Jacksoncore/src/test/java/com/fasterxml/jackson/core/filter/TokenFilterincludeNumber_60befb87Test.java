package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeNumber_60befb87Test {
    @Test
    public void testIncludeNumber() throws Exception {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeNumber(123L);
        Assert.assertTrue(result);
    }
}
