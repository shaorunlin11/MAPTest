package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeNumber_441f3bc5Test {
    @Test
    public void testIncludeNumber() throws Exception {
        TokenFilter filter = new TokenFilter();
        boolean result = filter.includeNumber(0);
        Assert.assertTrue(result);
    }
}
