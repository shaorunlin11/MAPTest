package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilterfilterStartObjectTest {

    @Test
    public void testFilterStartObjectReturnsThis() {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/"));
        TokenFilter result = filter.filterStartObject();
        Assert.assertSame(filter, result);
    }
}
