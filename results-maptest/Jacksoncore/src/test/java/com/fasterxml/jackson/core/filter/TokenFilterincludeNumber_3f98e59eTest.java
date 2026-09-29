package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeNumber_3f98e59eTest {
    @Test
    public void testIncludeNumberFloat() {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeNumber(3.14f);
        Assert.assertTrue(result);
    }
}
