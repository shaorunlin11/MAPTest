package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterincludeBinaryTest {
    @Test
    public void testIncludeBinary() throws Exception {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeBinary();
        Assert.assertTrue(result);
    }
}
