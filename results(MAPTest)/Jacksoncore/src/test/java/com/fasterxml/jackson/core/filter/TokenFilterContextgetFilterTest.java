package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class TokenFilterContextgetFilterTest {
    @Test
    public void testGetFilterReturnsSetFilter() throws Exception {
        TokenFilterContext context = new TokenFilterContext(0, null, new TokenFilter(), false);
        TokenFilter filter = context.getFilter();
        Assert.assertNotNull(filter);
    }

    @Test
    public void testGetFilterReturnsNullIfNotSet() throws Exception {
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);
        TokenFilter filter = context.getFilter();
        Assert.assertNull(filter);
    }
}
