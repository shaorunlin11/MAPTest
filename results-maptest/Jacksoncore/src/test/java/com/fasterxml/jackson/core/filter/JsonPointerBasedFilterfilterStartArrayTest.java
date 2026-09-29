package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilterfilterStartArrayTest {
    @Test
    public void testFilterStartArrayReturnsThis() throws Exception {
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(JsonPointer.compile("/"));
        TokenFilter result = filter.filterStartArray();
        assertSame("filterStartArray should return this instance", filter, result);
    }
}
