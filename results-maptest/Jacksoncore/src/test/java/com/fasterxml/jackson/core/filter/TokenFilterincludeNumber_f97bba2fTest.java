package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

import java.math.BigInteger;

public class TokenFilterincludeNumber_f97bba2fTest {
    @Test
    public void testIncludeNumber() {
        TokenFilter tokenFilter = new TokenFilter();
        boolean result = tokenFilter.includeNumber(new BigInteger("123"));
        Assert.assertTrue(result);
    }
}
