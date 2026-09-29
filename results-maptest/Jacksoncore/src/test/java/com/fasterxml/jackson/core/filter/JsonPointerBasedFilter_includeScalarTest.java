package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter_includeScalarTest {
    private JsonPointerBasedFilter filter;
    private JsonPointer mockPathToMatch;

    @Before
    public void setUp() throws Exception {
        mockPathToMatch = new JsonPointer() {
            @Override
            public boolean matches() {
                return true;
            }
        };
        filter = new JsonPointerBasedFilter(mockPathToMatch);
    }

    @Test
    public void testIncludeScalarReturnsMatchesResult() {
        Assert.assertTrue(filter._includeScalar());
    }
}
