package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilterincludePropertyTest {

    @Test
    public void testIncludePropertyReturnsNullWhenMatchFails() throws Exception {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/invalid"));
        TokenFilter result = filter.includeProperty("property");
        Assert.assertNull(result);
    }

    @Test
    public void testIncludePropertyReturnsIncludeAllWhenMatches() throws Exception {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/root"));
        TokenFilter result = filter.includeProperty("root");
        Assert.assertEquals(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludePropertyReturnsNewFilterForPartialMatch() throws Exception {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/root/child"));
        TokenFilter result = filter.includeProperty("root");
        Assert.assertNotNull(result);
        Assert.assertNotSame(TokenFilter.INCLUDE_ALL, result);
    }
}
