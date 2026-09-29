package com.fasterxml.jackson.core.filter;

import org.junit.Test;

public class TokenFilterContextAppendDescZeroCoverageTest {
    @Test
    public void testAppendDescWithParent() {
        // Create a parent context with _parent != null
        TokenFilterContext parent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext context = new TokenFilterContext(0, parent, null, false);

        StringBuilder sb = new StringBuilder();
        context.appendDesc(sb);
    }

@Test
    public void testAppendDescWithArray() {
        // Create a context with _type == TYPE_ARRAY and _parent == null
        TokenFilterContext context = new TokenFilterContext(1, null, null, false);

        StringBuilder sb = new StringBuilder();
        context.appendDesc(sb);
    }
}
