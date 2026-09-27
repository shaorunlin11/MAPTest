package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class TokenFilterincludeNumber_e8fdbc1eTest {

    @Test
    public void testIncludeNumber() {
        TokenFilter tokenFilter = new TokenFilter();
        BigDecimal value = new BigDecimal("123.45");
        boolean result = tokenFilter.includeNumber(value);
        Assert.assertTrue(result);
    }
}
