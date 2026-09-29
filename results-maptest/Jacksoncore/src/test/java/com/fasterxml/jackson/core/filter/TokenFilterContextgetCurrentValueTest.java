package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterContextgetCurrentValueTest {
    @Test
    public void testGetCurrentValue() throws Exception {
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);
        Object result = context.getCurrentValue();
        Assert.assertNull(result);
    }
}
